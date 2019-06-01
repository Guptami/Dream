package Excel;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXLSXCreateListOfMap {

	public List<Map<String, String>> createXLSXListMap(String filePath) {
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

		try {
			String fileName = filePath;
			FileInputStream fileToRead = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fileToRead);
			XSSFSheet sheet = workbook.getSheetAt(0);
			String sheetName = workbook.getSheetName(0);

			int rowIteration = sheet.getLastRowNum();
			int columnIteration = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println(rowIteration + "  " + columnIteration);

			for (int i = 3; i <= rowIteration; i++) {
				Map<String, String> map = new HashMap<String, String>();
				Row row = sheet.getRow(i);
				Row fieldRow = sheet.getRow(0);
				if (row != null) {
					for (int j = 0; j < columnIteration; j++) {
						Cell dataCell = row.getCell(j);
						if (!(dataCell == null )) {
							Cell fieldCell = fieldRow.getCell(j);
								DataFormatter fmt=new DataFormatter();
								String ValueAsSeenInExcel=fmt.formatCellValue(dataCell);
								map.put(fieldCell.getStringCellValue(),
										ValueAsSeenInExcel);
						}
					}
					dataList.add(map);
				}
			}

		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception occur at reading XLSX file " + e);
		}

		return dataList;
	}

}
