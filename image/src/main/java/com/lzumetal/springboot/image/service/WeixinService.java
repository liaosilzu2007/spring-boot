package com.lzumetal.springboot.image.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springboot.image.config.ConfigurationBean;
import com.lzumetal.springboot.image.entity.dto.AccessToken;
import com.lzumetal.springboot.redisson.enums.ERedisKey;
import com.lzumetal.springboot.redisson.lock.RedissonLockUtil;
import com.lzumetal.springboot.utils.HttpRequest;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EBaseResponseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Service
@Slf4j
public class WeixinService {

    private static final String WECHAT_API_HOST = "https://api.weixin.qq.com";

    private static final String ACCESS_TOKEN_URL = WECHAT_API_HOST + "/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";

    private static final String IMAGE_CHECK_URL = WECHAT_API_HOST + "/wxa/img_sec_check?access_token={access_token}";

    private static final String MSG_CHECK_URL = WECHAT_API_HOST + "/wxa/msg_sec_check?access_token={access_token}";


    @Autowired
    private RedissonLockUtil redissonLockUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ConfigurationBean config;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 文字违规内容审核
     *
     * @param content
     * @param appid
     * @param secret
     * @return
     * @throws ServiceException
     */
    public boolean textCheck(String content, String appid, String secret) throws ServiceException {
        if (StringUtils.isBlank(content)) {
            return true;
        }
        try {
            String accessToken = this.getAndCacheAccessToken(appid, secret);
            if (StringUtils.isEmpty(accessToken)) {
                log.error("获取微信access token|FAIL|appid={},secret={}", appid, secret);
                throw new ServiceException(EBaseResponseCode.C500.getCode(), EBaseResponseCode.C500.getMessage());
            }
            String url = MSG_CHECK_URL.replace("{access_token}", accessToken);
            JSONObject params = new JSONObject();
            params.put("content", content);
            String resp = new HttpRequest().postJson(url, params.toString());
            log.info("微信文本审核|http request end|content={}|resp={}", content, resp);
            if (StringUtils.isNotEmpty(resp)) {
                SecCheckResp json = objectMapper.readValue(resp, SecCheckResp.class);
                Integer errcode = json.getErrcode();
                if (Objects.equals(0, errcode)) {
                    return true;
                } else if (Objects.equals(87014, errcode)) {
                    //内容含违法违规内容
                    throw new ServiceException(EServiceException.CONTENT_UNAPPROVED.code(), EServiceException.CONTENT_UNAPPROVED.msg());
                } else if (Objects.equals(40001, errcode)) {
                    this.clearAccessTokenInRedis(appid);
                }
            }
            return false;
        } catch (IOException e) {
            throw new ServiceException(EBaseResponseCode.C500);
        }
    }


    /**
     * 微信的图片违规审核
     *
     * @param file
     * @param appid
     * @param secret
     * @return
     * @throws ServiceException
     */
    public boolean imageCheck(MultipartFile file, String appid, String secret) throws ServiceException {
        try {
            String accessToken = this.getAndCacheAccessToken(appid, secret);
            if (StringUtils.isEmpty(accessToken)) {
                log.error("获取微信access token|FAIL|appid={},secret={}", appid, secret);
                throw new ServiceException(EBaseResponseCode.C500);
            }
            String url = IMAGE_CHECK_URL.replace("{access_token}", accessToken);
            String resp;
            Map<String, String> heards = new HashMap<>();
            heards.put("Content-Type", "multipart/form-data");
            resp = new HttpRequest().addHeads(heards).postMultipartFile(url, "media", file, Collections.emptyMap());
            log.info("微信图片审核|http request end|fileName={}|resp={}", file.getOriginalFilename(), resp);
            if (StringUtils.isNotEmpty(resp)) {
                SecCheckResp json = objectMapper.readValue(resp, SecCheckResp.class);
                Integer errcode = json.getErrcode();
                if (Objects.equals(0, errcode)) {
                    return true;
                } else if (Objects.equals(87014, errcode)) {
                    //图片含违法违规内容
                    throw new ServiceException(EServiceException.IMAGE_UNAPPROVED.code(), EServiceException.IMAGE_UNAPPROVED.msg());
                } else if (Objects.equals(40001, errcode)) {
                    this.clearAccessTokenInRedis(appid);
                }
            }
            return false;
        } catch (IOException e) {
            throw new ServiceException(EBaseResponseCode.C500);
        }
    }


