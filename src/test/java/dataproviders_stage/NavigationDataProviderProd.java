package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class NavigationDataProviderProd {
	static Logger log = Logger.getLogger(NavigationDataProviderProd.class);

	@DataProvider(name = "navigate")
	public static Object[][] getNavigationData() throws Exception {
		log.info("starting to get data");
		
		String sheetName = "navigation_checks";
		String filePath = ClassLoader.getSystemResource("TestDataProd.xls").getFile();
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
		
		log.info("exiting the navigation data provider method");
		return obj;
		
		}
	}
	