package performance.Delete;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Driver {

	public static void main(String[] args) throws SQLException, ParseException {
	
		String from = "1958-08-06";
		DateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fDate = fDateFormat.parse(from);
		String fDateFormated=fDateFormat.format(fDate );
		
		String to = "2001-09-08";
		DateFormat tDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date tDate = tDateFormat.parse(to);
		String tDateFormated=tDateFormat.format(tDate );
		
		int emp_no= 32197;
		int eSalary= 671950;
		
		//Mysql...
		Abs_insert mysqlCrud = new Mysql_insert();
		mysqlCrud.initConnection();
		mysqlCrud.insertEmployee(emp_no, eSalary, fDateFormated, tDateFormated);
		mysqlCrud.flush();
		
		//Mongo...
		mongoCrud.initConnection();
		abs_insert mongoCrud = new Mongo_insert();
		mongoCrud.insertEmployee(emp_no, eSalary, fDate, tDate);
	
	}	
}
