package myTesting;
import java.awt.*;



import myTesting.TellerInterface;


public class TellerGui implements TellerInterface {
	
	
	private Client client;
	
	public TellerGui(Client client) {
		this.client = client;
	}
	
	
	
	public void processCommands()
	 {
		 String[] commands = {
				 	"Set Customer Account",
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
			 	case 0: doAddOrModifyDVD(); break;
			 	case 1: doRemoveDVD(); break;
			 	case 2: doGetDVDsByRating(); break;
			 	case 3: doGetTotalRunningTime(); break;
			 	case 4: makeWatchList(); break;   
			 	case 5: viewDvD(); break;
			 	case 6: editDvD(); break; // modified whole list for new functions and established ones 
			 	case 7: viewWatchList(); break;
			 	case 8: doSave(); break;
			 	default:  // do nothing
			 }
			 
		 } while (choice != commands.length-1);
		 return;
	 } 
	
	
	private void SetCustomerAccount() {
		
		
		
		
	}

	
	
}
