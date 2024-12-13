package testcases.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;



import pageobjects.AppInfoPageObject;
import pageobjects.CIPAdminPageObject;
import pageobjects.CreateCustomerPageObject;
import pageobjects.ELSLoginPageObject;
import pageobjects.ProdLoginPageObject;
import pageobjects.LoanConfirmationPageObject;
import pageobjects.LoanPayOffPageObject;
import pageobjects.ReviewDisclosurePageObject;


public class TestUtil {

	public static String appInfoError(String ssnvalue, String homephonevalue, ReviewDisclosurePageObject rd)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
		  ai.setBrowser(rd.getBrowser());		 
		  ai.enterSSN(ssnvalue);
		  ai.enterHomePhone(homephonevalue);		  
		  Thread.sleep(1000);
		  ai.chkEligANDdisclosure();
		//---click submit 
		  ai.clkSubmit();
		 String actualError= ai.getError();	
		//click finish
		  ai.clkFinish();  
		  ai.clkLogOut();
		// close the browser
		 ai.close();
		 return actualError;
		
	}
	
	public static String appInfoErrorwithEmpData(String ssnvalue, String homephonevalue,String add, String city,String state,String workstate,ReviewDisclosurePageObject rd)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
		  ai.setBrowser(rd.getBrowser());		
		  /*ai.enterAdd(add); 
		  ai.entercity(city);
		  ai.selectState(state);*/
		  //ai.selectWorkState(workstate);
		  ai.enterSSN(ssnvalue);
		  ai.enterHomePhone(homephonevalue);	
		  Thread.sleep(5000);
		  ai.chkEligANDdisclosure();
		//---click submit 
		  ai.clkContinueORNextButton();
		  Thread.sleep(5000);
		//---capture error 
		  String actualError= ai.getError();
		 //click finish
		  ai.clkFinish();  
		  ai.clkLogOut();
		// close the browser
		 ai.close();
		return actualError;
	}
	 
	
	  public static AppInfoPageObject appInfoFlowwithEmpData1(String ssnvalue, String homephonevalue,String wrkemail,String add, String city,String
	  state,String workstate, ELSLoginPageObject lp) throws Exception,InterruptedException 
	  { 
		  AppInfoPageObject ai = new AppInfoPageObject();
		  ai.setBrowser(lp.getBrowser()); 
		  Thread.sleep(2000);
		 //enter SSN and Home Phone 
		  ai.enterSSN(ssnvalue);
		  ai.enterHomePhone(homephonevalue); 
		  if (wrkemail.equalsIgnoreCase("No"))
		  {
			  System.out.println("Work email will not be entered."); 
		  } 
		  else 
		  {
			  ai.enterWorkEmail(wrkemail); 
		  } 
		  Thread.sleep(3000);
		  ai.chkEligANDdisclosure();
		  Thread.sleep(3000);
		  //---click submit 
		  ai.clkContinueORNextButton();
		  Thread.sleep(1000); 
		  //---capture error 
		  String actualError= ai.getError();
		  ai.setMessage(actualError); 
		  return ai; 
	  }
	 
	
	public static AppInfoPageObject appInfoFlowwithEmpData3(String ssnvalue, String homephonevalue,String wrkemail,String add, String city,String state,String workstate, CreateCustomerPageObject cc)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
		  ai.setBrowser(cc.getBrowser());		
		  Thread.sleep(2000);		 
		
		 //enter SSN and Home Phone
		 ai.enterSSN(ssnvalue);
		 ai.enterHomePhone(homephonevalue);	
		 if (wrkemail.equalsIgnoreCase("No"))
		 {
			 System.out.println("Work email will not be entered.");
		 }
		 else
		 {
				 ai.enterWorkEmail(wrkemail);
		 }
		  Thread.sleep(3000);
		  ai.chkEligANDdisclosure();
		  Thread.sleep(3000);
		//---click submit 
		  ai.clkContinueORNextButton();
		  
		  Thread.sleep(1000);
		//---capture error 
		  String actualError= ai.getError();
		  ai.setMessage(actualError);
		return ai;
	}
	
	public static CreateCustomerPageObject addAddressManually(String add, String city,String state,CreateCustomerPageObject cc)
			throws Exception, InterruptedException {
		Thread.sleep(1000);
		  cc.enableAdd();
		  //first enable address fields and fill data manually		
		  Thread.sleep(1000);
		 cc.enterAdd(add); 
		 cc.entercity(city); 
		 cc.selectState(state);			 
		 return cc;
	}
	
	public static AppInfoPageObject appInfoFlowwithEmpData2(String ssnvalue, String homephonevalue,String wrkemail,String add, String city,String state,String workstate, LoanConfirmationPageObject lc)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
		  ai.setBrowser(lc.getBrowser());		
		  ai.enterSSN(ssnvalue);
		 if (wrkemail.equalsIgnoreCase("No"))
		 {
			 System.out.println("Work email will not be entered.");
		 }
		 else
		 {
				 ai.enterWorkEmail(wrkemail);
		 }
		 ai.enterHomePhone(homephonevalue);	
		 //ai.enterWorkEmail(wrkemail);		 
		
		 Thread.sleep(3000);
		 ai.chkEligANDdisclosure();
		 Thread.sleep(3000);
		  //Thread.sleep
		//---click submit 
		  ai.clkContinueORNextButton();
		  //ai.clkSubmit();
		  Thread.sleep(1000);
		//---capture error 
		  String actualError= ai.getError();
		  ai.setMessage(actualError);
		return ai;
	}
	
	public static AppInfoPageObject appInfo(String ssnvalue, String homephonevalue, ReviewDisclosurePageObject rd)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
	  ai.setBrowser(rd.getBrowser());
	  ai.enterSSN(ssnvalue);
	  ai.enterHomePhone(homephonevalue);		
	  Thread.sleep(1000);
	  ai.chkEligANDdisclosure();
	//---click submit to move to loan amt page
	  ai.clkSubmit();	
	  return ai;
	}
	
	public static AppInfoPageObject appInfowithEmpData(String ssnvalue, String homephonevalue,String workEmail,String add, String city,String state, String workstate,ELSLoginPageObject els)
			throws Exception, InterruptedException {
		AppInfoPageObject ai = new AppInfoPageObject();
		ai.setBrowser(els.getBrowser());
		/*ai.enterAdd(add); 
		ai.entercity(city);
		ai.selectState(state);*/
		 //ai.selectWorkState(workstate);
		ai.enterSSN(ssnvalue);
		ai.enterHomePhone(homephonevalue);		
		Thread.sleep(3000);
		 
		ai.enterWorkEmail(workEmail);
		 Thread.sleep(3000);
		 ai.chkEligANDdisclosure();		 
		
		//---click submit to move to loan amt page
		ai.clkContinueORNextButton();
		return ai;
}

	
	
	
		public static boolean  elslogin(String email, String password, ELSLoginPageObject lpo) throws InterruptedException {
				
				lpo.clickLoginLink();
				Thread.sleep(3000);
				lpo.enterEmail(email);
				lpo.clickContinueHome();
				
				Thread.sleep(3000);
				lpo.chkConfirm();
				lpo.enterpwd(password);
				Thread.sleep(3000);
				//lpo.clickloginButton();
				lpo.clickLoginScroll();
				boolean login=lpo.chkLoginSuccess();
				return login;
	}
		
		/****different verison of login, clicking login link using java script*********/
		public static boolean  elsloginScroll(String email, String password, ELSLoginPageObject lpo) throws InterruptedException {
			lpo.clickLoginLink();
			Thread.sleep(3000);
			lpo.enterEmail(email);
			lpo.clickContinueHome();
			
			Thread.sleep(3000);
			lpo.chkConfirm();
			lpo.enterpwd(password);
			Thread.sleep(3000);
			lpo.clickLoginScroll();
			boolean login=lpo.chkLoginSuccess();
			return login;
		}
		
		public static boolean  elsloginAdmin(String adminemail, String password, ELSLoginPageObject lpo) throws InterruptedException {
			
			Thread.sleep(20000);			
			lpo.enterAdminEmail(adminemail);
			lpo.clickContinueHomeAdmin();			
			Thread.sleep(3000);
			lpo.chkConfirmAdmin();
			lpo.enterpwdAdmin(password);
			lpo.clickloginButtonAdmin();
			boolean login=lpo.chkLoginSuccess();
			return login;		
			
		}
		
