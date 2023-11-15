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
				    "Enter Customer Account", //0
				 	"Create CA", // 1 
				 	"Deposit", // 2 
				 	"Withdraw", // 3 
				 	"share new BA", // 4
				 	"share old BA", // 5
				 	"delete BA",  // 6 
				 	"money transfer", // 7
				 	"Create Bank Account",
				 	"clear customer account"}; // 9
		 
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
			 	case 2: try {
					Deposit();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 3: try {
					Withdraw();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 4: try {
					ShareNewBA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;   
			 	case 5: try {
					ShareOldBA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 6: try {
					DeleteBA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break; // modified whole list for new functions and established ones 
			 	case 7: MoneyTransfer(); break;
			 	case 8: try {
					CreateBA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 9: logOut(); break;
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
	
	
	private void Deposit() throws ClassNotFoundException {
		
		try {
		Message newMess = new Message("Hello", "Deposit");
		client.sendMessage(newMess);
		
		Message returnMess = client.getMessage();
		
		if(returnMess.getType().equals("SendBankId")) {
			String ID = JOptionPane.showInputDialog("Enter a bank id");
			
			Message newMess1 = new Message(ID, "BankID");
			client.sendMessage(newMess1);
			
			Message returnMess1 = client.getMessage();
			if(returnMess1.getType().equals("Amount")) {
				
				String amount = JOptionPane.showInputDialog("enter amount to deposit");
				
				Message newMess2 = new Message(amount, "SentAmount");
				client.sendMessage(newMess2);
				
				
				Message returnMess2 = client.getMessage();
				
				if(returnMess2.getType().equals("BalanceCheck")) {
					
					
					JFrame frame = new JFrame("Gui");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					JOptionPane.showMessageDialog(frame, returnMess2.getText());
					
					
					
				}
				
				
				
			}
			
			
			
		}
		
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void Withdraw() throws ClassNotFoundException {
		
		try {
			Message newMess = new Message("Hello", "Withdraw");
			client.sendMessage(newMess);
			
			Message returnMess = client.getMessage();
			
			
			if(returnMess.getType().equals("SendBankId")) {
				
				String ID = JOptionPane.showInputDialog("Enter a bank id");
				
				Message newMess1 = new Message(ID, "BankID");
				client.sendMessage(newMess1);
				
				
				
				Message returnMess1 = client.getMessage();
				if(returnMess1.getType().equals("Amount")) {
					String amount = JOptionPane.showInputDialog("enter amount to withdraw");
					
					Message newMess2 = new Message(amount, "WithdrawAmount");
					client.sendMessage(newMess2);
					
					Message returnMess2 = client.getMessage();
					
					if(returnMess2.getType().equals("BalanceCheck")) {
						
						
						JFrame frame = new JFrame("Gui");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						
						JOptionPane.showMessageDialog(frame, returnMess2.getText());
						
						
						
					}
					
					
					
					
				}
				
				
				
			
				
				
			}
			
			
			
			
		
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	private void ShareNewBA() throws ClassNotFoundException {
		
		
		try {
				
				String id = JOptionPane.showInputDialog("Enter a new bank id");
			//	String FirstN = JOptionPane.showInputDialog("Enter a FirstName");
			//	String LastN = JOptionPane.showInputDialog("Enter a LastName");
				String balance = JOptionPane.showInputDialog("Enter orignal balance");
				String pin = JOptionPane.showInputDialog("Enter a pin to set");
	
			
				Message newMess = new Message(id +" " + balance + " " + pin , "ShareNewBA");
				client.sendMessage(newMess);
			
				Message returnMess = client.getMessage();
				if (returnMess.getType().equals("NeedEmail")){
					String Email = JOptionPane.showInputDialog("Enter a Email of account to share with");
					
					Message newMess1 = new Message(Email, "ShareEmail");
					client.sendMessage(newMess1);
					
					
					Message returnMess1 = client.getMessage();
					
					
					if(returnMess1.getType().equals("Success")) {
						
						JFrame frame = new JFrame("Gui");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						
						JOptionPane.showMessageDialog(frame, returnMess1.getText());
						
						
					}
					
					
					
					
				}
				
				
				
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
	
	private void ShareOldBA() throws ClassNotFoundException {
		
		
		
		try {
				
			String id = JOptionPane.showInputDialog("Enter a bank id to share");
			String email = JOptionPane.showInputDialog("Enter email with account to share with");

			
				
			Message newMess = new Message(id + " " + email, "ShareOldBA");
			client.sendMessage(newMess);	
			
			
			Message returnMess = client.getMessage();
			
			if (returnMess.getType().equals("Success")) {
				
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, returnMess.getText());
				
				
				
			}
				
				
				
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	private void DeleteBA() throws ClassNotFoundException  {
		
		
		try {
			String id = JOptionPane.showInputDialog("Enter a bank id to delete");
			Message newMess = new Message(id, "DeleteBA");
			client.sendMessage(newMess);
			
			
			Message returnMess = client.getMessage();
			
			if (returnMess.getType().equals("SendPin")) {
				String pin = JOptionPane.showInputDialog("Enter the pin for the BA to delete");
				Message newMess1 = new Message("pin", "Pin");
				client.sendMessage(newMess1);
				
				Message returnMess1 = client.getMessage();
				
				if(returnMess1.getType().equals("Success")) {
					
					JFrame frame = new JFrame("Gui");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					JOptionPane.showMessageDialog(frame, returnMess1.getText());
					
					
					
					
				}
				
				
				
			}
			
			
			
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
			
			
		}
		
		
		
		
		
		
		
		
	}
	private void MoneyTransfer() {
		
	}
	
	private void CreateBA() throws ClassNotFoundException {
		
			String id = JOptionPane.showInputDialog("Enter a new bank id");
		//	String FirstN = JOptionPane.showInputDialog("Enter a FirstName");
		//	String LastN = JOptionPane.showInputDialog("Enter a LastName");
			String balance = JOptionPane.showInputDialog("Enter orignal balance");
			String pin = JOptionPane.showInputDialog("Enter a pin to set");
			
			try {
				
					Message newMess = new Message(id + " " + balance + " " + pin, "CreateBA");
					client.sendMessage(newMess);
					
					Message returnMess = client.getMessage();
				
					if (returnMess.getType().equals("Success")) {
						
						JFrame frame = new JFrame("Gui");
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						
						JOptionPane.showMessageDialog(frame, returnMess.getText());
						
						
						
					}
				
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		
	}
	
	
	

	private void logOut() throws ClassNotFoundException  {
		
	   try {
		   
		   Message sendMess = new Message("clear working customerAccount", "logout");
		   client.sendMessage(sendMess);
		   
		   
		   
		   Message returnMess = client.getMessage();
		   
		   if (returnMess.getType().equals("logout")) {
			   
			   JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, returnMess.getText());
			   
			   
		   }
		   
		   
		   
		   
	   } catch (IOException e) {
		   
		   e.printStackTrace();
		   
		   
	   }
		
		
		
		
	}
	
	
}
