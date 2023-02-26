package regLoansNewUI;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.LightStreamDataProvider;
import pageobjects.ELSLoginPageObject;
import pageobjects.LargeDollarPageObject;
import testcases.util.TestUtil;



public class CreditFlowExtLendLightStream 
{
					
		@Test(dataProvider="Light",dataProviderClass=LightStreamDataProvider.class)  
				
		public void CreditFlowLightStream(String email,String password,String extLender,String SelfAsses,String loanoption,String expectedurl,String userType,String browserType) throws Exception
		{		
				String actualurl;
				ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
				
				TestUtil.elsloginScroll(email, password, lpo);			
				Thread.sleep(3000);			
				
				LargeDollarPageObject ld = new LargeDollarPageObject();		
				ld.setBrowser(lpo.getBrowser());
				
				//customer clicks on Credit loan link	
				Reporter.log("User: " +email + " having External Lender clicks on Credit Loan.");
				//---click on Credit loan link
				Thread.sleep(5000);				
				lpo.clickCreditLoan();
				Thread.sleep(5000);
								
				if (extLender.equalsIgnoreCase("Yes"))
				{
					ld.ExtLenderSelfAssesCredit(SelfAsses);
				}			
								
				Reporter.log("Customer with User Name: " +email +" selects Light Stream Loan option : "+loanoption);
				//Select a Light stream product			
				ld.pickLSLoanOption(loanoption);
				ld.clickLDollContinue();
				Thread.sleep(15000);
				//Now new url opens in a new tab instead of same url and trueconnect user is logged off but tab remains open
				ld.switchtab();
				
				actualurl=ld.geturl();
				//Check destination url is reached
				if(actualurl.equals(expectedurl)) 
				{
					Reporter.log("Navigated to Light Stream portal and loan details corresponding to option selected by user displayed.");				
					// close the browser
					ld.close();					
				}
				else
				{
					Reporter.log("Navigation to Light Stream portal failed.");	
					// close the browser
					ld.close();
					Assert.assertEquals("Light Stream url not visible.","Light Stream url Should be visible.");
				}				 
		}
}
	
	