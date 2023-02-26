package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class CIPAppInfoDataProvider {
	static Logger log = Logger.getLogger(CIPAppInfoDataProvider.class);

	@DataProvider(name = "cipnotclear2")
	public static Object[][] getTermiCustomerData() throws Exception {
		log.info("starting to get cipappinfo data");
		
		String sheetName = "CIP_App_info";
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
		
		log.info("exiting the cipappinfo data provider method");
		return obj;
		
		}
	}
	