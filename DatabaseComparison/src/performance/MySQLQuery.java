
package performance;

import java.sql.*;

/**
*
*@author Drew Whittaker
* @author John Cutsavage
* @author Sharmeen Jahan
*
* This class is the subclass of Query which implements all queries of mysql.
*
**/
public class MysqlQuery extends Query {
	Connection con;
	
	/**
	* Creates a new mysql connection on the default host. Provide
	* the connection with your username and password to log into MySQL
	*
	* @param username: The user's username.
	* @param password: The user's password.
	*
	*/
	public void initSQLConnection(){
		try {
			String url = "jdbc:mysql://localhost/";
			String dbName = "employees";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root"; 
			String password = "mysql";
			try {
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/employees?user=root&password=mysql");
				 
			} catch (Exception e) {
					  e.printStackTrace();
			}	  
		} finally{}
	}
	
	
	/**
	 * Queries Manager information using employees, dept_manager, departments, dept_emp and salaries Tables.
	 */
	public void managerSalaries(){
		try {
			
			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			
			//query to find the first_name, last_name, department name and current salary of each manager.
			ResultSet res = statement.executeQuery("select employees.first_name, employees.last_name,"
					+ " departments.dept_name,salaries.salary from dept_manager "
					+ "inner join departments on departments.dept_no=dept_manager.dept_no inner join salaries "
					+ "on dept_manager.emp_no=salaries.emp_no inner join employees on employees.emp_no= dept_manager.emp_no"
					+ " where salaries.to_date= (select max(to_date) from salaries where salaries.emp_no= dept_manager.emp_no)");

			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("Time to execute query:" + runTime + " miliseconds.");
			System.out.println("");
			//printing the column name.
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();
			System.out.print(rsmd.getColumnName(1));
			System.out.print("\t");
			System.out.print(rsmd.getColumnName(2));
			System.out.print("\t\t");
			System.out.print(rsmd.getColumnName(3));
			System.out.print("\t\t\t");
			System.out.print(rsmd.getColumnName(4));
			
			System.out.println("");
			System.out.println("");
			
			//Printing the values we got from the query.
			while (res.next()){
				for (int i=1; i<=columnCount; i++){
					
					//System.out.print("\t");
		            String columnValue= res.getString(i);
		            System.out.print(columnValue.trim() + "\t\t");
		            //System.out.print("\t\t");
		            
				}
				System.out.print("\n");
				
			}	
			//releasing memory.
			res.close();
			res = null;
			
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
			
}

		
		
		


