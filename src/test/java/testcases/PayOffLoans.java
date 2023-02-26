package testcases;
import org.testng.annotations.Test;

import dataproviders.PayOffLoanDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;
public class PayOffLoans {
	
	@Test(dataProvider="payoff",dataProviderClass=PayOffLoanDataProvider.class)
	public void LoanPayoff(String email,String password,String pathPaidoff, String pathLoanNums,String userType,String browserType) throws Exception
	{
		String loans;
		//login to the ELS application and click on Start Application
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		TestUtil.elslogin(email, password, lpo);
		LoanPayOffPageObject lp= new LoanPayOffPageObject();
		lp.setBrowser(lpo.getBrowser());
		lp.navigateMenu(pathPaidoff);
		//input Loan Number---Loan numbers will be input from a notepad file seperated by #
		//Reading from file.Create Object of java FileReader and BufferedReader classes. 
		loans=lp.readFile(pathLoanNums);
		TestUtil.payOffLoan(lp,loans);
		//exit after clearing all loans
		lp.clkLogOut();
		lp.close();
		
	}
}
		

