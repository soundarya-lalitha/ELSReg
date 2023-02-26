package regLoansNewUI;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CreditExtLenAbsentDataProvider;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class CreditFlowExtLend_Absent {
	String Message,cls;	
	boolean b,c;
	
	@Test(dataProvider="ext",dataProviderClass=CreditExtLenAbsentDataProvider.class)
	public void CreditInvalidEmployees(String email,String password,String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		{			
			TestUtil.elslogin(email, password, lpo);
			Reporter.log("Customer with User Name: " +email +" logs into Truconnect portal. ");
			Thread.sleep(5000);			
				
			cls=lpo.getClassCreditChkLink();	
			System.out.println(cls);
			//check whether credit check loans link is disabled
			if (cls.equals("card carddisable"))
			{
				Reporter.log("Credit Check Loans icon is disabled.");	
			}
			else
			{
				Reporter.log("Credit Check Loans icon is not disabled.");	
			}
			
			Reporter.log("Customer views message next to Credit Check Loan link.");		
			Message=lpo.creditCheckMessage();
			
			if(Message.equalsIgnoreCase("Sorry this product is not available yet."))
			{
				Reporter.log("Message displayed: " +Message);
			}
									
			lpo.clkLogOut();
			lpo.close();
			
			
								
		}
		
			
	}
}


