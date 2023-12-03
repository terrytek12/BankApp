package myTesting;
import java.io.*;

import java.net.*;

import java.util.HashMap;

public class BankAppServer {
	
	
	public static void main(String[] args) {
		
		 CustomerAccount[] MasterList = new CustomerAccount[7];
		 HashMap<String, CustomerAccount> CustomerEmail = new HashMap<String, CustomerAccount>();
		 HashMap<String, TellerAccount> TellerEmail = new HashMap<String, TellerAccount>();
		 TellerAccount TestTeller = new TellerAccount("Jeff", "Doe", "Teller@gmail.com", "pass");
		 
		 /*
		 BankAccount TestBa = new BankAccount(1234, 100, "John", "Doe", 1234);
		 
		 CustomerAccount TestAccount = new CustomerAccount("TestEmail@gmail.com", "John", "Doe", "pass");
		 CustomerAccount TestAccount2 = new CustomerAccount("John@gmail.com", "John", "jeff", "pass");
		 TellerAccount TestTeller = new TellerAccount("Jeff", "Doe", "Teller@gmail.com", "pass");
		 
		 TestAccount.AddBankAccount(TestBa);
		 */
		 
		 
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
	                    double balance = Double.parseDouble(parts[i++]);
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
		 
		 
		 
		 MasterList[0] = CustomerEmail.get("TestEmail@gmail.com");
		 MasterList[1] = CustomerEmail.get("John@gmail.com");
		 
		
		 TellerEmail.put("Teller@gmail.com", TestTeller);
		 
		 
		 ServerSocket serverSocket = null;
		 
		 
		 
		 
		 try { 
			 
			 serverSocket = new ServerSocket(3005);
			 serverSocket.setReuseAddress(true);
		 
			 
			 
			 
			 while (true) {
				
				 
				 Socket client = serverSocket.accept();
				 
				 System.out.println("New client connected" + client.getInetAddress().getHostAddress());
				 
				 ClientHandler clientSock = new ClientHandler(client, MasterList, CustomerEmail, TellerEmail, filename);
				 
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
		private CustomerAccount WorkingAccount = null;
		private CustomerAccount WorkingAccount2 = null;
		private HashMap<String, TellerAccount> TellerHash;
		private TellerAccount TellAcc = null;
		BankAccount WorkingBA = null;
		private String filename;
		
		public ClientHandler(Socket socket, CustomerAccount[] masterList, HashMap<String, CustomerAccount> EmailHash,  HashMap<String, TellerAccount> EmailHash2, String Fname) throws IOException {
			this.clientSocket = socket;
			this.bigList = masterList;
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
	        this.in = new ObjectInputStream(clientSocket.getInputStream());
	        this.CustomerHash = EmailHash;
	        this.TellerHash = EmailHash2;
	        this.filename = Fname;
		}
		
		
		public void run() {
			
			
			try  {
				
				System.out.println("Hello I am here");
				
				Message message = (Message) in.readObject();
				
				
				if (message.getType().equals("TellerLogin")) {
					
					TellAcc = TellerHash.get(message.getText());
					Message newMess20 = new Message("gimmepass", "ReqPass");
					out.writeObject(newMess20);
					out.flush();
					
					message = (Message) in.readObject();
					
					if (message.getType().equals("SentPass") && message.getText().equals(TellAcc.getPassword())) {
						Message newMess21 = new Message("login good", "Success");
						out.writeObject(newMess21);
						out.flush();
						
						
						
						
						
					} else {
						Message newMess21 = new Message("not authorized", "Fail");
						out.writeObject(newMess21);
						out.flush();
						
						
					}
					
					
				while (true) {	
					
				message = (Message) in.readObject();	
				
				
				
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
					
					
					
				}
				
				
				
				
				
				
				
				if (message.getType().equals("loginEmail")) { 
					
					String GetterEmail = message.getText();
					
					WorkingAccount = CustomerHash.get(GetterEmail);
					
					
					
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
	                        WorkingBA = WorkingAccount.getBankAccount(tempid);
	                        
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
	                    	double balance = Integer.parseInt(parts[1]);
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
	                    
	                    else if (message.getType().equals("CreateBA")) {
	                    	System.out.println("WE are making a BA");
	                    	String[] parts = message.getText().split(" ");
	                    	int id = Integer.parseInt(parts[0]);
	                    	int balance = Integer.parseInt(parts[1]);
	                    	int pin = Integer.parseInt(parts[2]);
	                    	String FN = WorkingAccount.getFirstName();
	                    	String LN = WorkingAccount.getLastName();
	                    	
	                    	
	                    	WorkingBA2 = new BankAccount(pin, balance, FN, LN, id);
	                    	
	                    	WorkingAccount.AddBankAccount(WorkingBA2);
	                    	
	                    	
	                    	Message Mess1 = new Message("new bank account created and added", "Success");
	                    	out.writeObject(Mess1);
	                    	out.flush();
	                    	
	                    	
	                    	
	                    	
	                    } else if (message.getType().equals("CreateCA")) {
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
	    					
	    					
	    					
	    				}
	                    
	                    
	                    else if (message.getType().equals("DeleteBA")) {
	                    	
	                    	int id = Integer.parseInt(message.getText());
	                    	WorkingBA = WorkingAccount.getBankAccount(id);
	                    	
	                    	Message sendMess = new Message("gimme pin", "SendPin");
	                    	out.writeObject(sendMess);
	                    	out.flush();
	                    	
	                    	
	                    	message = (Message) in.readObject();
	                    	
	                    	if (message.getType().equals("Pin ")) {
	                    		
	                    		int pin = Integer.parseInt(message.getText());
	                    		
	                    		if(pin == WorkingBA.getPin()) {
	                    			
	                    			WorkingAccount.removeBankAccount(id);
	                    			
	                    			Message sendMess2 = new Message("Deleted BA", "Success");
	                    			out.writeObject(sendMess2);
	                    			out.flush();
	                    			
	                    			
	                    			
	                    		}
	                    		
	                    		
	                    		
	                    		
	                    		
	                    	}
	                    	
	                    	
	                    	
	                    } 
	                    
	                    else if (message.getType().equals("MoneyTransfer")) {
	                    	
	                    	int id = Integer.parseInt(message.getText());
	                    	WorkingBA = WorkingAccount.getBankAccount(id);
	                    	
	                    	Message sendMess = new Message("gimme email and BA id to send to", "SendDetails");
	                    	out.writeObject(sendMess);
	                    	out.flush();
	                    	
	                    	message = (Message) in.readObject();
	                    	
	                    	if (message.getType().equals("SentInfo")) {
	                    		
	                    		String[] parts = message.getText().split(" ");
	                    		String email = parts[0];
	                    		Integer id2 = Integer.parseInt(parts[1]);
	                    		
	                    		WorkingAccount2 = CustomerHash.get(email);
	                    		WorkingBA2 = WorkingAccount2.getBankAccount(id2);
	                    		
	                    		
	                    		Message sendMess2 = new Message("gimme amount", "ProvideAmount");
	                    		out.writeObject(sendMess2);
	                    		out.flush();	                    		
	                    		
	                    		
	                    		message = (Message) in.readObject();
	                    		
	                    		if (message.getType().equals("SentAmountT")) {
	                    			
	                    			int amount = Integer.parseInt(message.getText());
	                    			
	                    			if (amount < WorkingBA.getBalance()) {
	                    				
	                    				WorkingBA.withdraw(amount);
	                    				WorkingBA2.withdraw(amount);
	                    				
	                    				Message sendMess3 = new Message("Money transfer success", "Success");
	                    				out.writeObject(sendMess3);
	                    				out.flush();
	                    				
	                    				
	                    				
	                    			} else {
	                    				
	                    				
	                    				Message sendMess3 = new Message("balance too low for amount", "Fail");
	                    				out.writeObject(sendMess3);
	                    				out.flush();
	                    				
	                    				
	                    				
	                    			}
	                    			
	                    		
	                    			 
	                    			
	                    		}	
	                    		
	                    	}	
	                    			
	                    			
	                    			
	                    		} else if (message.getType().equals("Clear")) {
	                    			WorkingAccount.setOnline(false);
	                    			WorkingAccount = null;
	                    			WorkingBA = null;
	                    			
	                    			Message newMess2 = new Message( "Thank you enter a Customer Account to use", "Success");
        	                        out.writeObject(newMess2);
        	                        out.flush();
	                    			
	                    			
	                    			
	                    		} else if (message.getType().equals("logout")) {
        	                    	System.out.println("Log out good");
        	                    	
        	                        Message newMess2 = new Message( "Thank you log out success", "logout");
        	                        out.writeObject(newMess2);
        	                        out.flush();
        	                       clientSocket.close();
        	                       break; }
        	                    
        	                 				
	                    		
	                    		
	                    		
	                    		
	                    	
	                    	
	                    	
	                    	
	                    	
	                    	
	                    	
	                    	
	                    
	                        
	                 
	                        
	                     
	                
								} 
				
					
							} else if (message.getType().equals("logout")) {
    	                    	System.out.println("Log out good");
    	        

    	                        Message newMess2 = new Message( "Thank you log out success", "logout");
    	                        out.writeObject(newMess2);
    	                        out.flush();
    	                       clientSocket.close();
    	                       break; }
				
						}
				
					}
				
				
				
				
				
				
				
				if (message.getType().equals("CustomerLogin")) {
					
					WorkingAccount = CustomerHash.get(message.getText());
					
					Message PassMess = new Message("gimme pass", "ReqPass");
					out.writeObject(PassMess);
					out.flush();
					
					
					message = (Message) in.readObject();
 					
					
					if (message.getType().equals("SentPass") && WorkingAccount.getPassword().equals(message.getText()) && WorkingAccount.getOnline() == false) {
						
						Message newMess = new Message("Logged in", "Success");
						out.writeObject(newMess);
						out.flush();
						
						WorkingAccount.setOnline(true);
						
						
						
						
						
					} else {
						
						Message newMess = new Message("not logged in password wrong or Acccount online", "Fail");
						out.writeObject(newMess);
						out.flush();
						
						clientSocket.close();
						
						
						
					}
					
					
					while(true) {
						 
						message = (Message) in.readObject();
						
						
						if (message.getType().equals("BankId")) {
							
							int id = Integer.parseInt(message.getText());
							
							WorkingBA = WorkingAccount.getBankAccount(id);
							
							Message sendMess = new Message("provide pin", "ProvidePin");
							out.writeObject(sendMess);
							out.flush();
							
							
							message = (Message) in.readObject();
							
							
							if (message.getType().equals("ProvidePin")) {
								
								int pin = Integer.parseInt(message.getText());
								
								
								if(pin == WorkingBA.getPin()) {
									
									Message sendMess1 = new Message("Success selection", "success");
									out.writeObject(sendMess1);
									out.flush();
									
									
									
								} else {
									
									Message sendMess1 = new Message("pin wrong or som", "fail");
									out.writeObject(sendMess1);
									out.flush();
									clientSocket.close();
									
									
									
									}
								
								
								
								
								
								
								
								
							}
								
							
						} else if (message.getType().equals("SentAmount")) {
							
							int amount = Integer.parseInt(message.getText());
							
							WorkingBA.deposit(amount);
							
							Message sendMess = new Message("Successfull deposit here is balance " + WorkingBA.getBalance(), "Success");
							out.writeObject(sendMess);
							out.flush();
							
							
							
							
							
						} else if (message.getType().equals("SentAmountWithdraw")) {
							
							int amount = Integer.parseInt(message.getText());
							
							
							
							if (amount > WorkingBA.getBalance()) {
								
								Message sendMess1 = new Message("Not enought in balance", "Fail");
								out.writeObject(sendMess1);
								out.flush();
								
								
								
							} else {
								
								
								
								WorkingBA.withdraw(amount);
								
								Message sendMess = new Message("Successfull withdraw here is balance " + WorkingBA.getBalance(), "Success");
								out.writeObject(sendMess);
								out.flush();
								
								
								
								
								
							}
							
							
				
							
						} else if (message.getType().equals("logout")) {
							
							
							WorkingAccount.setOnline(false);
							
							Message sendMess = new Message("log out good", "logout");
							out.writeObject(sendMess);
							out.flush();
							
							clientSocket.close();
							
							
							
							
							
						}
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
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
					
					
					try {
						
						File myfile = new File(filename);
						FileWriter writer = new FileWriter(myfile);
						
			            for (CustomerAccount account : CustomerHash.values()) {
			            	writer.write(account.toString());
			                 // Assuming each account should be on a new line
			            }
			            
			            writer.close();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
					
					
					
					
					
					
					clientSocket.close();
					} catch (IOException e) {
					e.printStackTrace();}
					
					}
			
					
		}
		

		
	
		
		
		
	}
	
	
	
	
	
	
	

}
