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
       this.generateInsertFile("D:\\tmp\\source.txt","D:\\tmp\\202312.sql");
    }



    public void generateInsertFile(String sourceFile, String outputFile) throws IOException {
        List<String> sourceLines = FileUtil.readFile(sourceFile);
        //String sql = "insert into t_data_export_temp(id,khh,flag) values(func_nextid('t_data_export_temp'),'%s',2);";
        String sql = "insert into t_temp(month,cust_no) values(202312,'%s');";
        //List<String> sqlLines = new ArrayList<>();
        StringBuilder sqlText = new StringBuilder();
        sqlText.append("prompt Importing table t_temp...").append("\n");
        sqlText.append("set feedback off").append("\n");
        sqlText.append("set define off").append("\n");
        for (String line : sourceLines) {
            sqlText.append(String.format(sql, line)).append("\n");
        }
        sqlText.append("prompt Done.");
        try (FileWriter fw = new FileWriter(outputFile);){
            fw.write(sqlText.toString());
        }
    }

}
