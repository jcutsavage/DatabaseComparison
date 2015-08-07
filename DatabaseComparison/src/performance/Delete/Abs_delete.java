package performance.Delete;
import java.util.Date;


public abstract class Abs_delete {
	
	public abstract void initConnection();
	public abstract void deleteEmployee(int emp_no, int salary, String fromDate);
	public abstract void deleteEmployee(int emp_no, int salary);
	public abstract void flush();
}
