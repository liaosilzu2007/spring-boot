package com.lzumetal.springboot.image.service;

import com.lzumetal.springboot.image.config.ConfigurationBean;
import com.lzumetal.springboot.utils.HttpRequest;
import com.lzumetal.springboot.utils.ImageUitl;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EServiceResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@Service
@Slf4j
public class FileUploadService {

    private static final String FILE_UPLOAD_URL = "http://file.liaosi.com/file/upload.do";

    @Autowired
    private ConfigurationBean configurationBean;

    @Autowired
    private WeixinService weixinService;


    public String uploadFile(MultipartFile imageFile) throws ServiceException, IOException {
        String result = new HttpRequest().postMultipartFile(FILE_UPLOAD_URL, "file", imageFile, Collections.emptyMap());
        log.info("FileUploadService|uploadFile|调用文件上传接口|END|fileName={}|result={}", imageFile.getOriginalFilename(), result);
        JSONObject json = new JSONObject(result);
        if (Objects.equals(json.optInt("status"), 200)) {
            return json.optJSONObject("data").optString("url");
        }
        throw new ServiceException(500, json.optString("message"));
    }


    public String uploadImageWithCheck(MultipartFile imageFile) throws IOException, ServiceException {
        String appid = configurationBean.getXcxAppId();
        String secret = configurationBean.getXcxSecret();
        long size = imageFile.getSize();
        long mb = size / (1024 * 1024);
        boolean imageCheck;
        if (mb < 1) {
            imageCheck = weixinService.imageCheck(imageFile, appid, secret);
        } else {
            String originalFilename = imageFile.getOriginalFilename();
            byte[] bytes = ImageUitl.compressMultipartImageFile(imageFile);
            imageCheck = weixinService.imageCheck(bytes, originalFilename, appid, secret);
        }
        if (!imageCheck) {
            throw new ServiceException(EServiceResponseCode.IMAGE_CHECK_ERROR.getCode(), EServiceResponseCode.IMAGE_CHECK_ERROR.getMessage());
        }
        return this.uploadFile(imageFile);
    }

}
