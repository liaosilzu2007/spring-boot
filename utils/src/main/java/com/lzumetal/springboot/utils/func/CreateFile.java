package com.lzumetal.springboot.utils.func;

import com.lzumetal.springboot.utils.FileUtil;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author liaosi
 */
public class CreateFile {



    @Test
    public void testGenerateInsertFile() throws IOException {
       this.generateInsertFile("D:\\tmp\\source.txt","D:\\tmp\\temp_1.sql");
    }



    public void generateInsertFile(String sourceFile, String outputFile) throws IOException {
        List<String> sourceLines = FileUtil.readFile(sourceFile);
        //String sql = "insert into t_data_export_temp(id,khh,flag) values(func_nextid('t_data_export_temp'),'%s',2);";
        String sql = "insert into t_temp(month,cust_no) values(202312,'%s');";
        //List<String> sqlLines = new ArrayList<>();
        StringBuilder sqlText = new StringBuilder();
        sqlText.append("prompt Importing table t_temp...").append("\n");
        sqlText.append("set feedback off").append("\n");
        sqlText.append("set define off").append("\n");*/    //ob数据库不需要加此命令
        for (String line : sourceLines) {
            sqlText.append(String.format(sql, line)).append("\n");
        }
        //sqlText.append("prompt Done.");
        try (FileWriter fw = new FileWriter(outputFile);){
            fw.write(sqlText.toString());
        }
    }


    @Test
    public void genenateByMonth() throws IOException {
        String month = "202412";
        Workbook workbook = POIUtil.getWorkbook("D:\\BrowserDownload\\数据需求.xlsx");
        List<List<String>> lists = POIUtil.readExcelValue(workbook, month);
        if (CollectionUtils.isEmpty(lists)) {
            System.err.println("sheet=" + month + "未获取到数据！！！！");
            return;
        }
        int count = 0;
        String sql = "insert into t_data_export_temp(id,khh,yf) values(t_data_export_temp.nextval,'%s',%d);";
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
        String outputFile = "D:\\tmp\\temp_" + month + ".sql";

        System.out.println("sheet=" + month + "，总记录" + count + "条。");
        try (FileWriter fw = new FileWriter(outputFile);){
            fw.write(sqlText.toString());
        }


    }

}
