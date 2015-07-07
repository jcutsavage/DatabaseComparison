package performance.NewQueries;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver{

	public static void main(String[] args) throws SQLException, ParseException {
		
		Query sqlQuery= new MysqlQueries();
		
		sqlQuery.initConnection();
		sqlQuery.avgSalary();
		String s = "1985-09-08";
		//formatting the string s to date for Mongo queries.
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dFormat.parse(s);
		//reformatting the date to string for mysql queries.
		String dateFormated = dFormat.format(date );
		sqlQuery.avgSalary(dateFormated);
	
		int salary = 150000;
		sqlQuery.empNoOfCertainSalary(salary);
		sqlQuery.empNameAndSalary();
		
		Query mongoQuery = new MongoQueries();
		mongoQuery.initConnection();
		mongoQuery.avgSalary();
		mongoQuery.avgSalaryAfterDate(date);
		mongoQuery.empNoOfCertainSalary(salary);
		
		sqlQuery.resetCache();
	}
}
