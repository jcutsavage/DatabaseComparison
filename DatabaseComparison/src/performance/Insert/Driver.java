package performance.Insert
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
	  InsertRecord mysqlInsert = new MysqlInsert();
		mysqlInsert.initConnection();
		
		String from = "1958-08-06";
		DateFormat fDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fDate = fDateFormat.parse(from);
		String fDateFormated=fDateFormat.format(fDate );
		
		String to = "2001-09-08";
		DateFormat tDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date tDate = tDateFormat.parse(to);
		String tDateFormated=tDateFormat.format(tDate );
		
		int emp_no= 32197;
		int eSalary= 678950;

		mysqlInsert.insertEmployee(emp_no,eSalary, fDateFormated, tDateFormated);
		
	//	mysqlInsert.updateEmployee(emp_no, 569832,"1958-08-06", "1997-07-05", "2006-04-07");
	//	mysqlInsert.deleteEmployee(emp_no, "1997-07-05");
	  
	}  
}
