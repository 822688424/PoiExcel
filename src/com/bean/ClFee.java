package com.bean;

public class ClFee
{
	private int id;
	private String pro;// 项目
	private double money;// 补贴金额(元)

	public ClFee()
	{
		super();
	}

	public ClFee(int id, String pro, double money)
	{
		super();
		this.id = id;
		this.pro = pro;
		this.money = money;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getPro()
	{
		return pro;
	}

	public void setPro(String pro)
	{
		this.pro = pro;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	@Override
	public String toString()
	{
		return "ClFee [id=" + id + ", pro=" + pro + ", money=" + money + "]";
	}

}
