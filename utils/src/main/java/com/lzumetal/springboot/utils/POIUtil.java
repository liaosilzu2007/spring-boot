package com.lzumetal.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 类描述：
 * 创建人：liaosi
 * 创建时间：2017年12月29日
 */

public class POIUtil {

    private static final Logger log = LoggerFactory.getLogger(POIUtil.class);
    private static final String EMPTY_STRING = "";


    private static List<List<String>> readExcelValue(Sheet sheet) {
        List<List<String>> list = new ArrayList<>();
        // 得到Excel的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        /*
         * 得到Excel的列数(前提是有行数)，在实践中发现如果列中间有空值的话，那么读到空值的地方就停止了。所以我们需要取得最长的列。
         * 如果每个列正好都有一个空值得话，通过这种方式获得的列长会比真实的列要少一列。所以我自己会在将要倒入数据库中的EXCEL表加一个表头
         * 防止列少了，而插入数据库中报错。
         */
        int totalColumns = 0;
        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                if (totalColumns < cells) {
                    totalColumns = cells;
                }
            }
        }

        //算行数的时候去掉第一行的行头
        log.info("sheet:{},数据总行数：{},数据总列数：{}", sheet.getSheetName(), totalRows - 1, totalColumns);

        // 遍历行
        for (int i = 0; i < totalRows; i++) {
            // 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
                List<String> beanFields = new ArrayList<>();
                for (int j = 0; j < totalColumns; j++) {
                    Cell cell = row.getCell(j);
                    beanFields.add(getStringCellValue(cell));
                }
                list.add(beanFields);
            }
        }
        return list;
    }


    public static List<List<String>> readExcelValue(Workbook workbook, int sheetIndex) {
        // 得到sheet
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        return readExcelValue(sheet);
    }


    public static List<List<String>> readExcelValue(Workbook workbook, String sheetName) {
        // 得到sheet
        Sheet sheet = workbook.getSheet(sheetName);
        return readExcelValue(sheet);
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private static String getStringCellValue(Cell cell) {
        String strCell = EMPTY_STRING;
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    String dataFormatString = cell.getCellStyle().getDataFormatString();
                    if ("hh:mm:ss".equals(dataFormatString)) {
                        Date date = cell.getDateCellValue();
                        strCell = DateFormatUtils.format(date, "HH:mm:ss");
                    } else {
                        strCell = String.valueOf((cell.getNumericCellValue()));
                    }
                    break;
                case STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case FORMULA:
                case _NONE:
                case BLANK:
                case ERROR:
                    break;
            }
        }
        return strCell;
    }

    /**
     * 获取Excel对应的WorkBook实例
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook;
        if (isExcel2003(filePath)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (isExcel2007(filePath)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new RuntimeException("该文件不是excel文件");
        }
        return workbook;
    }


    /**
     * 获取Excel文件表单的数量
     *
     * @param workbook
     * @return
     */
    public static int getSheetNum(Workbook workbook) {
        int numberOfSheets = workbook.getNumberOfSheets();
        log.info("Excel文件表单的数量：{}", numberOfSheets);
        return numberOfSheets;
    }


    /**
     * 根据后缀名判读是不是POI可处理的excel文件
     *
     * @param filePath
     */
    public static boolean isExcel(String filePath) {
        return isExcel2003(filePath) || isExcel2007(filePath);
    }


    /**
     * 是否是2003的excel，返回true是2003
     *
     * @param filePath 文件全路径名
     */
    private static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }


    /**
     * 是否是2007的excel，返回true是2007
     *
     * @param filePath 文件全路径名
     */
    private static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }


    /**
     * 指定单元格追加内容
     *
     * @param sheet
     * @param row
     * @param column
     * @param content
     */
    public static void appendCellContent(Sheet sheet, int row, int column, String content) {
        setCellContent(sheet, row, column, content, true);
    }

    /**
     * 指定单元格设置内容
     *
     * @param sheet
     * @param row
     * @param column
     * @param content
     */
    public static void setCellContent(Sheet sheet, int row, int column, String content) {
        setCellContent(sheet, row, column, content, false);
    }

    private static void setCellContent(Sheet sheet, int row, int column, String content, boolean append) {
        if (sheet == null) {
            throw new RuntimeException("sheet is null");
        }
        Row rowItem = sheet.getRow(row);
        if (rowItem == null) {
            throw new RuntimeException("the specified row in sheet is null");
        }
        Cell cell = rowItem.getCell(column);
        if (cell == null) {
            throw new RuntimeException("the specified cell in sheet is null");
        }
        if (append) {
            String oldValue = cell.toString() == null ? EMPTY_STRING : cell.toString();
            content = content == null ? EMPTY_STRING : content;
            cell.setCellValue(oldValue + content);
        } else {
            cell.setCellValue(content);
        }

    }


    /**
     * 根据行和列的索引获取单元格的数据
     *
     * @param row
     * @param column
     * @return
     */
    public static String getCellContent(Sheet sheet, int row, int column) {
        if (sheet == null) {
            return null;
        }
        Row rowItem = sheet.getRow(row);
        if (rowItem == null) {
            return null;
        }
        Cell cell = rowItem.getCell(column);
        if (cell == null) {
            return null;
        }
        return cell.toString();
    }


    public static void printCellContent(Sheet sheet) {
        if (sheet == null) {
            return;
        }
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            int columns = row.getPhysicalNumberOfCells();
            for (int j = 0; j < columns; j++) {
                Cell cell = row.getCell(j);
                String value = cell.toString();
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                System.out.println(i + "----" + j + "|" + value);
            }
        }
    }

}
