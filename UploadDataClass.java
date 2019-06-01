package Excel;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UploadDataClass {
	
	public static void main(String args[]) throws SQLException{
		long start=System.currentTimeMillis();
		ReadXLSXCreateListOfMap readXLSX=new ReadXLSXCreateListOfMap();
		File file=new File("D:\\MDO_S_A\\Develop_SA\\Template\\MDOMAPPING.xlsx");
		List<Map<String,String>> dataList=readXLSX.createXLSXListMap("D:\\MDO_S_A\\Develop_SA\\Template\\MDOMAPPING.xlsx");
		
		System.out.println(dataList);

		InsertDataInDbFromXLSX insertData=new InsertDataInDbFromXLSX();
		List<String> inserQuery=insertData.insertDataInDbFromXLSX(dataList,"MDOMAPPING");
		
		System.out.println("Insert Query "+inserQuery);
		CustomUtility utility=new CustomUtility();
		utility.executeUpdate(inserQuery);
		
		long end=System.currentTimeMillis();
		System.out.println("Time taken to upload the read from excel"+((end-start)/1000)+" s");
	}

}
