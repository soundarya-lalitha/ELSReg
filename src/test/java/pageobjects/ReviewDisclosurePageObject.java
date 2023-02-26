package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ReviewDisclosurePageObject extends MasterPageObject{
	public ReviewDisclosurePageObject() throws Exception {
		super();
		}
  /**
   * Click on I Meet check box 
   */
public void chkMeet() {
	this.waitFor(By.cssSelector(prop.getProperty("chkimeet")), 60);
	WebElement imeetCheckBox=browser.findElement(By.cssSelector(prop.getProperty("chkimeet")));
	imeetCheckBox.click();	
}
/**
  * Click on continue button on Review Eligibility screen
 */
public void clkContinue() {
	WebElement contiRevEliButton=browser.findElement(By.cssSelector(prop.getProperty("continueButton")));
	//Scroll the browser to the element's Y position
	((JavascriptExecutor) browser).executeScript("window.scrollTo(0,"+contiRevEliButton.getLocation().y+")");	
	contiRevEliButton.click();
}
/**
 * check i agree check box	
 * @throws InterruptedException 
 */
public void chkAgree() throws InterruptedException {
	Thread.sleep(1000);
	this.waitFor(By.cssSelector(prop.getProperty("chkdisc")), 60);
	WebElement iagreeCheckBox=browser.findElement(By.cssSelector(prop.getProperty("chkdisc")));
	iagreeCheckBox.click();
}
/**
 * check whether review disclosure page exists
 * @throws InterruptedException 
 */
public boolean chkReviewPageExists() throws InterruptedException {
	Thread.sleep(16000);
	boolean exists = browser.findElements(By.cssSelector(prop.getProperty("chkimeet"))).size() != 0;
	return exists;
}


}