public static boolean elsloginAdminProd(String adminemail, String password, ProdLoginPageObject lpo) throws InterruptedException {
			
			lpo.enterAdminEmail(adminemail);
			lpo.clickContinueHomeAdmin();			
			Thread.sleep(3000);
			lpo.chkConfirmAdmin();
			lpo.enterpwdAdmin(password);
			lpo.clickloginButtonAdmin();
			boolean login=lpo.chkLoginSuccess();
			return login;		
			
		}
	
	/*public static CustomerLinkPageObject clickreqnewloan(ELSLoginPageObject lpo) throws Exception, InterruptedException {
		CustomerLinkPageObject clpo = new CustomerLinkPageObject();
		  clpo.setBrowser(lpo.getBrowser());
		  Thread.sleep(1000);
		  clpo.clickRequestLoanlink();
		return clpo;
	}*/
	
	//file upload with auto it	
	public static void fileupload(String filepath,String exe1path,String exe2path,String browserType) throws IOException
	{
		if (browserType.equalsIgnoreCase("firefox"))
		{
			
			String pathToAIExe = System.getProperty("user.dir") + exe1path;
		    for (int i = 0; i <= 100; i++) {
		        String uploadFilePath = System.getProperty("user.dir") + filepath;
		        ProcessBuilder pb = new ProcessBuilder(pathToAIExe, uploadFilePath);
		        pb.start();
		    }
			 	
		}else if(browserType.equalsIgnoreCase("internetexplorer"))
		{
			String pathToAIExe = System.getProperty("user.dir") + exe2path;
		    for (int i = 0; i <= 100; i++) {
		        String uploadFilePath = System.getProperty("user.dir") + filepath;
		        ProcessBuilder pb = new ProcessBuilder(pathToAIExe, uploadFilePath);
		        pb.start();
		    }	//currently not running using ie--same var used for chrome		
		}
		else
		{
			String pathToAIExe = System.getProperty("user.dir") + exe2path;
		    for (int i = 0; i <= 100; i++) {
		        String uploadFilePath = System.getProperty("user.dir") + filepath;
		        ProcessBuilder pb = new ProcessBuilder(pathToAIExe, uploadFilePath);
		        pb.start();
		    }	//currently not running using ie--same var used for chrome	
			System.out.println("Chrome"+exe2path);
		}	
		Reporter.log("File "+ filepath +" is uploaded.");
	}
	
	//file upload with Robot
		public static void fileuploadRobot(String filepath) throws IOException, AWTException
		{
			Robot robot = new Robot();
			robot.setAutoDelay(5000);
			System.out.println("inside robot class "+filepath);
			 
	        StringSelection selection = new StringSelection(filepath);
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
	 
	        robot.setAutoDelay(5000);
	 
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	 
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
	 
	        robot.setAutoDelay(5000);
	 
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        
			Reporter.log("File "+ filepath +" is uploaded.");
		}
		
	public static String loanprocess(String loanoption, String routno, String acctno1,String ename, AppInfoPageObject ai)
			throws Exception {
		String loanNum;
		LoanConfirmationPageObject lb=new LoanConfirmationPageObject();		
		  lb.setBrowser(ai.getBrowser());
		  
		  if(lb.VerifyLoanOptionsPage()==true)
		  {
			  lb.chkLoanoptionChkBoxAny(loanoption);
			  lb.clkContinueORNextButton();
			 
			  lb.enterRoutingNumber(routno);
			  lb.enterAccntNumber(acctno1);		 
			  lb.clkSubmitButton();		
			  lb.signLoan(ename);
			  lb.clkiAgreeButton();
			 // Thread.sleep(1000);
			  lb.ansCommunityQuestion();
			  lb.signLoan(ename);
			  lb.clkiAgreeButton();
			  loanNum=lb.getLoanNumber();
		  }
		  else
		  {
			  loanNum="Loan number not generated"; 
		  }
		  
		  //Thread.sleep(2000);
		  lb.clkLogOut();	
		  Thread.sleep(2000);		  
		  lb.close();
		  return (loanNum);		
		  
	}
	
	public static LoanConfirmationPageObject loanProcedureUserloggedIn(String loanoption, String routno, String acctno1,String AcnTy,String ename, AppInfoPageObject ai)
			throws Exception {
		LoanConfirmationPageObject lb=new LoanConfirmationPageObject();		
		  lb.setBrowser(ai.getBrowser()); 
		  if(lb.VerifyLoanOptionsPage()==true)
		  {
			  		//Choose loan page
				  lb.chkLoanoptionChkBoxAny(loanoption);
				  lb.clkContinueORNextButton();	
				  
				   //If ECIP page appears then 
				  lb.chkEthnicRaceChkBox();
				  
				  //Bank Account page
				  Thread.sleep(1000);
				  lb.enterRoutingNumber(routno);
				  Thread.sleep(1000);
				  lb.enterAccntNumber(acctno1);	
				  Thread.sleep(1000);
				  lb.SelAccnType(AcnTy);				  
				  lb.clkContinueORNextButton();	
				  //Loan agreement page
				  Thread.sleep(2000);
				  lb.signLoan(ename);
				  lb.clkContinueORNextButton();	
				  //Auth page
				  Thread.sleep(2000);
				  lb.signLoan(ename);
				  lb.clkContinueORNextButton();
				  String loanNum=lb.getLoanNumber2();
				  //Thread.sleep(2000);
		  		  System.out.println(loanNum);
					if (loanNum.isEmpty()==false)
					{
						Reporter.log("Loan process is successful and loan number is: "+loanNum);
					}
					else
					{
						Reporter.log("Loan process is not successful.");
					}
		  }		
		  else
		  {
			 
			Reporter.log("Loan options Page is not visible.");
			Assert.assertEquals(true,false);
		  }
		  return (lb);				  
	}
	//Loan procedure complete new function
