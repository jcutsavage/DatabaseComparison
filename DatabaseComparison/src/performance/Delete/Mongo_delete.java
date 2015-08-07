package performance.Delete;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class Mongo_delete extends Abs_delete {

	MongoClient mongoClient;
	DB db;
	DBCollection sColl, eColl;	//collection variable to get the tables from database.

	//Initialize the connection and get the database from mongodb instance.
	public void initConnection(){
		try{   
			// To connect to mongodb server
			mongoClient = new MongoClient( "localhost" , 27017 );

			// Now use your databases
			db = mongoClient.getDB( "employees_copy" );


			sColl = db.getCollection("salaries");

			eColl = db.getCollection("employees");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
