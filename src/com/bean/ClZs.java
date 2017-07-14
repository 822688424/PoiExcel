package com.bean;

public class ClZs
{
	private int id;
	private int id0;
	private String sdate; /* 入住日期 */
	private String edate; /* 离开日期 */
	private String point; /* 地点 */
	private String reimbw; /* 报销方式 */
	private int tcktno; /* 票据张数 */
	private double money; /* 金额(元) */
	private String instrct; /* 超标说明 */

	public ClZs()
	{
		super();
	}

	public ClZs(int id, int id0, String sdate, String edate, String point, String reimbw, int tcktno,
			double money, String instrct)
	{
		super();
		this.id = id;
		this.id0 = id0;
		this.sdate = sdate;
		this.edate = edate;
		this.point = point;
		this.reimbw = reimbw;
		this.tcktno = tcktno;
		this.money = money;
		this.instrct = instrct;
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

	public String getSdate()
	{
		return sdate;
	}

	public void setSdate(String sdate)
	{
		this.sdate = sdate;
	}

	public String getEdate()
	{
		return edate;
	}

	public void setEdate(String edate)
	{
		this.edate = edate;
	}

	public String getPoint()
	{
		return point;
	}

	public void setPoint(String point)
	{
		this.point = point;
	}

	public String getReimbw()
	{
		return reimbw;
	}

	public void setReimbw(String reimbw)
	{
		this.reimbw = reimbw;
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

	public String getInstrct()
	{
		return instrct;
	}

	public void setInstrct(String instrct)
	{
		this.instrct = instrct;
	}

	@Override
	public String toString()
	{
		return "ClZs [id=" + id + ", id0=" + id0 + ", sdate=" + sdate + ", edate=" + edate + ", point=" + point
				+ ", reimbw=" + reimbw + ", tcktno=" + tcktno + ", money=" + money + ", instrct=" + instrct + "]";
	}
}
