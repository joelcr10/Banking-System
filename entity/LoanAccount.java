package com.ilp.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class LoanAccount extends Product{

	private double chequeDeposit;
	
	

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		this.chequeDeposit = 0.03;
	}
	
	public double getChequeDeposit() {
		return chequeDeposit;
	}

	public void setChequeDeposit(double chequeDeposit) {
		this.chequeDeposit = chequeDeposit;
	}

	@Override
	public void withdrawMoney(Account account, double withdrawMoney) {
		// TODO Auto-generated method stub
		System.out.println("Oops, can't withdraw money from Loan Account");
		if(withdrawMoney<account.getAccountBalance()) {
			account.setAccountBalance(account.getAccountBalance() - withdrawMoney);
			System.out.println("Current Balance: "+account.getAccountBalance());
		}else {
			System.out.println("Insufficient Balance");
		}
		
	}

	@Override
	public void depositMoney(Account account, double depositMoney) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\n1. Cash Deposit \n2. Cheque Deposit");
		
		System.out.print("Select deposit type: ");
		int depositType = scanner.nextInt();
		switch(depositType) {
		case 1: System.out.println("\n\nYou selected Cash Deposit");
				break;
		case 2: System.out.println("\n\nYou selected Cheque Deposit");
		
				
				
				depositMoney = depositMoney - (depositMoney* this.chequeDeposit);
				
				System.out.println("Cheque Deposit charges 0.3% so final deposit is: "+ depositMoney);
				break;
		}
		
		account.setAccountBalance(depositMoney+account.getAccountBalance());
		System.out.println("Current Balance: "+account.getAccountBalance());
		
	}
	
}
