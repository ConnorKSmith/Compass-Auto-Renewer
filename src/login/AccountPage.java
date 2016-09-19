package login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import login.database.DatabaseConnection;

public class AccountPage extends JFrame{

	String username;
	String password;
	String accountname;
	String passcode;
	
	public AccountPage(){
		
		
		setLayout(new FlowLayout());
		
		JLabel label1 = new JLabel("Enter account name:");
		JLabel retrievepasscode = new JLabel("Enter secret passcode:");
		JLabel retrieveuser = new JLabel("Enter your university account username:");	
		JLabel retrievepwd = new JLabel("Enter your university account password:");
		
		
		JTextField accountfield = new JTextField(40);
		JTextField userfield = new JTextField(40);
		JTextField passcodefield = new JTextField(40);
		
        JPasswordField pwdfield = new JPasswordField(40);
        
        JButton save = new JButton("Save");
        add(label1);
        add(accountfield);
        add(retrievepasscode);
        add(passcodefield);
        add(retrieveuser);
        add(userfield);
        add(retrievepwd);
        add(pwdfield);
        
        
        add(save);
        setSize(500, 500);
        setVisible(true);
        
        // Save entered info and create new account in database, close this frame
        save.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae) {
		             if(ae.getSource() == save) {
		            	 
		            	 username = userfield.getText();
		            	 password = pwdfield.getText();
		            	 accountname = accountfield.getText();
		            	 passcode = passcodefield.getText();
		            	 String insertStr = "";
		            	 insertStr = "insert into Account(passCode, accountName, userName, password) "
		                         + "values('" + passcode + "' , '" + accountname + "' , '" + username + "' , '" + password + "')";
		            	 ResultSet rs = null;
		            	    Connection connection = null;
		            	    Statement statement = null; 
		            	    
		            	    
		            	    try {           
		            	        connection = DatabaseConnection.getConnection();
		            	        statement = connection.createStatement();
		            	        statement.executeUpdate(insertStr);
		            	        new MenuPage();
		            	            
		            	        
		            	    } catch (SQLException e) {
		            	        e.printStackTrace();
		            	    } finally {
		            	        if (connection != null) {
		            	            try {
		            	                connection.close();
		            	            } catch (SQLException e) {
		            	                e.printStackTrace();
		            	            }
		            	        }
		            	    }
		                 dispose();
		              }
		       }
		 });
        
	}
	
}
