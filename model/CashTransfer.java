package com.ilp.model;

import com.ilp.entity.Account;
//import com.ilp.entity.Customer;

public interface CashTransfer {
	public void withdrawMoney(Account account,double withdrawMoney);
	public void depositMoney(Account account, double depositMoney);
	
}
