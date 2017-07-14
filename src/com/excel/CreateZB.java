package com.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateZB
{

	public void create()
	{
		try
		{
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("C:\\Users\\lc\\Desktop\\ecbxd-坐标.xlsx"));
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 0; i <= sheet.getLastRowNum(); i++)
			{
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j <= row.getLastCellNum(); j++)
				{
					XSSFCell cell = row.getCell(j);
					if (cell != null && cell.getStringCellValue().trim().equals("L"))
					{
						cell.setCellValue("(" + i + "," + j + ")");
						System.out.print(cell.getStringCellValue() + " ");
					}
				}
				 System.out.println();
			}
			// 新建一输出文件流
			FileOutputStream fOut = new FileOutputStream("C:\\Users\\lc\\Desktop\\ecbxdxxx.xlsx");
			// 把相应的Excel 工作簿存盘
			workbook.write(fOut);
			fOut.flush();
			// 操作结束，关闭文件
			fOut.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		CreateZB createZB = new CreateZB();
		createZB.create();
	}

}
