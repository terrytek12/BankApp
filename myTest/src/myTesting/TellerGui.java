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
		try {
		String email = JOptionPane.showInputDialog("Provide Teller email: ");
		
		Message tellermessage = new Message(email, "TellerLogin");
		
		client.sendMessage(tellermessage);
		
		
		try {
			Message returnMessTeller = client.getMessage();
			
			
			if (returnMessTeller.getType().equals("ReqPass")) {
				String password = JOptionPane.showInputDialog("Provide Teller password: ");
				
				Message tellermessage2 = new Message(password, "SentPass");
				client.sendMessage(tellermessage2);
				
				
				
				returnMessTeller = client.getMessage();
				
				if(returnMessTeller.getType().equals("Fail")) {
					
					JFrame frame = new JFrame("Gui");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					JOptionPane.showMessageDialog(frame, returnMessTeller.getText());
					
					return;
					
					
					
					
				} else {
					
					JFrame frame = new JFrame("Gui");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					JOptionPane.showMessageDialog(frame, returnMessTeller.getText());
					
					
					
					
				}
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	
		
		
		
		
		
		
		
		
		 String[] commands = {
				    "Select Customer Account", //0
				 	"Create CA", // 1 
				 	"Deposit", // 2 
				 	"Withdraw", // 3 
				 	"share new BA", // 4
				 	"share old BA", // 5
				 	"delete BA",  // 6 
				 	"money transfer", // 7
				 	"Create Bank Account", //8
				 	"clear accounts", //9
				 	"logout"}; // 10
		 
		 int choice;
		 
		 do {
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a command: , 
					 "Teller Bank", 
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
			 	case 7: try {
					MoneyTransfer();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 8: try {
					CreateBA();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 9: try {
					Clear();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	case 10: try {
			 		logout();
			 	} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			 	default:  // do nothing
			 }
			 
		 } while (choice != commands.length-1);
		 return;
	 } 
	
	
	private void SetCustomerAccount() throws ClassNotFoundException {
		
		String Email = JOptionPane.showInputDialog("Enter a email to work with: ");
		
		try {
		
		Message newMess = new Message(Email, "loginEmail");
		client.sendMessage(newMess);
		
		
		
		Message returnMess = client.getMessage();
		
		if(returnMess.getType().equals("RequestPass")) {
			String Password = JOptionPane.showInputDialog("Enter the password: ");
			
			Message newMess1 = new Message(Password, "Password");
			client.sendMessage(newMess1);

			
			Message returnMess1 = client.getMessage();
			if(returnMess1.getType().equals("Success")) {
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, "Success account selected");
				
			}
			
			
		} 
		
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		
		
		
	}
	
	private void createCA() throws ClassNotFoundException, IOException {
		
		
		String Email = JOptionPane.showInputDialog("Enter a new email to use: ");
		String FirstN = JOptionPane.showInputDialog("Enter a FirstName: ");
		String LastN = JOptionPane.showInputDialog("Enter a LastName: ");
		String Pass = JOptionPane.showInputDialog("Enter a Password: ");
		
		
		try {
			Message newMess = new Message(Email + " " + FirstN + " " + LastN + " " + Pass, "CreateCA");
			client.sendMessage(newMess);
			
			
			
			Message retrunMess = client.getMessage();
			
			if (retrunMess.getType().equals("Success")) {
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, "Success account created");
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
			String ID = JOptionPane.showInputDialog("Enter a bank id: ");
			
			Message newMess1 = new Message(ID, "BankID");
			client.sendMessage(newMess1);
			
			Message returnMess1 = client.getMessage();
			if(returnMess1.getType().equals("Amount")) {
				
				String amount = JOptionPane.showInputDialog("enter amount to deposit: ");
				
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
				
				String ID = JOptionPane.showInputDialog("Enter a bank id: ");
				
				Message newMess1 = new Message(ID, "BankID");
				client.sendMessage(newMess1);
				
				
				
				Message returnMess1 = client.getMessage();
				if(returnMess1.getType().equals("Amount")) {
					String amount = JOptionPane.showInputDialog("Enter amount to withdraw: ");
					
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
				
				String id = JOptionPane.showInputDialog("Enter a new bank id: ");
			//	String FirstN = JOptionPane.showInputDialog("Enter a FirstName");
			//	String LastN = JOptionPane.showInputDialog("Enter a LastName");
				String balance = JOptionPane.showInputDialog("Enter orignal balance: ");
				String pin = JOptionPane.showInputDialog("Enter a pin to set: ");
	
			
				Message newMess = new Message(id +" " + balance + " " + pin , "ShareNewBA");
				client.sendMessage(newMess);
			
				Message returnMess = client.getMessage();
				if (returnMess.getType().equals("NeedEmail")){
					String Email = JOptionPane.showInputDialog("Enter an email of the account to share with: ");
					
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
			String email = JOptionPane.showInputDialog("Enter email with the account to share with");

			
				
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
			String id = JOptionPane.showInputDialog("Enter a bank id to delete: ");
			Message newMess = new Message(id, "DeleteBA");
			client.sendMessage(newMess);
			
			
			Message returnMess = client.getMessage();
			
			if (returnMess.getType().equals("SendPin")) {
				String pin = JOptionPane.showInputDialog("Enter the pin for the BA to delete: ");
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
	private void MoneyTransfer() throws ClassNotFoundException {
		
		
		try {
			
				
			String id = JOptionPane.showInputDialog("provide id of BA to transfer money out of: ");
			Message newMess = new Message(id, "MoneyTransfer");
			client.sendMessage(newMess);
			
			Message returnMess = client.getMessage();
			
			if (returnMess.getType().equals("SendDetails")) {
				
				String email = JOptionPane.showInputDialog("Provide email of Customer to send to: ");
				String id2 = JOptionPane.showInputDialog("Provide BA of customer to send to: ");
				
				Message newMess2 = new Message(email + " " + id2, "SentInfo");
				client.sendMessage(newMess2);
				
				Message returnMess2 = client.getMessage();
				
				
				if (returnMess2.getType().equals("ProvideAmount"));
					String amount = JOptionPane.showInputDialog("Provide amount to send: ");
					
					Message sendMess2 = new Message(amount, "SentAmountT");
					client.sendMessage(sendMess2);

					
					Message returnMess3 = client.getMessage();
					
					JFrame frame = new JFrame("Gui");
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					JOptionPane.showMessageDialog(frame, returnMess3.getText());
					
					
					
				
				
				
			}
			
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private void CreateBA() throws ClassNotFoundException {
		
			String id = JOptionPane.showInputDialog("Enter a new bank id:");
		//	String FirstN = JOptionPane.showInputDialog("Enter a FirstName");
		//	String LastN = JOptionPane.showInputDialog("Enter a LastName");
			String balance = JOptionPane.showInputDialog("Enter orignal balance:");
			String pin = JOptionPane.showInputDialog("Enter a pin to set:");
			
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
	
	
	

	private void Clear() throws ClassNotFoundException  {
		
	   try {
		   
		   Message sendMess = new Message("clear working customerAccount", "Clear");
		   client.sendMessage(sendMess);
		   
		   
		   
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
	
	
	
	private void logout() throws ClassNotFoundException {
		
		try {
			
			Message newMess = new Message("logout", "logout");
			client.sendMessage(newMess);
			
			Message returnMess = client.getMessage();
			
			
			JFrame frame = new JFrame("Gui");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JOptionPane.showMessageDialog(frame, returnMess.getText());
			
			return;
			
			
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
	}
	
	
}
