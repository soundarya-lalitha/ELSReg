package testcases;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.TerminatedCustomer;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class CustTerminateOREmpDeactivate {
	String disabled;	
	
	@Test(dataProvider="terminate",dataProviderClass=TerminatedCustomer.class)
	public void verifyReqNewLoanLink(String email,String password,String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		try
		{
			
			TestUtil.elslogin(email, password, lpo);	
			//check whether request new loan link is disabled
			disabled=lpo.checkRequestLoanlink();
			System.out.println(disabled);
			if(disabled.equals("true"))
			{
				Reporter.log("Request new loan link should be disabled for terminated customer or customer of deactivated employer, it is disabled.");
			}
			lpo.clkLogOut();
			Thread.sleep(3000);
			lpo.close();
			Assert.assertEquals(disabled,"true");
		}
		catch(NullPointerException n)
		{
			Reporter.log("Request new loan link should be disabled for terminated customer or customer of deactivated employer, but it is not disabled.");
			lpo.clkLogOut();
			lpo.close();
			Assert.assertEquals("Request new loan link should be disabled.","Request new loan link is not disabled.");			
		}
			
	}
}


