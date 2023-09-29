package regloanscripts;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.CustomerRejectDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class Verify_Employment {
	
	@Test(dataProvider="customerreject",dataProviderClass=CustomerRejectDataProvider.class)
	public void verifyEmployment(String lastname,String zip,String dob, String userType,String browserType) throws Exception
	{
		//login to the ELS application and click on Start Application
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		//Click i Agree and continue on the review disclosure pages
		
		CreateCustomerPageObject cc = new CreateCustomerPageObject();
		cc.setBrowser(lpo.getBrowser());
		cc.verifyEmployee(lastname, zip, dob);
		cc.clkContinueORNextButton();
		//get the error	
		Thread.sleep(2000);
		
		
		String result=cc.getCustError();
		
		if (result.equals("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program."))
		{
			cc.close();
			System.out.println("The employee is not active.");
			Reporter.log("The employee is not active as he is Garnished or terminated. Hence customer is not created.");
		}
		else if(result.equals("Your employer is not participating in the TrueConnect voluntary benefit program."))
		{
			cc.close();
			System.out.println("The employer is not active.");
			Reporter.log("The employer is not participating in TrueConnect program. Hence customer is not created.");
		}
		else if(result.equals("We cannot verify your employment. Please login from your parent portal."))
		{
			cc.close();
			System.out.println("Employer is websso employer.");
			Reporter.log("The customers of the employer are initiated from websso. Hence customer is not created.");
		}
		else if(result.equals("You are not eligible to participate in the TrueConnect voluntary benefit program. Please contact your HR Admin."))
		{			
			cc.close();
			System.out.println("The Employee belongs to red state.");
			Reporter.log("The Employee belongs to red state. Hence customer is not created.");
		}
		else
		{
			boolean resul=cc.verifyAppinfoNavigation();	
			if (resul==true)
			{
				Reporter.log("The customer is created.");
				cc.clkLogOut();
				cc.close();
			}
			Assert.assertEquals(true,resul);
		}
		
	}
}


