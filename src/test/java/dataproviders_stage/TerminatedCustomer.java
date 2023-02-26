package dataproviders_stage;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class TerminatedCustomer {
	static Logger log = Logger.getLogger(TerminatedCustomer.class);

	@DataProvider(name = "terminate")
	public static Object[][] getTermiCustomerData() throws Exception {
		log.info("starting to get terminated customer data");
		
		String sheetName = "NoCred_Term_Inac_EmpDeacti";
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
		
		log.info("exiting the customer termianted data provider method");
		return obj;
		
		}
	}
	