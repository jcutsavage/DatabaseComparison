package performance;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 *
 * Driver used to run both implementations of the
 * Query abstract class.
 */
public class QueryDriver {

	public static void main(String[] args) {

		//Query mysql = new MySQLQuery();
		//mysql.run();
	
	   	Query mongoQuery= new MongoDBQuery();
		mongoQuery.initConnection();
		System.out.println("Looking for department based on dept_no");
		System.out.println();
		mongoQuery.departmentQuery("d002");
		System.out.println("Looking for all managers in a department based on dept_no");
		System.out.println();
		mongoQuery.managersQuery("d002");
		System.out.println("Looking an employee based on emp_no");
		System.out.println();
		mongoQuery.employeeQuery(11200);
		System.out.println("Looking for all employees with that share a random first name");
		System.out.println();
		mongoQuery.randomNameQuery("Herbert");
		System.out.println("Looking for all departments that contain at least"
				+ "one employee with a random first name");
		System.out.println();
		mongoQuery.randomNameDepartmentsQuery("Herbert");
		System.out.println("Looking for all employees in a department based on dept_no");
		System.out.println();
		mongoQuery.allEmployeesFromDepartmentQuery("d001");
	}
	

}
