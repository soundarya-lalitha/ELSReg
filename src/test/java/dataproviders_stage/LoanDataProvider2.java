package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class LoanDataProvider2 {
	static Logger log = Logger.getLogger(LoanDataProvider2.class);

	@DataProvider(name = "loan2")
	public static Object[][] getLoanData() throws Exception {
		log.info("starting to get data");
		
		String sheetName = "Loan_Tests";
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
		
		log.info("exiting the loan data provider method");
		return obj;
		
		}
	}
	