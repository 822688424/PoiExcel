package com.bean;

import java.util.List;

public class EcBxd
{
	private int 			id;
	private String			bz;				// 备注
	private	int			 	hrId;			// 员工id
	private String 			dep;			// 部门
	private String 			bxdate;			// 日期
	private String 			ldsp;			// 领导审批
	private int 			tcktno;			// 单据及附件页数
	private List<EcPro> 	pros;			// 报销项目

	public EcBxd()
	{
		super();
	}

	public EcBxd(int id, String bz, int hrId, String dep, String bxdate, String ldsp, int tcktno, List<EcPro> pros)
	{
		super();
		this.id = id;
		this.bz = bz;
		this.hrId = hrId;
		this.dep = dep;
		this.bxdate = bxdate;
		this.ldsp = ldsp;
		this.tcktno = tcktno;
		this.pros = pros;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBz()
	{
		return bz;
	}

	public void setBz(String bz)
	{
		this.bz = bz;
	}

	public int getHrId()
	{
		return hrId;
	}

	public void setHrId(int hrId)
	{
		this.hrId = hrId;
	}

	public String getDep()
	{
		return dep;
	}

	public void setDep(String dep)
	{
		this.dep = dep;
	}

	public String getBxdate()
	{
		return bxdate;
	}

	public void setBxdate(String bxdate)
	{
		this.bxdate = bxdate;
	}

	public String getLdsp()
	{
		return ldsp;
	}

	public void setLdsp(String ldsp)
	{
		this.ldsp = ldsp;
	}

	public int getTcktno()
	{
		return tcktno;
	}

	public void setTcktno(int tcktno)
	{
		this.tcktno = tcktno;
	}

	public List<EcPro> getPros()
	{
		return pros;
	}

	public void setPros(List<EcPro> pros)
	{
		this.pros = pros;
	}

	@Override
	public String toString()
	{
		return "EcBxd [id=" + id + ", bz=" + bz + ", hrId=" + hrId + ", dep=" + dep + ", bxdate=" + bxdate + ", ldsp="
				+ ldsp + ", tcktno=" + tcktno + ", pros=" + pros + "]";
	}

}
