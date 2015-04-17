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
		Query sqlQuery= new MysqlQuery();
		sqlQuery.initSQLConnection();
		sqlQuery.managerSalaries();
	}

}
