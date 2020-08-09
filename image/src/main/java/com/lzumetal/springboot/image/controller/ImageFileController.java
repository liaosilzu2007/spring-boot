package com.lzumetal.springboot.image.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzumetal.springboot.image.entity.dto.UploadImageDTO;
import com.lzumetal.springboot.image.service.FileUploadService;
import com.lzumetal.springboot.utils.response.PageBean;
import com.lzumetal.springboot.utils.response.ResponseData;
import com.lzumetal.springboot.utils.common.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liaosi
 * @date 2020-08-09
 */
@RestController
@RequestMapping(value = "/image", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class ImageFileController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping(value = "/uploadImage")
    public ResponseData uploadImage(@RequestParam("file") MultipartFile imageFile) throws ServiceException {
        log.info("单张图片上传|PARAM|userid={},fileName={}", userid, imageFile.getOriginalFilename());
        try {
            preCheck(imageFile);

            String url = fileUploadService.uploadImageWithCheck(imageFile);
            return ResponseData.data(url);
        } catch (Exception e) {
            log.error("文件|uploadImage|ERROR|userId={}", userid, e);
            if (e instanceof ServiceException) {
                throw (ServiceException) e;
            }
            throw new ServiceException(500, "服务器异常,上传失败");
        }

    }


    @RequestMapping(value = "/uploadImageBatch")
    public ResponseData uploadImageBatch(@RequestParam("files") MultipartFile[] imageFiles) throws ServiceException, IOException {
        int count = imageFiles.length;
        if (count == 0) {
            throw new ServiceException(401, "图片文件不能为空");
        }
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile imageFile : imageFiles) {
            preCheck(imageFile);
            fileNames.add(imageFile.getOriginalFilename());
        }

        log.info("批量图片上传|PARAM|userid={}|fileNames={}", userid, objectMapper.writeValueAsString(fileNames));

        final CountDownLatch countDownLatch = new CountDownLatch(count);
        final List<UploadImageDTO> uploadImageDTOList = new ArrayList<>();
        //多线程调用
        int seq = 0;
        for (MultipartFile imageFile : imageFiles) {
            seq++;
            final int index = seq;
            executor.submit(() -> {
                UploadImageDTO uploadImageDTO = new UploadImageDTO();
                try {
                    String url = fileUploadService.uploadImageWithCheck(imageFile);
                    uploadImageDTO.setReqIndex(index);
                    uploadImageDTO.setUrl(url);
                } catch (Exception e) {
                    log.error("文件|uploadImageBatch|ERROR|userId={}|fileName={}", userid, imageFile.getOriginalFilename(), e);
                    if (e instanceof ServiceException) {
                        ServiceException serviceException = (ServiceException) e;
                        if (Objects.equals(serviceException.getErrorCode(), EServiceException.IMAGE_UNAPPROVED.code())) {
                            uploadImageDTO.setErrorCode(serviceException.getErrorCode());
                        }
                    }
                } finally {
                    uploadImageDTOList.add(uploadImageDTO);
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(12, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("文件|uploadImageBatch|ERROR|userId={}", userid, e);
        }
        //排序。返回数据按照上传图片顺序
        uploadImageDTOList.sort((o1, o2) -> {
            int x = o1.getReqIndex();
            int y = o2.getReqIndex();
            return Integer.compare(x, y);
        });
        List<String> urls = new ArrayList<>();
        for (UploadImageDTO uploadImageDTO : uploadImageDTOList) {
            Integer errorCode = uploadImageDTO.getErrorCode();
            if (errorCode != null && Objects.equals(EServiceException.IMAGE_UNAPPROVED.code(), errorCode)) {
                throw new ServiceException(EServiceException.IMAGE_UNAPPROVED.code(), EServiceException.IMAGE_UNAPPROVED.msg());
            }
            String url = uploadImageDTO.getUrl();
            if (StringUtils.isEmpty(url)) {
                continue;
            }
            urls.add(url);
        }
        if (urls.isEmpty()) {
            throw new ServiceException(EBaseRespException.C500.code(), "服务器异常,上传失败");
        }
        return ResponseData.data(new PageBean<>(urls));
    }


    private static void preCheck(MultipartFile imageFile) throws ServiceException, IOException {
        String originalFilename = imageFile.getOriginalFilename();
        long fileSize = imageFile.getSize();
        if (fileSize == 0) {
            log.error("图片|uploadImage|FAIL|fileName={}|文件大小为0", originalFilename);
            throw new ServiceException(403, "图片大小不能为0");
        }
        long size = fileSize / (1024 * 1024);
        if (size >= 4) {
            log.error("图片|uploadImage|FAIL|fileName={}|文件大小：{}kb", originalFilename, fileSize / 1024);
            throw new ServiceException(403, "图片最大不能超过4M");
        }

        BufferedImage image = ImageIO.read(imageFile.getInputStream());
        if (image == null) {
            //如果image=null 表示上传的不是图片格式
            throw new ServiceException(EBaseRespException.C401.code(), "图片格式异常");
        }
//
//        if (image.getWidth() > 750 || image.getHeight() > 1334) {  //判断图片像素尺寸是否在范围内
//            throw new ServiceException(EBaseRespException.C401.code(), "图片尺寸不能超过 750 x 1334");
//        }
    }


}
