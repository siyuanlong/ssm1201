package com.util;

import com.bean.Classes;
import com.bean.UserTb;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UsersPoiUtil {
    private static HSSFWorkbook workbook = null;
    private static HSSFSheet sheet = null;
    //创建第一行
    public static void firstRow(String[] headers){
        //创建工作簿的顶级对象，可以完成对excel的读写操作
        workbook = new HSSFWorkbook();
        //创建一个工作簿
        sheet = workbook.createSheet();
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //行内创建列
        for (int i = 0; i <headers.length ; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    //创建其他行
    public static void otherRows(List<UserTb> list){
        for (int i = 0; i <list.size() ; i++) {
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell01 = row.createCell(0);
            cell01.setCellValue(i);
            HSSFCell cell02 = row.createCell(1);
            cell02.setCellValue(list.get(i).getUserName());
            HSSFCell cell03 = row.createCell(2);
            cell03.setCellValue(list.get(i).getUserRealname());
            HSSFCell cell04 = row.createCell(3);
            cell04.setCellValue(list.get(i).getRole().getRolename());
        }
    }

    //设置导出
    public static void export(FileOutputStream outputStream){
        //设置以表格形式导出
        sheet.setGridsPrinted(true);
        //导出
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
