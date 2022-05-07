package com.lzumetal.springboot.opensourcelib.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.font.FontProvider;
import com.lzumetal.springboot.utils.response.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/itext")
public class ItextController {


    @RequestMapping("/create-pdf-file")
    public ResponseData createPdfFile(@RequestParam String fileName) throws IOException {
        PdfFont font = PdfFontFactory.createFont("static/font/simsun.ttc,1");
        FontProvider fontProvider = new FontProvider();
        fontProvider.addFont(font.getFontProgram());
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(fontProvider);
        String content = "<p>姓名：张三 </br> 用户名：zhangsan </br> 账号编码：1234</p>";
        try (FileOutputStream outputStream = new FileOutputStream("/opt/runnable_jar/open-source-lib/data/" + fileName + ".pdf")) {
            HtmlConverter.convertToPdf(content, outputStream, converterProperties);
        }
        return ResponseData.success();
    }


}
