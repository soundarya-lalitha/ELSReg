package regLoansNewUI;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CustomerLoan2RejectNextDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class TwoLoanTests {
	private boolean custexists,custcreate;
	LoanConfirmationPageObject lc;
	
	
	@Test(dataProvider="customerloan2andreject",dataProviderClass=CustomerLoan2RejectNextDataProvider.class)
	public void customer2Loan_Reject_Other(String email,String password,String ssnvalue,String homephonevalue,String wrkemail,String lastname,String zip,String dob,String question1,String question2,String loanoption1,String loanoption2,String routno,String acctno1,String AcnType,String ename,String add,String city,String state,String workstate,String expectedmessage,String userType, String browserType) throws Exception
	{
	//login to the ELS application and click on Start Application
	ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
	
	//Verify employee to create customer with last name, zip code and date of birth
	CreateCustomerPageObject cc = new CreateCustomerPageObject();
	cc.setBrowser(lpo.getBrowser());
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
			}	
		}	

			if ((custexists==true) || (custcreate==true))
			{
				ELSLoginPageObject lpoloan=new ELSLoginPageObject(browserType, userType);
				
				TestUtil.elslogin(email, password, lpoloan);	
				
				//customer takes one loan
				Reporter.log("Customer with User Name: " +email +" tries to take first Loan of : "+loanoption1);
				//---click on No Credit loan link
				Thread.sleep(5000);
				//lpoloan.clickRequestLoanlink();
				lpoloan.clickNoCreditLoan();
				Thread.sleep(5000);
				//Click on New Loan link
				lpoloan.clickNewLoan();
				
				//---enter application info---enter ssn	and home phone,city,state and address
				AppInfoPageObject ai2 =TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lpoloan);
				String nooa1Message=ai2.getMessage();
				if(nooa1Message != "NOAA Error has not occured."){					
					ai2.clkLogOut();
					// close the browser
					 ai2.close(); 
					 Assert.assertEquals("NOAA Error "+nooa1Message +" has occcured for first loan.","NOAA Error "+nooa1Message +" should not occur for the first loan.");	
				}
				else
				{
					lc=TestUtil.loanProcedureUserloggedIn(loanoption1, routno, acctno1,AcnType,ename, ai2);
					//In new web site My Account link should be clicked to reach the home page
					lc.clkMyAccount();
					//customer takes second loan
					Reporter.log("Customer with User Name: " +email +" tries to take second Loan of : "+loanoption2);
					//---click on new loan link
					Thread.sleep(5000);
					lc.clickNewLoan();
					
					//---enter application info---enter ssn	and home phone
					AppInfoPageObject ai3 = TestUtil.appInfoFlowwithEmpData2(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lc);
					String nooa2Message=ai3.getMessage();
					
					if(nooa2Message != "NOAA Error has not occured."){					
						ai3.clkLogOut();
						// close the browser
						 ai3.close(); 
						 Assert.assertEquals("NOAA Error "+nooa2Message +" has occcured for second loan.","NOAA Error "+nooa2Message +" should not occur for the second loan.");	
				  	}
					else
					{
						lc=TestUtil.loanProcedureUserloggedIn(loanoption2, routno, acctno1,AcnType,ename, ai3);
						//In new web site My Account link should be clicked to reach the home page
						lc.clkMyAccount();
						//Loan rejection after amount exhausted					
						Reporter.log("Customer tries to take a Loan after his eligibility is exhausted.");
						//---click on new loan link
						Thread.sleep(5000);
						lc.clickNewLoan();
						
						//---enter application info---enter ssn	and home phone
						AppInfoPageObject ai4 = TestUtil.appInfoFlowwithEmpData2(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lc);
						String actualError=ai4.getMessage();
								if (ai4.getMessage().equalsIgnoreCase(expectedmessage))
								{
									Reporter.log("Warning message displayed: "+expectedmessage);
								}
								else
								{
									Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
									
								}
								ai4.clkLogOut();
								// close the browser
								 ai4.close();
								Assert.assertEquals(actualError,expectedmessage);
					}
								
				}
				
				
		}
				
				
	}
}
	
						
					


