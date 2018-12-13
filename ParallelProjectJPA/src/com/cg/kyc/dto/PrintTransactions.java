package com.cg.kyc.dto;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class PrintTransactions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tranId;
	private String mobNo;
	private String tranType;
	private double amount;
	private Date tranDate;
	
	public PrintTransactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrintTransactions(String mobNo, String tranType,
			double amount) {
		super();
		this.tranDate = new Date();
		this.mobNo = mobNo;
		this.tranType = tranType;
		this.amount = amount;
	}

	public Integer getTranId() {
		return tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Override
	public String toString() {
		return "PrintTransactions [tranId=" + tranId + ", mobNo=" + mobNo
				+ ", tranType=" + tranType + ", amount=" + amount
				+ ", tranDate=" + tranDate + "]";
	}
	
	
}
