package regCIPscripts;
import org.testng.annotations.Test;


import testcases.util.TestUtil;

public class CIPDataClear {

	@Test()
	public void CIPClear() throws Exception
	{	
				
			TestUtil.cIPAdminProcessCIP("cip.admin@test.com", "Paswrd@123#", "CIP Management-Clear CIP Status", "Employee Loan Solutions", "Decline", "",  "admin","chrome","5000133031");
			TestUtil.cIPAdminProcessCIP("cip.admin@test.com", "Paswrd@123#", "CIP Management-Clear CIP Status", "Employee Loan Solutions", "Decline", "",  "admin","chrome","5000133031");
	}
	
	
	

	/*private void customerApplyAgain(String email, String password, String homephone, String address, String city,
			String state, String ssn2, String loanamt, Stringx routno, String acntno, String ename, String browserType,
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
