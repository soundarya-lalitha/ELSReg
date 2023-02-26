package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class EligibilityDataProvider {
	static Logger log = Logger.getLogger(EligibilityDataProvider.class);

	@DataProvider(name = "eligibility")
	public static Object[][] getEligibilityData() throws Exception {
		log.info("starting to get data");
		String sheetName = "Loan_Eligibility_Checks";
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
		
		/*int rowCount = xls.getRowCount(sheetName) - 1;
		Object[][] obj = new Object[rowCount][6];

		for (int i = 1; i <= rowCount; i++) {
			obj[i - 1][0] = xls.getCellValue(sheetName, i, 0);
			obj[i - 1][1] = xls.getCellValue(sheetName, i, 1);
			obj[i - 1][2] = xls.getCellValue(sheetName, i, 2);
			obj[i - 1][3] = xls.getCellValue(sheetName, i, 3);
			obj[i - 1][4] = xls.getCellValue(sheetName, i, 4);
			obj[i - 1][5] = xls.getCellValue(sheetName, i, 5);
			//obj[i - 1][5] = Integer.parseInt(xls.getCellValue(sheetName, i, 5));
		}*/
		log.info("exiting the eligibility data provider method");
		return obj;
	}	
}