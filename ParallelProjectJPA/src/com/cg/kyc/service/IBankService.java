package com.cg.kyc.service;

import java.util.List;

import com.cg.kyc.dto.Customer;
import com.cg.kyc.dto.PrintTransactions;
import com.cg.kyc.exception.MyException;

public interface IBankService {
   public Customer createAccount(Customer c)throws MyException;
   public double showBalance(String mobileno)throws MyException;
   public Customer depositAmount(String mobileno,double amount)throws MyException;
   public Customer withdrawAmount(String mobileno,double amount)throws MyException;
   public Customer fundTransfer(String sourceMobNo,String destMobNo,double amount)throws MyException;
   public boolean validateName(String name)throws MyException;
   public boolean validatePhoneNo(String mobNo) throws MyException;
   public boolean validateAmount(double amt)throws MyException;
   public List<PrintTransactions> getTransactionList(String mobileNo) throws MyException;
   
}
