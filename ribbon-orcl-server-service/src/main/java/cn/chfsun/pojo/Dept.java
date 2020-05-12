package cn.chfsun.pojo;

import java.io.Serializable;

public class Dept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7188813463738533377L;
	private Integer deptNo;
	private String deptName;
	private String localtion;

	public Dept(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocaltion() {
		return localtion;
	}

	public void setLocaltion(String localtion) {
		this.localtion = localtion;
	}

}