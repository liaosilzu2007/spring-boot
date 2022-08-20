package com.lzumetal.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liaosi
 * @date 2020-07-09
 */
@Slf4j
public class FileUtil {


    public static List<String> readFromLogFiles(List<String> filesNames) {
        List<String> list = new ArrayList<>();
        for (String filesName : filesNames) {
            list.addAll(readFromLogFile(filesName));
        }
        return list;
    }


    public static List<String> readFromLogFile(String filesName) {
        List<String> list = new ArrayList<>();
        File file = new File(filesName);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String trim = line.trim();
                    if (StringUtils.isNotEmpty(trim)) {
                        list.add(trim);
                    }
                }
            } catch (Exception e) {
                log.error("读取日志文件|异常|", e);
                throw new RuntimeException("读取日志文件出错");
            }
        }
        return list;
    }

    /**
     * 删除文件夹以及文件夹下所有文件
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 判断文件是否是pdf格式
     * 依据：文件字节的前8位
     *
     * @param file
     * @return
     */
    public static boolean isPdfFile(File file) {
        try (InputStream is = new FileInputStream(file)) {
            byte[] bytes = new byte[4];
            is.read(bytes);
            String hexStr = Hex.encodeHexString(bytes);
            return hexStr.contains("25504446");
        } catch (IOException e) {
            log.error("判断文件是否为pdf格式异常", e);
            return false;
        }
    }


}
