
package performance.NewQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;


public class MysqlQueries extends Query {
	Connection con;
	int columnCount;
	ResultSet res;

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


	/** this function prints the result set after execution of a query.
	 * 
	 * @param res result set of a query.
	 */
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
		System.out.println();
	}	

	/**
	 * Find the average salary of all employees in the salaries table.
	 */
	public void avgSalary(){
		try{

			Statement statement = con.createStatement();
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			String sql="select  avg(salary) from salaries";
			res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;

			System.out.println("avgSalary");
			System.out.println("Time to execute = " + runTime + " milliseconds");
			//printing the result set.
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the average salary of all employees after a certain date.
	 * @param date is a string which will be using as the date mentioned above.
	 */
	public void avgSalary(String date){
		try{

			Statement indexStatement = con.createStatement();
			Statement chkStatement= con.createStatement();
			Statement queryStatement= con.createStatement();
			
			//checking if there is an existing index on from_date column.
			ResultSet chkIndex;
			String chkSql= "select count(1) IndexIsThere from information_schema.statistics "
					+ "where table_schema= 'employees' and table_name= 'salaries' and index_name= 'sal_from_date'";
			chkIndex = chkStatement.executeQuery(chkSql); 
			chkIndex.next();
			int val= chkIndex.getInt(1);
			// if there is no existing index on from_date, create one.
			if(val == 0){
				String indexSql= "create index sal_from_date on salaries(from_date)";
				indexStatement.executeUpdate(indexSql);
			}	

			long startTime = System.currentTimeMillis();
			String mainSql="select avg(salary) from  "
					+ "   salaries use index (sal_from_date) "
					+ "   where from_date > '" + date + "'";
			res = queryStatement.executeQuery(mainSql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("avgSalary after certain date ");
			System.out.println("Time to execute = " + runTime + " milliseconds");
			//printTime(runTime);

			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is not used in this implementation. See
	 * avgSalary(String date).
	 */
	public void avgSalaryAfterDate(Date date){}

	/**
	 * Get the employee numbers who get salaries above the parameter value.
	 * @param salary is a int value 
	 */
	public void empNoOfCertainSalary(int salary){
		try{

			Statement statement = con.createStatement();
			Statement indexStatement = con.createStatement();
			Statement chkStatement= con.createStatement();
			
			//checking if there is an existing index on salary.
			ResultSet chkIndex;
			String chkSql= "select count(1) IndexIsThere from information_schema.statistics "
					+ "where table_schema= 'employees' and table_name= 'salaries' and index_name= 'sal_salary'";
			chkIndex = chkStatement.executeQuery(chkSql); 
			chkIndex.next();
			int val= chkIndex.getInt(1);
			// if there is no existing index on salary, create one.
			if(val == 0){
				String indexSql= "create index sal_salary on salaries(salary)";
				indexStatement.executeUpdate(indexSql);
			}	

			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			String sql="select   emp_no  from  salaries use index (sal_salary) where  salary  > " + salary;
			res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("empNoOfCertainSalary");
			//printQuery(res);
			System.out.println("Time to execute = " + runTime + " milliseconds");
			//printTime(runTime);
			printQuery(res);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the execution time of finding first name, last name and salary of all employees joining two tables 
	 * employees and salaries. With and without indexes.
	 */
	public void empNameAndSalary(){
		try{

			Statement statement = con.createStatement();
			Statement chkSalStatement= con.createStatement();
			Statement salIndexStatement = con.createStatement();

			//check if there is any existing index on salary table.
			String chksalSql= "select count(1) IndexIsThere from information_schema.statistics "
					+ "where table_schema= 'employees' and table_name= 'salaries' and index_name= 'sal_emp_no'";
			ResultSet resIndex = chkSalStatement.executeQuery(chksalSql); 
			resIndex.next();
			//if there is no existing index, create one on salaries emp_no.
			if(resIndex.getInt(1) == 0){
				String indexSql= "create index sal_emp_no on salaries(emp_no)";
				salIndexStatement.executeUpdate(indexSql);
			}

			Statement chkEmpStatement= con.createStatement();
			Statement empIndexStatement = con.createStatement();
			//check if there is any index on employees table.
			String chkEmpSql= "select count(1) IndexIsThere from information_schema.statistics where"
					+ " table_schema= 'employees' and table_name= 'employees' and index_name= 'emp_emp_no'";
			ResultSet resEmpIndex=chkEmpStatement.executeQuery(chkEmpSql);
			resEmpIndex.next(); 
			//if not, create one on employees emp_no.
			if(resEmpIndex.getInt(1) == 0){
				String empIndexSql= "create index emp_emp_no on employees(emp_no)";
				empIndexStatement.executeUpdate(empIndexSql);
			}

			//Evaluating sql execution time with two indexes...
			//taking the time before executing query.
			long startTime = System.currentTimeMillis();
			//the query to find the first name, last name and salary of each employee with both indexes
			String sql="select e.first_name, e.last_name, s.salary from employees as e "
					+ "use index(emp_emp_no) inner join salaries as s use index(sal_emp_no) "
					+ "on e.emp_no= s.emp_no";
			res = statement.executeQuery(sql);
			//taking the time after executing query.
			long endTime = System.currentTimeMillis();
			//time takes to execute query.
			long runTime = endTime - startTime;
			System.out.println("empNameAndSalary with Index on both employees and salaries tables");
			System.out.println("Time to execute = " + runTime + " milliseconds\n");

			//Evaluating execution time with one Index...
			//taking the time before executing query.
			long startTime1 = System.currentTimeMillis();
			//the query to find the first name, last name and salary of each employee with index on employees.
			String sql1="select e.first_name, e.last_name, s.salary from employees as e use index(emp_emp_no)"
					+ " inner join salaries as s on e.emp_no= s.emp_no";
			ResultSet res1= statement.executeQuery(sql1);
			//taking the time after executing query.
			long endTime1 = System.currentTimeMillis();
			//time takes to execute query.
			long runTime1 = endTime1 - startTime1;
			System.out.println("empNameAndSalary with Index on only employees table");
			System.out.println("Time to execute = " + runTime1 + " milliseconds\n");

			//Evaluating execution time without any indexes...
			//taking the time before executing query.
			long startTime2 = System.currentTimeMillis();
			//the query to find the first name, last name and salary of each employee without using any index.
			String sql2="select e.first_name, e.last_name, s.salary from employees as e "
					+ " inner join salaries as s on e.emp_no= s.emp_no";
			ResultSet res2= statement.executeQuery(sql2);
			//taking the time after executing query.
			long endTime2 = System.currentTimeMillis();
			//time takes to execute query.
			long runTime2 = endTime2 - startTime2;
			System.out.println("empNameAndSalary without any Indexes");
			System.out.println("Time to execute = " + runTime2 + " milliseconds\n");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Reset the query cache for MySQL so queries can
	 * be timed as if they were being run for the first time.
	 */
	public void resetCache(){
		try{
			Statement statement = con.createStatement();
			String sql="flush tables";
			res = statement.executeQuery(sql);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
