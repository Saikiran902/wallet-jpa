package com.cg.kyc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cg.kyc.exception.MyException;
import com.cg.kyc.service.BankServiceImpl;

public class KycBankTest {

	@Test
	public void validateName_vn1() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(true,service.validateName("Saikiran"));
	}
	
	@Test
	public void validateName_vn2() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(false,service.validateName("saikiran"));
	}
	
	@Test
	public void validateName_vn3() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(false,service.validateName("sai123"));
	}
	
	@Test
	public void validateAmount_va1() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(true,service.validateAmount(1000));
	}

	@Test
	public void validateAmount_va2() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(false,service.validateAmount(-300));
		assertEquals(false,service.validateAmount(0));
	}
	
	@Test
	public void validatePhoneNo_vphno1() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(true,service.validatePhoneNo("9949003012"));
	}

	@Test
	public void validatePhoneNo_vphno2() throws MyException{
		BankServiceImpl service = new BankServiceImpl();
		assertEquals(false,service.validatePhoneNo("994900301"));
		assertEquals(false,service.validatePhoneNo("5478521363"));
		assertEquals(false,service.validatePhoneNo("9949@"));
	}
}
