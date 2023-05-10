package com.example.pojo;

public class Relationship {
    private Integer cId;

    private Integer pId;
    
    private Integer isdelete;

    
    
	

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	@Override
	public String toString() {
		return "Relationship [cId=" + cId + ", pId=" + pId + ", isdelete=" + isdelete + "]";
	}
}