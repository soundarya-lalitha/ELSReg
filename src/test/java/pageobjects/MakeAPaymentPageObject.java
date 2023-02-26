package pageobjects;




import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class MakeAPaymentPageObject extends MasterPageObject{
	
	public MakeAPaymentPageObject() throws Exception {
		super();
	}
	
	/**
	 * click pay button with specified loan number
	 */
	public void clkPayButton(String loanno) {
		String CSSSelec;		
		//"button[onclick*='5000049881']"
		CSSSelec="button[onclick*='"+loanno+"']";
		this.waitFor(By.cssSelector(CSSSelec), 60);
		WebElement payButton=browser.findElement(By.cssSelector(CSSSelec));		
		//System.out.println(CSSSelec);		
		payButton.click();	
		Reporter.log("Pay button corresponding to "+loanno +" is clicked");
	}	
	/**
	 * Click pay with Bank Account radio button
	*/
	public void clkRadio(String option) {	
		this.clkRadioWithValue(option);		
	}
/**
 * click continue in make a payment page
* @throws InterruptedException 
*/
public void clkConti() throws InterruptedException {
	this.waitFor(By.cssSelector(prop.getProperty("continueMakeButton")), 60);
	WebElement contButton=browser.findElement(By.cssSelector(prop.getProperty("continueMakeButton")));
	contButton.click();		
}
/**
 * click pay off loan option to pay loan as customer
*/
public void selectPayoffLoan() {
	this.clkRadioWithValue(prop.getProperty("payOffYourLoan"));
	Reporter.log("Payment method payOffYourLoan selected.");
	
}
/**
 * choose first date from the list
*/
public void setFirstDate() {
	Select selDate = new Select(browser.findElement(By.cssSelector(prop.getProperty("ChoosePayDate"))));
	selDate.selectByIndex(1);	
}
/**
 * enter bank name
*/
public void enterBankName(String bankName) {
	this.enterText(By.cssSelector(prop.getProperty("bankNametxt")),bankName);
	
}
/**
 * enter Account no twice
*/
public void enterAccNo(String ano) {
	this.enterText(By.cssSelector(prop.getProperty("AcNotxt")),ano);
	this.enterText(By.cssSelector(prop.getProperty("AcNtx")),ano);	
}
/**
 * enter routing no
*/
public void enteroutingNo(String rouno) {
	this.enterText(By.cssSelector(prop.getProperty("RoutingNotxt")),rouno);	
}

/**
 * enter other payment amount
*/
public void enterOtherPayTxt(String amt) {
	this.enterText(By.cssSelector(prop.getProperty("otherPayAmountxt")),amt);	
}
/**
 * Click Make a Payment(last screen-button)
*/
public void clkMakePayment() {
	this.waitFor(By.cssSelector(prop.getProperty("makePaymentButton")), 60);
	WebElement contButton=browser.findElement(By.cssSelector(prop.getProperty("makePaymentButton")));
	contButton.click();	
	Reporter.log("Make a payment button clicked in Payment Summary Screen");
	
}
/**
 * make payment with pay off loan option
*/
public void payoffLoan(String bName, String acno, String routNo, String acType) throws InterruptedException {
	this.selectPayoffLoan();
	Thread.sleep(2000);
	this.clkConti();
	Thread.sleep(2000);
	
	if (this.chkElementExists(By.cssSelector(prop.getProperty("paycheckdateErr"))))
			{
		
				this.clkButton(By.cssSelector(prop.getProperty("okMakePayment")));
				Reporter.log("Current date is pay check day, hence can not pay off");
				this.navigateMenu("MAKE A PAYMENT"); 
			}
	else
	{
			this.setFirstDate();
			Thread.sleep(2000);
			this.enterBankName(bName);
			Thread.sleep(2000);
			this.enterAccNo(acno);
			Thread.sleep(2000);
			this.enteroutingNo(routNo);
			Thread.sleep(2000);
			this.clkRadio(acType);
			Thread.sleep(2000);
			Reporter.log("Payment details are entered in payment details screen");
			this.clkContinueORNextButton();
			Thread.sleep(2000);
			this.clkMakePayment();	
	}
}
/**
 * make payment with makeLoanPay
*/
public void makeLoanPayment(String bName, String acno, String routNo, String acType) throws InterruptedException {
	this.clkRadioWithValue(prop.getProperty("makeLoanPayment"));
	this.clkConti();
	Thread.sleep(2000);
	this.setFirstDate();
	this.enterBankName(bName);
	this.enterAccNo(acno);
	this.enteroutingNo(routNo);
	this.clkRadio(acType);
	Reporter.log("Payment details are entered in payment details screen");
	this.clkContinueORNextButton();
	Thread.sleep(2000);
	this.clkMakePayment();
}

/**
 * make payment with makeLoanPay
*/
public void makeOtherPayment(String bName, String acno, String routNo, String acType,String amt) throws InterruptedException {
	this.clkRadioWithValue(prop.getProperty("makeOthePayment"));
	this.enterOtherPayTxt(amt);
	this.clkConti();
	Thread.sleep(2000);
	this.setFirstDate();
	this.enterBankName(bName);
	this.enterAccNo(acno);
	this.enteroutingNo(routNo);
	this.clkRadio(acType);
	Reporter.log("Payment details are entered in payment details screen");
	this.clkContinueORNextButton();
	Thread.sleep(2000);
	this.clkMakePayment();
}

public String validatePayment(String loanno) {
	
	WebElement LoanElement = browser.findElement(By.cssSelector("button[onclick*='"+loanno+"']"));
	String status=LoanElement.getText();
	System.out.println(status);
	return status;	
	
}
/**
 * make payment screen cancel button corresponding to loan
*/
public void clkMPCanclButton(String loanno) {
	String CSSSelec1,path1;		
	//"button[onclick*='5000049881']"
	//CSSSelec="button[onclick*='"+loanno+"']";
	//CSSSelec1="button.btn.btn-outline-secondary#cancelBtn[onclick*='5000050799']";
	//\javascript: return CancelConfirm('5000050799');\
	
	//jspath1="//button[@onclick=\"javascript: return CancelConfirm('5000050799');\"]";
	//browser.findElement(By.xpath("//[@id='cancelBtn'][@onclick=\"javascript: return CancelConfirm('5000050799');\"]")).click();
	//input[@class='t2'] and matches(@onclick,'1622662'))]
		browser.findElement(By.xpath("//button[@id='cancelBtn'] and matches(@onclick,'5000050799'")).click();	
		
		///////driver.findElement(By.xpath("(//td/input[@id='leadCaptureList_leadCaptureList_assignCampaign'])[1]")).click();
	//browser.findElement(By.xpath("//[@id='cancelBtn']")).click();
	//browser.execute_script("javascript:check_security('wlan1security_div0')")
	//browser.findElement(By.xpath(("//button[@onclick=\"javascript: return CancelConfirm('5000050799');\"]"))).click();
	
	//driver.find_element_by_css_selector("button.btn.btn-primary#YesBtn[onclick*='wlan1security_div0']").click()
	//CSSSelec1="button.btn.btn-outline-secondary#cancelBtn[onclick*='"+loanno+"']";
	//this.waitFor(By.cssSelector(CSSSelec1), 60);
	//WebElement cancelButton=browser.findElement(By.cssSelector(CSSSelec1));		
	//System.out.println(CSSSelec);		
	
	//cancelButton.click();	
		
		
	////WebElement elementclickButton=browser.findElement(By.xpath(jspath1));
	//JavascriptExecutor executor = (JavascriptExecutor)browser;
	//executor.executeScript("arguments[0].click();", elementclickButton);
	//Reporter.log("Cancel button corresponding to "+loanno +" is clicked");
}	
	
}



