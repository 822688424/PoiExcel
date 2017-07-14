package com.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.EcBxd;
import com.bean.EcPro;

import net.sf.json.JSONObject;

public class EcExcel
{
	private static final int ITEMS_PER_PAGE = 6;	// 每页记录数量

	public void export(String jsonStr, String inFileStr, String ouFileStr) throws Exception
	{
		JSONObject			jsonObject = JSONObject.fromObject(jsonStr);									// json字符串 -> json对象
		EcBxd				ecBxd = (EcBxd)JSONObject.toBean(jsonObject, EcBxd.class, createClassMap());	// json对象  -> java bean

		// 差旅费报销单表头
		String				dep = ecBxd.getDep();									// 报销部门
		String				year = ecBxd.getBxdate().substring(0, 4);				// 年
		String				month = ecBxd.getBxdate().substring(5, 7);				// 月
		String				day = ecBxd.getBxdate().substring(8, 10);				// 日
		int 				tcktno = ecBxd.getTcktno();								// 单据及附件页数

		// 报销单中间部分
		List<EcPro>			pros = ecBxd.getPros();									// 报销项目
		String 				bz = ecBxd.getBz();										// 备注
		String 				ldsp = ecBxd.getLdsp();									// 领导审批

		// 导出到excel
		createExcel(inFileStr, ouFileStr, dep, year, month, day, tcktno, pros, bz, ldsp);
	}

	@SuppressWarnings("rawtypes")
	private Map<String, Class> createClassMap()
	{
		Map<String, Class>	classMap = new HashMap<String, Class>();

		classMap.put("pros", EcPro.class);						// key为EcBxd私有变量的属性名

		return classMap;
	}

	//金额字符串
	private String moneyString(double money, int index)
	{
		DecimalFormat		df   = new DecimalFormat("000000.00");
		if (index == 7)
			return df.format(money).replace(".", "").substring(index);
		else
			return df.format(money).replace(".", "").substring(index, index + 1);
	}

	private void createExcel(String inFileStr, String ouFileStr, String dep, String year, String month, String day, int tcktno, List<EcPro> pros, String bz,
			String ldsp) throws Exception
	{
		// String				templteFilename = CxSvrVariable.dataMan.getDirRoot() + "reports/ecbxd.xlsx";
		String				templteFilename = inFileStr;
		// String				outFilename = (new Date()).getTime() + ".xlsx";
		String				outFilename = ouFileStr;

		FileInputStream		fis = null;
		FileOutputStream	fos = null;
		XSSFWorkbook		workbook = null;

		int					maxListSize = pros.size();		// 最大行数
		int					sheetNum = maxListSize / ITEMS_PER_PAGE + (maxListSize % ITEMS_PER_PAGE == 0 ? 0 : 1);	// sheet数量
		XSSFSheet			sheet;
		XSSFRow				row;
		EcPro 				pro;							// 项目
		int					i, j, count;

		try
		{
			fis = new FileInputStream(templteFilename);
			workbook = new XSSFWorkbook(fis);				//创建对Excel工作簿文件（Workbook）的引用
			fis.close();
			fis = null;

			//克隆sheet
			for(i = 1; i < sheetNum; i++)
			{
				workbook.cloneSheet(0);
			}
	
			//生成每一个sheet
			count = 0;
			for(i = 0; i < sheetNum; i++)
			{
				sheet = workbook.getSheetAt(i);								//sheet
	
				//表头
				row = sheet.getRow(2);
				row.getCell(4).setCellValue(dep);							// 部门
				row.getCell(11).setCellValue(year);							//年 月 日
				row.getCell(17).setCellValue(month);
				row.getCell(23).setCellValue(day);
				row.getCell(34).setCellValue(tcktno);						// 单据及附件页数
				
				row = sheet.getRow(3);
				row.getCell(32).setCellValue(bz);							// 备注
				row = sheet.getRow(7);
				row.getCell(32).setCellValue(ldsp);							// 领导审批
	
				//费用部分
				for(j = 0; j < ITEMS_PER_PAGE; j++)
				{
					row = sheet.getRow(j + 5);
	
					// 报销项目
					if(count < pros.size())
					{
						pro = pros.get(count);
						row.getCell(1).setCellValue(pro.getPro());		// 项目
						row.getCell(5).setCellValue(pro.getAbstr());	// 摘要
						for (int k = 0; k < 8; k++)
						{
							row.getCell(k + 22).setCellValue(moneyString(pro.getMoney(), k));	// 金额
						}
					}
		
					count++;
				}
			}

			//合计部分
			
			//输出文件
			fos = new FileOutputStream(outFilename);
			workbook.write(fos);
			
			//jo.put("dir", CxSvrVariable.dataMan.getDirTemp());
			//jo.put("filename", outFilename);
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(fis != null)
			{
				fis.close();
			}
			if(workbook != null)
			{
				workbook.close();
			}
			if(fos != null)
			{
				fos.flush();
				fos.close();
			}
		}
	}
}
