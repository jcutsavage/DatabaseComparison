package performance.Insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;


public class Mysql_Insert extends InsertRecord {
	Connection con;
	
	/**
	 * Creates a new mysql connection on the default host. Provide
	 * the connection with your username and password to log into MySQL
	 *
	 * @param username: The user's username.
	 * @param password: The user's password.
	 *
	 */
	public void initConnection(){
		try {
			String url = "jdbc:mysql://localhost/";
			String dbName = "employees";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root"; 
			String password = "mysql";
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees?user=root&password=password");

			} catch (Exception e) {
				e.printStackTrace();
			}	  
		} finally{}
	}
	
	/**
	 * Insert a record into salaries table.
	 * @param employee number, salary, starting date and end date of that salary.
	 **/
	public void insertEmployee(int emp_no, int salary, String fromDate, String toDate){

		initConnection();
		try {
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			
			String sql = "INSERT INTO salaries (emp_no, salary, from_date, to_date) values(" + emp_no + ","
					+  salary + ",'" + fromDate + "', '" + toDate + "')";
			
			Statement statement;

			statement = con.createStatement();

			statement.executeUpdate(sql); 

			//taking the time after executing query.
			long endTime = System.currentTimeMillis();

			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("Insert a record into salaries table.");
			System.out.println("Time to execute = " + runTime + " milliseconds");        

		} catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}	 

}	
