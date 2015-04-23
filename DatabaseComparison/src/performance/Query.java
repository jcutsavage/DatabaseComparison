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
	
	/**
	 * List all managers, their departments, and their salaries.
	 */
	public abstract void managerSalaries();
	
	
}
