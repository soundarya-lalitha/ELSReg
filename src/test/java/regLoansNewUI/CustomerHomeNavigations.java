package regLoansNewUI;



import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CustNavigationDataProvider;
import pageobjects.NavigationPageObject;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;


public class CustomerHomeNavigations {
		
	/**
	 * @param email
	 * @param password
	 * @param FinCouselurl
	 * @param userType
	 * @param browserType
	 * @throws Exception
	 */
	@Test(dataProvider="customernavigation",dataProviderClass=CustNavigationDataProvider.class)  
	
	public void NavigateCustomerHome(String email,String password,String FinCouselurl,String userType,String browserType) throws Exception
	{
		 
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
		TestUtil.elslogin(email, password, lpo);			
		Thread.sleep(3000);			
		NavigationPageObject npo=new NavigationPageObject();
		npo.setBrowser(lpo.getBrowser());	
			//Bank Account Page
		boolean flag1=npo.VerifyBankAccNavigation();
		if (flag1==true)
		{				 
			Reporter.log("Bank Account Page navigation is successful.");
		}else
		{
			Reporter.log("Bank Account Page navigation is not successful.");
		}
		//Financial Counseling Page
		boolean flag2=npo.VerifyFiNCounNavigation(FinCouselurl); 
		if (flag2==true) 
		{
			Reporter.log("Financial Couseling Page navigation is successful."); 
		}
		else
		{
			Reporter.log("Financial Couseling Page navigation is not successful."); 
		}
		//Debt Management Page
		boolean flag3=npo.VerifyDebtManagment(); 
		if (flag3==true) 
		{
			Reporter.log("Debt Management Page navigation is successful."); 
		}
		else
		{
			Reporter.log("Debt Management Page navigation is not successful."); 
		}
		boolean flag4=npo.VerifyTcRx(); 
		if (flag4==true) 
		{
			Reporter.log("TrueConnectRx Page navigation is successful."); 
		}
		else
		{
			Reporter.log("TrueConnectRx Page navigation is not successful."); 
		}
		boolean flag5=npo.VerifyNeedHelp(); 
		if (flag5==true) 
		{
			Reporter.log("TrueConnect Help Page navigation is successful."); 
		}
		else
		{
			Reporter.log("TrueConnect Help Page navigation is not successful."); 
		}
		boolean flag6=npo.VerifyEmerSav(); 
		if (flag6==true) 
		{
			Reporter.log("Emergency Savings Page navigation is successful."); 
		}
		else
		{
			Reporter.log("Emergency Savings Page navigation is not successful."); 
		}
		
		boolean flag7=npo.VerifyTrueCReward(); 
		  if (flag7==true) 
		  {
		  Reporter.log("TrueConnect Rewards Page navigation is successful."); 
		  } 
		  else 
		  {
		  Reporter.log("TrueConnect Rewards Page navigation is not successful."); 
		  }	
		  
		 Thread.sleep(2000);	
		 npo.clkLogOut();
		 npo.close();
			

		if ((flag1==true) && (flag2==true) && (flag3==true) && (flag4==true) && (flag5==true) && (flag6==true) && (flag7==true))
		{
			Reporter.log("Customer Home navigations are working as expected."); 
			Assert.assertEquals("Navigations Successful", "Navigations Successful");
		}
		else
		{
			Reporter.log("Customer Home navigations are not working as expected. Check above failed path."); 
			Assert.assertEquals("Navigations Successful", "Navigations Failed");
		}
			  
}
	
}
	
	




