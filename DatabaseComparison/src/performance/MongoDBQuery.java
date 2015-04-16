package performance;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 *
 * MongoDB implementation of Query class.
 */
public class MongoDBQuery extends Query {
	
	private MongoClient client;
	private DB employeeDB;
	
	public void run(){
		initConnection();
		executeQuery();
	}
	
	/**
	 * Sets up the connection to Mongo server
	 */
	public void initConnection(){
		try{   
	    	  // To connect to mongodb server
	         client = new MongoClient( "localhost" , 27017 );
	         
	         // Now use the employee database
	         employeeDB = client.getDB( "employees" );
	      }
	      catch (Exception e)
	      {
	    	  System.out.println(e.getMessage());
	      }
	}
	
	/**
	 * Execute a specific query in the database.
	 */
	public void executeQuery(){
		
	}
}
