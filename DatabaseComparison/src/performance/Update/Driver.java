package performance.Update;
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
	
		// Mysql crud_actions...
		abs_crud_actions mysqlCrud = new MysqlCrudActions();
		mysqlCrud.initConnection();
		
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
		int newSalary= 1000000;
	
		mysqlCrud.updateEmployee(emp_no, eSalary, newSalary, fDateFormated, tDateFormated);
		
		

		//Mongo crud_actions...
		MongoCrudActions mongoCrud = new MongoCrudActions();
		mongoCrud.initConnection();
		
		mongoCrud.updateEmployee(emp_no, eSalary,newSalary, fDate, tDate);
	
	}	
}
