package com.lhw.vue_test2;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class VueTest2ApplicationTests {
    public static void main(String[] args) {
        //获取表格路径
        String basePath = "E:\\A-YX\\testexcel\\test4.xls";
        //获取文件
        File file = new File(basePath);
        FileInputStream excelFileInputStream;
        try {
            excelFileInputStream = new FileInputStream(file);
            // 拿到文件转化为JavaPoi可操纵类型
            Workbook workbook = WorkbookFactory.create(excelFileInputStream);
            excelFileInputStream.close();
            //获取excel表格
            Sheet sheet = workbook.getSheetAt(0);
            //先获取表中总共的行数
            int num = sheet.getLastRowNum();
            System.out.println(num);
            //获取每一行有多少列
            Row rows = sheet.getRow(0);
            int column = rows.getLastCellNum();
            int start_column = column - 2;
            int end_column = column - 1;
            System.out.println(start_column);
            //开始遍历
            for (int i = 1 ; i < num+1 ; i++) {
                // 获取行
                Row row = sheet.getRow(i);
                //获取开始时间
                Cell cell_start = row.getCell(start_column);
                //获取结束时间时间
                Cell cell_end = row.getCell(end_column);
                //转换成string
                String start_string = cell_start.getStringCellValue().replaceAll("s","");
                String end_string = cell_end.getStringCellValue().replaceAll("s","");
                //转换成毫秒数double
                Double start_double = Double.parseDouble(start_string) *1000;
                Double end_double = Double.parseDouble(end_string) *1000;
                //转换成long
                long start_long = start_double.longValue();
                long end_long = end_double.longValue();
                //获取当前时间的毫秒
                Date now_date = new Date();
                long now_time = now_date.getTime();
                //将开始时间加上去
                long start_result = now_time + start_long;
                //将结束时间加上去
                long end_result = now_time + end_long;
                //格式转换
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String start_time = formatter.format(start_result);
                String end_time = formatter.format(end_result);
                //将转化后的时间填入单元格
                cell_start.setCellValue(start_time);
                cell_end.setCellValue(end_time);
                System.out.println("该单元格操作成功！");
            }
            //写入数据
            FileOutputStream excelFileOutPutStream = new FileOutputStream(file);
            workbook.write(excelFileOutPutStream);
            excelFileOutPutStream.flush();
            excelFileOutPutStream.close();
            System.out.println("指定单元格设置数据写入完成");
        } catch (EncryptedDocumentException | IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}
