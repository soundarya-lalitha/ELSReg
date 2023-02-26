package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class HRChngColNamesDataProvider {
	static Logger log = Logger.getLogger(HRChngColNamesDataProvider.class);

	@DataProvider(name = "HRChngColNames")
	public static Object[][] getHRData() throws Exception {
		log.info("starting to get HR Change Column Names data");
		String sheetName = "Change_Col_Names";
		String filePath = ClassLoader.getSystemResource("TestData_HR.xls").getFile();
		XlsReadWrite xls = new XlsReadWrite(filePath);
		int rowCount = xls.getRowCount(sheetName) - 1;
		int colCount = xls.getCellCount(sheetName, 1);
		Object[][] obj = new Object[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) 
		{	
		for (int j = 0; j <colCount; j++)
		{	
					obj[i - 1][j] = xls.getCellValue(sheetName, i, j);
		}
		}
		log.info("exiting the HR Change Column Names data provider method");
		return obj;
	}	

}
