package com.cg.kyc.dto;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kycbank")
public class Customer {

	private String custName;
	@Id
	private String mobileNo;
	private double amount;
	
	
	public Customer(String custName, String mobileNo, double amount) {
		super();
		this.custName = custName;
		this.mobileNo = mobileNo;
		this.amount = amount;
	}
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Customer [custName=" + custName + ", mobileNo=" + mobileNo + ", amount=" + amount + "]";
	}
	
}
