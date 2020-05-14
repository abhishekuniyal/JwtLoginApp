package com.abhi.login.model;

public class CustomerDTO {

	private int custId;
	private String custName;

	public CustomerDTO(int custId, String custName) {
		super();
		this.custId = custId;
		this.custName = custName;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

}
