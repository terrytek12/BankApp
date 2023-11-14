package myTesting;
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;




public class TellerGui implements TellerInterface {
	
	
	private Client client;
	
	public TellerGui(Client client) {
		this.client = client;
	}
	
	
	
	public void processCommands()
	 {
		 String[] commands = {
				 	"Enter Customer Account",
				 	"Create CA",
				 	"Create Bank Account",
				 	"Deposit",
				 	"Withdraw",
				 	"share new BA",
				 	"share old BA",
				 	"delete BA",  // modified list 
				 	"money transfer",
				 	"Logout"};
		 
		 int choice;
		 
		 do {
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a command", 
					 "DVD Collection", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands,
					 commands[commands.length - 1]);
		 
			 switch (choice) {
			 	case 0: try {
					SetCustomerAccount();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 1: try {
					createCA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 2: Deposit(); break;
			 	case 3: Withdraw(); break;
			 	case 4: ShareNewBA(); break;   
			 	case 5: ShareOldBA(); break;
			 	case 6: DeleteBA(); break; // modified whole list for new functions and established ones 
			 	case 7: MoneyTransfer(); break;
			 	case 8: logOut(); break;
			 	default:  // do nothing
			 }
			 
		 } while (choice != commands.length-1);
		 return;
	 } 
	
	
	private void SetCustomerAccount() throws ClassNotFoundException {
		
		String Email = JOptionPane.showInputDialog("Enter a email to work with");
		
		try {
		
		Message newMess = new Message(Email, "loginEmail");
		client.sendMessage(newMess);
		
		
		
		Message returnMess = client.getMessage();
		
		if(returnMess.getType().equals("RequestPass")) {
			String Password = JOptionPane.showInputDialog("Enter the password");
			
			Message newMess1 = new Message(Password, "Password");
			client.sendMessage(newMess1);

			
			Message returnMess1 = client.getMessage();
			if(returnMess1.getType().equals("Success")) {
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, "Succes account selected");
				
			}
			
			
		} 
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	private void createCA() throws ClassNotFoundException, IOException {
		
		
		String Email = JOptionPane.showInputDialog("Enter a new email to use");
		String FirstN = JOptionPane.showInputDialog("Enter a FirstName");
		String LastN = JOptionPane.showInputDialog("Enter a LastName");
		String Pass = JOptionPane.showInputDialog("Enter a Pass");
		
		
		try {
			Message newMess = new Message(Email + " " + FirstN + " " + LastN + " " + Pass, "CreateCA");
			client.sendMessage(newMess);
			
			
			
			Message retrunMess = client.getMessage();
			
			if (retrunMess.getType().equals("Success")) {
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, "Succes account created");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void Deposit() {
		
		
		
		
	}
	
	
	private void Withdraw() {
		
	}
	
	private void ShareNewBA() {
		
	}
	
	private void ShareOldBA() {
		
	}
	
	private void DeleteBA() {
		
	}
	private void MoneyTransfer() {
		
	}

	private void logOut() {
		
	}
	
	
}
