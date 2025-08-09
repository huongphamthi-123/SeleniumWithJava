package HuongTestingPractice.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class DataReader {

	// Identify Testcases coloum by scanning the entire 1st row
	// once coloumn is identified then scan entire testcase coloum to identify
	// purcjhase testcase row
	// after you grab purchase testcase row = pull all the data of that row and feed
	// into test

	public static List<HashMap<String, String>> getData() throws IOException {
		List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

		FileInputStream fis = new FileInputStream(
				"D:\\HUONG\\Testing\\Automation-Practice\\Java\\SeleniumJavaFW\\src\\test\\resources\\ExcelData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row r = rows.next();
				Iterator<Cell> irow = r.cellIterator();

				ArrayList<String> header = new ArrayList<String>(); // header
				while (irow.hasNext()) {
					Cell c = irow.next();
					header.add(c.getStringCellValue());
				}

				while (rows.hasNext()) {
					r = rows.next();
					irow = r.cellIterator();
					HashMap<String, String> map = new HashMap<String, String>();
					for (String h : header) {
						Cell c = irow.next();
						String v = getCellValue(c);
						map.put(h, v);
					}
					data.add(map);
				}
			}
		}

		return data;
	}

	public static String getCellValue(Cell c) {
		if (c.getCellType() == CellType.STRING) {
			return c.getStringCellValue();
		} else {
			return NumberToTextConverter.toText(c.getNumericCellValue());
		}
	}

	public ArrayList<String> getData(String testcaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(
				"D:\\HUONG\\Testing\\Automation-Practice\\Java\\SeleniumJavaFW\\src\\test\\resources\\ExcelData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify Testcases coloum by scanning the entire 1st row

				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int coloumn = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();

					if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						coloumn = k;
					}

					k++;
				}
				System.out.println(coloumn);

				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {

					Row r = rows.next();

					if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName)) {

						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test

						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {

								a.add(c.getStringCellValue());
							} else {

								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}
						}
					}

				}
			}
		}
		workbook.close();
		return a;
	}
}
