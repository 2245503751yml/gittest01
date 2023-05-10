package com.example.pojo;

public class Parent {
    private Integer id;

    private String name;

    private String lineUserid;

    private String lineId;

    @Override
	public String toString() {
		return "Parent [id=" + id + ", name=" + name + ", lineUserid=" + lineUserid + ", lineId=" + lineId + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLineUserid() {
        return lineUserid;
    }

    public void setLineUserid(String lineUserid) {
        this.lineUserid = lineUserid == null ? null : lineUserid.trim();
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId == null ? null : lineId.trim();
    }

    public Parent() {
	}
    
	public Parent(Integer id, String name, String lineUserid, String lineId) {
		this.id = id;
		this.name = name;
		this.lineUserid = lineUserid;
		this.lineId = lineId;
	}
    
}