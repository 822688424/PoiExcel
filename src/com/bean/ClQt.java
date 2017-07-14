package com.bean;

public class ClQt
{
	private int id;
	private int id0;
	private String popse; /* 用途 */
	private int tcktno; /* 票据张数 */
	private double money; /* 金额(元) */
	private String bz; /* 备注 */

	public ClQt()
	{
		super();
	}

	public ClQt(int id, int id0, String popse, int tcktno, double money, String bz)
	{
		super();
		this.id = id;
		this.id0 = id0;
		this.popse = popse;
		this.tcktno = tcktno;
		this.money = money;
		this.bz = bz;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId0()
	{
		return id0;
	}

	public void setId0(int id0)
	{
		this.id0 = id0;
	}

	public String getPopse()
	{
		return popse;
	}

	public void setPopse(String popse)
	{
		this.popse = popse;
	}

	public int getTcktno()
	{
		return tcktno;
	}

	public void setTcktno(int tcktno)
	{
		this.tcktno = tcktno;
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public String getBz()
	{
		return bz;
	}

	public void setBz(String bz)
	{
		this.bz = bz;
	}

	@Override
	public String toString()
	{
		return "ClQt [id=" + id + ", id0=" + id0 + ", popse=" + popse + ", tcktno=" + tcktno + ", money=" + money
				+ ", bz=" + bz + "]";
	}

}
