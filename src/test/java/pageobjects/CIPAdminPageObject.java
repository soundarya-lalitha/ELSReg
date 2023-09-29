package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;


/**
 * @author Soundarya Lalitha
 *
 */
public class CIPAdminPageObject extends MasterPageObject{
	public CIPAdminPageObject() throws Exception {
		super();
	}
	
	
/** Click on corresponding loan number on cip clear page
	 * @throws InterruptedException 
	 */
//#*****************************************************	
	public void clkCIPClearLoanNumber(String loanNumber) throws InterruptedException
	{
		Thread.sleep(3000);
		//WebElement parentContainerElement = browser.findElement(By.cssSelector(prop.getProperty("opencipscontainer")));
		//WebElement CIPtable = browser.findElement(By.cssSelector("#tableDiv > tbody"));
		
		WebElement CIPtable = browser.findElement(By.cssSelector("#tableDiv"));
		
		
		WebElement CIPtable2 = CIPtable.findElement(By.tagName("tbody"));
		
		
		List<WebElement> trelements=CIPtable2.findElements(By.tagName("tr"));
			for(WebElement var :trelements){	
				boolean existflag = var.findElements(By.tagName("td")).size()!=0;
				if (existflag==true)
				{
					WebElement td=var.findElement(By.tagName("td"));	
					boolean existflag1 = td.findElements(By.tagName("a")).size()!=0;
					if (existflag1==true)
					{
						WebElement reqnumberele=td.findElement(By.tagName("a"));
						String loannum=reqnumberele.getText();
							if (loannum.equals(loanNumber))
							{							
								reqnumberele.click();
								break;
								
							}
					}
				}
			}
	}


 /** Enter last name or first name of the employee
 * @throws InterruptedException */
public void CIPProcess(String employer,String loanNumber,String cipaction,String ssn2) throws InterruptedException {
	this.selectListValue(employer, By.cssSelector(prop.getProperty("selectEmployerCIPClearPage")), By.cssSelector(prop.getProperty("employerMenuCIPClearPage")));
	this.clkButton(By.cssSelector(prop.getProperty("allOpenCIPButton")));
	
	this.clkCIPClearLoanNumber(loanNumber);	
	Thread.sleep(3000);
	if (cipaction.equalsIgnoreCase("rerun")){ 
		this.clkButtonwithJS(By.cssSelector(prop.getProperty("rerunRadButton")));
	}
	else if(cipaction.equalsIgnoreCase("hold"))
	{
		this.clkButtonwithJS(By.cssSelector(prop.getProperty("holdRadButton")));
	}
	else if(cipaction.equalsIgnoreCase("decline"))
	{
		this.clkButtonwithJS(By.cssSelector(prop.getProperty("declineRadButton")));
	}
	else if(cipaction.equalsIgnoreCase("bypass"))
	{
		this.clkButtonwithJS(By.cssSelector(prop.getProperty("byPassRadButton")));
	}
	Reporter.log("CIP Admin enters SSN as "+ssn2);
	this.enterText(By.cssSelector(prop.getProperty("ssntxtCIPClear")), ssn2);	
	this.clkButton(By.cssSelector(prop.getProperty("submitCIPStatusButton")));
}
/** To get cip status from pending applications page

 * 
 * @throws InterruptedException **/
public String getCIPStatus() throws InterruptedException {	
	WebElement cipstatElement=browser.findElement(By.cssSelector(prop.getProperty("LoanStatus")));
	String cipStatus = cipstatElement.getText();	
	return cipStatus;			
}
/** Fetch CIP Status clear,failed or hold
  **/
public String getCIPResult() {	
	String cipResult=this.getTextVal(By.cssSelector(prop.getProperty("cipResult")));
	return cipResult;
}



}
