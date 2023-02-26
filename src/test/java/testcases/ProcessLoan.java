/*
 * package testcases;
 * 
 * import org.testng.annotations.Test;
 * 
 * import pageobjects.AppInfoPageObject;
 * 
 * import pageobjects.ELSLoginPageObject; import
 * pageobjects.ReviewDisclosurePageObject;
 * 
 * import dataproviders.LoanDataProvider2; import testcases.util.TestUtil;
 * 
 * 
 * 
 * public class ProcessLoan {
 * 
 * @Test(dataProvider="loan2",dataProviderClass=LoanDataProvider2.class) public
 * void CompleteLoan(String email,String password,String ssnvalue,String
 * homephonevalue,String loanoption,String routno,String acctno1,String
 * ename,String add,String city,String state,String workstate,String
 * userType,String browserType) throws Exception { ELSLoginPageObject lpo=new
 * ELSLoginPageObject(browserType, userType); TestUtil.elslogin(email, password,
 * lpo); //---click on req new loan link
 * 
 * // CustomerLinkPageObject clpo =TestUtil.clickreqnewloan(lpo);
 * lpo.clickRequestLoanlink();
 * 
 * //---check I meet check box and click on continue button and elect disclosure
 * page chk agree check box and click on continue click //
 * ReviewDisclosurePageObject rd =TestUtil.reviewdisclosure(lpo); //---enter
 * application info---enter ssn and home phone AppInfoPageObject ai=
 * TestUtil.appInfoFlowwithEmpData1(ssnvalue,
 * homephonevalue,add,city,state,workstate, lpo); String
 * loan=TestUtil.loanprocess(loanoption, routno, acctno1,ename, ai);
 * System.out.println(loan);
 * 
 * 
 * Thread.sleep(1000); } }
 * 
 * 
 * 
 * 
 * 
 * 
 */