package myTesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTEST {
	public static void main(String[] args) {
		
		 HashMap<String, CustomerAccount> CustomerEmail = new HashMap<String, CustomerAccount>();
		
		 BankAccount TestBa = new BankAccount(1234, 100, "John", "Doe", 1234);
		 BankAccount TestBa2 = new BankAccount(1235, 100, "John", "Doe", 1235);
		 
		 CustomerAccount TestAccount = new CustomerAccount("TestEmail@gmail.com", "John", "Doe", "pass");
		 CustomerAccount TestAccount2 = new CustomerAccount("John@gmail.com", "John", "jeff", "pass");
		 TellerAccount TestTeller = new TellerAccount("Jeff", "Doe", "Teller@gmail.com", "pass");
		 
		 TestAccount.AddBankAccount(TestBa);
		 TestAccount.AddBankAccount(TestBa2);
		 
		 TestBa.deposit(100);
		 TestBa.deposit(200);
		 TestBa.withdraw(50);
		
		 CustomerEmail.put("TestEmail@gmail.com", TestAccount);
		 CustomerEmail.put("John@gmail.com", TestAccount2);
		 
		 try {
				File myfile = new File("CustomerData.txt");
				FileWriter writer = new FileWriter(myfile);
				
				for (CustomerAccount account : CustomerEmail.values()) {
	            	writer.write(account.toString());
	                 // Assuming each account should be on a new line
	            }
				
				//writer.write(TestAccount.toString());
				//writer.write(TestAccount2.toString());
				
				System.out.println("done");
				writer.close();
				
			} catch (IOException e) {
				System.out.println(e);
				
				
			}
			
			
		/*
		
		 HashMap<String, CustomerAccount> CustomerEmail = new HashMap<String, CustomerAccount>();
		 
		 String filename = "CustomerData.txt";
		 
		 try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                if(parts.length < 4) continue; // Skip if line doesn't have enough data for CustomerAccount

	                // Parse CustomerAccount data
	                CustomerAccount customer = new CustomerAccount(parts[0], parts[1], parts[2], parts[3]);

	                // Process BankAccounts
	                for (int i = 4; i < parts.length; ) {
	                    // Assuming a fixed number of fields for BankAccount
	                    int id = Integer.parseInt(parts[i++]);
	                    int pin = Integer.parseInt(parts[i++]);
	                    Integer balance = Integer.parseInt(parts[i++]);
	                    String baFirstName = parts[i++];
	                    String baLastName = parts[i++];

	                    BankAccount bankAccount = new BankAccount(pin, balance, baFirstName, baLastName, id);

	                    // Assuming the remaining parts are log entries
	                    try {
							while (i < parts.length && parts[i].startsWith("LogEntry:")) {
							    bankAccount.addToLog(parts[i++]);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	                    customer.AddBankAccount(bankAccount);
	                }

	                // Add CustomerAccount to HashMap
	                CustomerEmail.put(customer.getEmail(), customer);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		 
		 
		 
		 
		  System.out.println(CustomerEmail.get("TestEmail@gmail.com").toString());
		 
		 */
		 
		 
		 
		 
		 
		
		
		
		System.out.println("Hi");
	}
	
	
	
	
	

}