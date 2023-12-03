package myTesting;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ATMGUI implements ATM {
	
	private Client client;
	
	public ATMGUI(Client c) {
		this.client = c;
	}
	
	public void processCommands() {
		
		
		
		
		try {
			String email = JOptionPane.showInputDialog("Provide Customer email");
			
			Message tellermessage = new Message(email, "CustomerLogin");
			
			client.sendMessage(tellermessage);
			
			
			try {
				Message returnMessTeller = client.getMessage();
				
				
				if (returnMessTeller.getType().equals("ReqPass")) {
					String password = JOptionPane.showInputDialog("Provide Customer password");
					
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
				
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			} catch(IOException e) {
				e.printStackTrace();
			}
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 String[] commands = {
				 	"Deposit", // 0 
				 	"Withdraw", // 1  
				 	"logout"}; // 2
		 
		 int choice;
		 
		 do {
			 choice = JOptionPane.showOptionDialog(null,
					 "Select a command", 
					 "Teller Bank", 
					 JOptionPane.YES_NO_CANCEL_OPTION, 
					 JOptionPane.QUESTION_MESSAGE, 
					 null, 
					 commands,
					 commands[commands.length - 1]);
		 
			 switch (choice) {
			 	case 0: try {
					Deposit();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 			break;
			 	case 1: try {
					Withdraw();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 			break;
			 	case 2: try {
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
	
	
	private void SelectBankAccount() throws ClassNotFoundException {
		
		String id = JOptionPane.showInputDialog("provide id of bank account to deposit: ");
		
		try {
			
			Message newMess = new Message(id, "BankId");
			client.sendMessage(newMess);

			
			Message returnMess = client.getMessage();
			if (returnMess.getType().equals("ProvidePin")) {
				
				
				String pin = JOptionPane.showInputDialog("Provide pin of bank account: ");
				
				Message newMess2 = new Message(pin, "ProvidePin");
				client.sendMessage(newMess2);

				
				Message returnMess2 = client.getMessage();
				
				JFrame frame = new JFrame("Gui");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JOptionPane.showMessageDialog(frame, returnMess2.getText());
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	private void Deposit() throws ClassNotFoundException {
		
		
		
		try {
			
			SelectBankAccount();
			
			String Amount = JOptionPane.showInputDialog("Provide amount to deposit: ");
			Message sendMess = new Message(Amount, "SentAmount");
			client.sendMessage(sendMess);
			
			Message sentMess = client.getMessage();
			
			
			JFrame frame = new JFrame("Gui");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JOptionPane.showMessageDialog(frame, sentMess.getText());
			
			
			
		
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
			
			
			
		}
		
		
		
		
		
		
	}
	
	private void Withdraw() throws ClassNotFoundException {
		
		

		try {
			
			SelectBankAccount();
			
			String Amount = JOptionPane.showInputDialog("Provide amount to withdraw: ");
			Message sendMess = new Message(Amount, "SentAmountWithdraw");
			client.sendMessage(sendMess);
			
			Message sentMess = client.getMessage();
			
			
			JFrame frame = new JFrame("Gui");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JOptionPane.showMessageDialog(frame, sentMess.getText());
			
			
			
		
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	private void logout() throws ClassNotFoundException {
		
		
		try {
			
			Message newMess = new Message("out", "logout");
			client.sendMessage(newMess);
			
			
			Message returnMess = client.getMessage();
			
			
			JFrame frame = new JFrame("Gui");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JOptionPane.showMessageDialog(frame, returnMess.getText());
			
	
			
			
			
			
			
		} catch(IOException e) {
			
			e.printStackTrace();
			
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
}
