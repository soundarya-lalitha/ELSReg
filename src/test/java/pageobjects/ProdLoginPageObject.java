package pageobjects;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;





//url=http://68.168.111.183/trueconnect/index.html
//url=https://www.employeeloansolutions.com/
public class ProdLoginPageObject {
	
  //field variAbles to interact with ui
	WebDriver browser;
	
	//all object keys
	Properties prop;
	
	/**
	 * Create a browser depending on browser type to open corresponding browser
	 * @param browserType
	 * @throws IOException 
	 */
  public ProdLoginPageObject(String browserType,String userType) throws Exception {
	if (browserType.equalsIgnoreCase("firefox"))
	{
		  browser=new FirefoxDriver();
		  /*DesiredCapabilities dc = new DesiredCapabilities();
		  dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		  browser=new FirefoxDriver(dc);
		  */		
	}
	else if(browserType.equals("internetexplorer"))
	{
		System.setProperty("webdriver.ie.driver",ClassLoader.getSystemResource("IEDriverServer.exe").getFile());
		browser=new InternetExplorerDriver();		
	}
	else
	{
		//System.setProperty("webdriver.chrome.driver", ClassLoader.getSystemResource("chromedriver.exe").getFile());
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
	
			
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("test-type"); options.addArguments("start-maximized");
			 * 
			 * 
			 * 
			 * options.addArguments("enable-automation");
			 * 
			 * options.addArguments("--window-size=1920,1080");
			 * options.addArguments("--no-sandbox");
			 * options.addArguments("--disable-extensions");
			 * options.addArguments("--dns-prefetch-disable");
			 * options.addArguments("--disable-gpu");
			 * options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			 */
			  
			  
			  browser = new ChromeDriver();
			  browser.manage().window().maximize();
			 
		
				
		
	}
  
  prop=new Properties();
  
  
  String file=ClassLoader.getSystemResource("commonProd.properties").getFile();
  
 /*String file1=ClassLoader.getSystemResource("Automation/ChngColNames_Upload").getFile();
 System.out.println(file1);*/
  //String file2=ClassLoader.getSystemResource("Automation\\ChngColNames_Upload\\2016.02.21.cf").getFile();
  //System.out.println("wtih //"+file2);
  
  FileInputStream fis=new FileInputStream(file);
  prop.load(fis);
  
  if (userType.equalsIgnoreCase("admin"))
		{
		  browser.get(prop.getProperty("urladmin"));	
		}
	  else
	  	{		  
		  	browser.get(prop.getProperty("urlcustomer"));	  
	  	 }

  
  fis.close();
//implicit wait
  browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 //maximize browser
 //browser.manage().window().maximize();

  }
		
