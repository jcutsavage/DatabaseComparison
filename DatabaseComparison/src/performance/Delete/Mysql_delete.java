package performance.Delete;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;

public class Mysql_delete extends Abs_delete{
	Connection con;
	public void initConnection(){
		try{
			String url = "jdbc:mysql://localhost/";
			String dbName = "employees_copy";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root"; 
			String password = "mysql";
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees_copy?user=root&password=mysql");

			} catch (Exception e) {
				e.printStackTrace();
			}	  
		} finally{}
	}
	
	public void deleteEmployee(int emp_no, int salary, String from_date  ){
		
		 initConnection();
		 try {
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
						
		 String sql1 = "DELETE from salaries where emp_no= " + emp_no + " and from_date= '"
		 + from_date + "' and salary= " + salary;
		 
        Statement statement;
		
			statement = con.createStatement();
			int chk= statement.executeUpdate(sql1); 
			
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();

			//time takes to execute query.
			long runTime = endTime - startTime;
			if(chk>0){
			System.out.println("Delete a record from salaries table.");
			}
			else{
				System.out.println("no record effcted.");
			}
			System.out.println("Time to execute = " + runTime + " milliseconds");        
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//reset cache
	public void flush(){
		Statement statement;
		try {
			statement= con.createStatement();
			String sql= "reset query cache";
			statement.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

  @Override
	public void deleteEmployee(int emp_no, int salary) {
		// TODO Auto-generated method stub
		
	}	 
}	

