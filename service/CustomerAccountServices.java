package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerAccountServices {

	public static Service createService() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter Service Code: ");
		String serviceCode = scanner.nextLine();
		
		System.out.print("Enter Service Name: ");
		String serviceName = scanner.nextLine();
		
		System.out.print("Enter Service Rate: ");
		double serviceRate = scanner.nextDouble();
		
		Service service = new Service(serviceCode, serviceName, serviceRate);
		
		System.out.println(serviceName+" Service Created");
		return service;
		
	}

	public static Product createProduct(ArrayList<Service> serviceList) {
		// TODO Auto-generated method 
		if(serviceList.size()<=0) {
			System.out.println("\nCreate Services First");
			return null;
		}
		Scanner scanner = new Scanner(System.in);
		
		Product product = null;
		
		System.out.print("Enter Product Code: ");
		String productCode = scanner.nextLine();
		
		String productName = "";
		
		System.out.println("\n\n1. Savings Max Account \n2. Current Account \n3. Loan Account");
		System.out.print("Select Product Name (options) : ");	
		int selectProduct = scanner.nextInt();
		
		switch(selectProduct) {
			case 1: productName = "Savings Max Account";
					break;
			case 2: productName = "Current Account";
				break;
			case 3: productName = "Loan Account";
				break;
			default: System.out.println("Invalid product selected! \n\nGo back to main menu and try again");
						return null;
		}
		
	
		ArrayList<Service> defaultServiceList = new ArrayList<Service>();
		
//		creating a copy of service list
		ArrayList<Service> tempServiceList =  new ArrayList<Service>();
		for(Service service: serviceList) {
			tempServiceList.add(service);
		}
		
		char serviceChoice = 'y';
		
		do {
			
			System.out.println("Service Code\tService Name");
			for(Service service: serviceList) {
				System.out.println(service.getServiceCode()+"\t\t"+service.getServiceName());
				tempServiceList.add(service);
			}
			System.out.print("\nSelect Service Code : ");
			scanner.nextLine();
			String selectedService = scanner.nextLine();
			
			boolean checkSelectedService = true;
			
			for(Service service:defaultServiceList) {
				if(selectedService.equalsIgnoreCase(service.getServiceCode())) {
					System.out.println("Service already exists in that product");
					checkSelectedService = false;
				}
			}
			
			if(checkSelectedService) {
				System.out.println("Inside check selected service");
				for(Service service: serviceList) {
					if(selectedService.equalsIgnoreCase(service.getServiceCode())) {
						defaultServiceList.add(service);
						System.out.println(service.getServiceName()+" added");
					}
				}
			}
			
			
			System.out.print("\n\nDo you want to add more services (y/n): ");
			serviceChoice = scanner.nextLine().charAt(0);
		
		}while(serviceChoice=='y' || serviceChoice=='Y');
		
		if(productName == "Savings Max Account") {
			SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCode, productName, defaultServiceList);
			product = savingsMaxAccount;
			
		}else if(productName == "Current Account") {
			CurrentAccount currentAccount = new CurrentAccount(productCode, productName, defaultServiceList);
			product =  currentAccount;
		}else {
			LoanAccount loanAccount = new LoanAccount(productCode,productName,defaultServiceList);
			product =  loanAccount;
		}
		
		System.out.println("Product Code: "+productCode);
		System.out.println("Product Name: "+productName);
		System.out.println("Services available");
		for(Service service: defaultServiceList) {
			System.out.println(service.getServiceName());
		}
		return product;
		
	}

	public static Customer createCustomer(ArrayList<Product> productList) {
		if(productList.size()<=0) {
			System.out.println("Create Products first");
			return null;
		}
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Customer Code: ");
		String customerCode = scanner.nextLine();
		
		System.out.println("Enter Customer Name: ");
		String customerName = scanner.nextLine();
		
		ArrayList<Account> accountList = new ArrayList<Account>();
	
		char accountChoice='y';
		
		do {
			
			Account accountDemo = AccountServices.createAccount(productList);
			accountList.add(accountDemo);
			
			
			System.out.println("Do you want to add more account (y/n): ");
			scanner.nextLine();
			accountChoice = scanner.nextLine().charAt(0);
			
			
		}while(accountChoice=='y' || accountChoice=='Y');
		
		Customer customer = new Customer(customerCode, customerName, accountList);
		
		System.out.println("Account created, you have the following services");
		
		for(Account account: customer.getAccountList()) {
			
			Product productTest = account.getProduct();
			System.out.println("\n\nproduct name: "+productTest.getProductName());
			
			for(Service service: productTest.getServiceList()) {
				System.out.println(service.getServiceName());
			}
		}
		
		return customer;
		
	}

	public static Customer manageAccounts(Customer customer) {
		// TODO Auto-generated method stub
		if(customer==null) {
			System.out.println("Create customer first");
			return null;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer Id");
		String customerId = scanner.nextLine();
		
		if(customerId.equalsIgnoreCase(customer.getCustomerCode())) {
			System.out.println(customer.getCustomerName()+" has the following accounts");
			
			for(Account account: customer.getAccountList()) {
				System.out.println(account.getProduct().getProductName());
			}
			
			System.out.println("Enter your choice: ");
			String selectedAccount = scanner.nextLine();
			
			char transactionMenuChoice = 'y';
			
			do {
				
			
				System.out.println("\n\n1. Deposit \n2. Withdraw Money \n3. Display Balance");
				System.out.print("Enter your choice: ");
				int transactionChoice = scanner.nextInt();
//				String transactionType = "";
				
				switch(transactionChoice) {
					case 1: //to deposit money
							System.out.print("Enter the money to deposit");
							double depositMoney = scanner.nextDouble();
							
							for(Account account: customer.getAccountList()) {
								
								if(selectedAccount.equalsIgnoreCase(account.getProduct().getProductName())) {
									
									if("Savings Max Account".equalsIgnoreCase(account.getProduct().getProductName())) {
										
										SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
										savingsMaxAccount.depositMoney(account, depositMoney);
										
									}else if("Current Account".equalsIgnoreCase(account.getProduct().getProductName())) {
										
										CurrentAccount currentAccount = (CurrentAccount) account.getProduct();
										currentAccount.depositMoney(account, depositMoney);
										
									}else {
										
										LoanAccount loanAccount = (LoanAccount) account.getProduct();
										loanAccount.depositMoney(account,depositMoney);
									}
									
									break;
								}
							}
							
							
							
							break;
					case 2: //to withdraw money from account
							
							System.out.print("Enter the money to dwithdraw: ");
							double withdrawMoney = scanner.nextDouble();
							
							for(Account account: customer.getAccountList()) {
								
								if(selectedAccount.equalsIgnoreCase(account.getProduct().getProductName())) {
									
									if("Savings Max Account".equalsIgnoreCase(account.getProduct().getProductName())) {
										
										SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
										savingsMaxAccount.withdrawMoney(account, withdrawMoney);
										
									}else if("Current Account".equalsIgnoreCase(account.getProduct().getProductName())) {
										
										CurrentAccount currentAccount = (CurrentAccount) account.getProduct();
										currentAccount.withdrawMoney(account, withdrawMoney);
										
									}else {
										
										LoanAccount loanAccount = (LoanAccount) account.getProduct();
										loanAccount.withdrawMoney(account,withdrawMoney);
									}
									
								
								}
							}
							break;
							
					case 3: //to display account balance
							for(Account account: customer.getAccountList()) {
								
								//to display the balance of that specific account
								if(selectedAccount.equalsIgnoreCase(account.getProduct().getProductName())) {
									account.displayAccount(customer);
								}
							}
							break;
							
					default: System.out.println("Invalid operation");
				}
				
				scanner.nextLine();
				
				System.out.println("Do you want to Do more Transaction? (y/n) : ");
				transactionMenuChoice = scanner.nextLine().charAt(0);
			}while(transactionMenuChoice=='y' || transactionMenuChoice=='Y');
			
			
			
			
		}else {
			System.out.println("No such customer Id exists");
		}
		
		
		return customer;
	}
	
	

	public static void displayCustomerAccounts(Customer customer) {
		// TODO Auto-generated method stub
		if(customer==null) {
			System.out.println("No Customer to display");
			return ;
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Customer id: ");
		String customerId = scanner.nextLine();
		
		if(!customerId.equalsIgnoreCase(customer.getCustomerCode())) {
			System.out.println("No such customer exists");
			return ;
		}
		
		System.out.println("\t\t\t\t"+customer.getCustomerName()+"'s Accounts");
		
		System.out.println("\nCustomerID"+"\t"+"CustomerName"+"\t"+"AccountType"+"\t\t"+"Balance");
		
		for(Account account: customer.getAccountList()) {
//			System.out.println("\t\t\t\t"+account.getProduct().getProductName());
			
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println(customer.getCustomerCode()+"\t\t"+customer.getCustomerName()+"\t\t"+account.getProduct().getProductName()+"\t"+String.valueOf(account.getAccountBalance()));
					
			System.out.println("\nServices Provided");
					
			for(Service service: account.getProduct().getServiceList()) {
						
				System.out.print(service.getServiceName()+", ");
				
			}
			System.out.println("\n");
		}
		
	}

	public static Customer addAccount(Customer customer, ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer ID");
		String customerId = scanner.nextLine();
		
		
		if(!customerId.equalsIgnoreCase(customer.getCustomerCode())) {
			System.out.println("No such Customer exists");
			return null;
		}
		
		//display customer's accounts
		System.out.println(customer.getCustomerName()+"'s accounts");
		for(Account account: customer.getAccountList()) {
			System.out.println(account.getProduct().getProductName());
		}
		
		
		
		for(Product productItem: productList) {
			System.out.println(productItem.getProductCode()+"\t"+productItem.getProductName());
			
		}
		
		System.out.print("Select account code: ");
		String productCode = scanner.nextLine();
		
		for(Account account: customer.getAccountList()) {
			if(productCode.equalsIgnoreCase(account.getProduct().getProductCode())) {
				System.out.println(customer.getCustomerName()+" already has "+account.getProduct().getProductName());
				return null;
			}
		}
		
		//create account
		Product product = null;
		
		for(Product productItem: productList) {
			if(productCode.equalsIgnoreCase(productItem.getProductCode())) {
				product = productItem;
				System.out.println(productItem.getProductName()+" add");
			}
		}
		
//		System.out.print("Enter Account Number: ");
//		String accountNo = scanner.nextLine();
		
		System.out.print("Enter Account Type: ");
		String accountType = scanner.nextLine();
		
		System.out.print("Enter Account Balance: ");
		double accountBalance = scanner.nextDouble();
		
		
		
		
		Account account = new Account(accountType, accountBalance, product);
		System.out.println("Account no in Add acc: "+account.getAccountNo());
		ArrayList<Account> accountList = customer.getAccountList();
		accountList.add(account);
		customer.setAccountList(accountList);
		
		
		
		
		
		
		return customer;
	}
	
}
