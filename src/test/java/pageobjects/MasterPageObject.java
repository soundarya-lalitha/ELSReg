package pageobjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class MasterPageObject {
	WebDriver browser;
	Properties prop;
	/**
	 * default constructor
	 */
		public MasterPageObject() throws Exception {
		String file12 = ClassLoader.getSystemResource("common.properties").getFile();
		FileInputStream fis12;
		fis12 = new FileInputStream(file12);
		prop = new Properties();
		prop.load(fis12);
		fis12.close();		
		
	}
	
/**
 * close the browser
 * @throws InterruptedException 
 */
public void close() throws InterruptedException {
	browser.close();
	browser.quit();
	Thread.sleep(1000);
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
 * set the current running browser to any other page object
 * 
 * @param runningBrowser
 */

public void setBrowser(WebDriver runningBrowser) {
	browser = runningBrowser;
}

/**
 * To click on Logout link
 */
public void clkLogOut() {
	WebElement logoutButton=browser.findElement(By.linkText(prop.getProperty("logout")));
	logoutButton.click();	
} 

public void clkLogOutAdmin() {
	WebElement logoutButton=browser.findElement(By.linkText(prop.getProperty("adminlogout")));
	logoutButton.click();	
} 
/**
 * Click on continue button on screens applicable
*/
public void clkContinueORNextButton() {
	//WebElement contiRevEliButton=browser.findElement(By.cssSelector(prop.getProperty("continueButton")));
	//contiRevEliButton.click();
	
	clkButtonwithJS(By.cssSelector(prop.getProperty("continueButton")));
}

//clkButtonwithJS
/**
 * To click on submit button on screens applicable
 */
public void clkSubmitButton() {
	WebElement submitButton=browser.findElement(By.cssSelector(prop.getProperty("submit")));
	submitButton.click();	
}

/*protected boolean isElementPresent(By by){ //with try catch element exists or not
    try{
        browser.findElement(by);
        return true;
    }
    catch(NoSuchElementException e){
        return false;
        
        
    }
}*/
/**
 * To check whether element exists or not, object prop should be passed from page object
 */

public boolean chkElementExists(By by) {
	boolean existflag = browser.findElements(by).size()!=0;
	 return existflag;	
}

/**
 * Retrieve error messages displayed after checking for their existence
 */
public String chkAndGetMessage(By by) {
	String message="Message not found";
	boolean existflag = browser.findElements(by).size()!=0;
	 	
		if (existflag==true){	
				message=getTextVal(by);
				Reporter.log("Message is displayed: "+message);			
		}else
		{
			//Reporter.log("Expected Message is not displayed.");
		}	
	 return message;
}
/**
 * To select a value from drop down---without By 
 */
//String dpdownprop=prop.getProperty("selectEmployer");
//String menuprop=prop.getProperty("employerMenu");
public void selectDropdownValue(String dpvalue,String q1Proper,String q1Menu) throws InterruptedException {
	WebElement eleDropdown = browser.findElement(By.cssSelector(q1Proper));
	eleDropdown.click();
	WebElement eleMenu=	browser.findElement(By.cssSelector(q1Menu));	
	List<WebElement> lielements=eleMenu.findElements(By.tagName("li"));
	for(WebElement var :lielements){
		String optionname;
		optionname=var.getText();
		if (optionname.trim().equals(dpvalue))
		{
			var.click();
			
		}
	}
}
/** 
* To select a value from drop down---with By
*/
public void selectListValue(
		String dpvalue,By by1,By by2) throws InterruptedException {
	WebElement eleDropdown = browser.findElement(by1);
	eleDropdown.click();
	WebElement eleMenu=	browser.findElement(by2);
	
	List<WebElement> lielements=eleMenu.findElements(By.tagName("li"));
	
	for(WebElement var :lielements){
		String optionname;
		
		optionname=var.getText();
		//System.out.println(optionname);
		if (optionname.trim().equals(dpvalue))
		{
			JavascriptExecutor je = (JavascriptExecutor) browser;
			je.executeScript("arguments[0].scrollIntoView(true);",var);
			Actions actions = new Actions(browser);
			actions.moveToElement(var).click().build().perform();
			Thread.sleep(10000);			
			//System.out.println("ELS Clicked");
			break;
		}
	}
}
/** 
* To select a value(Select Method) from list by inputting value
*/
public void selectByValMethod(By by1,String dpvalue) throws InterruptedException {
	WebElement eleDropdown=browser.findElement(by1);
	Select selObj1=new Select(eleDropdown);
	selObj1.selectByVisibleText(dpvalue);
}
/**
* To click a any WebElement(or button)---with By
*/
public void clkButton(By by) {
WebElement elementclickButton=browser.findElement(by);
elementclickButton.click();
}


public void clkButtonwithJS(By by) {
WebElement elementclickButton=browser.findElement(by);
JavascriptExecutor executor = (JavascriptExecutor)browser;
executor.executeScript("arguments[0].click();", elementclickButton);
}

/**
* To enter a value in text box---with By
*/
public void enterText(By by,String textValue) {
WebElement elementEnterText=browser.findElement(by);
elementEnterText.sendKeys(textValue);

}
/**
* To wait until an element can be clicked-Explicit wait implementation across all page objects-avoids hard wait
*/
public void waitFor(By by,int timeinSec) {
	WebDriverWait wait = new WebDriverWait(this.browser, timeinSec);
	wait.until(ExpectedConditions.elementToBeClickable(by));
}
/**
 * To extract the text value of a webelment---in this application to verify error messages
 */
public String getTextVal(By by3) {
	
	WebElement elementText=browser.findElement(by3);
	String textValue;
	textValue=elementText.getText();
	return textValue;
}

/**
 * To get the required attribute of webelement example style
 */
public boolean chkHidden(By by) {
	boolean hidden=false;
	WebElement webelehidden=browser.findElement(by);
	
	String attvaluehidden=webelehidden.getAttribute("style");
	if ((attvaluehidden.equals("display: none;"))) 
	{
		hidden=true;
	}
	else if ((attvaluehidden.equals("display:none;"))) 
	{
		hidden=true;
	}
	
	return hidden;
}
/**
 * click on request new loan link
 */
public void clickRequestLoanlink() {
	this.waitFor(By.linkText(prop.getProperty("reqnewloan")), 60);
	WebElement reqloanLink=browser.findElement(By.linkText(prop.getProperty("reqnewloan")));
	reqloanLink.click();	
}

/**
 * click No Credit Loan link
 */
public void clickNoCreditLoan() {
		
	this.waitFor(By.cssSelector(prop.getProperty("NoCreditlnk")), 60);
	WebElement NoCreditLink=browser.findElement(By.cssSelector(prop.getProperty("NoCreditlnk")));
	NoCreditLink.click();
}

/**
* click New Loan link
*/
public void clickNewLoan() {
	
	this.waitFor(By.cssSelector(prop.getProperty("NewLoanlnk")), 60);
	WebElement NewloanLink=browser.findElement(By.cssSelector(prop.getProperty("NewLoanlnk")));
	NewloanLink.click();
}

/**Mouse hover using java script **/
public void mouseHoverJScript(WebElement HoverElement) {
	try {
		if (isElementPresent(HoverElement)) {
			
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			((JavascriptExecutor) browser).executeScript(mouseOverScript,HoverElement);

		} else {
			System.out.println("Element was not visible to hover " + "\n");

		}
	} catch (StaleElementReferenceException e) {
		System.out.println("Element with " + HoverElement
				+ "is not attached to the page document"
				+ e.getStackTrace());
	} catch (NoSuchElementException e) {
		System.out.println("Element " + HoverElement + " was not found in DOM"
				+ e.getStackTrace());
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Error occurred while hovering"
				+ e.getStackTrace());
	}
}
public static boolean isElementPresent(WebElement element) {
	boolean flag = false;
	try {
		if (element.isDisplayed()|| element.isEnabled())
			flag = true;
	} catch (NoSuchElementException e) {
		flag = false;
	} catch (StaleElementReferenceException e) {
		flag = false;
	}
	return flag;
}
/**
 * Method to navigate to any menu item based on path provided
* @throws InterruptedException 
*/
public void navigateMenu(String path) throws InterruptedException {
	String navipath[]=path.split("-");
	boolean multivalues=path.contains("-");
	//menuitem has parent menu/menus then click till the menu item is reached.
	if (multivalues==true)
	{
		for(int i=0;i<navipath.length-1;i++)
		{
			Actions actions = new Actions(browser);
			WebElement mainMenu=browser.findElement(By.linkText(navipath[i]));
			System.out.println(navipath[i]);
			actions.moveToElement(mainMenu).build().perform();
		}
		Thread.sleep(1000);
		//click on the last element
		WebElement submenu = browser.findElement(By.linkText(navipath[navipath.length-1]));
		System.out.println(navipath[navipath.length-1]);
		
		submenu.click();
	}
	else
	{	//single menu item
		WebElement menu = browser.findElement(By.linkText(path));
		menu.click();		
	}
	Reporter.log("User is navigated to the path "+ path +".");
	}
/**
 * Method to read from any file given its path
 * @throws IOException 
 * @throws InterruptedException */
public String readFile(String filePath) throws IOException {
		String retString="";
		FileReader FR = new FileReader(filePath);
		BufferedReader BR= new BufferedReader(FR); 
		System.out.println(filePath);
	
		String Content = ""; 
		//Loop to read all lines one by one from file and print It.
		while((Content = BR.readLine())!= null)
		{ 
			retString=Content; 
			//System.out.println(retString);
		}
		BR.close();
		FR.close();
		//System.out.println(retString);
		return retString;		
		
}
/*** 
This will take screen shots irrespective of pass or fail to indicate test flow
	***/
public void captureScreenshot(WebDriver driver,String screenshotName)
{	 
	try 
	{
		TakesScreenshot ts=(TakesScreenshot)driver;		 
		File source=ts.getScreenshotAs(OutputType.FILE);		 
		FileUtils.copyFile(source, new File("E:\\Screenshots\\"+screenshotName+".png"));		 
		System.out.println("Screenshot taken");
	} 
	catch (Exception e)
	{		 
		System.out.println("Exception while taking screenshot "+e.getMessage());
	} 
}
/** Get unique value from table columns and rows
 * @throws InterruptedException */
public String getValueFromTable(By by,int row,int col) throws InterruptedException {
	Thread.sleep(2000);
	WebElement tableElement = browser.findElement(by);
	
	
	// get all rows of the table
	
	List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
	// get the first row
	WebElement FirstRowElement = rows.get(row);
	// get all cells of first row
	List<WebElement> firstRowCells = FirstRowElement.findElements(By.tagName("td"));
	//get the value of required cell
	
	WebElement requiredCellElement = firstRowCells.get(col);
	String requiredValue = requiredCellElement.getText();
	return requiredValue;
}
/*** Verify the button name when cip failed*/
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
/*** Verify the drop down values against a list separated by # */
public boolean verifyListValues(By by,String List) {
	boolean flag=true,res=true;
	WebElement eleDropdown=browser.findElement(by);
	Select selObj1=new Select(eleDropdown);			
	List<WebElement> ops=selObj1.getOptions();			
		for(WebElement var :ops){
			String optionname;						
			optionname=var.getText();
			if (List.contains(optionname))								
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
			res=res && flag;
		}
		return res;
}

/*** Click radio button with value */
public void clkRadioWithValue(String option) {
	WebElement rdPay = browser.findElement(By.cssSelector("input[value='"+option+"']"));
	rdPay.click();	
	Reporter.log("Value "+ option +" is selected.");
	
}


//This method to be used to switch tab and view the navigated url
	public void switchtab() throws InterruptedException {
		
		 String parentWindow = browser.getWindowHandle();
		 Set<String> windowHandles=browser.getWindowHandles();		 		  
		 
		 
			for (String windowkey:windowHandles)		
			{
				if(!windowkey.equals(parentWindow)) 
				   {//Switch to the opened tab
					browser.switchTo().window(windowkey);
					Thread.sleep(1000);
					
				   }
			}	
	}
	
	/*** get current url****/
	public String geturl() {
		
		String ur=browser.getCurrentUrl();
		return ur;
	}

	

//Click any link with LinkText property
public void ClinkLinkbyLnkTxt(String lnkName) {	
	this.clkButton(By.linkText(lnkName));	
}

/*WebElement eleDropdown=browser.findElement(By.cssSelector(prop.getProperty("mapcolLastName")));
Select selObj1=new Select(eleDropdown);			
List<WebElement> ops=selObj1.getOptions();			
			for(WebElement var :ops){
				String optionname;						
				optionname=var.getText();
				if (csvFieldList.contains(optionname))								
				{
					flag=true;
				}
				else
				{
					flag=false;
				}
				res=res && flag;
			}*/

}



