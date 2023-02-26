package pageobjects;
import java.awt.AWTException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class LargeDollarPageObject extends MasterPageObject {
	/**
	 * @author Soundarya Lalitha
	 *
	 */		
	public LargeDollarPageObject() throws Exception{
			super();
	}
	
	/*** click Continue button in large dollar flow 
	 * @throws InterruptedException ****/
	public void clickLDollContinue() throws InterruptedException {
		
		String m=this.getTextVal((By.cssSelector(prop.getProperty("contiButLargeDoll"))));
		System.out.println(m);
		//Thread.sleep(3000);
		this.waitFor(By.cssSelector(prop.getProperty("contiButLargeDoll")), 30);
		this.clkButtonwithJS(((By.cssSelector(prop.getProperty("contiButLargeDoll")))));
		
	}

	/*** pick any Light stream loan product****/
	public void pickLSLoanOption(String loanoption) {
		// TODO Auto-generated method stub
		if (loanoption.equals("Auto"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("autoloan")));
		}
		else if(loanoption.equals("Debt"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("debtloan")));
		}
		else if(loanoption.equals("Home"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("homeloan")));
		}
		else
		{
			this.clkButton(By.cssSelector(prop.getProperty("otherloan")));
		}	
		
	}

	/*** get current url****/
	public String geturl() {
		// TODO Auto-generated method stub
		String ur=browser.getCurrentUrl();
		return ur;
	}
	
	/*** LSFM self assessment flow to navigate to light stream url  non red flow****/
	public void ExtLenderSelfAssesCredit(String selfass) throws InterruptedException {
						
		
			String mess=this.chkAndGetMessage(By.cssSelector(prop.getProperty("addCretxt")));	
			
			
			if (selfass.equalsIgnoreCase("excellent"))
			{
				this.clkButton(By.cssSelector(prop.getProperty("excelRadButton")));		
				Reporter.log("Excellent Radio button is selected.");
			}
			else if (selfass.equalsIgnoreCase("goodtoExcellent"))
			{
				this.clkButton(By.cssSelector(prop.getProperty("goodExcelRadButton")));	
				Reporter.log("Good to Excellent Radio button is selected.");
			}
			else if (selfass.equalsIgnoreCase("good"))
			{
				this.clkButton(By.cssSelector(prop.getProperty("goodRadButton")));
				Reporter.log("Good Radio button is selected.");
			}
			else
			{
				this.clkButton(By.cssSelector(prop.getProperty("fairRadButton")));	
				Reporter.log("Fair Radio button is selected.");
			}
			Thread.sleep(2000);
			this.clkButton(By.cssSelector(prop.getProperty("continueButton")));	
			Thread.sleep(2000);
				
		}
	
	/*** LS self assessment flow to navigate to light stream url non red flow * @throws InterruptedException ****/
	public void SelfAssesCreditLSnonRed(String selfAsses) throws InterruptedException {
		boolean f1=false,f2=false;
		f1=this.chkElementExists(By.cssSelector(prop.getProperty("addCrRadButton")));	
		
		String mess=this.chkAndGetMessage(By.cssSelector(prop.getProperty("addCretxt")));	
		System.out.println(mess);
		
		if ((mess.equalsIgnoreCase("Credit Report and Additional Credit Qualifications Required - ( Loans between $5100 to $100,000 )")) && (f1==true))
		{
			Reporter.log("External Lender for this user is LS, Workstate not red hence Additonal Credit radio button exists.");
			
		}
		f2=this.chkElementExists(By.cssSelector(prop.getProperty("noCreditRadButton")));
		if (f2==true)
		{
			Reporter.log("User workstate is not red hence, No Credit radio button also exists.");
		}
		this.clkButton(By.cssSelector(prop.getProperty("addCrRadButton")));	
		Thread.sleep(2000);
		this.clkButton(By.cssSelector(prop.getProperty("continueButton")));	
		Thread.sleep(2000);
			
		
	}	
	
	
	/*** LSFM self assessment flow to navigate to light stream url  red state flow****/
	public void SelfAssesCreditLSFMRed(String selfass) throws InterruptedException {
		boolean f1=false,f2=false;
		
		f1=this.chkElementExists(By.cssSelector(prop.getProperty("addCrRadButton")));	
	
		String mess=this.chkAndGetMessage(By.cssSelector(prop.getProperty("addCretxt")));	
		System.out.println(mess);
		
		if ((mess.equalsIgnoreCase("Credit Report and Additional Credit Qualifications Required - Loan amounts based on credit - (Credit check is required)")) && (f1==true))
		{
			Reporter.log("External Lender for this user is LSFM, Workstate is red hence only Additonal Credit radio button exists.");
			
		}
		f2=this.chkElementExists(By.cssSelector(prop.getProperty("noCreditRadButton")));
		if (f2==false)
		{
			Reporter.log("User workstate is red hence, No Credit radio button does not exists.");
		}
		
		this.clkButton(By.cssSelector(prop.getProperty("addCrRadButton")));	
		Reporter.log("Additional Credit Radio button is selected.");
		Thread.sleep(2000);
		if (selfass.equalsIgnoreCase("excellent"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("excelRadButton")));		
			Reporter.log("Excellent Radio button is selected.");
		}
		else if (selfass.equalsIgnoreCase("goodtoExcellent"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("goodExcelRadButton")));	
			Reporter.log("Good to Excellent Radio button is selected.");
		}
		else if (selfass.equalsIgnoreCase("good"))
		{
			this.clkButton(By.cssSelector(prop.getProperty("goodRadButton")));
			Reporter.log("Good Radio button is selected.");
		}
		else
		{
			this.clkButton(By.cssSelector(prop.getProperty("fairRadButton")));	
			Reporter.log("Fair Radio button is selected.");
		}		
		
		Thread.sleep(2000);
		this.clkButton(By.cssSelector(prop.getProperty("continueButton")));	
		Thread.sleep(2000);
			
	}

	public void clickLFinMktContinue() {
		
		this.clkButtonwithJS(((By.cssSelector(prop.getProperty("contiButFinMkt")))));
		
	}

	
	
			
	
		
	}
	
	
	


	
	
	
	
	