  /**
   * click on log in link
   */
  public void clickLoginLink()  {
	  		
	  		//wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop.getProperty("login"))));
			WebElement loginhomeLink=browser.findElement(By.linkText(prop.getProperty("login")));
			loginhomeLink.click();
  }
  /**
   * enter email id to login
   * @param email
 * @throws InterruptedException 
   */
public void enterEmail(String email) throws InterruptedException {
	 WebElement emailTextBox=browser.findElement(By.cssSelector(prop.getProperty("email")));
	 emailTextBox.sendKeys(email);	
}
public void enterAdminEmail(String email) throws InterruptedException {
	 WebElement emailTextBox=browser.findElement(By.cssSelector(prop.getProperty("adminemail")));
	 emailTextBox.sendKeys(email);	
}
/**
 * Click continue button on home page
 */
public void clickContinueHome() {
	WebElement continueButton=browser.findElement(By.cssSelector(prop.getProperty("continuelogin")));
	continueButton.click();
}

public void clickContinueHomeAdmin() {
	WebElement continueButton=browser.findElement(By.cssSelector(prop.getProperty("admincontinuelogin")));
	continueButton.click();
}
/**
 * Click please confirm check box
 */
public void chkConfirm() {
	WebElement confirmCheckBox=browser.findElement(By.cssSelector(prop.getProperty("chklogin")));
	confirmCheckBox.click();
}

public void chkConfirmAdmin() {
	WebElement confirmCheckBox=browser.findElement(By.cssSelector(prop.getProperty("adminchklogin")));
	confirmCheckBox.click();
}
/**
 * enter pwd value
 * @param pwd
 */
public void enterpwd(String pwd) {
	WebElement pawd=browser.findElement(By.cssSelector(prop.getProperty("pwd")));
	pawd.sendKeys(pwd);		
}


public void enterpwdAdmin(String pwd) {
	WebElement pawd=browser.findElement(By.cssSelector(prop.getProperty("adminpwd")));
	pawd.sendKeys(pwd);		
}
/**
 * click on login button
 */
public void clickloginButtonAdmin() {
	//WebElement loginButton=browser.findElement(By.cssSelector(prop.getProperty("loginButton")));
	WebElement loginButton=browser.findElement(By.cssSelector(prop.getProperty("adminloginButton")));
	loginButton.click(); 
}

public void clickloginButton() {	
	WebElement loginButton=browser.findElement(By.cssSelector(prop.getProperty("loginButton")));
	loginButton.click(); 
}

public void clickLoginScroll() {	
	WebElement loginButton=browser.findElement(By.cssSelector(prop.getProperty("loginButton")));
	JavascriptExecutor executor = (JavascriptExecutor)browser;
	executor.executeScript("arguments[0].scrollIntoView(true);", loginButton);
	
	Actions actions = new Actions(browser);
	actions.moveToElement(loginButton).click().build().perform();
}

	
	
/**
 * signout
 */
public void signOut() {
	browser.findElement(By.cssSelector(prop.getProperty("signout"))).click();
}
/**
 * To click on Logout link on any user home page
 */
public void clkLogOut() {
	WebElement logoutButton=browser.findElement(By.linkText(prop.getProperty("logout")));
	logoutButton.click();	
}

/**
 * close the browser
 * @throws InterruptedException 
 */
public void close() throws InterruptedException {
	browser.close();
	
}
/**
 * get the current running browser
 * 
 * @return
 */
public WebDriver getBrowser() {
	return browser;
}

/**
 * Click on Start Application link
 */
public void clickStartApp()  {
	WebElement startApplicationButton=browser.findElement(By.cssSelector(prop.getProperty("startapp")));
	startApplicationButton.click();
}
/**
 * click on request new loan link
 */
public void clickRequestLoanlink() {
	
	WebElement reqloanLink=browser.findElement(By.linkText(prop.getProperty("reqnewloan")));
	reqloanLink.click();
}

/**
 * click No Credit Loan link
 */
public void clickNoCreditLoan() {
	
	WebElement NoCreditLink=browser.findElement(By.cssSelector(prop.getProperty("NoCreditlnk")));
	NoCreditLink.click();
}

/**
 * click Credit Loan link
 */
public void clickCreditLoan() {
	
	WebElement NoCreditLink=browser.findElement(By.cssSelector(prop.getProperty("creditlnk")));
	NoCreditLink.click();
}

/**
* click New Loan link
*/
public void clickNewLoan() {
	
	WebElement NewloanLink=browser.findElement(By.cssSelector(prop.getProperty("NewLoanlnk")));
	NewloanLink.click();
}


/**
* click Make a Payment link
*/
public void makePaymentlnk() {
	
	WebElement MakPayLink=browser.findElement(By.cssSelector(prop.getProperty("MakePaymentlnk")));
	MakPayLink.click();
}
/**
* Get the error message next to New Loan link
*/
public String NewLoanMessage() {	
	String mess;
	WebElement NewLoanMess=browser.findElement(By.cssSelector(prop.getProperty("NoCreditMessage")));
	mess=NewLoanMess.getText();
	return mess;
}

/**
* Get the error message next to New Loan link
*/
public String creditCheckMessage() {	
	String mess;
	WebElement NewLoanMess=browser.findElement(By.cssSelector(prop.getProperty("creditLoanMessage")));
	mess=NewLoanMess.getText();
	return mess;
}

/**
* Get the class of Credit Check Loan link---check whether enabled or not
*/
public String getClassCreditChkLink() {	
	String cls;
	
	WebElement creditchk=browser.findElement(By.cssSelector(prop.getProperty("creditDiv")));
	cls=creditchk.getAttribute("class");
	return cls;
	
}

/**
* Get the message of New Loan Button
*/
public String NewLoanButtonText() {	
	String mess;
	
	WebElement NewLoanButtonText=browser.findElement(By.cssSelector(prop.getProperty("NewLoanlnk")));
	mess=NewLoanButtonText.getText();
	return mess;
}

/**
* Get the message of update address Button
*/
public String UpdateAddButtonText() {	
	String mess;
	WebElement NewLoanButtonText=browser.findElement(By.cssSelector(prop.getProperty("updateAddlnk")));
	mess=NewLoanButtonText.getText();
	return mess;
}

public boolean chkLoginSuccess() {
	boolean existflag = browser.findElements(By.linkText(prop.getProperty("logout"))).size()!=0;
	return existflag;
}

public String checkRequestLoanlink() {
	
	WebElement reqloanLink=browser.findElement(By.cssSelector(prop.getProperty("reqnewloan")));
	String disabledflag=reqloanLink.getAttribute("disabled");	
	return disabledflag;
	
}

public boolean checkRequestLoanlinkNew() {	
	
	boolean existflag = browser.findElements(By.linkText("REQUEST NEW LOAN")).size()!=0;
	
	return existflag;
}
/**
 * Verify the button name of the cip failed 
 */
public String verifyCallBankButtonName() {
	String buttonName;
	boolean existflag = browser.findElements(By.cssSelector(prop.getProperty("CallBankButton"))).size()!=0;
	if (existflag==true){
	WebElement applyAgainButton=browser.findElement(By.cssSelector(prop.getProperty("CallBankButton")));
	buttonName=applyAgainButton.getText();
	}
	else
	{
		buttonName="Button not present";
	}
	return buttonName;
}
public String verifyApplyAgainButtonName() {
	String buttonName;
	boolean existflag = browser.findElements(By.cssSelector(prop.getProperty("ApplyAgainButton"))).size()!=0;
	if (existflag==true){
	WebElement applyAgainButton=browser.findElement(By.cssSelector(prop.getProperty("ApplyAgainButton")));
	buttonName=applyAgainButton.getText();	
	}
	else
	{
		buttonName="Button not present";
	}
	return buttonName;
}
public void clkApplyAgainButton() {
	WebElement applyAgainButton=browser.findElement(By.cssSelector(prop.getProperty("ApplyAgainButton")));
	applyAgainButton.click();
	
}

public boolean verifyPendingAppMessage() {
	boolean existflag = browser.findElements(By.cssSelector(prop.getProperty("nopendingapps"))).size()!=0;
	return existflag;
}
/**
 * Click on pending applications link
 */

public void clklnkPendingApplications() {
	WebElement peningAppsButton=browser.findElement(By.cssSelector(prop.getProperty("PendingApplnk")));	
	peningAppsButton.click();
	
}
/**
 * Check whether loan process started
 * @throws InterruptedException 
 */
public boolean chkLoanProcStart() throws InterruptedException {
	boolean existflag1 = browser.findElements(By.cssSelector(prop.getProperty("ssn"))).size()!=0;
	Thread.sleep(1000);
	boolean existflag2 = browser.findElements(By.cssSelector(prop.getProperty("chkimeet"))).size()!=0;
	Thread.sleep(1000);
	boolean existflag=existflag1||existflag2;
	 return existflag;	
}

/**
 * Click on pending applications link
 * @throws InterruptedException 
 */
public boolean verifyRedStatePop() throws InterruptedException {
	boolean existflag = browser.findElements(By.cssSelector(prop.getProperty("redstatepopup"))).size()!=0;
	WebElement okButton=browser.findElement(By.cssSelector(prop.getProperty("okButton")));
	Thread.sleep(1000);
	
	JavascriptExecutor executor = (JavascriptExecutor)browser;
	executor.executeScript("arguments[0].click();", okButton);
	//okButton.click();
	Thread.sleep(1000);
	return existflag;
}




}



