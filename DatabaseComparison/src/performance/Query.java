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
	
	public abstract void initSQLConnection();
	public abstract void managerSalaries();
}
