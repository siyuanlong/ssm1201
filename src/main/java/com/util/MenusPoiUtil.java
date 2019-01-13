package com.util;

import com.bean.Menu;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MenusPoiUtil {
    private static HSSFWorkbook workbook;
    private static HSSFSheet sheet;
    //第一行
    public static void firstRow(String[] headers){
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i <headers.length ; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }
    //其他行
    public static void otherRows(List<Menu> list){
        for (int i = 0; i <list.size() ; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell01 = row.createCell(0);
            cell01.setCellValue(list.get(i).getMenuid());
            HSSFCell cell02 = row.createCell(1);
            cell02.setCellValue(list.get(i).getMenuname());
            HSSFCell cell03= row.createCell(2);
            cell03.setCellValue(list.get(i).getMenupath());
            HSSFCell cell04 = row.createCell(3);
            cell04.setCellValue(list.get(i).getMenustate());
        }
    }
    //设置导出
    public static void export(FileOutputStream fileOutputStream){
        //设置以表格的形式导出
        sheet.setGridsPrinted(true);
        try {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
