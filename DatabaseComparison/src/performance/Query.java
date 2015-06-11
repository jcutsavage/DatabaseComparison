package performance;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 * 
 * Abstract class for timing database queries
 *
 */
public abstract class Query {
	
	/**
	 * Execute queries and print out the resulting
	 * table
	 */
	public abstract void run();
	
	/**
	 * Intialize connection to database server.
	 */
	public abstract void initConnection();
	
	public abstract void departmentQuery(String deptNo);
	
	public abstract void managersQuery(String deptNo);
	
	public abstract void employeeQuery(int empNO);
	
	public abstract void randomNameQuery(String firstName);
	
	public abstract void randomNameDepartmentsQuery(String firstName);
	
	public abstract void allEmployeesFromDepartmentQuery(String deptNo);
	
	public abstract void averageSalariesQuery(String deptNo);
}
