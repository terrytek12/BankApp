package myTesting;
import java.io.*;

import java.net.*;

import java.util.HashMap;

public class BankAppServer {
	
	
	public static void main(String[] args) {
		
		 CustomerAccount[] MasterList = new CustomerAccount[7];
		 HashMap<String, CustomerAccount> CustomerEmail = new HashMap<String, CustomerAccount>();
		
		 BankAccount TestBa = new BankAccount(1234, 100, "John", "Doe", 1);
		 
		 CustomerAccount TestAccount = new CustomerAccount("TestEmail@gmail.com", "John", "Doe", "pass");
		 CustomerAccount TestAccount2 = new CustomerAccount("John@gmail.com", "John", "jeff", "pass");
		 
		 TestAccount.AddBankAccount(TestBa);
		 
		 MasterList[0] = TestAccount;
		 MasterList[1] = TestAccount;
		 
		 CustomerEmail.put("TestEmail@gmail.com", TestAccount);
		 CustomerEmail.put("John@gmail.com", TestAccount2);
		
		 ServerSocket serverSocket = null;
		 
		 
		 
		 
		 try { 
			 
			 serverSocket = new ServerSocket(3005);
			 serverSocket.setReuseAddress(true);
		 
			 
			 
			 
			 while (true) {
				
				 
				 Socket client = serverSocket.accept();
				 
				 System.out.println("New client connected" + client.getInetAddress().getHostAddress());
				 
				 ClientHandler clientSock = new ClientHandler(client, MasterList, CustomerEmail);
				 
				 new Thread(clientSock).start();
				 
				 }
		 } catch(IOException e) {
					e.printStackTrace(); 
				 }
			 
			 
		 
		 		finally {
				 if (serverSocket != null) {
					 try {
						 serverSocket.close(); }
					 catch(IOException e) {
						 e.printStackTrace(); }
				 
				 }
			 
			 
			 
		
		 }
	}
	
	private static class ClientHandler implements Runnable {
		
		private Socket clientSocket;
		private CustomerAccount[] bigList;
		private ObjectOutputStream out;
	    private ObjectInputStream in;
		private HashMap<String, CustomerAccount> CustomerHash;
		private BankAccount WorkingBA2 = null;
		private CustomerAccount WorkingAccount2 = null;
		
