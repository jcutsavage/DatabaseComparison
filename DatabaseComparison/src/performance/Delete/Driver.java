import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MysqlJavaAPI {

	public static void main(String[] args) throws SQLException, ParseException {
		
		int emp_no= 32197;
		int newSalary= 1000000;
		
		// Mysql crud_actions...
		Abs_delete mysqlCrud = new Mysql_delete();
		mysqlCrud.initConnection();
		mysqlCrud.deleteEmployee(emp_no, newSalary);
		
		//Mongo crud_actions...
		Abs_delete mongoCrud = new Mongo_delete();
		mongoCrud.initConnection();
		mongoCrud.deleteEmployee(emp_no, newSalary);
	}	
}
	
