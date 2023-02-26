
package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class HRUploadDataProvider {
	static Logger log = Logger.getLogger(HRUploadDataProvider.class);

	@DataProvider(name = "HRUploaddata")
	public static Object[][] getCustomerData() throws Exception {
		log.info("starting to get hr upload data");
		
		String sheetName = "Census_Upload";
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
		
		log.info("exiting the hr upload data provider method");
		return obj;
		
		}
	}
	