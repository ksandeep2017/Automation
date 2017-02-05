package GenericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectOracle10gDB 
{
	
	public static void displayDBName() throws Throwable
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","sandeep");
		PreparedStatement stm=connection.prepareStatement("select name from v$database");
		ResultSet rs=stm.executeQuery();
		rs.next();
		System.out.println( "DataBase Name: " + rs.getString(1));
				
	}
	
	
	
	
	
	

}
