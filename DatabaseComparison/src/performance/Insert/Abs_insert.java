package performance.Insert;

import java.util.Date;

public abstract class Abs_insert

{
	public abstract void initConnection();
  	public abstract void insertEmployee(int emp_no, int salary, String fromDate, String toDate);
	public abstract void insertEmployee(int emp_no, int salary, Date fromDate, Date toDate);
	public abstract void flush();
}
