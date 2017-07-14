package cx.oa.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excel.EcExcel;

import cx.oa.services.ServiceXssfExcel;

@WebServlet("/ServletGisOa")
public class ServletGisOa extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		// 设置响应内容类型
		// response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/x-json");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String cmd = request.getParameter("cmd"); // 命令
		String callback = request.getParameter("callback");

		String jsonStr;
		String inFileStr;
		String ouFileStr;
		try
		{
			switch (cmd)
			{
			case "clbx":
				jsonStr = request.getParameter("data"); // json字符串
				inFileStr = getServletContext().getRealPath("\\excel-default\\bxd_bt.xlsx");// 输入文件路径
				ouFileStr = getServletContext().getRealPath("\\excel-out") + "\\" + "bxd_bt.xlsx"; // 输出文件路径
				ServiceXssfExcel.run(jsonStr, inFileStr, ouFileStr);
				out.println(callback + "({'filename':'http://192.168.2.101:8001/Excel/excel-out/bxd_bt.xlsx'})");
				System.out.println(callback);
				break;
			case "ecbx":
				jsonStr = request.getParameter("data"); // json字符串
				inFileStr = getServletContext().getRealPath("\\excel-default\\ecbxd.xlsx");// 输入文件路径
				ouFileStr = getServletContext().getRealPath("\\excel-out") + "\\" + "ecbxd.xlsx"; // 输出文件路径
				EcExcel ecExcel = new EcExcel();
				ecExcel.export(jsonStr, inFileStr, ouFileStr);
				System.out.println(ouFileStr);
				out.println(callback + "({'filename':'http://192.168.2.101:8001/Excel/excel-out/ecbxd.xlsx'})");
				System.out.println(callback);
				break;

			default:
				break;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
