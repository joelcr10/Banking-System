package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Product;

public class AccountServices {
	public static Account createAccount(ArrayList<Product> productList) {
		
		Scanner scanner = new Scanner(System.in);
		
		Product product = null;
		
		//display the products to user
		for(Product productItem: productList) {
			System.out.println(productItem.getProductCode()+"\t"+productItem.getProductName());
			
		}
		//select the product from user
		System.out.print("\nSelect account code: ");

		String productCode = scanner.nextLine();
		
		for(Product productItem: productList) {

			if(productCode.equalsIgnoreCase(productItem.getProductCode())) {
				System.out.println("\n"+productItem.getProductName()+" selected");
				product = productItem;
			}
		}
		
	
		System.out.print("Enter Account Type: ");
		String accountType = scanner.nextLine();
		
		System.out.print("Enter Account Balance: ");
		double accountBalance = scanner.nextDouble();
		
		if(product.getProductName().equalsIgnoreCase("Savings Max Account")) {
			System.out.println("checking for Minimum Balance");
			while(accountBalance<1000) {
				System.out.println("Minimum Balance is Rs.1000");
				System.out.print("Enter Account Balance (again) : ");
				accountBalance = scanner.nextDouble();
			}
		}
		Account account = new Account(accountType, accountBalance, product);
//		System.out.println("account no: "+account.getAccountNo());
		
		return account;
	}
}
