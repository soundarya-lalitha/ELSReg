package regLoansNewUI;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.FinMktDataProvider;
import pageobjects.ELSLoginPageObject;
import pageobjects.LargeDollarPageObject;
import testcases.util.TestUtil;



public class CreditFlowExtLendFinMarket
{
					
		@Test(dataProvider="FinMkt",dataProviderClass=FinMktDataProvider.class,groups= {"Regression"})  
				
		public void CreditFinMarketFlow(String email,String password,String extLender,String SelfAsses,String expectedurl,String userType,String browserType) throws Exception
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
								
				ld.clickLFinMktContinue();
				Thread.sleep(8000);
				//Now new url opens in a new tab instead of same url and trueconnect user is logged off but tab remains open
				ld.switchtab();			
				
				
				actualurl=ld.geturl();
				//Check destination url is reached
				System.out.println("actual url is:"+actualurl);
				System.out.println("expected url is:"+expectedurl);
				
				if(actualurl.equals(expectedurl)) 
				{
					Reporter.log("Navigated to FINMKT portal.");				
					// close the browser
					ld.close();					
				}
				else
				{
					Reporter.log("Navigation to FINMKT portal failed.");	
					// close the browser
					ld.close();
					Assert.assertEquals("FINMKT url not visible.","FINMKT url Should be visible.");
				}				 				 
		}
}
	
	