		public ClientHandler(Socket socket, CustomerAccount[] masterList, HashMap<String, CustomerAccount> EmailHash ) throws IOException {
			this.clientSocket = socket;
			this.bigList = masterList;
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
	        this.in = new ObjectInputStream(clientSocket.getInputStream());
	        this.CustomerHash = EmailHash;
		}
		
		
		public void run() {
			
			
			try  {
				
				System.out.println("Hello I am here");
				
				Message message = (Message) in.readObject();
				
				
				
				
				if (message.getType().equals("CreateCA")) {
					System.out.println("We are creating a CA");
					String[] parts = message.getText().split(" ");
					String email = parts[0];
					String firstN = parts[1];
					String lastN = parts[2];
					String pass = parts[3];
					
					CustomerAccount newAccount = new CustomerAccount(email, firstN, lastN, pass);
					CustomerHash.put(email, newAccount);
					System.out.println("account made");
					
					Message goBack = new Message("Account created", "Success");
					out.writeObject(goBack);
					out.flush();
					
					
					message = (Message) in.readObject();
				}
				
				
				
				
				
				
				
				if (message.getType().equals("loginEmail")) { 
					
					String GetterEmail = message.getText();
					
					CustomerAccount WorkingAccount = CustomerHash.get(GetterEmail);
					
					BankAccount WorkingBA = null;
					
					Message newMess = new Message("gimme Pass", "RequestPass");
	                out.writeObject(newMess); 
	                out.flush();
	                System.out.println("Working");
	                message = (Message) in.readObject();
	                if (message.getType().equals("Password")) {
	                	if (message.getText().equals(WorkingAccount.getPassword())) {
	                		
	                		if (WorkingAccount.getOnline()) {
	                			Message newMess2 = new Message("Account is online", "unSuccess");
		                		out.writeObject(newMess2);
		                		out.flush();
		                		clientSocket.close();
	                		}
	                		
	                		WorkingAccount.setOnline(true);
	                		Message newMess2 = new Message("great login", "Success");
	                		out.writeObject(newMess2);
	                		out.flush();
	                	} else {
	                		Message newMess2 = new Message("unSuccess", "go away");
	                		out.writeObject(newMess2);
	                		out.flush();
	                		clientSocket.close();
	                	}
	                }
	                
	               
	                 while (true) {
	                    message = (Message) in.readObject();
	                    System.out.println("Working");
	                    if (message.getType().equals("Deposit") || message.getType().equals("Withdraw")) {
	                        Message newMess1 = new Message("gimme bank id", "SendBankId");
	                        out.writeObject(newMess1);
	                        out.flush();
	                        System.out.println("deposit/withdraw");
	                    }
	                        
	                    else if (message.getType().equals("BankID")) {
	                    	int tempid = Integer.parseInt(message.getText());	                        
	                        WorkingBA = WorkingAccount.getBAbyIndex(0);
	                        System.out.println(WorkingBA.getId());
	                        Message newMess2 = new Message("how much", "Amount");
	                        out.writeObject(newMess2);
	                        out.flush();
	                        System.out.println("Hello");
	                    }
	                      
	                    
	                    else if (message.getType().equals("SentAmount")) {  
	                    	System.out.println("Hello amount");
	                       // message = (Message) in.readObject();
	                        int amount = Integer.parseInt(message.getText());
	                        WorkingBA.deposit(amount);
	                        System.out.println(WorkingBA.getBalance() + " here it is");
	                        
	                        Message newMess3 = new Message("Here is amount " + WorkingBA.getBalance(), "BalanceCheck");
	                        out.writeObject(newMess3);
	                        out.flush(); 
	                        System.out.println(WorkingBA.getBalance() + " here it is");
	                        
	                    }
	                    
	                    else if (message.getType().equals("WithdrawAmount")) {
	                    	System.out.println("withdraw amount here");
	                    	
	                    	
	                    	int amount = Integer.parseInt(message.getText());
	                    	
	                    	if (amount < WorkingBA.getBalance()) {
	                    		WorkingBA.withdraw(amount);
		                    	System.out.println(WorkingBA.getBalance() + " here it is");
		                    	
		                    	
		                    	Message newMess3 = new Message("Here is amount " + WorkingBA.getBalance(), "BalanceCheck");
		                        out.writeObject(newMess3);
		                        out.flush(); 
		                    	
	                    	}
	                	
	                    }
	                   
	                    else if (message.getType().equals("ShareNewBA")) {
	                    	
	                    	
	                    	
	                    	String[] parts = message.getText().split(" ");
	                    	int id = Integer.parseInt(parts[0]);
	                    	int balance = Integer.parseInt(parts[1]);
	                    	int pin = Integer.parseInt(parts[2]);
	                    	String FN = WorkingAccount.getFirstName();
	                    	String LN = WorkingAccount.getLastName();
	                    	
	                    	WorkingBA2 = new BankAccount(pin, balance, FN, LN, id);
	                    	
	                    	Message sendMess = new Message("I need a an email to share with", "NeedEmail");;
	                    	out.writeObject(sendMess);
	                    	out.flush();
	                    	
	                    	
	                    }
	                        
	                    
	                    else if (message.getType().equals("ShareEmail")) {
	                    	
	                    	WorkingAccount2 = CustomerHash.get(message.getText());
	                    	
	                    	WorkingAccount2.AddBankAccount(WorkingBA2);
	                    	
	                    	
	                    	Message sendMess = new Message("Share new Ba is good", "Success");
	                    	out.writeObject(sendMess);
	                    	out.flush();
	                    	
	                    }
	                    
	                    else if (message.getType().equals("ShareOldBA")) {
	                    	
	                    	String[] parts = message.getText().split(" ");
	                    	String email = parts[0];
	                    	int id = Integer.parseInt(parts[1]); 
	                    	
	                    	
	                    	WorkingBA = WorkingAccount.getBankAccount(id);
	                    	
	                    	WorkingAccount2 = CustomerHash.get(email);
	                    	
	                    	
	                    	WorkingAccount2.AddBankAccount(WorkingBA);
	                    	
	                    	
	                    	Message newMess1 = new Message("BA added to other account", "Success");
	                    	
	                    	out.writeObject(newMess1);
	                    	out.flush();
	                    	
	                    	
	                    }
	                        
	                 
	                        
	                       else if (message.getType().equals("logout")) {
	                        Message newMess2 = new Message( "Thank you log out", "logout");
	                        out.writeObject(newMess2);
	                        out.flush();
	                        WorkingAccount.setOnline(false);
	                        clientSocket.close();
	                        break; }
	                    
	                 		}	
	                
						} 
				
					
				
					
	            }
				
				
				

				
				
	
				
			 catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				
				try {
					
					clientSocket.close();
					} catch (IOException e) {
					e.printStackTrace();}
					
					}
			
					
		}
		

		
	
		
		
		
	}
	
	
	
	
	
	
	

}
