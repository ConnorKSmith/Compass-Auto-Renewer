package login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class LoginPage {
	
	public LoginPage(int passcode, String accountname, String username, String password){
		
		JFrame loginFrame = new JFrame();
		loginFrame.setLayout(new FlowLayout());
		JPasswordField passcodefield = new JPasswordField(30);
		JLabel confirmpasscode = new JLabel("Enter your secret passcode below:");
		JLabel incorrect_pass = new JLabel("That is not the correct passcode");
		JLabel success = new JLabel("Your U-Pass has been renewed!");
		JLabel failure = new JLabel("Your U-Pass could not be renewed.");
        
        JButton save = new JButton("Renew U-pass");
        loginFrame.add(confirmpasscode);
        loginFrame.add(passcodefield);
        loginFrame.add(save);
        loginFrame.add(incorrect_pass);
        loginFrame.add(success);
        success.setVisible(false);
        loginFrame.add(failure);
        failure.setVisible(false);
        incorrect_pass.setVisible(false);
        loginFrame.setVisible(true);
        loginFrame.setSize(400, 400);
        
        // If button hit, check to see if passcode is right. If so, create new RenewProcess, and attempt
        // to renew the Compass card. If returned true, display message saying compass renewed. If false, 
        // display message saying renewal failed.
        save.addActionListener(new ActionListener() {
		       @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
		             if(e.getSource() == save) {
		            	 
		                 if (passcodefield.getText().equals(Integer.toString(passcode))){
		                	 incorrect_pass.setVisible(false);
		                	 RenewProcess renew = new RenewProcess(username, password);
		                	 boolean is_renewed = renew.renewCompass();
		                	 if (is_renewed){
		                		 confirmpasscode.setVisible(false);
		                		 passcodefield.setVisible(false);
		                		 save.setVisible(false);
		                		 incorrect_pass.setVisible(false);
		                		 success.setVisible(true);
		                		 
		                	 }
		                	 else {
		                		 confirmpasscode.setVisible(false);
		                		 passcodefield.setVisible(false);
		                		 save.setVisible(false);
		                		 incorrect_pass.setVisible(false);
		                		 failure.setVisible(true);
		                	 }
		                 }
		                 else {
		                	 incorrect_pass.setVisible(true);
		                	 passcodefield.setText("");
		                 }
		              }
		       }
		 });
		
	}
}
