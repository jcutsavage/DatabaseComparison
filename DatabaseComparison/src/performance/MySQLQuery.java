package performance;


import java.sql.*;

/**
* @author Drew Whittaker
* @author John Cutsavage
* @author Sharmeen Jahan
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
					+ "inner join departments on departments.dept_no= dept_manager.dept_no inner join salaries "
					+ "on dept_manager.emp_no=salaries.emp_no and dept_manager.to_date= salaries.to_date inner join employees on employees.emp_no= dept_manager.emp_no"
					+ " where dept_manager.to_date= (select max(to_date) from dept_manager d2 where d2.dept_no= dept_manager.dept_no)");

			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
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
	/** Prints the result of a query
	 * @param ResultSet table
	 **/
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
	
	/**
	 * Queries for department name using department no.
	 * @param deptNo is a string containing the department number from departments table.
	 */
	public void departmentQuery(String deptNo){
		try {

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();

			//query to find the first_name, last_name, department name and current salary of each manager.
			ResultSet res = statement.executeQuery("select dept_name from departments where dept_no='"+ deptNo+ "'");
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

		/** Query for manager's first and last name of a department from dept_manager, departments and 
		 * employees table. 
		 * @param deptNo is ther department number of the managers.
		 **/
		public void managersQuery(String deptNo){
		try{

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			String sql="select first_name, last_name from employees inner join dept_manager on"
				+ " dept_manager.emp_no= employees.emp_no and dept_manager.dept_no='" + deptNo + "'";
			ResultSet res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Query for employee information using the employee number.
	 * @param empNo of the employee that we are looking for. 
	 **/ 
	public void employeeQuery(int empNo){
		try {

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();

			//query to find the first_name, last_name of an employee.
			ResultSet res = statement.executeQuery("select * from employees where emp_no="+ empNo);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** Retrieve all the employees information that match with the first name of the employees.
	 * @param the firstName is the first name of the employees we are looking for.
	**/
	public void randomNameQuery(String firstName){
		try{

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			//query to find the employee information of a given first name.
			String sql="select * from employees where first_name= '"+ firstName +  "'";
			ResultSet res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Retrieve the names of the departments that contain the employees who has this first name.
	 * @param firstName is the first name of the employees.
	**/
	public void randomNameDepartmentsQuery(String firstName){
		try{

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			String sql="select departments.dept_name from departments inner join dept_emp on dept_emp.dept_no= departments.dept_no "
					+ "inner join employees on dept_emp.emp_no= employees.emp_no and employees.first_name= '" + firstName + "'";
			ResultSet res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime); 
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**Retrieve all the employees first and last who works in the department with the dept_no matches with the param.
	* @param deptNo is the number of the deparment of which the employees information we are retrieving.
	**/
	public void allEmployeesFromDepartmentQuery(String deptNo){
		try{

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			String sql="select first_name, last_name from employees inner join dept_emp on dept_emp.emp_no= employees.emp_no inner join"
					+ " departments on departments.dept_no= dept_emp.dept_no and dept_emp.dept_no= '" + deptNo + "'";
			ResultSet res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void averageSalariesQuery(String deptNo){
		
	}
	
	/**Printing the runtime it takes to execute a query.
	 * @param runTime
	 */
	public void printTime(long runTime){
		System.out.println("Time to execute query:" + runTime + " miliseconds."); 
	}

}

		
		
		


