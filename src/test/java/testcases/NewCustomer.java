package testcases;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.CustomerDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class NewCustomer {
	
	@Test(dataProvider="customer",dataProviderClass=CustomerDataProvider.class)
	public void createCustomer(String email,String password,String lastname,String zip,String dob,String question1,String question2,String userType,String browserType) throws Exception
	{
		//login to the ELS application and click on Start Application
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		//lpo.clickStartApp();
		//Click i Agree and continue on the review disclosure pages
		//ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo);
		//Verify employee to create customer with last name, zip code and date of birth
		CreateCustomerPageObject cc = new CreateCustomerPageObject();
		cc.setBrowser(lpo.getBrowser());
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
			boolean resul=cc.veriProdSelPage();	
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
		
		
		
		 
		 
		
}
