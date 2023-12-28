package com.ilp.entity;

import java.util.ArrayList;

public class SavingsMaxAccount extends Product {
	private double minBalance;
	
	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		this.minBalance = 1000;
		// TODO Auto-generated constructor stub
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	@Override
	public void withdrawMoney(Account account, double withdrawMoney) {
		// TODO Auto-generated method stub
		
		if(account.getAccountBalance()-withdrawMoney > this.minBalance) {
			
			account.setAccountBalance(account.getAccountBalance() - withdrawMoney);
			System.out.println("Current Balance: "+account.getAccountBalance());
			
		}else {
			
			System.out.println("\nSorry, Minimum Balance of Rs"+this.minBalance+" must be maintained");
			
		}
		
	}

	@Override
	public void depositMoney(Account account, double depositMoney) {
		// TODO Auto-generated method stub
		account.setAccountBalance(depositMoney+account.getAccountBalance());
		System.out.println("Current Balance: "+account.getAccountBalance());
		
	}

	

	
	
	
}
