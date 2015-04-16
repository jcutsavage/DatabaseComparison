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
	 * Execute query and print out the resulting
	 * table
	 */
	public void run(){}
	
	/**
	 * Intialize connection to database server.
	 */
	public void initConnection(){}
	
	/**
	 * Execute a specific query in the database.
	 */
	public void executeQuery(){}
}
