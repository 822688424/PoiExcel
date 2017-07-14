package com.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.bean.ClBxd;
import com.bean.ClFee;
import com.bean.ClQt;
import com.bean.ClSnjt;
import com.bean.ClYtjt;
import com.bean.ClZs;

import net.sf.json.JSONObject;

public class XSSFExcel
{
	public static void run(String jsonStr, String inFileStr, String ouFileStr)
	{
		XSSFExcel xssfExcel = new XSSFExcel();
		// json 字符串 ===> json 对象
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		// json 对象 ===> java bean
		// 使用JSONObject.toBean(jsonObject,beanClass,classMap)
		ClBxd clBxd = (ClBxd) JSONObject.toBean(jsonObject, ClBxd.class, xssfExcel.createClassMap());

		// 差旅费报销单上面部分
		String bxdDate = clBxd.getBxdate();// 报销单日期
		String name = clBxd.getName();// 出差人
		String reason = clBxd.getReason();// 事由

		// 报销单中间部分
		List<ClYtjt> listYtjt = clBxd.getYtjt();// 远途交通 list 集合
		List<Map<String, String>> listFees = xssfExcel.getClBt(clBxd);// 出差补贴
		List<Map<String, String>> listOths = xssfExcel.getClQt(clBxd);// 其他

		// 生成Excel
		try
		{
			xssfExcel.createExcel(bxdDate, name, reason, listYtjt, listFees, listOths, inFileStr, ouFileStr);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 创建 classMap 将作为函数JSONObject.toBean(jsonObject, beanClass,classMap)的第三个参数
	 * 
	 * @return 类型：Map<String, Class>
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Class> createClassMap()
	{
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("ytjt", ClYtjt.class); // key为ClBxd私有变量的属性名
		classMap.put("zs", ClZs.class);
		classMap.put("snjt", ClSnjt.class);
		classMap.put("qt", ClQt.class);
		classMap.put("fee", ClFee.class);
		return classMap;
	}

	/**
	 * 根据ClBxd报销单类得到Excel中出差补贴部分的数据
	 * 
	 * @param clBxd
	 *            报销单类
	 * @return 返回List<Map<String, String>>类型的集合
	 */
	public List<Map<String, String>> getClBt(ClBxd clBxd)
	{
		List<Map<String, String>> listFees = new ArrayList<Map<String, String>>();// 出差补贴
		List<ClFee> fees = clBxd.getFee();
		// 夜补
		Map<String, String> mapFee1 = new HashMap<String, String>();
		mapFee1.put("pro", fees.get(0).getPro());
		mapFee1.put("pers", "1");
		int days1 = 0;
		List<ClYtjt> listYtjt = clBxd.getYtjt();
		for (ClYtjt clYtjt : listYtjt)
		{
			days1 += (Timestamp.valueOf(clYtjt.getEdate()).getTime() - Timestamp.valueOf(clYtjt.getSdate()).getTime())
					/ (1000 * 3600 * 24);
		}
		mapFee1.put("days", String.valueOf(days1));
		mapFee1.put("money", String.valueOf(fees.get(0).getMoney()));
		mapFee1.put("monSum", String.valueOf(fees.get(0).getMoney() * days1));
		listFees.add(mapFee1);
		// 差贴
		Map<String, String> mapFee2 = new HashMap<String, String>();
		mapFee2.put("pro", fees.get(1).getPro());
		mapFee2.put("pers", "1");
		int days2 = (int) ((Timestamp.valueOf(listYtjt.get(listYtjt.size() - 1).getEdate()).getTime()
				- Timestamp.valueOf(listYtjt.get(0).getSdate()).getTime()) / (1000 * 3600 * 24));
		mapFee2.put("days", String.valueOf(days2));
		mapFee2.put("money", String.valueOf(fees.get(1).getMoney()));
		mapFee2.put("monSum", String.valueOf(fees.get(1).getMoney() * days2));
		listFees.add(mapFee2);
		return listFees;
	}

	/**
	 * 根据ClBxd报销单类得到Excel中其他部分的数据
	 *
	 * @param clBxd
	 *            报销单类
	 * @return 返回List<Map<String, String>>类型的集合
	 */
	public List<Map<String, String>> getClQt(ClBxd clBxd)
	{
		List<Map<String, String>> listOths = new ArrayList<Map<String, String>>();// 除远途交通的其他费用
		// 住宿费
		Map<String, String> mapZs = new HashMap<String, String>();
		double moneyZs = 0;
		for (ClZs clZs : clBxd.getZs())
		{
			moneyZs += clZs.getMoney();
		}
		mapZs.put("name", "住宿费");
		mapZs.put("money", String.valueOf(moneyZs));
		listOths.add(mapZs);
		// 市内交通
		Map<String, String> mapSnjt = new HashMap<String, String>();
		double moneySnjt = 0;
		for (ClSnjt snjt : clBxd.getSnjt())
		{
			moneySnjt += snjt.getMoney();
		}
		mapSnjt.put("name", "市内交通");
		mapSnjt.put("money", String.valueOf(moneySnjt));
		listOths.add(mapSnjt);
		// 其他
		for (ClQt clQt : clBxd.getQt())
		{
			Map<String, String> mapQt = new HashMap<String, String>();
			mapQt.put("name", clQt.getPopse());
			mapQt.put("money", String.valueOf(clQt.getMoney()));
			listOths.add(mapQt);
		}
		return listOths;
	}

	/**
	 * 根据 apache/poi 生成相应的报销Excel表
	 * 
	 * @param bxdDate
	 *            报销单日期
	 * @param name
	 *            出差人
	 * @param reason
	 *            事由
	 * @param listYtjt
	 *            远途交通
	 * @param listFees
	 *            出差补贴
	 * @param listOths
	 *            其他
	 * @param inFileStr
	 *            读取已经做好的excel报销单模板及路径
	 * @param ouFileStr
	 *            输出新生成的报销单文件及路径
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void createExcel(String bxdDate, String name, String reason, List<ClYtjt> listYtjt,
			List<Map<String, String>> listFees, List<Map<String, String>> listOths, String inFileStr, String ouFileStr)
			throws FileNotFoundException, IOException
	{
		// 创建对 Excel 工作簿文件（Workbook）的引用
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inFileStr));
		// 在 listYtjt 、listClBt 和 listOths 三个集合中取 size 最大的值
		int maxListSize = (listYtjt.size() > listFees.size() ? listYtjt.size() : listFees.size()) > listOths.size()
				? (listYtjt.size() > listFees.size() ? listYtjt.size() : listFees.size()) : listOths.size();
		// 初始化 sheets
		for (int i = 0; i < maxListSize / 6; i++)
		{
			if (maxListSize % 6 == 0 && maxListSize / 6 - 1 == i)
				break;
			// 克隆模板的sheet
			workbook.cloneSheet(0);
		}
		for (int i = 0; i < maxListSize / 6 + 1; i++)
		{
			// 创建对 sheet 的引用
			XSSFSheet sheet = workbook.getSheetAt(i);
			// 报销单----时间：年 月 日
			XSSFRow row3 = sheet.getRow(3);
			row3.getCell(7).setCellValue(bxdDate.substring(0, 4));
			row3.getCell(9).setCellValue(bxdDate.substring(5, 7));
			row3.getCell(11).setCellValue(bxdDate.substring(8, 10));
			// 报销单----出差人和事由
			XSSFRow row4 = sheet.getRow(4);
			row4.getCell(4).setCellValue(name);
			row4.getCell(10).setCellValue(reason);
			// 报销单中间部分
			for (int count = 0; count < 6; count++)
			{
				// 报销单----远途交通
				if (!listYtjt.isEmpty())
				{
					ClYtjt clYtjt = listYtjt.get(0);
					XSSFRow rowYtjt = sheet.getRow(count + 7);
					rowYtjt.getCell(1).setCellValue(clYtjt.getSdate().substring(5, 7));// 月
					rowYtjt.getCell(2).setCellValue(clYtjt.getSdate().substring(8, 10));// 日
					rowYtjt.getCell(3).setCellValue(clYtjt.getSpoint());// 起点
					rowYtjt.getCell(4).setCellValue(clYtjt.getEdate().substring(5, 7));// 月
					rowYtjt.getCell(5).setCellValue(clYtjt.getEdate().substring(8, 10));// 日
					rowYtjt.getCell(6).setCellValue(clYtjt.getEpoint());// 终点
					rowYtjt.getCell(7).setCellValue(clYtjt.getTrfct()); // 交通工具
					rowYtjt.getCell(8).setCellValue(clYtjt.getTcktno());// 单据张数
					rowYtjt.getCell(9).setCellValue(clYtjt.getMoney());// 金额
					listYtjt.remove(0);
				}
				// 报销单----出差补贴（未写）
				if (!listFees.isEmpty())
				{
					Map<String, String> mapFee = listFees.get(0);
					XSSFRow rowFee = sheet.getRow(count + 7);
					rowFee.getCell(10).setCellValue(mapFee.get("pro"));// 项目
					rowFee.getCell(11).setCellValue(mapFee.get("pers"));// 人数
					rowFee.getCell(12).setCellValue(mapFee.get("days"));// 天数
					rowFee.getCell(13).setCellValue(mapFee.get("money"));// 补贴标准
					rowFee.getCell(14).setCellValue(mapFee.get("monSum"));// 金额
					listFees.remove(0);
				}
				// 报销单----其他
				if (!listOths.isEmpty())
				{
					Map<String, String> mapOth = listOths.get(0);
					XSSFRow rowOth = sheet.getRow(count + 7);
					rowOth.getCell(16).setCellValue(mapOth.get("name"));
					rowOth.getCell(17).setCellValue(mapOth.get("money"));
					listOths.remove(0);
				}
			}
			// 判断是不是最后一张差旅费报销单,从而是否需要填写合计（大写）、预支旅费、退回金额和补领金额
			boolean flag = false;
			if ((maxListSize % 6 == 0 && maxListSize / 6 - 1 == i) || maxListSize / 6 == i)
			{
				flag = !flag;
				// 最后的差旅费报销单上打印单尾

			}
			if (flag)
			{
				break;
			}
		}
		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(ouFileStr + "-" + name + ".xlsx");
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
	}

	public static void main(String[] args)
	{
		String jsonStr = "{'id':41,'bxdate':'2017-07-11','hrId':'1','name':'张三','position':'程序员','department':'IT','fno':2225,'fdbk':'','telext':5589,'alp':'','reason':'某某公司盛情相约','cstbl':'','ytjt':[{'id':21,'instrct':'','money':455,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-06-25 00:00:00','spoint':'汉口','edate':'2017-06-26 00:00:00','epoint':'北京'},{'id':22,'instrct':'','money':458,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-06-27 00:00:00','spoint':'北京','edate':'2017-06-28 00:00:00','epoint':'上海'},{'id':23,'instrct':'','money':399,'id0':41,'trfct':'交通工具2','tcktno':1,'sdate':'2017-06-29 00:00:00','spoint':'上海','edate':'2017-06-30 00:00:00','epoint':'深圳'},{'id':24,'instrct':'','money':264,'id0':41,'trfct':'交通工具2','tcktno':1,'sdate':'2017-07-01 00:00:00','spoint':'深圳','edate':'2017-07-02 00:00:00','epoint':'香港'},{'id':25,'instrct':'','money':1655,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-03 00:00:00','spoint':'香港','edate':'2017-07-04 00:00:00','epoint':'新加坡'},{'id':26,'instrct':'','money':2896,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-05 00:00:00','spoint':'新加坡','edate':'2017-07-06 00:00:00','epoint':'纽约'},{'id':27,'instrct':'','money':3547,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-07 00:00:00','spoint':'纽约','edate':'2017-07-08 00:00:00','epoint':'北京'},{'id':28,'instrct':'','money':558,'id0':41,'trfct':'交通工具1','tcktno':1,'sdate':'2017-07-08 00:00:00','spoint':'北京','edate':'2017-07-09 00:00:00','epoint':'武昌'}],'zs':[{'id':21,'edate':'2017-06-27 00:00:00','sdate':'2017-06-26 00:00:00','id0':41,'point':'北京国际大酒店','reimbw':1,'tcktno':1,'money':326,'instrct':''},{'id':22,'edate':'2017-06-29 00:00:00','sdate':'2017-06-28 00:00:00','id0':41,'point':'上海商务大酒店','reimbw':2,'tcktno':1,'money':496,'instrct':''},{'id':23,'edate':'2017-05-31 00:00:00','sdate':'2017-06-30 00:00:00','id0':41,'point':'深圳A区酒店','reimbw':1,'tcktno':1,'money':476,'instrct':''},{'id':24,'edate':'2017-07-03 00:00:00','sdate':'2017-07-02 00:00:00','id0':41,'point':'香港','reimbw':1,'tcktno':1,'money':469,'instrct':''},{'id':25,'edate':'2017-07-05 00:00:00','sdate':'2017-07-04 00:00:00','id0':41,'point':'新加坡','reimbw':2,'tcktno':1,'money':346,'instrct':''},{'id':26,'edate':'2017-07-08 00:00:00','sdate':'2017-07-06 00:00:00','id0':41,'point':'纽约','reimbw':2,'tcktno':1,'money':627,'instrct':''},{'id':27,'edate':'2017-07-10 00:00:00','sdate':'2017-07-08 00:00:00','id0':41,'point':'北京','reimbw':1,'tcktno':1,'money':376,'instrct':''}],'snjt':[{'id':21,'id0':41,'sdate':'2017-06-26 00:00:00','spoint':'1','epoint':'2','money':47.5,'tcktno':3,'trfct':'的士'},{'id':22,'id0':41,'sdate':'2017-06-29 00:00:00','spoint':'1','epoint':'2','money':55,'tcktno':25,'trfct':'公交车'}],'qt':[{'id':21,'money':126,'bz':'','tcktno':6,'popse':'过路费','id0':41},{'id':22,'money':34,'bz':'','tcktno':1,'popse':'保险','id0':41}],'fee':[{'id':1,'money':30,'pro':'出差'},{'id':2,'money':20,'pro':'夜补'}]}";
		String inFileStr = "C:\\Users\\lc\\Desktop\\ClBxdDefault.xlsx";
		String ouFileStr = "C:\\Users\\lc\\Desktop\\clbxd";
		XSSFExcel.run(jsonStr, inFileStr, ouFileStr);
	}
}