public static AppInfoPageObject loanCompleteAndAppinfo(String loanoption, String routno, String acctno1,String AcnTy,String ename, AppInfoPageObject ai,String ssnvalue,String homephonevalue,String wrkemail,String add,String city,String state,String workstate,LoanConfirmationPageObject lc)
			throws Exception {
		//LoanConfirmationPageObject lb=new LoanConfirmationPageObject();		
		//ai.setBrowser(ai.getBrowser()); 	
		lc=TestUtil.loanProcedureUserloggedIn(loanoption, routno, acctno1,AcnTy,ename, ai);
		//In new web site My Account link should be clicked to reach the home page
		lc.clkMyAccount();
		//customer takes second loan
		////////********Reporter.log("Customer with User Name: " +email +" tries to take second Loan of : "+loanoption);
		//---click on new loan link
		Thread.sleep(5000);
		lc.clickNewLoan();						
		//---enter application info---enter ssn	and home phone
		AppInfoPageObject ai3 = TestUtil.appInfoFlowwithEmpData2(ssnvalue, homephonevalue,wrkemail,add,city,state,workstate, lc);
		//String nooa2Message=ai3.getMessage();		
		return ai;
}
	
/** Customer clicks on Apply Again and Completed loan**/
	public static void customerApplyAgain(String email, String password,String cipaction2,String homephone, String address, String city,
			String state,String workstate, String ssn2,String wrkemail, String loanamt, String routno, String acntno, String AcnType, String ename, String userType,String browserType,
			String loanNumber) throws Exception, InterruptedException {
		LoanConfirmationPageObject lc;
		//login again as customer
		ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType, userType);		
		TestUtil.elslogin(email, password, lpo1);
					Reporter.log("Customer logs in and checks the name of the button.");
					String buttonName=lpo1.verifyApplyAgainButtonName();
					
						if (buttonName.equals("Apply Again"))
						{
							Reporter.log("Button Name is changed to Apply Again.");
			
							Reporter.log("Customer clicks on Apply Again button and completes loan process.");
							lpo1.clkApplyAgainButton();
							//ReviewDisclosurePageObject rdloan1 =TestUtil.reviewdisclosure(lpo1);
							AppInfoPageObject ai2 =TestUtil.appInfoFlowwithEmpData1(ssn2, homephone,wrkemail,address,city,state,workstate,lpo1);
							String nooa2Message=ai2.getMessage();
							
							if(nooa2Message.equals( "NOAA Error has not occured.")){		
								lc=TestUtil.loanProcedureUserloggedIn(loanamt, routno, acntno,AcnType,ename, ai2);
								lc.clkLogOut();
								// close the browser
								lc.close();					
							}
						}
						else if (buttonName.contains("Call Bank")){
							Reporter.log("Button Name is Call Bank.");
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
								Reporter.log("CIP Status for the loan is " +CIPStatus);
							}
							Thread.sleep(3000);
							//Reporter.log("Loan Process successful for loan " +loanNumber);
							ca2.clkLogOut();
							// close the browser
							ca2.close();
							Assert.assertEquals(buttonName,"Apply Again");
							}	
					
						else {
							lpo1.clkLogOut();
							// close the browser
							lpo1.close();
							}
		}
	
	public static void customerApplyAgainWithWrongSSN(String email, String password,String cipaction2,String homephone, String address, String city,
			String state,String workstate, String ssn2,String wrkemail, String loanamt, String routno, String acntno, String ename, String userType,String browserType,
			String loanNumber) throws Exception, InterruptedException {
		
			//login again as customer
			ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType, userType);		
			TestUtil.elslogin(email, password, lpo1);
			Reporter.log("Customer logs in and checks the name of the button.");
			String buttonName=lpo1.verifyApplyAgainButtonName();
					
				if (buttonName.equals("Apply Again")){
					Reporter.log("Button Name is changed to Apply Again.");
					Reporter.log("Customer clicks on Apply Again button.");
					lpo1.clkApplyAgainButton();
					//ReviewDisclosurePageObject rdloan1 =TestUtil.reviewdisclosure(lpo1);
					Reporter.log("Customer provides wrong snn in the App Info page.");
					AppInfoPageObject ai2 =TestUtil.appInfoFlowwithEmpData1(ssn2,homephone,wrkemail,address,city,state,workstate,lpo1);
					ai2.clkFinish();								
						
					CIPAdminPageObject ca2 = new CIPAdminPageObject();
					ca2.setBrowser(ai2.getBrowser());
					String CIPStatus=ca2.getCIPStatus();
					if (CIPStatus.equals("CIPHold")){
						Reporter.log("CIP Status is CIPHold for the loan " +loanNumber);
					}else if(CIPStatus.equals("CIPFailure")){
						Reporter.log("Pending Applications Page: CIP Status is CIPFailure for the loan " +loanNumber);
					}else if(CIPStatus.equals("CIPByPass")){
						Reporter.log("CIP Status is CIPBypass for the loan " +loanNumber);
					}
					else{
						Reporter.log("CIP Status for the loan is " +CIPStatus);
					}
													
					ca2.clkLogOut();
					// close the browser
					ca2.close();
					Assert.assertEquals(buttonName,"Apply Again");
											
					}
				}
						 
		
		
	
	/** CIP Admin takes action on cip cipclear,Bypass etc**/
	public static void cIPAdminProcessCIP(String cipadminEmail, String cipadminPassword, String path, String employer,
			String cipaction, String ssn2, String userType,String browserType, String loanNumber)
			throws Exception, InterruptedException {
		ELSLoginPageObject lpocipad=new ELSLoginPageObject(browserType, userType);	
		Reporter.log("CIP Admin logs in and selects "+cipaction +" option.");
		Thread.sleep(1000);
		TestUtil.elsloginAdmin(cipadminEmail, cipadminPassword, lpocipad);
		CIPAdminPageObject ca = new CIPAdminPageObject();
		ca.setBrowser(lpocipad.getBrowser());
		Thread.sleep(3000);
		ca.navigateMenu(path);
		Thread.sleep(3000);
		//Select correct employer,Click on loan number, select cip action and clear the cip with correct ssn
		ca.CIPProcess(employer, loanNumber, cipaction, ssn2);
		Thread.sleep(2000);
		String cipresult=ca.getCIPResult(); //table=table/tr/td(2)/p/b
				if (cipresult.contains("CIP has been failed again for the loan")){
					Reporter.log("CIP has been failed again for the loan " +loanNumber);
				}else if(cipresult.contains("CIP is put on Hold for the loan")){
					Reporter.log("CIP is put on Hold for the loan " +loanNumber);
				}else if(cipresult.contains("is rejected")){ //Loan application # 5000042034 is rejected.
					Reporter.log("CIP is Rejected for the loan " +loanNumber);
				}
				else if(cipresult.contains("CIP has been cleared for the loan")){
					Reporter.log("CIP Cleared  for the loan " +loanNumber);
					//CIP has been cleared for the loan					
				}
			Thread.sleep(1000);	
			ca.clkLogOutAdmin();	
			Thread.sleep(5000);	
			ca.close();
	}

	/** Customer logs in and tries for a loan**/
	public static AppInfoPageObject loantrial(String email, String password, String ssn, String homephone, String workEmail,String address,
			String city, String state, String workstate,String userType,String browserType) throws Exception, InterruptedException {
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		
		TestUtil.elslogin(email, password, lpo);	
		//customer takes  loan
		Reporter.log("Customer with User Name: " +email +" tries to take a Loan: ");
		//---click on req new loan link
		Thread.sleep(5000);
		
		lpo.clickNoCreditLoan();
		Thread.sleep(5000);
		//Click on New Loan link
		lpo.clickNewLoan();				
		Thread.sleep(5000);
		
		//---enter application info---enter ssn	and home phone,city,state and address
		AppInfoPageObject ai1 =TestUtil.appInfowithEmpData(ssn, homephone,workEmail,address,city,state,workstate,lpo);
		//CIP Failure occurs
		return ai1;
	}	
	
	/**
	  * Pay off  loans seperated by #
	 * @throws InterruptedException 
	 */
	public static void payOffLoan(LoanPayOffPageObject lp,String loans) throws InterruptedException {
		String loan[] =loans.split("#");
		for(String l:loan)
		{
				System.out.println(l);
				lp.enterLoanNumber(l);				
				lp.clickSearch();
				if (lp.paidOffDetailsPage()==true)
				{
					//lp.enterPaidOffAmt("1");//enter any amount for time being
					//lp.enterPaidOffDate(lp.getfutureDate(0,3,0,"MM/dd/yyyy")); //03/11/2016 mm/dd//yyyy- todays date+ 3 month date 06/11/2016
					lp.selectPayoffDate();	
					Thread.sleep(5000);
					lp.clickPayOff();
					Thread.sleep(7000);
				}	
				String outputMessage=lp.getPayOffResult();		
				//Loan# 5000042141 is Paid Off
				String expectedMessage1="Loan# "+ l +" is Paid Off";
				String expectedMessage2="Loan "+ l +" current status is PaidOff, the loan's status can not be changed.";
				String expectedMessage3="Details for LoanID "+ l +" not found";
				if (outputMessage.equals(expectedMessage1))
				{
					Reporter.log("Loan "+ l +" is Paid Off successfully.");
				}
				else if (outputMessage.equals(expectedMessage2))
				{
					Reporter.log("Loan "+ l +" is already Paid Off.");
				}
				else if (outputMessage.equals(expectedMessage3))
				{
				
					Reporter.log("Loan Number  "+ l +" not found in the system.");
				}
		}	
	}
	/**
	  * to take screenshot
	 */
	public static void screenshotUtil(String scrname)	
	{
	
	    try{
	        Thread.sleep(1000);
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        ImageIO.write(image, "jpg", new File("E:\\Screenshots\\"+scrname+".jpg"));
	        //String URL="E:\\Screenshots\\"+scrname+".jpg";
	       // Reporter.log("<a href=" + URL+ "click to open screenshot"+"</a>");
	        //Reporter.log("<a href=" + URL + " target='_blank' >" + URL + "</a>");
	       // Reporter.log("<img src=\"file:///" + URL + "\" alt=\"\"/><br />");
	       // String s="<a href="+URL + "</a>";
	       // Reporter.log(s);
	    }
	    
	    catch(Exception e){
	        e.printStackTrace();
	    }
	}

	public static void customerChkCIPStatus(String email, String password,String cipaction, String homephone, String address,
			String city, String state,String workstate, String ssn2, String loanamt, String routno, String acntno, String ename,String userType,
			String browserType, String loanNumber) throws Exception {
		//login again as customer
		ELSLoginPageObject lpo1=new ELSLoginPageObject(browserType, userType);		
		TestUtil.elslogin(email, password, lpo1);
		Thread.sleep(5000);
		
		if (cipaction.equalsIgnoreCase("decline")){  
				lpo1.clickNoCreditLoan();
				Thread.sleep(5000);
				lpo1.clklnkPendingApplications();
				
				boolean existflag = lpo1.verifyPendingAppMessage();
				if (existflag==true){     
					Reporter.log("No Pending loan applications available message is displayed for Decline option.");
				}
				else
				{
					Reporter.log("No Pending loan applications available message is not displayed for Decline option.");
				}
				lpo1.clkLogOut();
				// close the browser
				lpo1.close();				
		}
		else
		{	
				Reporter.log("Customer logs in and checks the name of the button.");
				String buttonName=lpo1.verifyCallBankButtonName();				
					 if (buttonName.contains("Call Bank")){
						Reporter.log("Button Name is Call Bank.");
						CIPAdminPageObject ca2 = new CIPAdminPageObject();
						ca2.setBrowser(lpo1.getBrowser());
						String CIPStatus=ca2.getCIPStatus();
						if (CIPStatus.equals("CIPHold")){
							Reporter.log("CIP Status is CIPHold for the loan " +loanNumber);
							 Assert.assertEquals("CIPHold",CIPStatus);
						}else if(CIPStatus.equals("CIPFailure")){
							Reporter.log("CIP Status is CIPFailure for the loan " +loanNumber);
							Assert.assertEquals("CIPFailure",CIPStatus);
						}else if(CIPStatus.equals("CIPByPass")){
							Reporter.log("CIP Status is CIPBypass for the loan " +loanNumber);
							Assert.assertEquals("CIPByPass",CIPStatus);
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
		
	}
	
}

