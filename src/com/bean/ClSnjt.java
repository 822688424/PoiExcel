package com.bean;

public class ClSnjt
{
	private int id;
	private int id0;
	private String sdate; /* 日期 */
	private String spoint; /* 起点 */
	private String epoint; /* 终点 */
	private String trfct; /* 交通工具 */
	private int tcktno; /* 票据张数 */
	private double money; /* 金额(元) */

	public ClSnjt()
	{
		super();
	}

	public ClSnjt(int id, int id0, String sdate, String spoint, String epoint, String trfct, int tcktno,
			double money)
	{
		super();
		this.id = id;
		this.id0 = id0;
		this.sdate = sdate;
		this.spoint = spoint;
		this.epoint = epoint;
		this.trfct = trfct;
		this.tcktno = tcktno;
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

	public String getSpoint()
	{
		return spoint;
	}

	public void setSpoint(String spoint)
	{
		this.spoint = spoint;
	}

	public String getEpoint()
	{
		return epoint;
	}

	public void setEpoint(String epoint)
	{
		this.epoint = epoint;
	}

	public String getTrfct()
	{
		return trfct;
	}

	public void setTrfct(String trfct)
	{
		this.trfct = trfct;
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

	@Override
	public String toString()
	{
		return "ClSnjt [id=" + id + ", id0=" + id0 + ", sdate=" + sdate + ", spoint=" + spoint + ", epoint=" + epoint
				+ ", trfct=" + trfct + ", tcktno=" + tcktno + ", money=" + money + "]";
	}
	
}
