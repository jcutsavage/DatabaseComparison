package performance.Update;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.Date;

public class Mysql_update extends Abs_update{
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
	
	//updating a record's salary of which the emp_no, salary and from_date will match with the parameters.w
	//@param employee number,old salary, new salary, from_date and to_date.
  	public void updateEmployee(int emp_no, int salary, int newSalary, String fromDate, String toDate){

		initConnection();
		try {
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			
			String sql1 = "UPDATE salaries SET salary= "+ newSalary +
					", from_date= '" + fromDate + "', to_date= '" + toDate + "' where "
							+ "emp_no = " + emp_no + " and salary=" + salary ;
		
			Statement updateStatement, checkStatement;

			updateStatement = con.createStatement();

			updateStatement.executeUpdate(sql1); 
						
			//checking if any record has updated.
			String sql2= "select * from salaries where emp_no=" + emp_no + " and salary= "
					+ newSalary;
			checkStatement= con.createStatement();
			ResultSet chkUpdate = checkStatement.executeQuery(sql2);
						
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();

			if (chkUpdate.next()){
			System.out.println("One record updated.");
			}
			else {
				System.err.println("No record has updated.");
			}
			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("Execute the sql to update.");
			System.out.println("Time to execute = " + runTime + " milliseconds");        

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//reseting the cache...
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
	public void updateEmployee(int emp_no, int salary, int newSalary,
			Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		
	}	 	 
}	
