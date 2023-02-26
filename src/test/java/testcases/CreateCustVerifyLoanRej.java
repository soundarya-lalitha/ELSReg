/*package testcases;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageobjects.*;
import dataproviders.CustomerRejectLoanDataProvider;
import testcases.util.TestUtil;

public class CreateCustVerifyLoanRej {
	
	@Test(priority=1,dataProvider="customerloanreject",dataProviderClass=CustomerRejectLoanDataProvider.class)
	public void createCustomer(String email,String password,String ssnvalue,String homephonevalue,String lastname,String zip,String dob,String question1,String question2,String add,String city,String state,String expectedmessage, String browserType) throws Exception
	{
		//login to the ELS application and click on Start Application
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType);
		Reporter.log("Customer is created in case if the customer does not exist.");
		lpo.clickStartApp();
		//Click i Agree and continue on the review disclosure pages
		ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo);
		//Verify employee to create customer with last name, zip code and date of birth
		CreateCustomerPageObject cc = new CreateCustomerPageObject();
		cc.setBrowser(rd.getBrowser());
		cc.verifyEmployee(lastname, zip, dob);
		
		cc.clkContinueORNextButton();
		//exit if customer already exists		
		boolean result=cc.ChkCustomerExists();
		if (result==true)
		{
			cc.close();
			System.out.println("The customer already created.");
			Reporter.log("The Customer already created.");
		}
		else
		{
			//enter customer email pwd and questions
			cc.createAccountData(email, password,question1,question2);
			//cc.selectQuestion1(question1);
			//cc.selectQuestion2(question2);
			cc.clkContinueORNextButton(); 
			//check customer creation is successful or not
			boolean resul=cc.verifyAppinfoNavigation();	
			if (resul==true)
			{
				Reporter.log("Customer Creation is successful.");
			}
			else
			{
				Reporter.log("Customer Creation is not successful.");
			}
			cc.clkLogOut();
			cc.close();
			//compare whether successfully navigated to app info after customer creation
			Assert.assertEquals(true,resul);
		}
	}
	@Test(priority=2,dataProvider="customerloanreject",dataProviderClass=CustomerRejectLoanDataProvider.class)
	public void verifyLoanRejection(String email,String password,String ssnvalue,String homephonevalue,String lastname,String zip,String dob,String question1,String question2,String add,String city,String state,String expectedmessage, String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType);
		TestUtil.elslogin(email, password, lpo);	
		//---click on req new loan link
		lpo.clickRequestLoanlink();
		//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
		ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo);
		//---enter application info---enter ssn	and home phone
		String actualError = TestUtil.appInfoErrorwithEmpData(ssnvalue, homephonevalue,add,city,state, rd);
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