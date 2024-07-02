package regLoansNewUI;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.GarnishedCustLoanRejectedDataProvider;
import pageobjects.AppInfoPageObject;
import pageobjects.ELSLoginPageObject;
import pageobjects.ReviewDisclosurePageObject;
import testcases.util.TestUtil;

//Author Soundarya Lalitha

public class NoCreditGarnishVerifyLoanRej {
	
	@Test(dataProvider="garnishedCustomer",dataProviderClass=GarnishedCustLoanRejectedDataProvider.class,groups= {"Regression"})
	public void verifyGarnishment(String email,String password,String ssnvalue,String homephonevalue,String wrkemail,String add,String city,String state,String workstate,String expectedmessage,String userType,String browserType) throws Exception
	{
		
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		TestUtil.elslogin(email, password, lpo);	
		
		Thread.sleep(5000);
		lpo.clickNoCreditLoan();
		Thread.sleep(5000);
		//Click on New Loan link
		lpo.clickNewLoan();
		//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
		//ReviewDisclosurePageObject rd1 =TestUtil.reviewdisclosure(lpo1);
		Thread.sleep(5000);
		//---enter application info---enter ssn	and home phone
		AppInfoPageObject ai2= TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lpo);
		String actualError=ai2.getMessage();

		//compare error and assert the values
		if (actualError.equalsIgnoreCase(expectedmessage))
		{
			Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
		}
		else
		{
			Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
		}
		
		 //click finish
		  ai2.clkFinish();  
		  ai2.clkLogOut();
		// close the browser
		 ai2.close();		
		
		 Assert.assertEquals(actualError,expectedmessage);
	}
}
