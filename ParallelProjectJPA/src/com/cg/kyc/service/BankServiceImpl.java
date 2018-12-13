package com.cg.kyc.service;



import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.kyc.dao.BankDaoImpl;
import com.cg.kyc.dao.IBankDao;
import com.cg.kyc.dto.Customer;
import com.cg.kyc.dto.PrintTransactions;
import com.cg.kyc.exception.MyException;

public class BankServiceImpl implements IBankService {
	   IBankDao cliDao = null;
       public BankServiceImpl(){
    	   cliDao = new BankDaoImpl();
       }
	
       @Override
	public Customer createAccount(Customer c) {
		// TODO Auto-generated method stub
		return cliDao.createAccount(c);
	}
	
       @Override
	public double showBalance(String mobileno) throws MyException {
		// TODO Auto-generated method stub
    	   String transactionType="check balance";
		Customer custBal = cliDao.getAccountDetails(mobileno);
		if (custBal == null)
			throw new MyException("entered mobile no doesnot exist please enter mobile number present in map or db");
		cliDao.loadTransactioninDB(new PrintTransactions(mobileno, transactionType, custBal.getAmount()));
		return custBal.getAmount();
		
	}
	
       @Override
	public Customer depositAmount(String mobileno, double amt)throws MyException {
		// TODO Auto-generated method stub
    	   String transactionType="deposit";
		Customer custDeposit = cliDao.getAccountDetails(mobileno);
		
		if(custDeposit==null)
			throw new MyException("enter mobile number present in map");
	   
		boolean b = cliDao.setAccountDetails(mobileno, custDeposit.getAmount() + amt);
	    cliDao.loadTransactioninDB(new PrintTransactions(mobileno, transactionType, amt));
	    if(!b)
		throw new MyException("unable to deposit the money");
		else
			return cliDao.getAccountDetails(mobileno);
			
	}
	
       @Override
	public Customer withdrawAmount(String mobno, double amount) throws MyException {
		// TODO Auto-generated method stub
    	   String transactionType="withdraw";
		
			Customer c = cliDao.getAccountDetails(mobno);
			if(c==null){
				throw new MyException("enter mobile number present in map");
			}
			double remBal = c.getAmount()-amount;
			
				try {
					if(remBal>=0){
						boolean b = cliDao.setAccountDetails(mobno, remBal);
						cliDao.loadTransactioninDB(new PrintTransactions(mobno, transactionType, amount));

					}
					else{
						throw new MyException("unable to withdraw money");
					}
				} catch (MyException e) {
					// TODO Auto-generated catch block
					System.out.println("entered amount should be less than existed amount");
				}
		
		
		
		return cliDao.getAccountDetails(mobno);
	}
	
       @Override
	public Customer fundTransfer(String sourceMobNo, String destMobNo, double amount) throws MyException {
		// TODO Auto-generated method stub
    	   String transactionType="transferMoney";
    	   Customer sourceMob = cliDao.getAccountDetails(sourceMobNo);
    	   Customer destMob = cliDao.getAccountDetails(destMobNo);
    	   
    	   if(sourceMob==null)
    	   {
    		   throw new MyException("enter valid source mobile number to transfer money");
    	   }
    	   if(destMob==null){
    		   throw new MyException("enter valid destination mobile number to transfer money");
    	   }
    	   cliDao.setAccountDetails(destMobNo, destMob.getAmount() + amount);
			cliDao.setAccountDetails(sourceMobNo, sourceMob.getAmount() - amount);
			cliDao.loadTransactioninDB(new PrintTransactions(sourceMobNo, transactionType, -amount));
			cliDao.loadTransactioninDB(new PrintTransactions(destMobNo, transactionType, amount));
		return cliDao.getAccountDetails(sourceMobNo);
	}
	
       @Override
	public boolean validateName(String name) throws MyException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]+");
		Matcher mat = p.matcher(name);
		boolean b = mat.matches();
		
		if(!b)
			throw new MyException("name should start with capital letter and should not start contain numerics ");
		return b;
		
	}
	
       @Override
	public boolean validatePhoneNo(String mobNo) throws MyException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("[7-9]{1}[0-9]{9}");
		Matcher mat = p.matcher(mobNo);
		boolean b = mat.matches();
		
		if(!b)
			throw new MyException("mobile number should start with {7,8,9}");
		
		return b;
		
	}
	
       @Override
	public boolean validateAmount(double amt) throws MyException {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("[1-9]{1}[0-9.]{0,9}");
		Matcher mat = p.matcher(String.valueOf(amt));
		boolean b = mat.matches();
		if(!b)
			throw new MyException("Invalid amount format");
		return b;
		
	}

	@Override
	public List<PrintTransactions> getTransactionList(String mobileNo)  {
		// TODO Auto-generated method stub
List<PrintTransactions> list = null;
		
		list = cliDao.getTransactions(mobileNo);
		
		return list;
		
	}
       
       
       
       
}
