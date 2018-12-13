package com.cg.kyc.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.kyc.dto.Customer;
import com.cg.kyc.dto.PrintTransactions;
import com.cg.kyc.exception.MyException;
import com.cg.kyc.service.BankServiceImpl;
import com.cg.kyc.service.IBankService;

public class Client {
	static IBankService cliSer = null;
	static Scanner sc = null;

	public static void main(String[] args) throws MyException {

		sc = new Scanner(System.in);
		while (true) {
			System.out.println("1.create account");
			System.out.println("2.show balance");
			System.out.println("3. Fund Transfer");
			System.out.println("4. Deposit Amount");
			System.out.println("5. Withdraw Amount");
			System.out.println("6.print transactions");
			System.out.println("7. Exit");
			System.out.println("enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				createAccount();
				break;
			case 2:
				showBalance();
				break;
			case 3:
				fundTransfer();
				break;
			case 4:
				depositAmount();
				break;
			case 5:
				withdrawAmount();
				break;
			case 6:
				printTransactions();
				break;
			case 7:
				exit();
				break;
			}

		}

	}

	private static void printTransactions() {
		// TODO Auto-generated method stub
		String mobNo = null;
		cliSer = new BankServiceImpl();
		do{
			System.out.println("printing transactions for the given moobileno");
			mobNo = sc.next();
			try {
				if(cliSer.validatePhoneNo(mobNo))
					break;
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		
		List<PrintTransactions> transactionList = null;
		try {
			if (cliSer.validatePhoneNo(mobNo)) {
				transactionList = cliSer.getTransactionList(mobNo);
				for (PrintTransactions printTransactions : transactionList) {
					System.out.println(printTransactions);
				}
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

	private static void exit() {
		// TODO Auto-generated method stub
		System.exit(0);

	}

	private static void withdrawAmount() {
		// TODO Auto-generated method stub
		cliSer = new BankServiceImpl();
		String mobno = null;
		double amt = 0;
		do {
			System.out.println("enter mobile number");
			mobno = sc.next();
			try {
				if (cliSer.validatePhoneNo(mobno)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		} while (true);
		do {
			System.out.println("enter amount to be withdrawn");
			amt = sc.nextDouble();
			try {
				if (cliSer.validateAmount(amt)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		} while (true);

		// Customer cWithDraw = null;
		Customer cWithDraw = null;
		try {
			cWithDraw = cliSer.withdrawAmount(mobno, amt);
			System.out.println("remaining amount is:" + cWithDraw.getAmount());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		//System.out.println("remaining amount is:" + cWithDraw.getAmount());
	}

	private static void depositAmount()  {
		// TODO Auto-generated method stub
		String mobno = null;
		cliSer = new BankServiceImpl();
		double amt = 0;
		do{
			System.out.println("enter mobile number");
			mobno = sc.next();
			try {
				if (cliSer.validatePhoneNo(mobno)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		do{
			System.out.println("enter amount to be deposited");
			amt = sc.nextDouble();
			try {
				if (cliSer.validateAmount(amt)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		
		Customer cDepAmt = null;
		try {
			cDepAmt = cliSer.depositAmount(mobno, amt);
			System.out.println("your cuurent  amount is :" + cDepAmt.getAmount());
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		//System.out.println("your cuurent  amount is :" + cDepAmt.getAmount());
	}

	private static void fundTransfer(){
		// TODO Auto-generated method stub
		String sourceMobNo = null;
		double amount = 0;
		String destinationMobNo =null;
		cliSer = new BankServiceImpl();
		do{
			System.out.println("enter source mobile no");
			sourceMobNo = sc.next();
			try {
				if (cliSer.validatePhoneNo(sourceMobNo)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		do{
			System.out.println("enter target mobile no");
			destinationMobNo = sc.next();
			try {
				if (cliSer.validatePhoneNo(destinationMobNo)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		do{
			System.out.println("enter the amount to be transfer");
			amount = sc.nextDouble();
			try {
				if (cliSer.validateAmount(amount)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		
		Customer funds = null;
		try {
			funds = cliSer.fundTransfer(sourceMobNo, destinationMobNo, amount);
			System.out.println("amount rs" + amount + "transferred to "
					+ destinationMobNo + "available balance is"
					+ funds.getAmount());

		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		/*System.out
				.println("amount rs" + amount + "transferred to "
						+ destinationMobNo + "available balance is"
						+ funds.getAmount());*/
	}

	private static void showBalance() {
		// TODO Auto-generated method stub
		cliSer = new BankServiceImpl();
		double bal = 0;
		String mobNo = null;
		do{
			System.out.println("enter mobile number present in map");
			mobNo = sc.next();
			try {
				if(cliSer.validatePhoneNo(mobNo))
					break;
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
			try {
				bal = cliSer.showBalance(mobNo);
				System.out.println("balance available is " + bal);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		//	System.out.println("balance available is " + bal);
		
	}

	private static void createAccount() {
		// TODO Auto-generated method stub
		String name = null;
		String mobNo = null;
		double amount = 0;
		cliSer = new BankServiceImpl();
		do{
			System.out.println("enter customer name");
			name = sc.next();
			try {
				if(cliSer.validateName(name))
					break;
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		do{
			System.out.println("enter mobile no");
			mobNo = sc.next();
			try {
				if (cliSer.validatePhoneNo(mobNo)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		do{
			System.out.println("enter amount");
			amount = sc.nextDouble();
			try {
				if (cliSer.validateAmount(amount)) {
					break;
				}
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}while(true);
		
		Customer cust = new Customer(name, mobNo, amount);
		Customer c1 = null;
		try {
			c1 = cliSer.createAccount(cust);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		System.out.println("account created for:" + c1.getCustName()
				+ "having mobile no as " + c1.getMobileNo());
	}
}