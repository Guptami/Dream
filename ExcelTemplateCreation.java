package Excel;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTemplateCreation {

	public ExcelTemplateCreation() {
		
	}
	
	public XSSFWorkbook templateCreation(String templateName) throws SQLException {
		Long start=System.currentTimeMillis();
		String templateQuery="SELECT FIELDNAME,FIELDDESCRI,FIELDTYPE FROM EXCELTEMPLATE_FIELDS"
				+ " WHERE SNO=( SELECT SNO FROM EXCELTEMPLATE WHERE  TEMPLATE_NAME='"+templateName+"')";
		CustomUtility utility=new CustomUtility();
		List<Map<String,String>> listData=utility.executeQuery(templateQuery, "");
		System.out.println(listData);
		XSSFWorkbook workbook=writeHeader(listData, templateName);
		long end=System.currentTimeMillis();
		System.out.println((end-start)/1000);
		return workbook;
		
	}
	
	
	public XSSFWorkbook writeHeader(List<Map<String,String>> listData,String templateName) {
		System.out.println("In XSSFWorkbook writeHeader" );
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet(templateName);
		Row r1=sheet.createRow(0);
		Row r2=sheet.createRow(1);
		Row r3=sheet.createRow(2);
		
		int cellNo=0;
		for(Map<String,String> map : listData) {
			r1.createCell(cellNo).setCellValue((String)map.get("FIELDNAME"));
			r2.createCell(cellNo).setCellValue((String)map.get("FIELDDESCRI"));
			r3.createCell(cellNo).setCellValue((String)map.get("FIELDTYPE"));
			cellNo++;
		}
		return workbook;
	}
	
	public String createXLSXFile(XSSFWorkbook workbook, String templateName) {
		String path="D:"+File.separator+"MDO_S_A"+File.separator+"Develop_SA"+File.separator+"Template";
		File targetDir=new File(path);
		targetDir.mkdirs();
		System.out.println(targetDir.mkdirs());
		File targetFile=new File(targetDir.getAbsolutePath()+File.separator+templateName+"_"+System.currentTimeMillis()+".xlsx");
		System.out.println(path);
		try(FileOutputStream outputStream=new FileOutputStream(targetFile)){
			workbook.write(outputStream);
		}
		catch(Exception ex) {
			System.out.println("Exception occured at Workbook write "+ex);
		}
		return targetFile.getName();
	}
	
	public void writeHeaderInDownloadXLSX(){
		
	}
	
	public XSSFWorkbook writeDatainDownloadXLSX(XSSFWorkbook workbook,List<Map<String,String>> dataList,List<String> key,String templateName){
		long start=System.currentTimeMillis();
		System.out.println("Column list "+key);
		XSSFWorkbook wb=workbook;
		XSSFSheet sheet=wb.getSheet(templateName);
		int rowNumber=sheet.getLastRowNum();
		
		for(Map map: dataList)
		{
			Row createRow=sheet.createRow(rowNumber);
			Iterator<String> keyIterator=key.iterator();
			int cellNumber=0;
			while(keyIterator.hasNext()){
				createRow.createCell(cellNumber).setCellValue((String)map.get(keyIterator.next()));
				cellNumber++;
			}
			rowNumber++;
		}
		long end=System.currentTimeMillis();
		System.out.println((end-start)/1000);
		return workbook;

	}
	public static void main(String args[]) throws SQLException {
		ExcelTemplateCreation creation=new ExcelTemplateCreation();
		creation.templateCreation("ABC_MODULE");
	}
}
