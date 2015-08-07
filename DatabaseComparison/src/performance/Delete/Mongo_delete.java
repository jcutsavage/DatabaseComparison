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
	
	//Delete a document of the salaries table using the keys emp_no and salary.
	//@param employee number and salary.
	public void deleteEmployee(int emp_no, int salary) { 
		//taking the time before executing query.
		long startTime = System.currentTimeMillis();

		BasicDBObject findID = new BasicDBObject();
		findID.put("emp_no", emp_no);
		DBCursor cursor = eColl.find(findID);
		while(cursor.hasNext()) {
			DBObject currentEmp=cursor.next();
			Object id=currentEmp.get("_id");

			BasicDBObject findDocs = new BasicDBObject();
			findDocs.put("emp_no", id);
			findDocs.put("salary", salary);
			DBCursor curDelete = sColl.find(findDocs);
			if (curDelete.hasNext()){
				DBObject currDoc= curDelete.next();
				sColl.remove(currDoc);
			}
			else{
				System.err.println("error while deleting: no such document");
			}
		}
		//taking the time after executing query.
		long endTime = System.currentTimeMillis();

		//time takes to execute query.
		long runTime = endTime - startTime;
		System.out.println();
		System.out.println("(Delete) Time to execute = " + runTime + " milliseconds");
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(int emp_no, int salary, String fromDate) {
		// TODO Auto-generated method stub
		
	}
}
