package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class PayOffLoanDataProvider {
	static Logger log = Logger.getLogger(PayOffLoanDataProvider.class);

	@DataProvider(name = "payoff")
	public static Object[][] getCustomerData() throws Exception {
		log.info("starting to get payoff loan data");
		
		String sheetName = "PayOffLoans";
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
		
		log.info("exiting the payoff loan data provider method");
		return obj;
		
		}
	}
	