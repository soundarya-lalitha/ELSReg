package pageobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoanPayOffPageObject extends MasterPageObject {
	/**
	 * @author Soundarya Lalitha
	 *
	 */		
	public LoanPayOffPageObject() throws Exception{
			super();
	}
	
	/**
	  * click pay off button in Loan Pay off page
	 * @throws InterruptedException 
	 */
	public String getPayOffResult() throws InterruptedException {
		this.waitFor(By.cssSelector(prop.getProperty("outputPayOffMessage")), 60);
		String outputval=this.getTextVal(By.cssSelector(prop.getProperty("outputPayOffMessage")));
		return outputval;
	}
	/**
	  * click pay off button in Loan Pay off page
	 * @throws InterruptedException 
	 */
	public void clickPayOff() throws InterruptedException {
		this.waitFor(By.cssSelector(prop.getProperty("payoffButton")), 60);
		WebElement payOffButton=browser.findElement(By.cssSelector(prop.getProperty("payoffButton")));
		payOffButton.click();		
	}
	
	/**
	  * enter pay off amount
	 * @throws InterruptedException 
	 */
	public void enterPaidOffAmt(String amt) throws InterruptedException {
		this.waitFor(By.cssSelector(prop.getProperty("payOffAmounttxt")), 60);
		WebElement payOffAmtTextBox=browser.findElement(By.cssSelector(prop.getProperty("payOffAmounttxt")));
		payOffAmtTextBox.sendKeys(amt);			
	}
	/**
	  * enter pay off date ---obsolete functionality
	 * @throws InterruptedException 
	 */
	/*public void enterPaidOffDate(String date) throws InterruptedException {
		WebElement payOffDateTextBox=browser.findElement(By.cssSelector(prop.getProperty("payOffDatetxt")));
		payOffDateTextBox.clear();
		payOffDateTextBox.sendKeys(date);			
	}*/
	/**
	  * click pay off date
	 * @throws InterruptedException 
	 */
	public void clickPayOffDate() throws InterruptedException {
		this.waitFor(By.cssSelector(prop.getProperty("payOffDatetxt")), 60);
		WebElement payOffButton=browser.findElement(By.cssSelector(prop.getProperty("payOffDatetxt")));
		payOffButton.click();
		
	}
	
	
/**
 * @throws InterruptedException
 */
///*public void selectPayoffDate() throws InterruptedException {
//		clickPayOffDate();
//		Thread.sleep(1000);
//		Calendar calendar = Calendar.getInstance();
//		 calendar.setTime(new Date());
//		 calendar.add(Calendar.DATE,40);
//		 	 
//		 SimpleDateFormat formatter = new SimpleDateFormat("MMM");
//		 String mon;
//		 mon=formatter.format(calendar.getTime());
//		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
//		 String year;
//		 year=formatter1.format(calendar.getTime());
//		 SimpleDateFormat formatter2 = new SimpleDateFormat("dd");
//		String day;
//		day=formatter2.format(calendar.getTime());
//				
//		this.selectByValMethod(By.cssSelector(prop.getProperty("payoffmonSelect")), mon);
//		this.selectByValMethod(By.cssSelector(prop.getProperty("payoffyearSelect")), year);
//		WebElement daybutton=browser.findElement(By.linkText(day));
//		daybutton.click();
//	}*/

public void selectPayoffDate() throws InterruptedException {
	clickPayOffDate();
	Thread.sleep(1000);
	Calendar calendar = Calendar.getInstance();
	 calendar.setTime(new Date());
	 calendar.add(Calendar.DATE,40);
	 	 
	 SimpleDateFormat formatter = new SimpleDateFormat("MMM");
	 String mon;
	 mon=formatter.format(calendar.getTime());
	 
	 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
	 String year;
	 year=formatter1.format(calendar.getTime());
	 
	 SimpleDateFormat formatter2 = new SimpleDateFormat("dd");
	 String day2digit;
	 day2digit=formatter2.format(calendar.getTime());
	 
	 SimpleDateFormat formatter3 = new SimpleDateFormat("d");
	 String day1digit;
	 day1digit=formatter3.format(calendar.getTime());
		
			
	
	this.selectByValMethod(By.cssSelector(prop.getProperty("payoffyearSelect")), year);
	this.selectByValMethod(By.cssSelector(prop.getProperty("payoffmonSelect")), mon);
	int dayint = Integer.parseInt(day2digit);
	if (dayint>10)
	{
	WebElement daybutton=browser.findElement(By.linkText(day2digit));
	daybutton.click();
	}
	else
		
	{
		WebElement daybutton=browser.findElement(By.linkText(day1digit));
		daybutton.click();
		
	}
	
}
	/*
	  * check whether navigated to pay off details page*/
	 
	public boolean paidOffDetailsPage() throws InterruptedException {
		boolean existflag=false;
		Thread.sleep(1000);
		WebElement loansearchElement=browser.findElement(By.cssSelector(prop.getProperty("loansearchElement")));
		
		String searchfieldHidden=loansearchElement.getAttribute("style");
		if (searchfieldHidden.equals("display: none;"))
		{
			existflag=true;
		}
		//boolean existflag=this.chkElementExists(By.cssSelector(prop.getProperty("payOffAmounttxt")));
		return existflag;
	}
	/**
	  * click search button in Loan Pay off page*/
	 
	public void clickSearch() {
		WebElement loanSearchButton=browser.findElement(By.cssSelector(prop.getProperty("searchButton")));
		loanSearchButton.click();		
	}
	/**
	  * Enter Loan Number in Loan Pay off page
	 */
	public void enterLoanNumber(String l) {
		
		WebElement loanTextBox=browser.findElement(By.cssSelector(prop.getProperty("loanNumbertxt")));
		loanTextBox.clear();		
		loanTextBox.sendKeys(l);
	}
	
	/**
	  * get a date after interval---format can be like this MM/dd/yyyy
	 */
	public String getfutureDate(int d,int m,int y,String format) {
		Calendar cal = Calendar.getInstance();
		if (d!=0)
		{
			cal.add(Calendar.DATE, d);
		}
		if (m!=0)
		{
			cal.add(Calendar.MONTH, m);
		}
		if (y!=0)
		{
			cal.add(Calendar.YEAR, y);
		}
				
		Date newdate = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat(format);
		String datenext = dateFormat.format(newdate);
		return datenext;
	}
}
