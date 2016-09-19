
	package login.database;
	 
	//Step 1: Use interfaces from java.sql package 
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	 
	public class DatabaseConnection {
	    //static reference to itself
	    private static DatabaseConnection instance = new DatabaseConnection();
	    public static final String URL = "jdbc:mysql://localhost/compass"; //your MySQL server destination here
	    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	     
	    
	    DatabaseConnection() {
	        try {
	            //Step 2: Load MySQL Java driver
	            Class.forName(DRIVER_CLASS);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	     
	    Connection createConnection() {
	 
	        Connection connection = null;
	        try {
	            //Step 3: Establish Java MySQL connection
	            connection = DriverManager.getConnection(URL, "<MySQL root here>", "<MySQL server password here>");
	        } catch (SQLException e) {
	            System.out.println("ERROR: Unable to Connect to Database.");
	        }
	        return connection;
	    }   
	     
	    public static Connection getConnection() {
	        return instance.createConnection();
	    }
	}
	

