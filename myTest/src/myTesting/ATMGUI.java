package myTesting;

import javax.swing.*;
import java.io.IOException;

public class ATMGUI implements ATM {

    private Client client;

    // Constructor to initialize the ATM GUI with a client
    public ATMGUI(Client c) {
        this.client = c;
    }

    // Main method to process user commands after authentication
    public void processCommands() {
        authenticateUser();
        executeUserCommands();
    }

    // Method to authenticate the user
    private void authenticateUser() {
        try {
            // Prompting user to enter email and sending it to the server
            String email = JOptionPane.showInputDialog("Provide Customer email");
            client.sendMessage(new Message(email, "CustomerLogin"));
            handlePasswordRequest();
        } catch (IOException e) {
            // Displaying an error dialog in case of an IOException
            showErrorDialog(e.getMessage());
        }
    }

    // Method to handle password request from the server
    private void handlePasswordRequest() throws IOException {
        try {
            Message returnMessTeller = client.getMessage();
            if (isPasswordRequest(returnMessTeller)) {
                // Prompting user to enter password and sending it to the server
                String password = JOptionPane.showInputDialog("Provide Customer password");
                client.sendMessage(new Message(password, "SentPass"));
                handleLoginResponse();
            }
        } catch (ClassNotFoundException e) {
            // Displaying an error dialog in case of a ClassNotFoundException
            showErrorDialog(e.getMessage());
        }
    }

    // Method to handle login response from the server
    private void handleLoginResponse() throws IOException, ClassNotFoundException {
        Message returnMessTeller = client.getMessage();
        // Displaying the message from the server
        JOptionPane.showMessageDialog(new JFrame(), returnMessTeller.getText());
    }

    // Method to execute user commands after successful authentication
    private void executeUserCommands() {
        String[] commands = {"Deposit", "Withdraw", "Logout"};
        int choice;
        do {
            // Displaying command options to the user and handling user's choice
            choice = showCommandDialog(commands);
            switch (choice) {
                case 0: performDeposit(); break;
                case 1: performWithdraw(); break;
                case 2: logout(); break;
                default: // Do nothing if an invalid choice is made
            }
        } while (choice != commands.length - 1); // Loop until 'Logout' is chosen
    }

    // Method to handle deposit functionality
    private void performDeposit() {
        try {
            // Asking user for bank ID and amount, and sending the details to the server
            String bankId = JOptionPane.showInputDialog("Enter bank account ID for deposit:");
            String amount = JOptionPane.showInputDialog("Enter amount to deposit:");
            client.sendMessage(new Message(bankId + " " + amount, "DepositRequest"));

            // Receiving and showing server's response to the user
            Message response = client.getMessage();
            JOptionPane.showMessageDialog(new JFrame(), response.getText());
        } catch (IOException | ClassNotFoundException e) {
            // Displaying an error dialog in case of an exception
            showErrorDialog("Error during deposit: " + e.getMessage());
        }
    }

    // Method to handle withdrawal functionality
    private void performWithdraw() {
        try {
            // Asking user for bank ID and amount, and sending the details to the server
            String bankId = JOptionPane.showInputDialog("Enter bank account ID for withdrawal:");
            String amount = JOptionPane.showInputDialog("Enter amount to withdraw:");
            client.sendMessage(new Message(bankId + " " + amount, "WithdrawRequest"));

            // Receiving and showing server's response to the user
            Message response = client.getMessage();
            JOptionPane.showMessageDialog(new JFrame(), response.getText());
        } catch (IOException | ClassNotFoundException e) {
            // Displaying an error dialog in case of an exception
            showErrorDialog("Error during withdrawal: " + e.getMessage());
        }
    }

    // Method to handle logout functionality
    private void logout() {
        try {
            // Sending logout message to the server
            client.sendMessage(new Message("", "LogoutRequest"));

            // Receiving and showing server's response to the user
            Message response = client.getMessage();
            JOptionPane.showMessageDialog(new JFrame(), response.getText());

            // Optionally, close the application or return to the login screen
        } catch (IOException | ClassNotFoundException e) {
            // Displaying an error dialog in case of an exception
            showErrorDialog("Error during logout: " + e.getMessage());
        }
    }

    // Helper method to check if the server has requested a password
    private boolean isPasswordRequest(Message message) {
        return "ReqPass".equals(message.getType());
    }

    // Helper method to check if the login attempt failed
    private boolean isLoginFailed(Message message) {
        return "Fail".equals(message.getType());
    }

    // Method to show an error dialog
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(new JFrame(), errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to show the command dialog and return the user's choice
    private int showCommandDialog(String[] commands) {
        return JOptionPane.showOptionDialog(null,
                "Select a command",
                "ATM Menu",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                commands,
                commands[commands.length - 1]);
    }
}