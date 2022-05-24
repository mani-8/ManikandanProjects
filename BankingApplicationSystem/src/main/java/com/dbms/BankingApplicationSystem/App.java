package com.dbms.BankingApplicationSystem;

import java.time.LocalTime;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class App
{	
	static BankService b =new BankService();
	
	public static void main( String[] args ) throws Exception
    {
    	int choice;
    	Scanner sc =new Scanner(System.in);
    	
    	do {
	    	System.out.println("Press 1. for Add account\nPress 2. for withdraw amount\nPress 3. for deposite amount\nPress 4. for check balance\nPress 5. for update account\nPress 6. for delete account\nPress 7. for quit"); 
	    	System.out.println("===================================================================="); 
	    	System.out.println("Enter your choice!"); 
	    	choice=sc.nextInt(); 
	    	System.out.println("===================================================================="); 
	    	sc.close();
	    	
	    	switch(choice) {
	    	
	    	case 1: //Add account
	    		b.addAccount();
	    		break;
	    		
	    	case 2: //Withdrawal amount
	    		b.withdrawAmount();
	    		break;
	    		
	    	case 3: //Deposit amount
	    		b.depositAmount();
		    	break;
		    	
	    	case 4: //Check balance
	    		b.checkBalance();
	    	    break;
	    	    
	    	case 5:	// Update account
	    		b.updateAccount();
	    		break;
	    		
	    	case 6:	// Delete Account
	    		b.deleteAccount();
	    		break;
	    		
	    	case 7:
	    		System.out.println("Thank You Visit Again!");
	    		System.out.println("===================================================================="); 
	    		break;
	    	}
    	}while(choice!=7);
    }

}