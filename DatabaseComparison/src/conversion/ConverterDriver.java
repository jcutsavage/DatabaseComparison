package conversion;

/**
 * 
 * @author John Cutsavage
 * @author Sharmeen Jahan
 * @author Drew Whittaker
 *
 * Class used to run Converter
 */
public class ConverterDriver {

	public static void main(String[] args){
		Converter employeeConverter = new Converter();
		
		employeeConverter.removeDB();
		employeeConverter.initMongoConnection();
		employeeConverter.initSQLConnection();
		
		employeeConverter.copyDepartments();
		employeeConverter.copyEmployees();
		System.out.println("Conversion finished");
	}
}
