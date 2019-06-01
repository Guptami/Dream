package Excel;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

	public Connection getConnection() {
		Connection conn = null;
		try{  
				
			  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
              conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=test", "sa", "enter");
		
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		return conn;  
			}  

	
}
