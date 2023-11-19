package myTesting;
import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Client {
	private Socket socket;
	private ObjectOutputStream out;
    private ObjectInputStream in;
    private TellerGui tellerGui;
    public TellerInterface TInterface;
    private ATMGUI AGUI;
    private ATM ATMINTER;

    public Client(String serverAddress, int serverPort, String option) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        // Initialize and display the GUI
        if (option.equals("TELLER")) {
        	tellerGui = new TellerGui(this);
        	TInterface = tellerGui;
        	tellerGui.processCommands();
        	
        } else if (option.equals("ATM")) {
        	AGUI = new ATMGUI(this);
        	ATMINTER = AGUI;
        	ATMINTER.processCommands();
        	
        	
        }
        
        
        
    }
	
	
    public void sendMessage(Message message) throws IOException {
        out.writeObject(message);
        out.flush();
        // Additional logic to handle the response from the server
    }
	
	public Message getMessage() throws ClassNotFoundException, IOException {
		Message message = (Message) in.readObject();
		return message;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		
		try {
			
			
			String option = JOptionPane.showInputDialog("Enter Teller for teller or Atm for atm");
			String finalop = option.toUpperCase();
			
			
            new Client("localhost", 3005, finalop); // Replace with actual server address and port
        } catch (IOException e) {
            e.printStackTrace();
            // Handle initialization errors (e.g., server not reachable)
        }
		
		
		
		
		
		
	/*	try (Socket socket = new Socket("localhost", 3005)){
			
			

		
				
				ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
				
				Message message = new Message("100", "Depostit");
				Message message1 = new Message("200", "Deposit");
				
				out2.writeObject(message);
				out2.writeObject(message1);
						

				// object of scanner class
				Scanner sc = new Scanner(System.in);
				String line = null;

				while (!"exit".equalsIgnoreCase(line)) {
					
					// reading from user
					line = sc.nextLine();

					// sending the user input to server

					// displaying server reply
					
				}
				
				// closing the scanner object
				sc.close();
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	
	}
	
}
