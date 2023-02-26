package excel;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsReadWrite {
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private HSSFWorkbook wb;

	/**
	 * create the xls read write object with file information
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public XlsReadWrite(String filePath) throws Exception {
		file = new File(filePath);
		fis = new FileInputStream(file);
		wb = new HSSFWorkbook(fis);
		fis.close();
	}

	/**
	 * get the row count of given sheet name This is not index based count! it
	 * will return the actual row count
	 * 
	 * @param sheetName
	 * @return row count
	 */
	public int getRowCount(String sheetName) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		// In case of wrong sheet name return 0 row count
		if (sheet == null) {
			return 0;
		}
		return sheet.getLastRowNum() + 1;
	}

	/**
	 * Get the cell count of a particular row of the sheet
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @return cell count
	 */
	public int getCellCount(String sheetName, int rowIndex) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return 0;
		}
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			return 0;
		}
		return row.getLastCellNum();
	}

	/**
	 * return string cell value
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return String cell value
	 */
	public String getCellValue(String sheetName, int rowIndex, int cellIndex) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			return "sheet doesn't exist for sheet name : " + sheetName;
		}
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			return "row doesn't exist for index : " + rowIndex;
		}
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			return "cell doesn't exist for row index : " + rowIndex
					+ " and cell index : " + cellIndex;
		}
		return cell.toString();
	}

	/**
	 * Write numeric value to the cell
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param value
	 * @throws IOException
	 */
	public void writeNumericCellValue(String sheetName, int rowIndex,
			int cellIndex, double value) throws IOException {
		HSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	/**
	 * save to excel file
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void write() throws FileNotFoundException, IOException {
		fos = new FileOutputStream(file);
		wb.write(fos);
		fos.close();
	}

	/**
	 * Write String value to the cell
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param value
	 * @throws IOException
	 */
	public void writeStringCellValue(String sheetName, int rowIndex,
			int cellIndex, String value) throws IOException {
		HSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	/**
	 * Write Boolean value to the cell
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param value
	 * @throws IOException
	 */
	public void writeBooleanCellValue(String sheetName, int rowIndex,
			int cellIndex, Boolean value) throws IOException {
		HSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	/**
	 * Write Date value to the cell
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param value
	 * @throws IOException
	 */
	public void writeDateCellValue(String sheetName, int rowIndex,
			int cellIndex, Date value) throws IOException {
		HSSFCell cell = getCell(sheetName, rowIndex, cellIndex);
		cell.setCellValue(value);
		write();
	}

	/**
	 * get the cell object
	 * 
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return the cell object
	 */
	private HSSFCell getCell(String sheetName, int rowIndex, int cellIndex) {
		HSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			sheet = wb.createSheet(sheetName);
			
			
		}
		HSSFRow row = sheet.getRow(rowIndex);
		if (row == null) {
			row = sheet.createRow(rowIndex);
		}
		HSSFCell cell = row.getCell(cellIndex);
		if (cell == null) {
			cell = row.createCell(cellIndex);
		}
		return cell;
	}
}
