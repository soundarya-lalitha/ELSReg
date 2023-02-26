package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class CustomerLoan2DataProvider {
	static Logger log = Logger.getLogger(CustomerLoan2DataProvider.class);

	@DataProvider(name = "customerloan2")
	public static Object[][] getCustomerData() throws Exception {
		log.info("starting to get two customer loans data");
		
		String sheetName = "Create_Cust_Provide_two_Loan";
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
		
		log.info("exiting the two customer loans data provider method");
		return obj;
		
		}
	}
	