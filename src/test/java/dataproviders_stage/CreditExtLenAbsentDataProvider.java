package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class CreditExtLenAbsentDataProvider {
	static Logger log = Logger.getLogger(CreditExtLenAbsentDataProvider.class);

	@DataProvider(name = "ext")
	public static Object[][] getTermiCustomerData() throws Exception {
		log.info("starting to get Credit Ext Lender customer data");
		
		String sheetName = "Credit_ExtLender_Absent";
		String filePath = ClassLoader.getSystemResource("TestDataStage.xls").getFile();
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
		
		log.info("exiting the Credit Ext Lender data provider method");
		return obj;
		
		}
	}
	