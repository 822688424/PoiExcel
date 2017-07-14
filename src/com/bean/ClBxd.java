package com.bean;

import java.util.List;

public class ClBxd
{
	private int id; /* id */
	private String bxdate; /* 时间 */
	private String hrid; /* 员工ID */
	private String name; /* 姓名 */
	private String position; /* 职务 */
	private String department; /* 部门 */
	private int fno; /* 财务编号 */
	private int telext; /* 分机号 */
	private String reason; /* 出差事由 */
	private String cstbl; /* 费用归属 */
	private String alp; /* 同行人员 */
	private String fdbk; /* 出差反馈 */

	private List<ClYtjt> ytjt;
	private List<ClZs> zs;
	private List<ClSnjt> snjt;
	private List<ClQt> qt;
	private List<ClFee> fee;

	public ClBxd()
	{
		super();
	}

	public ClBxd(int id, String bxdate, String hrid, String name, String position, String department, int fno,
			int telext, String reason, String cstbl, String alp, String fdbk, List<ClYtjt> ytjt, List<ClZs> zs,
			List<ClSnjt> snjt, List<ClQt> qt, List<ClFee> fee)
	{
		super();
		this.id = id;
		this.bxdate = bxdate;
		this.hrid = hrid;
		this.name = name;
		this.position = position;
		this.department = department;
		this.fno = fno;
		this.telext = telext;
		this.reason = reason;
		this.cstbl = cstbl;
		this.alp = alp;
		this.fdbk = fdbk;
		this.ytjt = ytjt;
		this.zs = zs;
		this.snjt = snjt;
		this.qt = qt;
		this.fee = fee;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBxdate()
	{
		return bxdate;
	}

	public void setBxdate(String bxdate)
	{
		this.bxdate = bxdate;
	}

	public String getHrid()
	{
		return hrid;
	}

	public void setHrid(String hrid)
	{
		this.hrid = hrid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public int getFno()
	{
		return fno;
	}

	public void setFno(int fno)
	{
		this.fno = fno;
	}

	public int getTelext()
	{
		return telext;
	}

	public void setTelext(int telext)
	{
		this.telext = telext;
	}

	public String getReason()
	{
		return reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}

	public String getCstbl()
	{
		return cstbl;
	}

	public void setCstbl(String cstbl)
	{
		this.cstbl = cstbl;
	}

	public String getAlp()
	{
		return alp;
	}

	public void setAlp(String alp)
	{
		this.alp = alp;
	}

	public String getFdbk()
	{
		return fdbk;
	}

	public void setFdbk(String fdbk)
	{
		this.fdbk = fdbk;
	}

	public List<ClYtjt> getYtjt()
	{
		return ytjt;
	}

	public void setYtjt(List<ClYtjt> ytjt)
	{
		this.ytjt = ytjt;
	}

	public List<ClZs> getZs()
	{
		return zs;
	}

	public void setZs(List<ClZs> zs)
	{
		this.zs = zs;
	}

	public List<ClSnjt> getSnjt()
	{
		return snjt;
	}

	public void setSnjt(List<ClSnjt> snjt)
	{
		this.snjt = snjt;
	}

	public List<ClQt> getQt()
	{
		return qt;
	}

	public void setQt(List<ClQt> qt)
	{
		this.qt = qt;
	}

	public List<ClFee> getFee()
	{
		return fee;
	}

	public void setFee(List<ClFee> fee)
	{
		this.fee = fee;
	}

	@Override
	public String toString()
	{
		return "ClBxd [id=" + id + ", bxdate=" + bxdate + ", hrid=" + hrid + ", name=" + name + ", position=" + position
				+ ", department=" + department + ", fno=" + fno + ", telext=" + telext + ", reason=" + reason
				+ ", cstbl=" + cstbl + ", alp=" + alp + ", fdbk=" + fdbk + ", ytjt=" + ytjt + ", zs=" + zs + ", snjt="
				+ snjt + ", qt=" + qt + ", fee=" + fee + "]";
	}

}
