package com.cg.kyc.dao;



import java.util.List;

import java.util.Set;

import com.cg.kyc.dto.Customer;
import com.cg.kyc.dto.PrintTransactions;
import com.cg.kyc.exception.MyException;


public interface IBankDao {
	public Customer createAccount(Customer c);
	public Customer getAccountDetails(String mobileno);
	public boolean setAccountDetails(String mobileNo, double amount);
	public List<PrintTransactions> getTransactions(String mobileNo) ;
	public void loadTransactioninDB(PrintTransactions prt);
}
