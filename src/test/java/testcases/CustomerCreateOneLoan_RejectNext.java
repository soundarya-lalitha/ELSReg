/*package testcases;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageobjects.*;


import dataproviders.CustomerLoan1RejectNextDataProvider;
import testcases.util.TestUtil;

public class CustomerCreateOneLoan_RejectNext {
	private boolean custexists,custcreate;
	LoanConfirmationPageObject lc;
	
	
	@Test(dataProvider="customerloan1andreject",dataProviderClass=CustomerLoan1RejectNextDataProvider.class)
	public void customerOneLoan_Reject_Other(String email,String password,String ssnvalue,String homephonevalue,String lastname,String zip,String dob,String question1,String question2,String loanoption1,String routno,String acctno1,String ename,String add,String city,String state,String workstate,String expectedmessage,String userType, String browserType) throws Exception
	{
	//login to the ELS application and click on Start Application
	ELSLoginPageObject lpo=new ELSLoginPageObject(browserType);
	lpo.clickStartApp();
	//Click i Agree and continue on the review disclosure pages
	ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo);
	//Verify employee to create customer with last name, zip code and date of birth
	CreateCustomerPageObject cc = new CreateCustomerPageObject();
	cc.setBrowser(rd.getBrowser());
	cc.verifyEmployee(lastname, zip, dob);
	cc.clkContinueORNextButton();
	//Check if error occurs in identification
	String result=cc.getCustError();
	
	
	if ((result.equals("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program."))||(result.equals("Your employer is not participating in the TrueConnect voluntary benefit program.")) || (result.equals("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program."))||result.equals("We cannot verify your employment. Please try again."))
	{
		cc.close();
		System.out.println("The employee can not be verified.");
		Reporter.log("The employee verification failed.");
		Assert.assertEquals(false,true);
	}
	else
	{
		//check customer already exists and do not create a customer if already exists	
		custexists=cc.ChkCustomerExists();
		if (custexists==true)
		{
			cc.close();
			Reporter.log("The Customer already created.");
		}
		else      //Create a Customer				
		{	
			//enter customer email pwd and questions---Enter customer details to create
			cc.createAccountData(email, password,question1,question2);
			cc.clkContinueORNextButton(); 
			//check customer creation is successful or not
			
			custcreate=cc.verifyAppinfoNavigation();	
			if (custcreate==true)
			{
				Reporter.log("Customer Creation is successful.");
			}
			else
			{
				Reporter.log("Customer Creation is not successful.");
				Assert.assertEquals("Customer is not created.","Customer should be created.");	
			}	
			cc.clkLogOut();
			cc.close();
			//compare whether successfully navigated to app info after customer creation-Asset customer creation
												}
		}	

			
			if ((custexists==true) || (custcreate==true))
			{
				ELSLoginPageObject lpoloan=new ELSLoginPageObject(browserType, userType);
				
				TestUtil.elslogin(email, password, lpoloan);	
				
				//customer takes one loan
				Reporter.log("Customer with User Name: " +email +" tries to take a Loan of : "+loanoption1);
				//---click on req new loan link
				lpoloan.clickRequestLoanlink();
				//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
				 ReviewDisclosurePageObject rdloan =TestUtil.reviewdisclosure(lpoloan);
				//---enter application info---enter ssn	and home phone,city,state and address
				AppInfoPageObject ai2 =TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,add,city,state,workstate, rdloan);
				String nooaMessage=ai2.getMessage();
				if(nooaMessage.equals("NOAA Error has not occured.")){
					lc=TestUtil.loanProcedureUserloggedIn(loanoption1, routno, acctno1,ename, ai2);
					//Loan rejection after amount exhausted
					Reporter.log("Customer tries to take a Loan after his eligibility is exhausted.");
					//---click on req new loan link
					lc.clickRequestLoanlink();
					//---check  I meet check box and click on continue button and elect disclosure page chk agree check box and click on continue click
					ReviewDisclosurePageObject rdloan1 =TestUtil.reviewdisclosure2(lc);
					//---enter application info---enter ssn	and home phone
					
					//compare error and assert the values
					        
					AppInfoPageObject ai3 = TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,add,city,state,workstate, rdloan1);
					String actualError=ai3.getMessage();
						if (ai3.getMessage().equalsIgnoreCase(expectedmessage))
						{
							Reporter.log("Warning message displayed: "+expectedmessage);
						}
						else
						{
							Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
							
						}
						
						if (actualError.equalsIgnoreCase("You have already obtained the maximum number of TrueConnect loans."))
						{
							Reporter.log("Customer is from: City of Anaheim or Eastern Municipal Water District, hence can avail only one Loan.");
						}
						ai2.clkLogOut();
						// close the browser
						 ai2.close();
						//Assert.assertEquals(actualError,expectedmessage);
				}
				else{
						 ai2.clkLogOut();
						// close the browser
						 ai2.close(); 
						 Assert.assertEquals("NOAA Error "+nooaMessage +" has occcured.","NOAA Error "+nooaMessage +" should not occur.");	
				}
				
				
			}
	}
						
					


*/