package myTesting;
import java.io.*;
import java.net.*;

public class BankAppServer {
	
	
	public static void main(String[] args) {
		
		 CustomerAccount[] MasterList = new CustomerAccount[1];
		
		 BankAccount TestBa = new BankAccount(1234, 100, "John", "Doe", 1);
		 
		 CustomerAccount TestAccount = new CustomerAccount("TestEmail@gmail.com", "John", "Doe", "pass");
		 
		 TestAccount.AddBankAccount(TestBa);
		 
		 MasterList[0] = TestAccount;
		
		
		 ServerSocket serverSocket = null;
		 
		 
		 
		 
		 try { 
			 
			 serverSocket = new ServerSocket(3005);
			 serverSocket.setReuseAddress(true);
		 
			 
			 
			 
			 while (true) {
				
				 
				 Socket client = serverSocket.accept();
				 
				 System.out.println("New client connected" + client.getInetAddress().getHostAddress());
				 
				 ClientHandler clientSock = new ClientHandler(client, MasterList);
				 
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
		
		
		public ClientHandler(Socket socket, CustomerAccount[] masterList) {
			this.clientSocket = socket;
			this.bigList = masterList;
		}
		
		
		public void run() {
			
			
			try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
		             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
				
				System.out.println("Hello I am here");
				
				Object obj;
				
				
				
				while ((obj = in.readObject()) != null) {
	                if (obj instanceof Message) {
	                    Message message = (Message) obj;
	                    // Process the received message
	                    System.out.println("Received from client: " + message.getType() + message.getText());
	                    
	                    BankAccount temp = bigList[0].getBankAccount(1);
	                    
	                    int amount = Integer.parseInt(message.getText());
	                    
	                    temp.deposit(amount);
	                    
	                    System.out.println(temp.getBalance());

	                    // Respond back to the client
	                    // Example: Echoing the received message
	                    out.writeObject(message);
	                }
	            } 
				
				
				
				
				
				
				
				
			} catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			finally {
				
				try {
					
					clientSocket.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
					
		}
		
		
		
	
		
		
		
	}
	
	
	
	
	
	
	

}
