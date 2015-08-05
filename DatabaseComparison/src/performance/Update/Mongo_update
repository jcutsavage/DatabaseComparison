package performance.Update;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class Mongo_update extends Abs_update {

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
	
  	@Override
	public void updateEmployee(int emp_no, int salary, int newSalary,
			String fromDate, String toDate) {
		// TODO Auto-generated method stub

	}

	//update a document in the salaries collection using keys emp_no and from_date.
	//@param employee number, salary, from date and to date of the existing document and 
	//the new salary that we want to update.
	public void updateEmployee(int emp_no, int salary, int newSalary,
			Date fromDate, Date toDate) {

		//taking the time before executing query.
		long startTime = System.currentTimeMillis();

		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("emp_no", emp_no);
		DBCursor cursor = eColl.find(whereQuery);
		while(cursor.hasNext()) {
			DBObject currentEmp=cursor.next();
			Object id=currentEmp.get("_id");
			//Object fDate=currentEmp.get("from_date"); 
			BasicDBObject checkQuery = new BasicDBObject();
			checkQuery.put("emp_no", id);

			checkQuery.put("from_date", fromDate);
			DBCursor cursor1 = sColl.find(checkQuery);

			if (!cursor1.hasNext()){
				System.err.println("Error: collection does not have this record to update.");
			}
			else {
				BasicDBObject oldRow = new BasicDBObject("emp_no",id).append("salary", salary).append("from_date", fromDate).append("to_date", toDate);
				BasicDBObject newRow = new BasicDBObject("emp_no",id).append("salary", newSalary).append("from_date", fromDate).append("to_date", toDate);

				sColl.update(oldRow, newRow);
				System.out.println(newRow);
				System.out.println("successfull");
			}
		}
		//taking the time after executing query.
		long endTime = System.currentTimeMillis();

		//time takes to execute query.
		long runTime = endTime - startTime;
		System.out.println();
		System.out.println("Time to execute = " + runTime + " milliseconds");        


	}
}
