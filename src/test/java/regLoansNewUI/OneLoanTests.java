package regLoansNewUI;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.CustomerLoan1RejectNextDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class OneLoanTests {
	private boolean custexists,custcreate,custwebsso;
	LoanConfirmationPageObject lc;
	
	
	@Test(dataProvider="customerloan1andreject",dataProviderClass=CustomerLoan1RejectNextDataProvider.class,groups= {"Regression"})
	public void customerOneLoan_Reject_Other(String email,String password,String ssnvalue,String homephonevalue,String wrkemail,String lastname,String zip,String dob,String question1,String question2,String loanoption1,String routno,String acctno1,String AcnType,String ename,String add,String city,String state,String workstate,String expectedmessage, String userType,String browserType) throws Exception
	{
				//login to the ELS application and click on Start Application
				ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
				
				//Verify employee to create customer with last name, zip code and date of birth
				CreateCustomerPageObject cc = new CreateCustomerPageObject();
				AppInfoPageObject ai2 = new AppInfoPageObject();
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
				else if(result.equals("We cannot verify your employment. Please login from your parent portal."))
				{
					cc.close();
					System.out.println("Employer is websso employer.");
					Reporter.log("The customers of the employer are initiated from websso. Hence customer is not created.");
					custwebsso=true;
				}
				else
				{
						//check customer already exists and do not create a customer if already exists	
						custexists=cc.ChkCustomerExists();
						if ((custexists==true) || (custwebsso==true))
						{
								cc.close();
								Reporter.log("The Customer already created.");
								System.out.println("The Customer already created.");
								//Login with customer details
								ELSLoginPageObject lpoloan=new ELSLoginPageObject(browserType, userType);
								TestUtil.elslogin(email, password, lpoloan); 
								//customer takes one loan
								Reporter.log("Customer with User Name: " +email +" tries to take first Loan of : "+loanoption1); 
								//---click on No Credit loan  link 
								Thread.sleep(5000); 					  
								lpoloan.clickNoCreditLoan(); 
								Thread.sleep(5000);
								//Click on New Loan link
								lpoloan.clickNewLoan();
								ai2 =TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lpoloan);	
						}
						else      //Create a Customer				
						{	
								//enter customer email pwd and questions---Enter customer details to create-Create a Customer	
								cc.createAccountData(email, password,question1,question2);
								cc.clkContinueORNextButton(); 
								cc.clickNoCreditLoan();	
								//check customer creation is successful or not
								custcreate=cc.verifyAppinfoNavigation();	
								TestUtil.addAddressManually(add,city,state,cc);
								//---enter application info---enter ssn	and home phone,city,state and address
								Reporter.log("Customer with User Name: " +email +" tries to take first Loan of : "+loanoption1); 
								ai2 =TestUtil.appInfoFlowwithEmpData3(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, cc);
									if (custcreate==true)
									{
										Reporter.log("Customer Creation is successful.");
									}
									else
									{
										Reporter.log("Customer Creation is not successful.");
										Assert.assertEquals("Customer is not created.","Customer should be created.");	
									}	
								
							}
				//}	
				String nooaMessage=ai2.getMessage();
					if(nooaMessage.equals("NOAA Error has not occured."))
					{
						lc=TestUtil.loanProcedureUserloggedIn(loanoption1, routno, acctno1,AcnType,ename, ai2);
						//In new web site My Account link should be clicked to reach the home page
						lc.clkMyAccount();
						//Loan rejection after amount exhausted
						Reporter.log("Customer tries to take a Loan after his eligibility is exhausted.");
						//---click on req new loan link
						//lc.clickRequestLoanlink();
						Thread.sleep(5000);
						lc.clickNewLoan();					
						//---enter application info---enter ssn	and home phone					
						AppInfoPageObject ai3 = TestUtil.appInfoFlowwithEmpData2(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lc);
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
							Assert.assertEquals(actualError,expectedmessage);
					}
					else
					{
							 ai2.clkLogOut();
							// close the browser
							 ai2.close(); 
							 Assert.assertEquals("NOAA Error "+nooaMessage +" has occcured.","NOAA Error "+nooaMessage +" should not occur.");	
					}
			}
		}
}	

