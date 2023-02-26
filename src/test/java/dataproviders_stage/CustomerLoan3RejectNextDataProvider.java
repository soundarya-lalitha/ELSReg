package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class CustomerLoan3RejectNextDataProvider {
	static Logger log = Logger.getLogger(CustomerLoan3RejectNextDataProvider.class);

	@DataProvider(name = "customerloan3andreject")
	public static Object[][] getCustomerData() throws Exception {
		log.info("starting to get customer one loan data");
		
		String sheetName = "Customer_3Loan_Reject_Next";
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
		
		log.info("exiting the customer one loan data provider method");
		return obj;
		
		}
	}
	