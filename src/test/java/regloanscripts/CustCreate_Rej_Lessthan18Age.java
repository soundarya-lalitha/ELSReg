package regloanscripts;


import org.testng.Assert;
import org.testng.Reporter;

import org.testng.annotations.Test;

import dataproviders_stage.CustomerLess18AgeDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class CustCreate_Rej_Lessthan18Age {
	
	@Test(dataProvider="customerreject",dataProviderClass=CustomerLess18AgeDataProvider.class)
	public void customerCreationReject(String lastname,String zip,String dob, String userType,String browserType) throws Exception
	{
		//login to the ELS application and click on Start Application
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		//Click i Agree and continue on the review disclosure pages
		
		CreateCustomerPageObject cc = new CreateCustomerPageObject();
		cc.setBrowser(lpo.getBrowser());
		cc.verifyEmployee(lastname, zip, dob);
		cc.clickLname();
		//cc.clkContinueORNextButton();
		//get the error	
		Thread.sleep(3000);
		
		String result=cc.getCustLessAgeError();
		boolean resul=false;
		if (result.equals("You must be at least 18 years of age."))
		{
			cc.close();
			resul=true;			
			Reporter.log("Customer is not created as his age is less than 18 years.");
		}
		Assert.assertEquals(true,resul);
		}
		
	}



