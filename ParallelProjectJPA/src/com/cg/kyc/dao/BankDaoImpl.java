package com.cg.kyc.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cg.kyc.dto.Customer;
import com.cg.kyc.dto.PrintTransactions;
import com.cg.kyc.exception.MyException;


public class BankDaoImpl implements IBankDao {
  Map<String,Customer> custWallet;
  EntityManager em;
  
  public BankDaoImpl(){
	  custWallet = new HashMap<String,Customer>();

		/*custWallet.put("9949003012", new Customer("9949003012", "Saikiran", 10000));
		custWallet.put("9876543210", new Customer("9876543210", "Saiteja", 1000));
		custWallet.put("9949862592", new Customer("9949862592", "Mahesh", 5000));*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
		em = emf.createEntityManager();

  }

@Override
public Customer createAccount(Customer c) {
	// TODO Auto-generated method stub
	em.getTransaction().begin();
	em.persist(c);
	em.getTransaction().commit();
	return c;

	
	
	
}

@Override
public Customer getAccountDetails(String mobNo) {
	// TODO Auto-generated method stub
	em.getTransaction().begin();
	Customer c = em.find(Customer.class, mobNo);
	em.getTransaction().commit();
	return c;

}

@Override
public boolean setAccountDetails(String mobNo, double amt) {
	// TODO Auto-generated method stub
	em.getTransaction().begin();
	Customer c = em.find(Customer.class, mobNo);
	c.setAmount(amt);
	em.merge(c);
	em.getTransaction().commit();
	return true;

}

@Override
public List<PrintTransactions> getTransactions(String mobileNo)
		 {
	// TODO Auto-generated method stub
	List<PrintTransactions> transactionList = null;
	em.getTransaction().begin();
	Query query = em.createQuery("from PrintTransactions where mobNo = :mobno");
	query.setParameter("mobno", mobileNo);
	transactionList = query.getResultList();
	
	em.getTransaction().commit();
	return transactionList;
	
}

@Override
public void loadTransactioninDB(PrintTransactions prt) {
	// TODO Auto-generated method stub
	em.getTransaction().begin();
	em.persist(prt);
	em.getTransaction().commit();

	
}



  
}
