package com.lzumetal.springboot.image.controller;

import com.lzumetal.springboot.utils.POIUtil;
import com.lzumetal.springboot.utils.common.ServiceException;
import com.lzumetal.springboot.utils.response.EServiceResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author liaosi
 */
@RestController
@RequestMapping(value = "/excel", method = {RequestMethod.GET, RequestMethod.POST})
@Slf4j
public class ExcelController {



    @GetMapping("/exportExcel")
    public void exportExcel(@RequestParam Long id, HttpServletResponse response) throws IOException {
        try (ServletOutputStream outputStream = response.getOutputStream();){
            Workbook workbook = POIUtil.getWorkbook("D:\\test.xlsx");
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("结果导出.xlsx", "UTF-8"));
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error("导出excel文档异常|id={}", id, e);
            throw new ServiceException(EServiceResponseCode.FILE_ERROR.getCode(), "导出excel文档异常");
        }

    }




}
