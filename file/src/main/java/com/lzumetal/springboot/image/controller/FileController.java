package com.lzumetal.springboot.image.controller;

import cn.hutool.json.JSONUtil;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EServiceResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

/**
 * @author liaosi
 */
@RestController
@RequestMapping(value = "/file", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class FileController {



    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) {
        File file = new File("D:\\test.txt");
        if (file == null) {
            throw new ServiceException(EServiceResponseCode.FILE_ERROR);
        }
        try (InputStream inputStream = Files.newInputStream(file.toPath());
             ServletOutputStream outputStream = response.getOutputStream();){
            response.reset();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            log.error("文件下载异常", e);
            throw new ServiceException(EServiceResponseCode.FILE_ERROR.getCode(), "文件下载异常");
        }

    }




}
