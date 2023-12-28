package com.ilp.entity;

import com.ilp.model.DisplayAccount;

public class Account implements DisplayAccount{
	private static int nextAccount = 1;
	private String accountNo;
	private String accountType;
	private double accountBalance;
	private Product product;
	
	public Account(String accountType, double accountBalance, Product product) {
		this.accountNo = "A00"+nextAccount;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.product = product;
		
		nextAccount++;
	}
	
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public void displayAccount(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("\t\t\t\t"+this.getProduct().getProductName());
		System.out.println("\nCustomerID"+"\t"+"CustomerName"+"\t"+"AccountType"+"\t\t"+"Balance");
		
		System.out.println(customer.getCustomerCode()+"\t\t"+customer.getCustomerName()+"\t\t"+this.getProduct().getProductName()+"\t\t"+String.valueOf(this.getAccountBalance()));
				
		System.out.println("\nServices Provided");
				
		for(Service service: this.getProduct().getServiceList()) {
					
			System.out.print(service.getServiceName()+", ");
			
		}
		System.out.println("\n");
		
	}
}
