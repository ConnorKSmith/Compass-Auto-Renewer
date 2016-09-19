package login;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import login.database.DatabaseConnection;

public class MenuPage {

	public static void main(String[] args) {
		initiateAccountsList();
		
		}
	// Create Menu frame that shows list of accounts registered, and allows users to 
	// either login to an account they select, create a new account, or refresh the 
	// the list above
	public static void initiateAccountsList(){
		
		JFrame myFrame = new JFrame("Menu");
		myFrame.setLayout(new FlowLayout());
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ArrayList<String> arrayaccounts = new ArrayList<String>();
		int i = 0;
		Connection connection = null;
		ResultSet rs = null;
		Statement statement = null;
		
		connection = DatabaseConnection.getConnection();
        try {
			statement = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        //Grab all accounts 
        String fetch_accounts = "SELECT * FROM Account";
        
        try {
			rs = statement.executeQuery(fetch_accounts);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try {
			while (rs.next()) {
				arrayaccounts.add(i, rs.getString("accountName"));
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //Transfer accountNames from ArrayList to String Array to create JList
        String[] accounts = new String[arrayaccounts.size()+1];
        for (int x = 0; x<arrayaccounts.size(); x++){
        	accounts[x+1] = arrayaccounts.get(x);
        }
		JList<String> list = new JList<String>(accounts);
        list.setPreferredSize(new Dimension(300, 200));
        
        myFrame.add(list);
		JButton register = new JButton("Create Account");
		JButton login = new JButton("Select Account above to Log-In");
		JButton refresh = new JButton("Refresh List Above");
		login.setPreferredSize(new Dimension(250, 20));
		register.setPreferredSize(new Dimension(250, 20));
		refresh.setPreferredSize(new Dimension(250, 20));
		myFrame.add(login);
		myFrame.add(register);
		myFrame.add(refresh);
		myFrame.setSize(500, 500);
		myFrame.setVisible(true);
		login.setEnabled(false);
		
		
		//If an account is selected, enable login button
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	login.setText("Log-in");
                	login.setEnabled(true);
                }
            }
        });
		
		// If refresh is hit, create new Frame to display an updated version of 
		// the accounts list. Dispose current Frame
		refresh.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		             if(e.getSource() == refresh) {
		            	 
		                 initiateAccountsList();
		                 myFrame.dispose();
		              }
		       }
		 });
		// If register is hit, open new page to allow users to create a new account
		register.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		             if(e.getSource() == register) {
		            	 
		                 new AccountPage();
		              }
		       }
		 });
		
		//If login is hit, grab accountName that was selected from list, 
		// fetch information from that account from database, and create new 
		// login page
		login.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae) {
		             if(ae.getSource() == login) {
		            	    String account = list.getSelectedValue().toString();
		            	 	ResultSet rs = null;
		            	    Connection connection = null;
		            	    Statement statement = null; 
		            	    
		            	    
		            	    try {           
		            	        connection = DatabaseConnection.getConnection();
		            	        statement = connection.createStatement();
		            	        String username = "SELECT userName FROM Account WHERE accountName='" + account +"'";
		            	        String password = "SELECT password FROM Account WHERE accountName='" + account +"'";
		            	        String passcode = "SELECT passCode FROM Account WHERE accountName='" + account +"'";
		            	        int code= 0;
		            	        rs = statement.executeQuery(username);
		            	        while (rs.next()) {
		            	            username = rs.getString(1);
		            	            
		            	        }
		            	        rs = statement.executeQuery(password);
		            	        while (rs.next()) {
		            	            password = rs.getString(1);
		            	        }
		            	        rs = statement.executeQuery(passcode);
		            	        while (rs.next()) {
		            	            code = rs.getInt(1);
		            	        }
		            	        new LoginPage(code, account, username, password); 
		            	        
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
		                 
		                 
		              }
		       }
		 });
	}

	
		

			
	

}
