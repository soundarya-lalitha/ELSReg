package pageobjects;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class AppInfoPageObject extends MasterPageObject{
	private String message;
	public AppInfoPageObject() throws Exception {
		super();
		
		}
	

	/**
	 * select state for new customer
	 */
	public void selectState(String state) throws InterruptedException {
		this.selectListValue(state, By.cssSelector(prop.getProperty("selectState")), By.cssSelector(prop.getProperty("stateMenu")));
	}
	
	/**
	 * select work state for new customer
	 * @throws InterruptedException 
	 */
public void selectWorkState(String workstate) throws InterruptedException {
	//this.selectByValMethod(By.cssSelector(prop.getProperty("workState")),workstate);
	this.waitFor(By.cssSelector(prop.getProperty("workstatebutton")), 100);
	Thread.sleep(2000);
	//this.clkButton(By.cssSelector(prop.getProperty("workState")));
	//this.selectByValMethod(By.cssSelector(prop.getProperty("workState")),workstate);
	this.selectListValue(workstate, By.cssSelector(prop.getProperty("workstatebutton")), By.cssSelector(prop.getProperty("workstatemenu")));
	
}
	/**
	 * enter address for new customer
	 */
	public void enterAdd(String add) {
		WebElement addTextBox=browser.findElement(By.cssSelector(prop.getProperty("empAddress")));
		addTextBox.clear();
		addTextBox.sendKeys(add);
	}
	/**
	 * enter city for new customer
	 */
	public void entercity(String city) {
		WebElement cityTextBox=browser.findElement(By.cssSelector(prop.getProperty("empCity")));
		cityTextBox.clear();
		cityTextBox.sendKeys(city);
	}
	
	
  public void enterSSN(String ssnV) {
	WebElement ssnTextBox=browser.findElement(By.cssSelector(prop.getProperty("ssn")));
	ssnTextBox.sendKeys(ssnV);
	
}


public void enterHomePhone(String homephone) {
	JavascriptExecutor myExecutor = ((JavascriptExecutor) browser);
	WebElement homephoneTextBox=browser.findElement(By.cssSelector(prop.getProperty("homephone")));
	myExecutor.executeScript("arguments[0].value='" + homephone +"';", homephoneTextBox);
}

public void enterWorkEmail(String wrkemail) {
		/*
		 * WebElement
		 * wrkemailTextBox=browser.findElement(By.cssSelector(prop.getProperty(
		 * "workEmail"))); wrkemailTextBox.clear(); wrkemailTextBox.sendKeys(wrkemail);
		 */
	JavascriptExecutor myExecutor = ((JavascriptExecutor) browser);
	WebElement wrkemailTextBox=browser.findElement(By.cssSelector(prop.getProperty("workEmail")));
	myExecutor.executeScript("arguments[0].value='" + wrkemail +"';", wrkemailTextBox);

	
	}



public void chkEligANDdisclosure() {
	this.waitFor(By.cssSelector(prop.getProperty("eligChkBox")), 60);
	WebElement imeetCheckBox=browser.findElement(By.cssSelector(prop.getProperty("eligChkBox")));
	imeetCheckBox.click();	
}

public void clkSubmit() {
	this.waitFor(By.cssSelector(prop.getProperty("submit")), 30);	
	WebElement submitButton=browser.findElement(By.cssSelector(prop.getProperty("submit")));	
	//Scroll the browser to the element's Y position
	((JavascriptExecutor) browser).executeScript("window.scrollTo(0,"+submitButton.getLocation().y+")");	
	submitButton.click();	
	//implicit wait
    browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
/**
 * check NOAA Error occured after clicking on submit button on app info page
 */
public String getError() {
	
	String NooaErr;	
	if(this.chkElementExists(By.cssSelector(prop.getProperty("nooamessage"))))
	{	
		WebElement Nooalenerror=browser.findElement(By.cssSelector(prop.getProperty("nooamessage")));
		NooaErr=Nooalenerror.getText();
		
	}
	else
	{
		NooaErr="NOAA Error has not occured.";
	}
	return NooaErr;
}
/**
 * check CIP Failure occured after clicking on submit button on app info page---OBSELETE Method
 * @throws InterruptedException 
 */
public String chkCIPErrorAndclkFinish() throws InterruptedException {
	String CIPError="";
	Thread.sleep(2000);  
	if(this.chkHidden(By.cssSelector(prop.getProperty("cipFailurediv")))==false)
	{	
		CIPError="CIP Failure has occured.";		
	}
	else if(this.chkHidden(By.cssSelector(prop.getProperty("nooaerrordiv")))==false)
		
	{	
		CIPError="NOAA Error has occured.";		
	}
	else
	{		
		CIPError="CIP Failure has not occured.";
	}
	
	this.clkFinish();
	return CIPError;
}

public String chkCIPErrorAndclkFinish2() throws InterruptedException {
	
	Thread.sleep(2000);  
	String CIPErr;	
	if(this.chkElementExists(By.cssSelector(prop.getProperty("cipFailurediv"))))
	{	
		WebElement Nooalenerror=browser.findElement(By.cssSelector(prop.getProperty("cipFailurediv")));
		CIPErr=Nooalenerror.getText();		
	}
	else
	{
		CIPErr="CIP Failure has not occured.";
	}
	this.clkFinish();
	return CIPErr;
}



/**
 * retrieve cip failed loan number from pening app page----obsolete due to customer screen changes
 * @throws InterruptedException 
 */
public String getCIPFailureLoanNumber() throws InterruptedException {	
	String loannumber;
	Thread.sleep(2000); 
	//WebElement tableElement= browser.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_gvLoanAgreements"));
	loannumber=this.getValueFromTable(By.cssSelector(prop.getProperty("pendingappTable")),1,0);	
	/*List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
	// get the first row
	WebElement FirstRowElement = rows.get(2);
	// get all cells of first row
	List<WebElement> firstRowCells = FirstRowElement.findElements(By.tagName("td"));
	//get the value of required cell	
	WebElement requiredCellElement = firstRowCells.get(1);*/
	//loannumber = requiredCellElement.getText();
	return loannumber;	
}

public String getCIPFailureLoanNumber2() throws InterruptedException {	
	String loannumber;
	Thread.sleep(2000);
	WebElement LoannoElement=browser.findElement(By.cssSelector(prop.getProperty("LoanID")));
	loannumber = LoannoElement.getText();	
	return loannumber;	
}
public void clkFinish() {
	//WebElement finishButton=browser.findElement(By.cssSelector(prop.getProperty("finish")));
	//finishButton.click();	
	clkButtonwithJS(By.cssSelector(prop.getProperty("finish")));
	
   
}


public void setMessage(String string) {
	message=string;
	
}
public String getMessage() {
	return message;
	
}



}
