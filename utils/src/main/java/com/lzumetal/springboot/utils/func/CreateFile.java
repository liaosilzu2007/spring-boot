package com.lzumetal.springboot.utils.func;

import com.lzumetal.springboot.utils.DateUtils;
import com.lzumetal.springboot.utils.FileUtil;
import com.lzumetal.springboot.utils.POIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author liaosi
 */
public class CreateFile {


    public static void main(String[] args) {
        String sql = "insert into t_data_export_temp(id,khh,extra) values(func_nextid('t_data_export_temp'),'%s',%s);";
        String khh = "123";
        String extra = "0.5";
        System.out.println(String.format(sql, khh, extra));
    }




    @Test
    public void generateByFlag() throws IOException {
        final int flag = 2;
        final String sourceFile = "D:\\tmp\\source.txt";
        final String outputFile = "D:\\tmp\\temp_" + flag +".sql";
        List<String> sourceLines = FileUtil.readFile(sourceFile);
        String sql = "insert into t_data_export_temp(id,khh,flag) values(func_nextid('t_data_export_temp'),'%s',%d);";
        //String sql = "insert into t_data_export_temp(id,khh,yf) values(func_nextid('t_data_export_temp'),'%s',202503);";
        //List<String> sqlLines = new ArrayList<>();
        StringBuilder sqlText = new StringBuilder();
        /*sqlText.append("prompt Importing table t_data_export_temp...").append("\n");
        sqlText.append("set feedback off").append("\n");
        sqlText.append("set define off").append("\n");*/    //ob数据库不需要加此命令
        for (String line : sourceLines) {
            if (sqlText.length() > 0) {
                sqlText.append("\n");
            }
            sqlText.append(String.format(sql, line,flag));
        }
        //sqlText.append("prompt Done.");
        try (FileWriter fw = new FileWriter(outputFile);){
            fw.write(sqlText.toString());
        }
    }


    @Test
    public void generateByMonth() throws IOException {
        String month = "202605";
        Workbook workbook = POIUtil.getWorkbook("D:\\tmp\\数据需求_20260710\\数据需求.xlsx");
        List<List<String>> lists = POIUtil.readExcelValue(workbook, month);
        if (CollectionUtils.isEmpty(lists)) {
            System.err.println("sheet=" + month + "未获取到数据！！！！");
            return;
        }
        int count = 0;
        String sql = "insert into t_data_export_temp(id,khh,yf) values(func_nextid('t_data_export_temp'),'%s',%d);";
        StringBuilder sqlText = new StringBuilder();
        for (int i = 1; i < lists.size(); i++) {
            List<String> line = lists.get(i);
            String custNo = line.get(0);
            if (StringUtils.isNotBlank(custNo)) {
                System.out.println("客户号：" + custNo);
                if (sqlText.length() > 0) {
                    sqlText.append("\n");
                }
                sqlText.append(String.format(sql, custNo, Integer.parseInt(month)));
                count++;
            }

        }
        String outputFile = "D:\\tmp\\数据需求_20260710\\数据需求_X_" + month + ".sql";

        System.out.println("sheet=" + month + "，总记录" + count + "条。");
        try (FileWriter fw = new FileWriter(outputFile);) {
            fw.write(sqlText.toString());
        }


    }


    @Test
    public void generateByExtra() throws IOException {
        Workbook workbook = POIUtil.getWorkbook("D:\\tmp\\extra_source.xlsx");
        List<List<String>> lists = POIUtil.readExcelValue(workbook, 0);
        if (CollectionUtils.isEmpty(lists)) {
            System.err.println("未获取到数据！！！！");
            return;
        }
        int count = 0;
        String sql = "insert into t_data_export_temp(id,khh,extra) values(func_nextid('t_data_export_temp'),'%s','%s');";
        StringBuilder sqlText = new StringBuilder();
        for (int i = 1; i < lists.size(); i++) {
            List<String> line = lists.get(i);
            String custNo = line.get(0);
            String extra = line.get(1);
            if (StringUtils.isNotBlank(custNo)) {
                System.out.println("客户号：" + custNo + ",extra=" + extra);
                if (sqlText.length() > 0) {
                    sqlText.append("\n");
                }
                sqlText.append(String.format(sql, custNo, extra));
                count++;
            }

        }
        String outputFile = "D:\\tmp\\" + "extra_source.sql";

        System.out.println("总记录" + count + "条。");
        try (FileWriter fw = new FileWriter(outputFile);) {
            fw.write(sqlText.toString());
        }


    }


    @Test
    public void genenateSoftAccessFile() throws IOException {
        String sheetName = "sheet2";
        Workbook workbook = POIUtil.getWorkbook("D:\\tmp\\世纪大道分公司外接申请.xlsx");
        List<List<String>> lists = POIUtil.readExcelValue(workbook, sheetName);
        if (CollectionUtils.isEmpty(lists)) {
            System.err.println("sheet=" + sheetName + "未获取到数据！！！！");
            return;
        }
        int count = 0;
        String sql = "insert into TWBJR_EXPORT_TEMP(ID,KHH,KHH2,RQ,JYXT,APPID) values(func_nextid('TWBJR_EXPORT_TEMP'),'%s','%s',%d,'%s','%s');";
        StringBuilder sqlText = new StringBuilder();
        for (int i = 1; i < lists.size(); i++) {
            List<String> line = lists.get(i);
            int day = Integer.parseInt(DateUtils.parseToDateTime(line.get(1), DateUtils.DEFAULT_DATE_FORMAT).toString(DateUtils.COMPACT_DATE_FORMAT));
            String custNo = line.get(2);
            String tradeSystem = line.get(5);
            String appid = line.get(6);
            if (StringUtils.isNotBlank(custNo)) {
                String custNo2 = custNo.contains("/") ? custNo.substring(0, custNo.indexOf("/")) : custNo;
                System.out.println("客户号：" + custNo + "，日期：" + day + "，appid：" + appid);
                if (sqlText.length() > 0) {
                    sqlText.append("\n");
                }
                sqlText.append(String.format(sql, custNo, custNo2, day, tradeSystem, appid));
                count++;
            }

        }
        String outputFile = "D:\\tmp\\数据需求_世纪大道分公司外接申请.sql";

        System.out.println("sheet=" + sheetName + "，总记录" + count + "条。");
        try (FileWriter fw = new FileWriter(outputFile);) {
            fw.write(sqlText.toString());
        }
        System.out.println(sqlText.toString());

    }

}
