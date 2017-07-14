package com.bean;

public class EcPro
{
	private int 		id;			
	private double 		money;		// 金额
	private int 		id0;  		// id0
	private String 		pro;		// 报销项目
	private String 		abstr;		// 摘要

	public EcPro()
	{
		super();
	}

	public EcPro(int id, double money, int id0, String pro, String abstr)
	{
		super();
		this.id = id;
		this.money = money;
		this.id0 = id0;
		this.pro = pro;
		this.abstr = abstr;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public int getId0()
	{
		return id0;
	}

	public void setId0(int id0)
	{
		this.id0 = id0;
	}

	public String getPro()
	{
		return pro;
	}

	public void setPro(String pro)
	{
		this.pro = pro;
	}

	public String getAbstr()
	{
		return abstr;
	}

	public void setAbstr(String abstr)
	{
		this.abstr = abstr;
	}

	@Override
	public String toString()
	{
		return "EcPro [id=" + id + ", money=" + money + ", id0=" + id0 + ", pro=" + pro + ", abstr=" + abstr + "]";
	}

}
