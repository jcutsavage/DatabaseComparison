package performance.Insert;

import java.util.Date;

public abstract class InsertRecord

{
	public void executeQuery(){}
	public abstract void initConnection();
  	public abstract void insertEmployee(int emp_no, int salary, String fromDate, String toDate);
	
}
