package performance;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 *
 * Driver used to run both implementations of the
 * Query abstract class.
 */
public class QueryDriver {

	public static void main(String[] args) {

		Query mysql = new MySQLQuery();
		mysql.run();
		
		//Query mongo = new MongoDBQuery();
		//mongo.run();
	
	   	Query mongoQuery= new MongoQuery();
		mongoQuery.initConnection();
	   	mongoQuery.managerSalaries();
	   
	}
	

}
