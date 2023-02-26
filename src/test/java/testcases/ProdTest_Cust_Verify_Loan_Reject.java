/*package testcases;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageobjects.*;


import dataproviders.CustomerRejectLoanDataProvider;

import testcases.util.TestUtil;

public class ProdTest_Cust_Verify_Loan_Reject {
	
	LoanConfirmationPageObject lc;
	
	
	@Test(dataProvider="customerloanreject",dataProviderClass=CustomerRejectLoanDataProvider.class)
	public void customer_Loan_Reject(String email,String password,String ssnvalue,String homephonevalue,String lastname,String zip,String dob,String question1,String question2,String add,String city,String state,String expectedmessage, String browserType) throws Exception
	{
	//Customer should already exist in case running in production
				ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType);
				TestUtil.elslogin(email, password, lpo1);	
				//---click on req new loan link
				lpo1.clickRequestLoanlink();
				//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
				ReviewDisclosurePageObject rd1 =TestUtil.reviewdisclosure(lpo1);
				//---enter application info---enter ssn	and home phone
				String actualError = TestUtil.appInfoErrorwithEmpData(ssnvalue, homephonevalue,add,city,state, rd1);
				//compare error and assert the values
				if (actualError.equalsIgnoreCase(expectedmessage))
				{
					Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
				}
				else
				{
					Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
				}
				Assert.assertEquals(actualError,expectedmessage);
				}
				
				
			
	}
						
					


*/