package regCIPscripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.CIPAppInfoDataProvider;
import pageobjects.AppInfoPageObject;


import testcases.util.TestUtil;

public class CIPFailureAppInfo {

	@Test(dataProvider="cipnotclear2",dataProviderClass=CIPAppInfoDataProvider.class)
	public void CIPFailureAppInfoTest(String email,String password,String ssn,String homephone,String workEmail,String address,String city,String state,String workstate,String cipadminEmail,String cipadminPassword,String path,String employer,String cipaction,String ssn2,String loanamt,String routno,String acntno,String ename,String userType1,String userType2,String browserType) throws Exception
	{
		
		String result1,loanNumber;
		AppInfoPageObject ai1 = TestUtil.loantrial(email, password, ssn, homephone,workEmail, address, city, state,workstate, userType1, browserType);
		result1=ai1.chkCIPErrorAndclkFinish2();
		if(result1.equals("CIP Failure has not occured.")){	
			Reporter.log("CIP has not Failed:");
			 ai1.clkLogOut();
				// close the browser
			 ai1.close();
			 Assert.assertEquals("CIP has not failed.","CIP Failure should occur to take further action.");				
		}
		else
		{
			loanNumber=ai1.getCIPFailureLoanNumber2();
			System.out.println(loanNumber);	
			Reporter.log("CIP has failed for the Customer and Loan number of failed CIP is: "+loanNumber);
			String buttonName1=ai1.verifyCallBankButtonName();
				if (buttonName1.contains("Call Bank")){
					Reporter.log("Button Name is displayed as call bank under Pending Applications.");
				}
				else
				{
					Reporter.log("Button is not displayed as call bank under Pending Applications...Report as defect.");
				}
	
			ai1.clkLogOut();
			// close the browser
			ai1.close();		
			TestUtil.cIPAdminProcessCIP(cipadminEmail, cipadminPassword, path, employer, cipaction, ssn2, userType2, browserType,loanNumber);
			TestUtil.customerApplyAgainWithWrongSSN(email, password,cipaction, homephone, address, city, state,workstate, ssn,workEmail,loanamt, routno, acntno,
					ename, userType1, browserType, loanNumber);
		}
	}		 
}
