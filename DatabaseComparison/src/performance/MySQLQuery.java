package performance;

import java.sql.*;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 * 
 * MySQL implementation of Query class.
 *
 */
public class MySQLQuery extends Query {
	
	private Connection con;
	
	public void run(){
		initConnection();
	}
	
	/**
	 * Initialize connection to MySQL server and
	 * begin using the employee database.
	 */
	public void initConnection(){
		try{
			//String url = "jdbc:mysql://localhost/";
			//String dbName = "employees";
			//String driver = "com.mysql.jdbc.Driver";
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees?user=root&password=mysql");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}	  
		   } 
		finally{}
	}
}
