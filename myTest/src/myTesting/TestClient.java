package myTesting;
import java.io.*;
import java.net.*;
import java.util.Scanner;



/////////////////////////
/////////////////////////

//////// THIS CLASS IS JUST FOR TESTING PURPOSES ///////////////

public class TestClient {
public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		Socket socket = new Socket("localhost", 3005);
		
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		
		
		 Message message = new Message("TestEmail@gmail.com", "loginEmail");
		 out.writeObject(message);
		 out.flush();
		 
		 message = (Message) in.readObject();
		    if (message.getType().equals("RequestPass")) {
		    	Message newMess = new Message("pass", "Password");
		    	out.writeObject(newMess);
		    	out.flush();
		    	
		    	message = (Message) in.readObject();
		    	if (message.getType().equals("Success")) {
		    	
		    	System.out.println("logged in."); 
		    	
		    	}
		    	
		    	Message newMess1 = new Message("money", "Deposit");
		    	out.writeObject(newMess1);
		    	out.flush();
		    	
		    	
		    	
		    	message = (Message) in.readObject();
		    	
		    	if (message.getType().equals("SendBankId")) {
		    		Message newNess2 = new Message("1234", "BankID");
		    		System.out.println("I will send Id");
		    		out.writeObject(newNess2);
		    		out.flush();
		    	}
		    	
		    	
		    	
		    	
		    	
		    	message = (Message) in.readObject();
		    	if (message.getType().equals("Amount")) {
		    		System.out.println("I will send amount");
		    		Message newNess3 = new Message("400", "SentAmount");
		    		out.writeObject(newNess3);
		    		out.flush();
		    	}
		    	
		    	
		    	
		    	message = (Message) in.readObject();
		    	if (message.getType().equals("BalanceCheck")) {
		    		System.out.println(message.getText());
		    	}
		    	
		    	
		        Scanner scanner = new Scanner(System.in);
		        while (true) {
		        	message = (Message) in.readObject();
		        	
		        	
		        	
		        	
		        	
		        	
		            System.out.print("Enter text to send to the server ");
		            String text = scanner.nextLine();
		            message = new Message("text", "");
		            out.writeObject(message);
		            out.flush();
		            message = (Message) in.readObject();
		            System.out.println("Received from server: " + message.getText());
		            if (text.equalsIgnoreCase("logout")) {
		                 message = new Message("logout", "");
		                 out.writeObject(message);
		                 out.flush();
		                 message = (Message) in.readObject();
		                  if (message.getType().equals("success")) {
		                        System.out.println("Successfully logged out.");
		                         socket.close();
		                          break;
		                   }
		             }
		        }
		
		
		
		    }
		
		
		
		
		
	}

}
