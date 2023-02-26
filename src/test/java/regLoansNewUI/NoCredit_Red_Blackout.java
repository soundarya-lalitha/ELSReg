package regLoansNewUI;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.RedStatesDataProvider;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class NoCredit_Red_Blackout {
	String Message;	
	boolean b,c;
	
	@Test(dataProvider="redstates",dataProviderClass=RedStatesDataProvider.class)
	public void NoCreditRedBlackoutEmpls(String email,String password,String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		
		{			
			TestUtil.elslogin(email, password, lpo);
			Reporter.log("Customer with User Name: " +email +" logs into Truconnect portal. ");
			//---click on No Credit loan link
			Thread.sleep(5000);
			Reporter.log("Customer clicks on No Credit Loan and views message next to New Loan.");
			lpo.clickNoCreditLoan();	
			Thread.sleep(5000);
			Message=lpo.NewLoanMessage();	
			Thread.sleep(5000);
			
			if(Message.equalsIgnoreCase("You are not eligible to participate in the TrueConnect voluntary benefit program. Please contact your HR Admin."))
			{
				Reporter.log("Message displayed: " +Message);
				Reporter.log("Customer belongs to red state.");
			}
			else if	(Message.contains("We are not taking loan applications from"))
			{
				Reporter.log("Message displayed: " +Message);
				Reporter.log("Customer belongs to employer who is currently Blacked out.(Current date falls under Black out period)");
			}
			
			b=lpo.NewLoanButtonText().contains("Apply Later");			
			if(b==true)
			{
				Reporter.log("New Loan link is Greyed out.");
			}
			else
			{
				Reporter.log("New Loan link is not Greyed out.");
			}	
				
			
			c=lpo.UpdateAddButtonText().contains("Update Later");
			if(c==true)
			{
				Reporter.log("Update Address link is Greyed out.");
			}
			else
			{
				Reporter.log("Update Address link is not Greyed out.");
			}	
			lpo.clkLogOut();
			lpo.close();
			
			Assert.assertEquals(b,true);
			
			
					
		}
		
			
	}
}


