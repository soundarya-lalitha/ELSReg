package pageobjects;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;





public class NavigationPageObject extends MasterPageObject {
	public NavigationPageObject() throws Exception {
		super();
	}
	String l;
	public String navigatetoPath(String path,String userType) throws InterruptedException  {
			String r="", headingname;
			boolean multivalues=path.contains("-");
			if (multivalues==true)
			{
					String splt[] =path.split("-");			
					Thread.sleep(1000);
					//for ( String s: splt) { 
						
						//boolean existflag = browser.findElements(By.linkText(s)).size()!=0;
						/*if (existflag == true) -----old code	
						{
							WebElement empManagementLink=browser.findElement(By.linkText(s));
							//Thread.sleep(1000);
							empManagementLink.click();	
							Thread.sleep(500);
						}
						else
						{
							WebElement homeLink=browser.findElement(By.cssSelector(prop.getProperty("homeLink")));
							homeLink.click();	
							Reporter.log(" not able to click on "+s);	
						}				
						}	
						*/
								
						//}	
					
		
					//USE ACTIONS
					/*Actions actions = new Actions(browser);
					
					WebElement mainMenu=browser.findElement(By.linkText(splt[0]));
					//System.out.println(splt[0]);
					//System.out.println(splt[1]);
					actions.moveToElement(mainMenu).build().perform();*/
					
					//OR use jscript
					
						WebElement mainMenu=browser.findElement(By.linkText(splt[0]));
						mouseHoverJScript(mainMenu);
						
						WebDriverWait wait1 = new WebDriverWait(browser, 20);
						wait1.until(ExpectedConditions.presenceOfElementLocated(By.linkText(splt[1])));
					
			
					//click on the submenu if parent menu is present otherwise click on the single item in case null value passed as parent(no main menu)
					WebElement subMenu = browser.findElement(By.linkText(splt[1]));
					subMenu.click();				
					
					//last string is the actual menu item			
			    	l=splt[splt.length-1];
					//System.out.println(l);
					Thread.sleep(6000);
			}
			else
			{//single menu item
				WebElement menu = browser.findElement(By.linkText(path));
				menu.click();				
				//this is the sinlge menu item not under any main menu so this is the switch value to compare		
		    	l=path;
				//System.out.println(l);
				Thread.sleep(6000);				
			}
			
			if (userType.equalsIgnoreCase("admin"))
			{
				WebElement parent=browser.findElement(By.cssSelector(prop.getProperty("parentsubmenuHeading")));
				WebElement child = parent.findElement(By.tagName("label"));
				headingname=child.getText();
			}
			else
		  	{		  
			  	WebElement parent=browser.findElement(By.cssSelector(prop.getProperty("customersubmenuHeading")));
				headingname=parent.getText();  
		  	 }
				
				
				switch(l){
			    case "Create Users" :
			    	if ((headingname.trim().contains("Create")) || (headingname.trim().equals("Add HR Admin Account"))||  (headingname.trim().equals("Add Payroll Master Account"))||(headingname.trim().equals("Add CIP Account"))){
			    		r="navigated to page";
			    		Reporter.log("Create Users Page navigation successful.");
				    }else{
				    	r="not able to navigate to create Page";
				    	Reporter.log("Create Users Page navigation is not successful.");
				    }break; 
			    case "Edit Existing Users" :			    	
			    	if((headingname.trim()).contains("Edit")){
			    		r="navigated to page";
			    		Reporter.log("Edit Existing Users Page navigation successful.");
				    }else{
				    	r="not able to navigate to Edit Existing Users Page";
				    	Reporter.log("Edit Existing Users Page navigation is not successful.");
				    }break; 
			    case "Create CIP User" :
			    	if((headingname.trim()).equalsIgnoreCase("Add CIP Account")){
			    		r="navigated to page";
			    		Reporter.log("Create CIP User Page navigation successful.");
				    }else{
				    	r="not able to navigate to create CIP User Page";
				    	Reporter.log("Create CIP User Page navigation is not successful.");
				    }break; 
			    case "Edit CIP User" :			    	
			    	if((headingname.trim()).equalsIgnoreCase("Edit CIP Account")){
			    		r="navigated to page";
			    		Reporter.log("Edit CIP User Page navigation successful.");
				    }else{
				    	r="not able to navigate to Edit CIP Users Page";
				    	Reporter.log("Edit CIP User Page navigation is not successful.");
				    }break; 
				    
			    case "CIP Report" :			    	
			    	if((headingname.trim()).equalsIgnoreCase("CIP Details Report")){
			    		r="navigated to page";
			    		Reporter.log("CIP Report Page navigation successful.");
				    }else{
				    	r="not able to navigate to CIP Report Page";
				    	Reporter.log("CIP Report Page navigation is not successful.");
				    }break; 
				    
			    case "Withdrawn Loans Report" :			    	
			    	if((headingname.trim()).equalsIgnoreCase("CIP Withdrawn Report")){
			    		r="navigated to page";
			    		Reporter.log("Withdrawn Loans Report Page navigation successful.");
				    }else{
				    	r="not able to navigate to Withdrawn Loans Report";
				    	Reporter.log("Withdrawn Loans Report Page navigation is not successful.");
				    }break; 					    
				    
			    case "Pay off Loan" :			    	
			    	if((headingname.trim()).contains("Paid off")){
			    		r="navigated to page";
			    		Reporter.log("Pay off Loan Page navigation successful.");
				    }else{
				    	r="not able to navigate to Pay off Loan Page";
				    	Reporter.log("Pay off Loan Page navigation is not successful.");
				    }break;
			    		       
			    case "Employee Service" :
			    	if((headingname.trim()).equalsIgnoreCase("Employee Service")){
			    		r="navigated to page";
			    		Reporter.log("Employee Service Page navigation successful.");
			    	}else{
				    	r="not able to navigate to Employee Service Page";
				    	Reporter.log("Employee Service Page navigation is not successful.");
				    }break; 		     
			    case "Clear CIP Status" :
			    	if((headingname.trim()).equalsIgnoreCase("CIP Administration")){
			    		r="navigated to page";
			    		Reporter.log("Clear CIP Page navigation successful.");
			    	}else{
				    	r="not able to navigate to Clear CIP Page";
				    	Reporter.log("Clear CIP Page navigation is not successful.");
				    }break; 
			     case "Underwriting Data" :			    	
			    	if((headingname.trim()).equalsIgnoreCase("Underwriting Data Report")){
			  		r="navigated to page";
			    		Reporter.log("Underwriting Data Page navigation successful.");
			    	}else{
				    	r="not able to navigate to Under Writing Data Report Page";
				    	Reporter.log("Under Writing Data Page navigation is not successful.");
				    }break; 		       
			    case "Revenue Report" :
			    	if((headingname.trim()).equalsIgnoreCase("Annual Revenue Report")){
			    		r="navigated to page";
			    		Reporter.log("Revenue Report Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Revenue Report Page";
			    		Reporter.log("Revenue Report Page navigation is not successful.");
			    	}break; 		     
			    case "Loan Details Report" :
			    	if((headingname.trim()).contains("Loan Details Report")){
			    		r="navigated to page";
			    		Reporter.log("Loan Details Report Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Loan Details Report Page";
			    		Reporter.log("Loan Details Report Page navigation is not successful.");
			    	}break; 			    	
			    case "Pay Calendar Report" :
			    	if((headingname.trim()).equalsIgnoreCase("Pay Check Calendar Report")){
			    		r="navigated to page";
			    		Reporter.log("Pay Calendar Report Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Pay Calendar Report Page";
			    		Reporter.log("Pay Calendar Report Page navigation is not successful.");
			    	}break; 
			    case "Pay Deduction Report" :
			    	if((headingname.trim()).equalsIgnoreCase("Payroll Deduction Report")){
			    		r="navigated to page";
			    		Reporter.log("Payroll Deduction Report navigation successfull.");
			    	}else{
			    		r="not able to navigate to Pay Deduction Report Page";
			    		Reporter.log("Payroll Deduction Report navigation is not successful.");
			    	}break; 		
					case "Daily Loans Details" :
			    	if((headingname.trim()).equalsIgnoreCase("Daily Loans Report")){
			    		r="navigated to page";
			    		Reporter.log("Daily Loans Details navigation successful.");
			    	}else{
			    		r="not able to navigate to Pay Deduction Report Page";
			    		Reporter.log("Daily Loans Details Report navigation is not successful.");
			    	}break;
			    	
					case "Blackout Schedule" :
				    	if((headingname.trim()).equalsIgnoreCase("Blackout Report")){
				    		r="navigated to page";
				    		Reporter.log("Blackout Report navigation successful.");
				    	}else{
				    		r="not able to navigate to Blackout Report Page";
				    		Reporter.log("Blackout Report navigation is not successful.");
				    	}break; 
				    	
					case "School Calendar" :
				    	if((headingname.trim()).equalsIgnoreCase("School Calendar Report")){
				    		r="navigated to page";
				    		Reporter.log("School Calendar Report navigation successful.");
				    	}else{
				    		r="not able to navigate to School Calendar Report Page";
				    		Reporter.log("School Calendar Report navigation is not successful.");
				    	}break; 
				    
					case "Florida Stamp Tax Report" :
				    	if((headingname.trim()).equalsIgnoreCase("Florida Stamp Tax Report")){
				    		r="navigated to page";
				    		Reporter.log("Florida Stamp Tax Report navigation successful.");
				    	}else{
				    		r="not able to navigate to Florida Stamp Tax Report Page";
				    		Reporter.log("Florida Stamp Tax Report navigation is not successful.");
				    	}break; 
				    	
					case "Employer Info Report" :
				    	if((headingname.trim()).equalsIgnoreCase("Employer Details Report")){
				    		r="navigated to page";
				    		Reporter.log("Employer Info Report navigation successful.");
				    	}else{
				    		r="not able to navigate to Employer Info Report Page";
				    		Reporter.log("Employer Info Report navigation is not successful.");
				    	}break; 
				    	
					case "ACH Report" :
				    	if((headingname.trim()).equalsIgnoreCase("ACH Report")){
				    		r="navigated to page";
				    		Reporter.log("ACH Report navigation successful.");
				    	}else{
				    		r="not able to navigate to Employer Info Report Page";
				    		Reporter.log("ACH Report navigation is not successful.");
				    	}break; 
				    	
				    	
			    case "Setup New Employer" :
			    	if((headingname.trim()).equalsIgnoreCase("Create Employer")){
			    		r="navigated to page";
			    		Reporter.log("Setup New Employer Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Setup New Employer Page";
			    		Reporter.log("Setup New Employer Page navigation is not successful.");
			    	}break; 		     
			    case "Upload Payroll Calendar" :
			    	if((headingname.trim()).equalsIgnoreCase("Payroll Calendar Data Wizard")){
			    		r="navigated to page";
			    		Reporter.log("Upload Payroll Calendar Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Upload Payroll Calendar Page";
			    		Reporter.log("Upload Payroll Calendar Page navigation is not successful.");
			    	}break;  			    	
			    case "Edit Employer" :
			    	if((headingname.trim()).equalsIgnoreCase("Edit Employer")){
			    		r="navigated to page";
			    		Reporter.log("Edit Employer Information Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Edit Employer Page";
			    		Reporter.log("Edit Employer Page navigation is not successful.");
			    	}break; 
			    case "Census Search" :
			    	if((headingname.trim()).equalsIgnoreCase("Census Search")){
			    		r="navigated to page";
			    		Reporter.log("Census Search Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Census Search Page";
			    		Reporter.log("Census Search Page navigation is not successful.");
			    	}break; 
			    case "Report Termination" :
			    	if((headingname.trim()).equalsIgnoreCase("Report Termination")){
			    		r="navigated to page";
			    		Reporter.log("Report Termination Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Report Termination Page";
			    		Reporter.log("Report Termination Page navigation is not successful.");
			    	}break; 
			    	
			    case "Rollback Termination" :
			    	if((headingname.trim()).equalsIgnoreCase("Rollback Termination")){
			    		r="navigated to page";
			    		Reporter.log("Rollback Termination Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Report Termination Page";
			    		Reporter.log("Rollback Termination Page navigation is not successful.");
			    	}break; 
			    	
			    case "CreditEngine Test Page" :
			    	if((headingname.trim()).equalsIgnoreCase("Credit Engine Test")){
			    		r="navigated to page";
			    		Reporter.log("Credit Engine Test Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Credit Engine Test Page";
			    		Reporter.log("Credit Engine Test Page navigation is not successful.");
			    	}break; 
			    	
			    case "Upload LoanBalance" :
			    	if((headingname.trim()).contains("Please upload Loan")){
			    		r="navigated to page";
			    		Reporter.log("Upload LoanBalance Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Upload LoanBalance Page";
			    		Reporter.log("Upload LoanBalance Page navigation is not successful.");
			    	}break; 
			    	
			    case "Cancel Loans" :
			    	if((headingname.trim()).contains("Cancel Loans")){
			    		r="navigated to page";
			    		Reporter.log("Cancel Loans Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Cancel Loans Page";
			    		Reporter.log("Cancel Loans Page navigation is not successful.");
			    	}break; 	
			    	
			    case "Customer Service" :
			    	if((headingname.trim()).contains("Customer Servic")){
			    		r="navigated to page";
			    		Reporter.log("Customer Service Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Customer Service Page";
			    		Reporter.log("Customer Service Page navigation is not successful.");
			    	}break; 
			    	
			    case "Employer Dashboard" :
			    	if((headingname.trim()).equalsIgnoreCase("Employer Dashboard")){
			    		r="navigated to page";
			    		Reporter.log("Employer Dashboard Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Employer Dashboard Page";
			    		Reporter.log("Employer Dashboard Page navigation is not successful.");
			    	}break; 
	            
			    case "Daily Files Verification" :
		    	if((headingname.trim()).equalsIgnoreCase("Determine if a file has run and was posted to bank’s FTP site")){
		    		r="navigated to page";
		    		Reporter.log("Daily Files Verification Page navigation successful.");
		    	}else{
		    		r="not able to navigate to Daily Files Verification Page";
		    		Reporter.log("Daily Files Verification Page navigation is not successful.");
		    	}break;
			    case "Regenerate Loan Documents" :
			    	if((headingname.trim()).equalsIgnoreCase("Generate Loan Documents")){
			    		r="navigated to page";
			    		Reporter.log("Regenerate Loan Documents Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Regenerate Loan Documents Page";
			    		Reporter.log("Regenerate Loan Documents Page navigation is not successful.");
			    	}break;
			    case "App Log" :
			    	if((headingname.trim()).equalsIgnoreCase("Application Log")){
			    		r="navigated to page";
			    		Reporter.log("Application Log Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Application Log Page";
			    		Reporter.log("Application Log Page navigation is not successful.");
			    	}break;
			    case "Add HR Users" :
			    	if((headingname.trim()).equalsIgnoreCase("Add HR Admin Account")){
			    		r="navigated to page";
			    		Reporter.log("Add HR Users Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Add HR Users Page";
			    		Reporter.log("Add HR Users Page navigation is not successful.");
			    	}break;
			    case "Edit HR Users" :
			    	if((headingname.trim()).equalsIgnoreCase("Edit HR Admin Account")){
			    		r="navigated to page";
			    		Reporter.log("Edit HR Users Page navigation successful.");
			    	}else{
			    		r="not able to navigate to edit HR Users Page";
			    		Reporter.log("Edit HR Users Page navigation is not successful.");
			    	}break;
			    case "Upload Census File" :
			    	if((headingname.trim()).equalsIgnoreCase("Census Data Wizard")){
			    		r="navigated to page";
			    		Reporter.log("Upload Census File Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Upload Census Page";
			    		Reporter.log("Upload Census File Page navigation is not successful.");
			    	}break;	
			    case "Upload PDT File" :
			    	if((headingname.trim()).equalsIgnoreCase("Payroll Deductions Taken Data Wizard")){
			    		r="navigated to page";
			    		Reporter.log("Upload PDT File Page navigation successful.");
			    	}else{
			    		r="not able to navigate to upload PDT Page";
			    		Reporter.log("Upload PDT File Page navigation is not successful.");
			    	}break;
			    case "Download PDI File" :
			    	if((headingname.trim()).equalsIgnoreCase("Payroll Deductions Instructions")){
			    		r="navigated to page";
			    		Reporter.log("Download PDI File Page navigation successful.");
			    	}else{
			    		r="not able to navigate to download PDI Page";
			    		Reporter.log("Download PDI File Page navigation is not successful.");
			    	}break;
			    case "Search Documents" :
			    	if((headingname.trim()).equalsIgnoreCase("Download Documents")){
			    		r="navigated to page";
			    		Reporter.log("Search Documents Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Search Documents";
			    		Reporter.log("Search Documents Page navigation is not successful.");
			    	}break;
			    case "HR Faqs" :
			    	if((headingname.trim()).equalsIgnoreCase("FAQ")){
			    		r="navigated to page";
			    		Reporter.log("HR Faqs Page navigation successful.");
			    	}else{
			    		r="not able to navigate to FAQs Page";
			    		Reporter.log("HR Faqs Page navigation is not successful.");
			    	}break;
			    case "MY LOANS" :
			    	if((headingname.trim()).equalsIgnoreCase("Employee Home Page")){
			    		r="navigated to page";
			    		Reporter.log("Employee Home Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Employee Home Page";
			    		Reporter.log("Employee Home Page navigation is not successful.");
			    	}break;
			    	
			    case "MANAGE PROFILE" :
			    	if((headingname.trim()).equalsIgnoreCase("Manage Your Profile")){
			    		r="navigated to page";
			    		Reporter.log("Manage Profile Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Manage Profile Page";
			    		Reporter.log("Manage Profile Page navigation is not successful.");
			    	}break;
			    case "UPDATE ADDRESS" :
			    	if((headingname.trim()).equalsIgnoreCase("Update Address")){
			    		r="navigated to page";
			    		Reporter.log("Update Address  Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Update Address Page";
			    		Reporter.log("Update Address Page navigation is not successful.");
			    	}break;
			    
			    case "LOAN DOCUMENTS" :
			    	if((headingname.trim()).equalsIgnoreCase("Loan Documents")){
			    		r="navigated to page";
			    		Reporter.log("Loan Documents Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Loan Documents Page";
			    		Reporter.log("Loan Documents Page navigation is not successful.");
			    	}break;
			    case "ELECTRONIC DISCLOSURE" :
			    	if((headingname.trim()).equalsIgnoreCase("Electronic Disclosure")){
			    		r="navigated to page";
			    		Reporter.log("Electronic Disclosure Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Electronic Disclosure Page";
			    		Reporter.log("Electronic Disclosure Page navigation is not successful.");
			    	}break;
			    	
			    case "MAKE A PAYMENT" :
			    	if((headingname.trim()).equalsIgnoreCase("Make a Payment")){
			    		r="navigated to page";
			    		Reporter.log("Make A Payment Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Make A Payment Page";
			    		Reporter.log("Make A Payment Page navigation is not successful.");
			    	}break;
			    case "CONTACT US" :
			    	if((headingname.trim()).equalsIgnoreCase("Contact Us")){
			    		r="navigated to page";
			    		Reporter.log("Contact Us Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Contact Us Page";
			    		Reporter.log("Contact Us Page navigation is not successful.");
			    	}break;
			    case "PENDING APPLICATIONS" :
			    	if((headingname.trim()).equalsIgnoreCase("Pending Loan Applications")){
			    		r="navigated to page";
			    		Reporter.log("Pending Applications Page navigation successful.");
			    	}else{
			    		r="not able to navigate to Pending Applications Page";
			    		Reporter.log("Pending Applications Page navigation is not successful.");
			    	}break;
			    default :
		    		r= l+ " is not a valid Menu option.";
		    		Reporter.log(l+ " is not a valid Menu option.");			    	
			}
			return r;
	}
	
	
	//Check all home page navigations---separate function for each navigation	



//Verify Bank Account link navigation
public boolean VerifyBankAccNavigation() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Open Your Account");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				existflag=this.chkElementExists(By.className(prop.getProperty("chimeLabelclass")));
				if (existflag==true)
				{
					System.out.println("chime page appears.");
				}	
				else
				{
					System.out.println("chime page has not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}

//Verify Financial Counseling link navigation
public boolean VerifyFiNCounNavigation(String expurl) throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Start Your Counseling");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
		
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				String acturl=this.geturl();	
				if (acturl.equals(expurl))
				{
					System.out.println("Financial Counselling page is visible.");
					existflag=true;
				}
				else
				{
					System.out.println("Financial Counselling page is not visible.");
					existflag=false;
				}				
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	return existflag;
}

//Verify Debt Management link navigation
public boolean VerifyDebtManagment() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Apply Today");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				existflag=this.chkElementExists(By.cssSelector(prop.getProperty("debtMgmt")));
				if (existflag==true)
				{
					System.out.println("Debt Mgmt page appeared.");
				}	
				else
				{
					System.out.println("Debt Mgmt has not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}

//Verify clicking on Trueconnect Rx link navigated to Rx page
public boolean VerifyTcRx() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Start Saving Now");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set <String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				existflag=this.chkElementExists(By.className(prop.getProperty("rxcls")));
				if (existflag==true)
				{
					System.out.println("TrueConnect Rx page has appeared.");
				}	
				else
				{
					System.out.println("TrueConnect Rx has not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}

//Verify clicking on Help? link navigated to Help page
public boolean VerifyNeedHelp() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Get Help");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				existflag=this.chkElementExists(By.className(prop.getProperty("helpcls")));
				if (existflag==true)
				{
					System.out.println("TrueConnect Help page appeared.");
				}	
				else
				{
					System.out.println("TrueConnect Help has not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}


//Verify clicking on Emergency Savings link navigated to Savings page
public boolean VerifyEmerSav() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.ClinkLinkbyLnkTxt("Create Your Account");
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				Thread.sleep(3000);

				existflag=this.chkElementExists(By.className(prop.getProperty("savingcls")));
				if (existflag==true)
				{
					System.out.println("Emergency Savings page appeared.");
				}	
				else
				{
					System.out.println("Emergency Savings has not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}

//Verify clicking on Emergency Savings link navigated to Savings page
public boolean VerifyTrueCReward() throws InterruptedException {	
	boolean existflag;
	existflag=false;
	this.clkButton(By.cssSelector(prop.getProperty("rewardslnk")));
	Thread.sleep(3000);
	//click on Continue button on the External page warning pop up 
	this.clkButton(By.cssSelector(prop.getProperty("externpopupid")));
	Thread.sleep(3000);
	String parentWindow = browser.getWindowHandle();
	 Set<String> windowHandles=browser.getWindowHandles();	  
	 	 
		for (String windowkey:windowHandles)		
		{
			if(!windowkey.equals(parentWindow)) 
			   {//Switch to the opened tab
				browser.switchTo().window(windowkey);
				//page not loading currently
				Thread.sleep(3000);
				existflag=this.chkElementExists(By.className(prop.getProperty("rewardscls")));
				if (existflag==true)
				{
					System.out.println("Rewards page appeared.");
				}	
				else
				{
					System.out.println("Rewards page not appeared.");
				}
				browser.close();				
			   }
		}		
		browser.switchTo().window(parentWindow);
	
	return existflag;
}


//Verify New Loan link navigation
public String VerifyNewLoanNavigation() throws InterruptedException {	
	String r;
	this.clkButton(By.cssSelector(prop.getProperty("NewLoanlnk")));
	Thread.sleep(5000);
	boolean existflag=this.chkElementExists((By.cssSelector(prop.getProperty("ssn"))));
	
	if (existflag==true)
	{ 
		Reporter.log("App info page is displayed and New loan link navigation succeded");
		r="navigated to page";
	}
	else
	{
		Reporter.log("New Loan link navigation failed.");
		r="not able to navigate to App info page";
	}
	return r;
}

//Verify Pending apps navigation
public String VerifyPendingApps() throws InterruptedException {	
	String r;
	boolean existflag=this.chkElementExists((By.cssSelector(prop.getProperty("PendingApplnk"))));		
	Thread.sleep(3000);
	if (existflag==true)
	{ 
		Reporter.log("Pending Applications Header is displayed.");
		r="navigated to page";
	}
	else
	{
		Reporter.log("Pending Applications Header is not displayed.");
		r="not able to navigate to Pending Applications page";
	}	
	return r;
}


//Verify Loan History page,Make A Payment,Loan Documents and update Address navigation
public String VerifyNavigations(String lnkOnNoCred,String headerexp) throws InterruptedException {
	String r="";
	this.ClinkLinkbyLnkTxt(lnkOnNoCred);
	Thread.sleep(5000);
	String headeract=this.getTextVal((By.cssSelector(prop.getProperty("bankaccHeader"))));
	
	switch(headerexp)
	{
    case "Employee Loan History / Balances" :
    	if (headerexp.equals(headeract))
    	{ 
    		Reporter.log("Loan History page is displayed.");
    		r="navigated to page";
    	}
    	else
    	{
    		Reporter.log("Loan History page is not displayed.");
    		r="not able to navigate to Loan History page";
    	}break; 
    	
    case "Make A Payment" :			    	
    	if (headeract.equals("Make A Payment"))
    	{ 
    		Reporter.log("Make A Payment Page is displayed.");
    		r="navigated to page";
    	}
    	else
    	{
    		Reporter.log("Make A Payment Page is not displayed.");
    		r="not able to navigate to Make A Payment page";
    	}break;
    	
    case "Loan Documents" :	
    	if (headerexp.equals(headeract))
    	{ 
    		Reporter.log("Loan Documents Page is displayed.");
    		r="navigated to page";
    	}
    	else
    	{
    		Reporter.log("Loan Documents Page is not displayed.");
    		r="not able to navigate to Loan Documents page";
    	}break;		
		
	case "Update Address" :	
		if (headerexp.equals(headeract))
		{ 
			Reporter.log("Update Address Page is displayed.");
			r="navigated to page";
		}
		else
		{
			Reporter.log("Update Address Page is not displayed.");
			r="not able to navigate to Update Address page";
		}break;	
		
	case "Electronic Disclosure" :	
		if (headerexp.equals(headeract))
		{ 
			Reporter.log("Electronic Disclosure Page is displayed.");
			r="navigated to page";
		}
		else
		{
			Reporter.log("Electronic Disclosure Page is not displayed.");
			r="not able to navigate to Electronic Disclosure page";
		}break;		
}
	return r;
}


//Electronic Disclosure page navigation
public String VerifyEDNavigation() throws InterruptedException {
	String r;		
	this.clkButton(By.cssSelector(prop.getProperty("ElecDislnk")));	
	Thread.sleep(5000);
	String header=this.getTextVal((By.cssSelector(prop.getProperty("bankaccHeader"))));		
	
		if (header.equals("Electronic Disclosure"))
		{ 
			Reporter.log("Electronic Disclosure Page is displayed.");
			r="navigated to page";
		}
		else
		{
			Reporter.log("Electronic Disclosure Page is not displayed.");
			r="not able to navigate to Electronic Disclosure page";
		}	
	return r;
	}

public String VerifyNoCreditNavigation() throws InterruptedException {		
	String r;
	this.ClinkLinkbyLnkTxt("Check Your Account");
	Thread.sleep(5000);
	boolean existflag=this.chkElementExists((By.cssSelector(prop.getProperty("NewLoanlnk"))));	
	
	if (existflag==true)
	{ 
		Reporter.log("No Credit Check Loans page is displayed.");
		r="navigated to page";
	}
	else
	{
		Reporter.log("No Credit Check Loans page is not displayed.");
		r="not able to navigate to No Credit Check Navigation page";
	}	
	return r;
	
}

//Click Loans Home Link
public void ClkLoansHome() throws InterruptedException {
	this.clkButton(By.cssSelector(prop.getProperty("LoansHomePage")));
	
}





}	
	
	

	


