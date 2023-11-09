package myTesting;
import java.io.*;
import java.net.*;

public class BankAppServer {
	
	
	public static void main(String[] args) {
		
		 //CustomerAccount MasterList[];
		
		
		
		 ServerSocket serverSocket = null;
		 
		 
		 
		 
		 try { 
			 
			 serverSocket = new ServerSocket(3000);
			 serverSocket.setReuseAddress(true);
		 
			 
			 
			 
			 while (true) {
				
				 
				 Socket client = serverSocket.accept();
				 
				 System.out.println("New client connected" + client.getInetAddress().getHostAddress());
				 
				 ClientHandler clientSock = new ClientHandler(client);
				 
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
		
		
		public ClientHandler(Socket socket) {
			this.clientSocket = socket;
		}
		
		
		public void run() {
			PrintWriter out = null;
			BufferedReader in = null;
			
			try { 
				
				
				out = new PrintWriter(clientSocket.getOutputStream(), true); 
				
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				
				
				String line; 
				
				while ((line = in.readLine())  != null) {
					System.out.printf("Sent from the client: %s\n", line);
					out.println(line);
				}
				
				
				
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			finally {
				
				try {
					
					if (out != null) {
						out.close();
					}
					
					if (in != null) {
						in.close();
						clientSocket.close();
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
					
		}
		
		
		
	
		
		
		
	}
	
	
	
	
	
	
	

}
