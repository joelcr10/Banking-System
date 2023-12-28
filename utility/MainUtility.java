package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
import com.ilp.service.CustomerAccountServices;

public class MainUtility {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Customer customer = null;
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		char choice;
		int mainMenuChoice;
		
//		String[] serviceCodes = {"s001","s002","s003","s004","s005"};
//		String[] serviceNames = {"Cash Deposit","ATM Withdrawal", "Online Banking", "Cheque Deposit", "Mobile Banking"};
//		double[] serviceRates = {10,15,20,25,30};
//		
//		for(int i=0;i<5;i++) {
//			Service service = new Service(serviceCodes[i],serviceNames[i],serviceRates[i]);
//			serviceList.add(service);
//		}
//		
//		String[] productCodes = {"p001","p002","p003"};
//		String[] productNames = {"Savings Max Account","Current Account","Loan Account"};
//		for(int i=0;i<3;i++) {
//			ArrayList<Service> defaultServiceList = new ArrayList<Service>();
//			if(i==0) {
//				//insert the default services
//				for(Service service: serviceList) {
//					if("Cash Deposit".equalsIgnoreCase(service.getServiceName()) || "ATM Withdrawal".equalsIgnoreCase(service.getServiceName()) || "Online Banking".equalsIgnoreCase(service.getServiceName())) {
//						defaultServiceList.add(service);
//					}
//				}
//				
//				SavingsMaxAccount savingsMaxAccount = new SavingsMaxAccount(productCodes[i],productNames[i],defaultServiceList);
//				productList.add(savingsMaxAccount);
//			
//			}else if(i==1) {
//				
//				for(Service service: serviceList) {
//					if("Cash Deposit".equalsIgnoreCase(service.getServiceName()) || "Online Banking".equalsIgnoreCase(service.getServiceName()) || "ATM Withdrawal".equalsIgnoreCase(service.getServiceName()) || "Mobile Banking".equalsIgnoreCase(service.getServiceName())) {
//						defaultServiceList.add(service);
//					}
//				}
//				
//				CurrentAccount currentAccount = new CurrentAccount(productCodes[i], productNames[i], defaultServiceList);
//				productList.add(currentAccount);
//			}else {
//				for(Service service: serviceList) {
//					if("Cash Deposit".equalsIgnoreCase(service.getServiceName()) || "Cheque Deposit".equalsIgnoreCase(service.getServiceName())){
//						defaultServiceList.add(service);
//					}
//				}
//				
//				LoanAccount loanAccount = new LoanAccount(productCodes[i],productNames[i],defaultServiceList);
//				productList.add(loanAccount);
//			}
//			
//			
//		}
//		
		
		do {
			System.out.println("\n1. Create Service \n2. Create Product \n3. Create Customer \n4. Manage Accounts \n5. Display Customer \n6. Add Account \n7. display Service list \n8. display Product list");
			System.out.print("Enter Your choice: ");
			mainMenuChoice = scanner.nextInt();
			switch(mainMenuChoice) {
				case 1: serviceList.add( CustomerAccountServices.createService());
						break;
						
				case 2: Product productDemo = CustomerAccountServices.createProduct(serviceList);
						if(productDemo!=null) {
							productList.add(productDemo);
						}
						
						break;
				case 3: Customer customerDemo1 = CustomerAccountServices.createCustomer(productList);
						if(customerDemo1!=null) {
							customer = customerDemo1;
						}
						break;	
				
				case 4: Customer customerDemo2 = CustomerAccountServices.manageAccounts(customer);
						if(customerDemo2==null) {
							customer = customerDemo2;
						}
						break;
						
				case 5: CustomerAccountServices.displayCustomerAccounts(customer);
						break;
						
				case 6: Customer customerDemo = CustomerAccountServices.addAccount(customer, productList);
						if(customerDemo != null) {
							customer = customerDemo;
						}
						break;
						
				case 7: System.out.println("Service List:");
						for(Service service: serviceList) {
							System.out.println(service.getServiceName());
						}
						
						break;
				case 8: System.out.println("Product List");
						for(Product product: productList) {
							System.out.println(product.getProductName());
						}
						break;
			}
			System.out.print("Do you want to continue (y/n): ");
			scanner.nextLine();
			choice = scanner.nextLine().charAt(0);
		}while(choice=='y' || choice=='Y');
		
	}
}
