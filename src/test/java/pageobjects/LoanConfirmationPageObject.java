package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * @author Soundarya Lalitha
 *
 */

public class LoanConfirmationPageObject extends MasterPageObject {
	public LoanConfirmationPageObject() throws Exception {
		super();
	}
	
	/**
	 * select 1000 loan
	 */	
		
		public void chkLoanoptionChkBox()  {
			
				WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("thousandLoanChkBox")));
				loanChkBox.click();       
		   
		}
		
		public void chkEthnicRaceChkBox()  {
			
			//boolean existflag=browser.findElement(By.cssSelector(prop.getProperty("hispanicChkBox"))).isDisplayed();
			boolean existflag=this.chkElementExists(By.cssSelector(prop.getProperty("hispanicChkBox")));
			System.out.println("existfalg is "+existflag);
			if (existflag==true)
			{ 
				WebElement hispanChkBox=browser.findElement(By.cssSelector(prop.getProperty("hispanicChkBox")));
				hispanChkBox.click();
				Reporter.log("ECIP page displayed and selected hispanic check box for Etchnicity");
				
				WebElement amerIndChkBox=browser.findElement(By.cssSelector(prop.getProperty("ameriIndChkBox")));
				amerIndChkBox.click();
				Reporter.log("selected American Indian check box for Race");
				
				WebElement idonotwishChkBox=browser.findElement(By.cssSelector(prop.getProperty("idonotwishChkBox")));
				idonotwishChkBox.click();
				Reporter.log("selected I do not wish check box for Language Preference");
				
				clkButtonwithJS(By.cssSelector(prop.getProperty("finish")));
				
				
				
			}
			else
			{
				Reporter.log("ECIP page not displayed.");
			}
	}
		
				
		
		public void enterRoutingNumber(String routno) {
			this.waitFor(By.cssSelector(prop.getProperty("routingnumber")), 60);			
			WebElement routingTextBox=browser.findElement(By.cssSelector(prop.getProperty("routingnumber")));
			routingTextBox.sendKeys(routno);
			
		}
		
		public void enterAccntNumber(String acctno) {
			WebElement acctnoTextBox1=browser.findElement(By.cssSelector(prop.getProperty("accnumber1")));
			acctnoTextBox1.sendKeys(acctno);
			WebElement acctnoTextBox2=browser.findElement(By.cssSelector(prop.getProperty("accnumber2")));
			acctnoTextBox2.sendKeys(acctno);
		}
		
		public void SelAccnType(String actype) {
			if (actype.equalsIgnoreCase("Checking")){ 
				/*WebElement typeOfAc=browser.findElement(By.cssSelector(prop.getProperty("checkingType")));
				typeOfAc.click();*/
				clkButtonwithJS(By.cssSelector(prop.getProperty("checkingType")));
				
			}
			else if(actype.equals("Saving"))
			{
				/*WebElement typeOfAc=browser.findElement(By.cssSelector(prop.getProperty("savingType")));
				typeOfAc.click();*/
				clkButtonwithJS(By.cssSelector(prop.getProperty("savingType")));
			}
		}
		
	public void signLoan(String username) {
	
		this.waitFor(By.cssSelector(prop.getProperty("signloan")), 80);
		WebElement signLoanTextBox=browser.findElement(By.cssSelector(prop.getProperty("signloan")));
		signLoanTextBox.sendKeys(username);		
	}	
	
	public void clkiAgreeButton() {
		WebElement iAgreeButton=browser.findElement(By.cssSelector(prop.getProperty("iAgreeButton")));
		iAgreeButton.click();		
	}
	/** check auth heading**/
	public void ChkAuthHeading()  {
		String textval;	
		WebElement parentofAuthHeading= browser.findElement(By.cssSelector(prop.getProperty("printpagediv")));
		WebElement strongelement=parentofAuthHeading.findElement(By.tagName("strong"));
		WebElement uelement=strongelement.findElement(By.tagName("u"));
		textval=uelement.getText();
		Reporter.log(textval+" Page has appeared for this customer.");
		
		System.out.println(textval);		
	}
	
	public String getLoanNumber() {		
		this.waitFor(By.cssSelector(prop.getProperty("loantxtparent")), 60);
		WebElement parentContainerElement = browser.findElement(By.cssSelector(prop.getProperty("loantxtparent")));
		WebElement tableElement = parentContainerElement.findElement(By.tagName("table"));
		// get all rows of the table
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		// get the 3rd row
		WebElement thirdRowElement = rows.get(2);
		// get all cells of first row		
		WebElement thirdrowCell= thirdRowElement.findElement(By.tagName("td"));
		WebElement loanconfElem = thirdrowCell.findElement(By.tagName("p"));
		String loannumber  = loanconfElem.getText().split(" ")[4];
		return loannumber;		
	}
	
	public String getLoanNumber2() {		
		this.waitFor(By.cssSelector(prop.getProperty("loanid")), 60);		
		WebElement loanconfElem = browser.findElement(By.cssSelector(prop.getProperty("loanid")));
		String loannumber  = loanconfElem.getText();
		return loannumber;		
	}

	public void chkLoanoptionChkBoxAny(String loanoption) throws InterruptedException {
		
		
		if (loanoption.equals("1000")){ 
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("thousandLoanChkBox")));
			loanChkBox.click();
		}
		/*else if (loanoption.equals("400")){ 
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("thousandLoanChkBox")));
			loanChkBox.click();
		}*/
		else if(loanoption.equals("1500"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("fifteenhunLoanChkBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("2000"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("2thousandLoanChkBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("2500"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("2500LoanChkBox")));
			loanChkBox.click();
		}
		
		else if(loanoption.equals("3000"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("3thousandLoanChkBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("3500"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("3thoufivehun_LoanChkBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("4000"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("4thouLoanCheckBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("4500"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("4thoFivehun_LoanChkBox")));
			loanChkBox.click();
		}
		else if(loanoption.equals("5000"))
		{
			WebElement loanChkBox=browser.findElement(By.cssSelector(prop.getProperty("5thoLonCheckBox")));
			loanChkBox.click();
		}
		
	}

	public void ansCommunityQuestion() {
		boolean existflag=browser.findElement(By.cssSelector(prop.getProperty("marriageStatusElement"))).isDisplayed();
		if (existflag==true)
		{ 
			WebElement marriedRadioButton=browser.findElement(By.cssSelector(prop.getProperty("marriedradioButton")));
			marriedRadioButton.click();
			Reporter.log("Community Question is displayed and answered the Community Question.");
		}
		else
		{
			Reporter.log("Community Question is not displayed.");
		}
	}
	
	public boolean VerifyLoanOptionsPage() throws InterruptedException {
		
		Thread.sleep(2000);
		boolean existflag=this.chkElementExists(By.cssSelector(prop.getProperty("continueButton")));
		
		if (existflag==true)
		{ 
			Reporter.log("Proceed to check Loan option CheckBox.");
		}
		else
		{
			Reporter.log("Loan options page is not displayed.");
		}
		return existflag;
	}

	public void clkMyAccount() {
		WebElement myaccnt=browser.findElement(By.linkText(prop.getProperty("myAccount")));
		myaccnt.click();
		
	}
	
	/*public void setBrowser(WebDriver runningBrowser) {
		browser = runningBrowser;
		//implicit wait
	  browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}*/
	
	}
	




