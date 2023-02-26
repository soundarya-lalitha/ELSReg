package regLoansNewUI;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CustomerRejectLoanDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;

public class NoCredit_Verify_Loan_Reject
 {
	private boolean custexists,custcreate;
	LoanConfirmationPageObject lc;
	
	
	@Test(dataProvider="customerloanreject",dataProviderClass=CustomerRejectLoanDataProvider.class)
	public void customer_Loan_Reject(String email,String password,String ssnvalue,String homephonevalue,String wrkemail, String lastname,String zip,String dob,String question1,String question2,String add,String city,String state,String workstate,String expectedmessage,String userType,String browserType) throws Exception
	{
	//login to the ELS application and click on Start Application
	ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
	
	//Verify employee to create customer with last name, zip code and date of birth
	CreateCustomerPageObject cc = new CreateCustomerPageObject();
	cc.setBrowser(lpo.getBrowser());
	cc.verifyEmployee(lastname, zip, dob);
	cc.clkContinueORNextButton();
	Thread.sleep(3000);
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
			
		}
	}	

			
			if ((custexists==true) || (custcreate==true))
			{
				ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType, userType);
				TestUtil.elslogin(email, password, lpo1);	
				
				//---click on No Credit loan link
				Thread.sleep(5000);				
				lpo1.clickNoCreditLoan();
				Thread.sleep(5000);
				//Click on New Loan link
				lpo1.clickNewLoan();				
				Thread.sleep(5000);
				
				//---enter application info---enter ssn	and home phone
				AppInfoPageObject ai2= TestUtil.appInfoFlowwithEmpData1(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lpo1);
				String actualError=ai2.getMessage();
				 //click finish
				  ai2.clkFinish();  
				  ai2.clkLogOut();
				// close the browser
				 ai2.close();
				//compare error and assert the values
				if (actualError.equalsIgnoreCase(expectedmessage))
				{
					Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
				}
				else
				{
					Reporter.log("Actual error message: "+actualError+" Expected error message: "+expectedmessage);
				}
				Assert.assertEquals(actualError,expectedmessage);
				}				
			}
	}
						
					


