
package performance.Update;
import java.util.Date;

public abstract class Abs_update {
	
	public abstract void initConnection();
	public abstract void updateEmployee(int emp_no, int salary, int newSalary, String fromDate, String toDate);
	public abstract void updateEmployee(int emp_no, int salary, int newSalary, Date fromDate, Date toDate);
	public abstract void flush();
}
