package Excel;

import java.sql.SQLException;
import java.util.*;

public class InsertDataInDbFromXLSX {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		InsertDataInDbFromXLSX insert=new InsertDataInDbFromXLSX();
		long start=System.currentTimeMillis();
		CustomUtility utility=new CustomUtility();
		List<Map<String,String>> data=utility.executeQuery("SELECT TOP 1000 FIELDNAME,FIELDDESCRI,FIELDTYPE FROM EXCELTEMPLATE_FIELDS", "");
		System.out.println("Data from DB"+data);
		List<String> inserQuery=insert.insertDataInDbFromXLSX(data,"EXCELTEMPLATE_FIELDS");
		long end=System.currentTimeMillis();
		int insertNoRecord= utility.executeUpdate(inserQuery);
		System.out.println("Time taken to complete the InsertDataInDbFromXLSX  "+((end-start)/1000)+" insert record count "+insertNoRecord);
	}
	
	public  List<String> insertDataInDbFromXLSX(List<Map<String,String>> data,String sheetName) throws SQLException {
		long start=System.currentTimeMillis();
		List<String> insertList=new ArrayList<String>();
		String insertQuery="INSERT INTO "+sheetName+" (";
		String column="";
		String values="";
		
		for(Map<String, String> map: data) {
			Set<String> columnSet=map.keySet();
			column=String.join(",", columnSet);
			Collection<String> valueSet=map.values();
			values=String.join("','", valueSet);
			insertQuery=insertQuery+column+") VALUES ('"+values+"');";
			System.out.println(insertQuery);
			insertList.add(insertQuery);
			insertQuery="INSERT INTO "+sheetName+" (";
		}
		long end=System.currentTimeMillis();
		
		System.out.println("Time taken to insert creation "+((end-start)/1000));
		return insertList;
		
	}

}
