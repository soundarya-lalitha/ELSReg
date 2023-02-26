/*
 * package regloanscripts; import org.testng.Assert; import org.testng.Reporter;
 * import org.testng.annotations.Test; import pageobjects.ELSLoginPageObject;
 * import pageobjects.ReviewDisclosurePageObject; import
 * dataproviders.GarnishedCustLoanRejectedDataProvider; import
 * testcases.util.TestUtil;
 * 
 * //Author Soundarya Lalitha
 * 
 * public class ExistingCustVerifyLoanRej {
 * 
 * @Test(dataProvider="garnishedCustomer",dataProviderClass=
 * GarnishedCustLoanRejectedDataProvider.class) public void
 * verifyGarnishment(String email,String password,String ssnvalue,String
 * homephonevalue,String add,String city,String state,String workstate,String
 * expectedmessage,String userType,String browserType) throws Exception {
 * 
 * ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
 * TestUtil.elslogin(email, password, lpo); //---click on req new loan link
 * lpo.clickRequestLoanlink(); //---check I meet check box and click on continue
 * button and elect disclosure page chk agree check box and click on continue
 * click ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo);
 * //---enter application info---enter ssn and home phone
 * 
 * String actualError = TestUtil.appInfoErrorwithEmpData(ssnvalue,
 * homephonevalue,add,city,state,workstate,rd); //compare error and assert the
 * values if (actualError.equalsIgnoreCase(expectedmessage)) {
 * Reporter.log("Actual error message: "+actualError+" Expected error message: "
 * +expectedmessage); } else {
 * Reporter.log("Actual error message: "+actualError+" Expected error message: "
 * +expectedmessage); }
 * 
 * Assert.assertEquals(actualError,expectedmessage); } }
 */