    public boolean imageCheck(byte[] fileBytes, String originalFilename, String appid, String secret) throws ServiceException {
        try {
            String accessToken = this.getAndCacheAccessToken(appid, secret);
            if (StringUtils.isEmpty(accessToken)) {
                log.error("获取微信access token|FAIL|appid={},secret={}", appid, secret);
                throw new ServiceException(EBaseResponseCode.C500);
            }
            String url = IMAGE_CHECK_URL.replace("{access_token}", accessToken);
            String resp;
            Map<String, String> heards = new HashMap<>();
            heards.put("Content-Type", "multipart/form-data");
            resp = new HttpRequest().addHeads(heards).postByte(url, "media", fileBytes, originalFilename, Collections.emptyMap());
            log.info("微信图片审核|http request end|fileName={}|resp={}", originalFilename, resp);
            if (StringUtils.isNotEmpty(resp)) {
                SecCheckResp json = objectMapper.readValue(resp, SecCheckResp.class);
                Integer errcode = json.getErrcode();
                if (Objects.equals(0, errcode)) {
                    return true;
                } else if (Objects.equals(87014, errcode)) {
                    //内容含违法违规内容
                    throw new ServiceException(EServiceException.IMAGE_UNAPPROVED.code(), EServiceException.IMAGE_UNAPPROVED.msg());
                } else if (Objects.equals(40001, errcode)) {
                    this.clearAccessTokenInRedis(appid);
                }
            }
            return false;
        } catch (IOException e) {
            throw new ServiceException(EBaseResponseCode.C500);
        }
    }


    @Getter
    @Setter
    private static class SecCheckResp {

        /**
         * errcode : 0
         * errmsg : ok
         */
        private Integer errcode;
        private String errmsg;

    }


    /**
     * 获取access token，并缓存到redis中
     *
     * @param appid
     * @param secret
     * @return
     * @throws ServiceException
     * @throws IOException
     */
    public String getAndCacheAccessToken(final String appid, final String secret) throws ServiceException, IOException {
        final String key = ERedisKey.WECHAT_ACCESS_TOKEN.getCode() + appid;
        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        String value = ops.get();
        if (StringUtils.isNotEmpty(value)) {
            AccessToken accessToken = objectMapper.readValue(value, AccessToken.class);
            return accessToken.getToken();
        }
        //保证只在生产环境会调用获取access token接口
        if (!Objects.equals("prod", config.getEnv())) {
            return null;
        }
        String url = ACCESS_TOKEN_URL.replace("{appid}", appid)
                .replace("{secret}", secret);
        String resp;
        final String lockKey = ERedisKey.LOCK_WECHAT_ACCESS_TOKEN.getCode() + appid;
        boolean lock = false;
        try {
            lock = redissonLockUtil.tryLock(lockKey, 10);
            if (!lock) {
                log.error("获取微信access token|FAIL|获取锁失败|appid={}", appid);
                throw new ServiceException(EBaseResponseCode.C500);
            }
            //在锁的内部再从redis中获取一次
            value = ops.get();
            if (StringUtils.isNotEmpty(value)) {
                AccessToken accessToken = objectMapper.readValue(value, AccessToken.class);
                return accessToken.getToken();
            }
            resp = new HttpRequest().get(url);
            log.info("获取微信access token|http request end|appid={},secret={}|resp={}", appid, secret, resp);
            if (StringUtils.isNotEmpty(resp)) {
                AccessTokenResp json = objectMapper.readValue(resp, AccessTokenResp.class);
                if (StringUtils.isEmpty(json.getErrcode())) {
                    String accessToken = json.getAccess_token();
                    int expiresIn = json.getExpires_in();
                    expiresIn = expiresIn - 60; // 本地保存tokende超时比微信告知的少一分钟，防止出现时间误差
                    AccessToken accessTokenBean = new AccessToken(accessToken, expiresIn, System.currentTimeMillis());
                    ops.set(accessTokenBean.toString(), expiresIn, TimeUnit.SECONDS);
                    return accessToken;
                }
            }
        } finally {
            if (lock) {
                redissonLockUtil.unlock(lockKey);
            }
        }
        return null;
    }


    @Getter
    @Setter
    private static class AccessTokenResp {

        /**
         * access_token : 35__cQkhlwXDEp3m7l9rtMyQ8GMkR8Yuh4uL4U-gVuT98IY3nSSgXosoyLCEuN4-pCvDztcqBWJ035TQfprCTxrus0CtnpWhwdNkvUNl5grzrNzuJZa6mxSz2P_sdExLoMIAtzI_w16I-sI9XAIUDOjADAENY
         * expires_in : 7200
         */
        private String errcode;
        private String access_token;
        private int expires_in;


    }


    public void clearAccessTokenInRedis(final String appid) {
        final String key = ERedisKey.WECHAT_ACCESS_TOKEN.getCode() + appid;
        redisTemplate.delete(key);
    }


}
