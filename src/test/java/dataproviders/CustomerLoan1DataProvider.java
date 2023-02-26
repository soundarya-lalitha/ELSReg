package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class CustomerLoan1DataProvider {
	static Logger log = Logger.getLogger(CustomerLoan1DataProvider.class);

	@DataProvider(name = "customerloan1")
	public static Object[][] getCustomerData() throws Exception {
		log.info("starting to get customer one loan data");
		
		String sheetName = "Create_Cust_Provide_one_Loan";
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
		
		log.info("exiting the customer one loan data provider method");
		return obj;
		
		}
	}
	