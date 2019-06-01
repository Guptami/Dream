package Excel;

import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CallingClass {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String templateName="ABC_MODULE";
		ExcelTemplateCreation creation =new ExcelTemplateCreation();
		XSSFWorkbook workbook=creation.templateCreation("ABC_MODULE");
		String FileName=creation.createXLSXFile(workbook,templateName);
		System.out.println(FileName);
	}

}
