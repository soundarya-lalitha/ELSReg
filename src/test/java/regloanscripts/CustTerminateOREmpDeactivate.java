package regloanscripts;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.TerminatedCustomer;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class CustTerminateOREmpDeactivate {
	boolean present;	
	
	@Test(dataProvider="terminate",dataProviderClass=TerminatedCustomer.class)
	public void verifyReqNewLoanLink(String email,String password,String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		{
			
			TestUtil.elslogin(email, password, lpo);
			present=lpo.checkRequestLoanlinkNew();
			
			System.out.println(present);
			if(present==false)
			{
				Reporter.log("Request new loan link should be disabled for terminated customer or customer of deactivated employer, it is disabled.");
			}
			else
			{
				Reporter.log("Request new loan link should be disabled for terminated customer or customer of deactivated employer, but it is not disabled.");
			}
			lpo.clkLogOut();
			lpo.close();
			Assert.assertEquals(present,false);
			
			
		}
		
			
	}
}


