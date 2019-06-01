package Excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static void main(String args[]) throws IOException {
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet createSheet=workbook.createSheet("Master Data");
		createSheet.createRow(0).createCell(0).setCellValue("1");
		FileOutputStream outputStream=new FileOutputStream("Output1.xlsx");
		workbook.write(outputStream);
		outputStream.close();
	//	workbook.close();
	}
}
