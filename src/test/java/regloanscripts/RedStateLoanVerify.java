package regloanscripts;
/*
 * package regloanscripts;
 * 
 * 
 * 
 * import org.testng.Assert; import org.testng.Reporter; import
 * org.testng.annotations.Test;
 * 
 * import pageobjects.*;
 * 
 * 
 * import dataproviders.RedStatesDataProvider; import testcases.util.TestUtil;
 * 
 * public class RedStateLoanVerify { private boolean custexists,custcreate;
 * LoanConfirmationPageObject lc;
 * 
 * 
 * @Test(dataProvider="redstates",dataProviderClass=RedStatesDataProvider.class)
 * public void RedStates_Verify_Loan(String email,String password,String
 * ssnvalue,String homephonevalue,String lastname,String zip,String dob,String
 * question1,String question2,String userType,String browserType) throws
 * Exception { //login to the ELS application and click on Start Application
 * ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
 * 
 * //Click i Agree and continue on the review disclosure pages
 * ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo); //Verify
 * employee to create customer with last name, zip code and date of birth
 * CreateCustomerPageObject cc = new CreateCustomerPageObject();
 * cc.setBrowser(rd.getBrowser()); cc.verifyEmployee(lastname, zip, dob);
 * cc.clkContinueORNextButton(); //Check if error occurs in identification
 * String result=cc.getCustError();
 * 
 * 
 * if ((result.
 * equals("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program."
 * ))||(result.
 * equals("Your employer is not participating in the TrueConnect voluntary benefit program."
 * )) || (result.
 * equals("You are not actively employed with an employer participating in the TrueConnect voluntary benefit program."
 * ))||result.equals("We cannot verify your employment. Please try again.")) {
 * cc.close(); System.out.println("The employee can not be verified.");
 * Reporter.log("The employee verification failed.");
 * Assert.assertEquals(false,true); } else { //check customer already exists and
 * do not create a customer if already exists custexists=cc.ChkCustomerExists();
 * if (custexists==true) { cc.close();
 * Reporter.log("The Customer already created."); } else //Create a Customer {
 * //enter customer email pwd and questions---Enter customer details to create
 * cc.createAccountData(email, password,question1,question2);
 * cc.clkContinueORNextButton(); //check customer creation is successful or not
 * 
 * custcreate=cc.verifyAppinfoNavigation(); if (custcreate==true) {
 * Reporter.log("Customer Creation is successful."); } else {
 * Reporter.log("Customer Creation is not successful.");
 * Assert.assertEquals("Customer is not created.","Customer should be created."
 * ); } cc.clkLogOut(); cc.close(); //compare whether successfully navigated to
 * app info after customer creation-Asset customer creation } }
 * 
 * 
 * if ((custexists==true) || (custcreate==true)) { ELSLoginPageObject
 * lpoloan=new ELSLoginPageObject(browserType, userType);
 * TestUtil.elslogin(email, password, lpoloan); //customer takes one loan
 * Reporter.log("Customer "+email+
 * " whose work state is redstate tries to take a Loan:"); //---click on req new
 * loan link lpoloan.clickRequestLoanlink();
 * 
 * Thread.sleep(5000);
 * 
 * boolean flag=lpoloan.verifyRedStatePop(); if(flag==true) { Reporter.
 * log("Popup appears, Loan process not initiated for Red State customer."); }
 * else { Reporter.log("Red State popup has not appeared.Test failed."); }
 * 
 * Thread.sleep(2000);
 * 
 * lpoloan.clkLogOut(); lpoloan.close(); Assert.assertEquals(flag,true); } }
 * 
 * 
 * }
 * 
 */