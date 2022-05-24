package com.dbms.BankingApplicationSystem;

import java.time.LocalTime;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BankService {

    static SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	static Session session=factory.openSession();
	static Transaction tx=session.beginTransaction();

	static Scanner sc = new Scanner(System.in);
	
	public static Bank checkExistingAccount(int accountno) {
		Bank b =session.get(Bank.class, accountno);
		try {
			if(b!=null) {
				throw new AccountExistException("Account number already exit!");
			}
		}catch (AccountExistException e) {
			System.out.println(e.getMessage());
		}
		return b;
	}
   	
	public static Bank checkAccount(int accno) {
		
		Bank bk =session.find(Bank.class, accno);
		
		try {
			if(bk==null) {
				throw new AccountNotFoundException("Account number not found!");
			}
		}catch (AccountNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return bk;
	}

	String name; 
	long withdraw,accbalance,damnt; 
	int accno,choice; 
	String holderName; 
	
	public void withdraw(long wamount,Bank bank) 
			throws InsufficientBalanceException,MinimumBalanceException //20000
	{		
		try {
		
		if(wamount<bank.accbalance)  //if((wamount<accbalance) && (wamount>=500))
		{
			try {
			if(wamount>=500) {
			
		bank.accbalance=bank.accbalance-wamount;  //10000-5000=5000
		System.out.println(wamount +" withdraw has done successfully");
			
			}
			else  
			{
				
				throw new MinimumBalanceException("Minimum withdraw amount 500");
			}
			
			}catch (MinimumBalanceException  me) {
				System.out.println(me.getMessage());
			}
			
		}
		else
		{
			throw new InsufficientBalanceException("Insufficient account balance");
		}
		}catch(InsufficientBalanceException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	public void deposit(long damnt,Bank bank)
	{
		bank.accbalance=bank.accbalance+damnt;
		System.out.println("deposit is completed");
	}


	public void addAccount() {
		System.out.println("Enter Account number: ");
		accno=sc.nextInt();
		Bank ca=checkExistingAccount(accno);
		if(ca==null) {
			sc.nextLine();
			System.out.println("Enter Account holder name: ");
			holderName=sc.nextLine();
			System.out.println("Enter Account balance: ");
			accbalance=sc.nextLong();
			LocalTime lt = LocalTime.now();
			System.out.println(lt); 
			session.save(new Bank(accno,holderName,accbalance));
			tx.commit();
			System.out.println("Account has been created!");
		}
		System.out.println("===================================================================="); 
		
	}

	public void withdrawAmount() throws Exception {
		System.out.println("Enter account number : ");
		accno=sc.nextInt();
		Bank ban=checkAccount(accno);
				
    		if(ban!=null) {
    			System.out.println("Enter Withdrawal amount: ");
    			withdraw=sc.nextLong();		    			
				withdraw(withdraw, ban);
    			tx.commit();
    		}
		System.out.println("===================================================================="); 
		
	}

	public void depositAmount() {
		System.out.println("Enter account number : ");
		accno=sc.nextInt();
		Bank bn=checkAccount(accno);
			if(bn!=null) {
				System.out.println("Enter deposit amount: ");
				damnt=sc.nextLong();	
					deposit(damnt,bn);					
				tx.commit();
			}
			System.out.println("===================================================================="); 
		
	}

	public void checkBalance() {
		System.out.println("Enter account number : ");
		accno=sc.nextInt();
		Bank bn1 =checkAccount(accno);
		
		if(bn1!=null) {
			Bank check =session.get(Bank.class, accno);
			System.out.println("Mr/Ms : "+check.getHolderName()+", account balance is : "+check.getAccbalance());			
		}
		System.out.println("===================================================================="); 
		
	}

	public void updateAccount() {
		System.out.println("Enter account number : ");
		accno=sc.nextInt();
		Bank bn2 =checkAccount(accno);

		if(bn2!=null) {
			System.out.println("Enter Account holder name: ");
			name=sc.nextLine();
			bn2.setHolderName(name);		
			session.update(bn2);
			tx.commit();
			System.out.println("Your Account has been Updated!");
		}
		System.out.println("===================================================================="); 
		
	}

	public void deleteAccount() {
		System.out.println("Enter account number : ");
		accno=sc.nextInt();
		Bank bn3 =checkAccount(accno);

		if(bn3!=null) {
			session.delete(bn3);
			tx.commit();
			System.out.println("Your Account has been Deleted!");
		}
		System.out.println("===================================================================="); 
		
	}	
		
	}

