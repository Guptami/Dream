package Excel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomUtility {

	public CustomUtility() {
		
	}
	
	public int executeUpdate(String Query) throws SQLException {
		int rowAffected=0;
		MySqlConnection mysql=new MySqlConnection();
		Connection con=mysql.getConnection();
		Statement stmt=con.createStatement();
		rowAffected=stmt.executeUpdate(Query);
		
		return rowAffected;
	}
	
	public int executeUpdate(List<String> queryList) throws SQLException {
		int rowAffected=0;
		MySqlConnection mysql=new MySqlConnection();
		Connection con=mysql.getConnection();
		Statement stmt=con.createStatement();
		for(String query : queryList) {
		rowAffected=rowAffected+stmt.executeUpdate(query);
		}
		return rowAffected;
	}
	
	public List<Map<String,String>> executeQuery(String Query,String type) throws SQLException{
		List<Map<String,String>> resultList=new ArrayList<Map<String, String>>();
		
		long start=System.currentTimeMillis();
		MySqlConnection mysql=new MySqlConnection();
		Connection con=mysql.getConnection();
		Statement stmt=con.createStatement();
		ResultSet rst=stmt.executeQuery(Query);
		ResultSetMetaData md=rst.getMetaData();
		int colcount=md.getColumnCount();
		while(rst.next()) {
			Map<String,String> map=new HashMap<String,String>();
			for(int i=1;i<=colcount;i++) {
				map.put(md.getColumnName(i), rst.getString(i));
			}
			resultList.add(map);
		}
		long end=System.currentTimeMillis();
		
		System.out.println("Took   "+ ((end-start)/1000)+"  s");
		return resultList;
	}

	
}
