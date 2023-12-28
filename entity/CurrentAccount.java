package com.ilp.entity;

import java.util.ArrayList;

public class CurrentAccount extends Product {
//	private String depositType;
	
	public CurrentAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void withdrawMoney(Account account, double withdrawMoney) {
		// TODO Auto-generated method stub
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
		account.setAccountBalance(depositMoney+account.getAccountBalance());
		System.out.println("Current Balance: "+account.getAccountBalance());
	}



	
	
}
