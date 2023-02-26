package dataproviders;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import excel.XlsReadWrite;

public class LoanDataProvider {
	static Logger log = Logger.getLogger(LoanDataProvider.class);

	@DataProvider(name = "loan")
	public static Object[][] getEligibilityData() throws Exception {
		log.info("starting to get data");
		String sheetName = "Loan_Tests";
		String filePath = ClassLoader.getSystemResource("TestData.xls").getFile();
		XlsReadWrite xls = new XlsReadWrite(filePath);
		int rowCount = xls.getRowCount(sheetName) - 1;
		Object[][] obj = new Object[rowCount][9];
		

		for (int i = 1; i <= rowCount; i++) {
			obj[i - 1][0] = xls.getCellValue(sheetName, i, 0);
			obj[i - 1][1] = xls.getCellValue(sheetName, i, 1);
			obj[i - 1][2] = xls.getCellValue(sheetName, i, 2);
			obj[i - 1][3] = xls.getCellValue(sheetName, i, 3);
			obj[i - 1][4] = xls.getCellValue(sheetName, i, 4);
			obj[i - 1][5] = xls.getCellValue(sheetName, i, 5);
			obj[i - 1][6] = xls.getCellValue(sheetName, i, 6);
			obj[i - 1][7] = xls.getCellValue(sheetName, i, 7);
			obj[i - 1][8] = Integer.parseInt(xls.getCellValue(sheetName, i, 8));
		}
		log.info("exiting the loan data provider method");
		return obj;
	}	
}