package testcases;




import org.testng.annotations.Test;

import dataproviders.LoanDataProvider2;
import pageobjects.*;
import testcases.util.TestUtil;

public class LoginTest {
	

	
	
	@Test(dataProvider="loan2",dataProviderClass=LoanDataProvider2.class)
	public void loginCheck(String email,String password,String ssnvalue,String homephonevalue,String loanamount,String routno,String actno,String ename,String add,String city,String state,String userType,String browserType) throws Exception
	{
	
			
			
				ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType, userType);
				TestUtil.elslogin(email, password, lpo1);	
				TestUtil.screenshotUtil("loin");
				
				
				
				lpo1.clkLogOut();
				// close the browser
				lpo1.close(); 
				
				
				
			
	}
						
}					


