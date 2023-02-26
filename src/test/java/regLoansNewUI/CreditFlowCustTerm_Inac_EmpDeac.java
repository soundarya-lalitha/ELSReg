package regLoansNewUI;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CreditTerminatedCustomer;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class CreditFlowCustTerm_Inac_EmpDeac {
	String Message;	
	boolean b,c;
	
	@Test(dataProvider="creditterminate",dataProviderClass=CreditTerminatedCustomer.class)
	public void CreditInvalidEmployees(String email,String password,String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		{			
			TestUtil.elslogin(email, password, lpo);
			Reporter.log("Customer with User Name: " +email +" logs into Truconnect portal. ");
			Thread.sleep(5000);
			Reporter.log("Customer views message next to Credit Check Loan link.");
			
			Message=lpo.creditCheckMessage();	
			Thread.sleep(5000);
			
			if(Message.equalsIgnoreCase("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program. Please contact your HR Admin."))
			{
				Reporter.log("Message displayed: " +Message);
				Reporter.log("Customer is either Terminated or inactive.");
			}
			else if	(Message.equalsIgnoreCase("Your employer is not participating in the TrueConnect voluntary benefit program."))
			{
				Reporter.log("Message displayed: " +Message);
				Reporter.log("Customer belongs to employer who is deactivated.");
			}
						
			
			lpo.clkLogOut();
			lpo.close();
			
								
		}
		
			
	}
}


