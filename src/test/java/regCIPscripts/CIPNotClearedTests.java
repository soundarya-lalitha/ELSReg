package regCIPscripts;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.CIPNotClearDataProvider;
import pageobjects.AppInfoPageObject;


import testcases.util.TestUtil;

public class CIPNotClearedTests {

	@Test(dataProvider="cipnotclear",dataProviderClass=CIPNotClearDataProvider.class)
	public void CIPNotCleared(String email,String password,String ssn,String homephone,String workEmail,String address,String city,String state,String workstate,String cipadminEmail,String cipadminPassword,String path,String employer,String cipaction,String ssn2,String loanamt,String routno,String acntno,String ename,String cipaction2,String userType1,String userType2,String browserType) throws Exception
	{
		
		String result1,loanNumber;
		AppInfoPageObject ai1 = TestUtil.loantrial(email, password, ssn, homephone,workEmail,address, city, state,workstate, userType1, browserType);
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
					Reporter.log("Button is not displayed as call bank under Pening Applications...Report as defect.");
				}
	
			ai1.clkLogOut();
			// close the browser
			ai1.close();		
			//TestUtil.cIPAdminProcessCIP(cipadminEmail, cipadminPassword, path, employer, cipaction, ssn2, browserType,loanNumber, userType2);
			TestUtil.cIPAdminProcessCIP(cipadminEmail, cipadminPassword, path, employer, cipaction, ssn2,  userType2,browserType,loanNumber);
			TestUtil.customerChkCIPStatus(email, password,cipaction,homephone, address, city, state,workstate, ssn2, loanamt, routno, acntno,
					ename,userType1,browserType, loanNumber);
			
//			if (cipaction.equalsIgnoreCase("rerun")|| cipaction.equalsIgnoreCase("hold"))
//			{     
//				Reporter.log("CIP Admin logs again and declines cip to reset the data.(only for Data Setup,not a step in the script)");
//				TestUtil.cIPAdminProcessCIP(cipadminEmail, cipadminPassword, path, employer, cipaction2, ssn2, browserType,loanNumber);
//				
//		}
			
			//This is to clear the user so that user do not have any pending cips, just to clear the data
			//TestUtil.cIPAdminProcessCIP(cipadminEmail, cipadminPassword, path, employer, cipaction2, ssn2, browserType,loanNumber);
			
		}
	}
	
	
	

	/*private void customerApplyAgain(String email, String password, String homephone, String address, String city,
			String state, String ssn2, String loanamt, String routno, String acntno, String ename, String browserType,
			String loanNumber) throws Exception, InterruptedException {
		LoanConfirmationPageObject lc;
		//login again as customer
		ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType);		
		TestUtil.elslogin(email, password, lpo1);
		String buttonName=lpo1.verifyButtonName();
		
			if (buttonName.equals("Apply Again")){
				Reporter.log("Button Name is changed to Apply Again.");
				Reporter.log("Customer clicks on Apply Again button and completes loan process.");
				lpo1.clkApplyAgainButton();
				ReviewDisclosurePageObject rdloan1 =TestUtil.reviewdisclosure(lpo1);
				AppInfoPageObject ai2 =TestUtil.appInfoFlowwithEmpData1(ssn2, homephone,address,city,state, rdloan1);
				String nooa2Message=ai2.getMessage();
				
				if(nooa2Message.equals( "NOAA Error has not occured.")){		
					lc=TestUtil.loanProcedureUserloggedIn(loanamt, routno, acntno,ename, ai2);
					
					lc.clkLogOut();
					// close the browser
					lc.close();
					
				}
			}
			else if (buttonName.contains("Call Bank")){
				CIPAdminPageObject ca2 = new CIPAdminPageObject();
				ca2.setBrowser(lpo1.getBrowser());
				String CIPStatus=ca2.getCIPStatus();
				if (CIPStatus.equals("CIPHold")){
					Reporter.log("CIP Status is CIPHold for the loan " +loanNumber);
				}else if(CIPStatus.equals("CIPFailure")){
					Reporter.log("CIP Status is CIPFailure for the loan " +loanNumber);
				}else if(CIPStatus.equals("CIPByPass")){
					Reporter.log("CIP Status is CIPBypass for the loan " +loanNumber);
				}
				else{
					Reporter.log("CIP Status is for the loan is " +CIPStatus);
				}
						
				//Reporter.log("Loan Process successful for loan " +loanNumber);
				ca2.clkLogOut();
				// close the browser
				ca2.close();	
			}	
			else {
				lpo1.clkLogOut();
				// close the browser
				lpo1.close();
				}
	}

	private void cIPAdminProcessCIP(String cipadminEmail, String cipadminPassword, String path, String employer,
			String cipaction, String ssn2, String browserType, String loanNumber)
			throws Exception, InterruptedException {
		ELSLoginPageObject lpocipad=new ELSLoginPageObject(browserType);	
		Reporter.log("CIP Admin logs in and selects "+cipaction +" option.");
		TestUtil.elslogin(cipadminEmail, cipadminPassword, lpocipad);
		CIPAdminPageObject ca = new CIPAdminPageObject();
		ca.setBrowser(lpocipad.getBrowser());
		ca.navigateMenu(path);
		//Select correct employer,Click on loan number, select cip action and clear the cip with correct ssn
		ca.CIPProcess(employer, loanNumber, cipaction, ssn2);
		String cipresult=ca.getCIPResult(); //table=table/tr/td(2)/p/b
				if (cipresult.contains("CIP has been failed again for the loan")){
					Reporter.log("CIP has been again failed again for the loan " +loanNumber);
				}else if(cipresult.contains("CIP is put on Hold for the loan")){
					Reporter.log("CIP is put on Hold for the loan " +loanNumber);
				}else if(cipresult.contains("CIP is rejected for the loan")){
					Reporter.log("CIP is Rejected for the loan " +loanNumber);
				}
				else if(cipresult.contains("CIP has been cleared for the loan")){
					Reporter.log("CIP Cleared  for the loan " +loanNumber);
					//CIP has been cleared for the loan					
				}
			ca.clkLogOut();	
			ca.close();
	}

	private AppInfoPageObject loantrial(String email, String password, String ssn, String homephone, String address,
			String city, String state, String browserType) throws Exception, InterruptedException {
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType);		
		TestUtil.elslogin(email, password, lpo);	
		//customer takes  loan
		Reporter.log("Customer with User Name: " +email +" tries to take a Loan: ");
		//---click on req new loan link
		lpo.clickRequestLoanlink();
		//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
		 ReviewDisclosurePageObject rdloan =TestUtil.reviewdisclosure(lpo);
		//---enter application info---enter ssn	and home phone,city,state and address
		AppInfoPageObject ai1 =TestUtil.appInfowithEmpData(ssn, homephone,address,city,state, rdloan);
		//CIP Failure occurs
		return ai1;
	}	*/
		 
}
