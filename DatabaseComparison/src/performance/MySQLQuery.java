
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

public class MySQLQuery extends Query {
	Connection con;
	
	public void run(){
		initConnection();
		managerSalaries();
	}

	
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

			System.out.println();
			
			// Print the query
			printQuery(res);
			
			//releasing memory.
			res.close();
			res = null;	

		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void printQuery(ResultSet res){
		try{
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			
			// Print column names
			StringBuilder fields = new StringBuilder();
			
			for (int i = 1; i <= columnCount; i++){
				fields.append(String.format("%-20s", rsmd.getColumnName(i)) + " ");
			}
			
			System.out.println(fields);
			
			// Print query result
			StringBuilder data = new StringBuilder();

			while (res.next()) {
			    for (int i = 1; i <= columnCount; i++) {
			        data.append(String.format("%-20s",res.getString(i)) + "|");
			    }
			    data.append("\n");
			}
			
			System.out.println(data);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
			
}

		
		
		


