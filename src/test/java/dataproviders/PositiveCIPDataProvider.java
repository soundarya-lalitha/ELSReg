package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class PositiveCIPDataProvider {
	static Logger log = Logger.getLogger(PositiveCIPDataProvider.class);

	@DataProvider(name = "cipclear")
	public static Object[][] getTermiCustomerData() throws Exception {
		log.info("starting to get cipclear data");
		
		String sheetName = "CIP_Clear";
		String filePath = ClassLoader.getSystemResource("TestData.xls").getFile();
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
		
		log.info("exiting the cipclear data provider method");
		return obj;
		
		}
	}
	