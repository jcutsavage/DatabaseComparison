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
	
<<<<<<< HEAD
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
=======
	public abstract void initSQLConnection();
>>>>>>> 3fc510ffa629c281c939621fd3099601e37a0ad7
	public abstract void managerSalaries();
}
