package com.example.pojo;

public class Receiving {
	private String message;
	private String LineUserid;
	private Child child;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLineUserid() {
		return LineUserid;
	}

	public void setLineUserid(String lineUserid) {
		LineUserid = lineUserid;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return "Receiving [message=" + message + ", LineUserid=" + LineUserid + ", child=" + child + "]";
	}

}
