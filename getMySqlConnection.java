package Excel;
import java.sql.Connection;

public class getMySqlConnection {
	public static void main(String args[]) {
		MySqlConnection mysql=new MySqlConnection();
		Connection con=mysql.getConnection();
		
		System.out.println("Connection"+con);
	}

}
