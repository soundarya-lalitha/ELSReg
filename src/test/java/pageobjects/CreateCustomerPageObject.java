package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * @author Soundarya Lalitha
 *
 */

public class CreateCustomerPageObject extends  MasterPageObject {
	public CreateCustomerPageObject() throws Exception {
		super();
		}
	/**
	 * enter LastName to verify employee
	 *//*
	public void enterLastName(String lname) {
		WebElement lnameTextBox=browser.findElement(By.cssSelector(prop.getProperty("lastNametxtBox")));
		lnameTextBox.sendKeys(lname);
		
		}
	*//**
	 * enter Zip to verify employee
	 *//*
	public void enterZip(String zip) {
		WebElement zipcodeTextBox=browser.findElement(By.cssSelector(prop.getProperty("zipCodetxtBox")));
		zipcodeTextBox.sendKeys(zip);
	}
	*//**
	 * enter DOB to verify employee
	 *//*
	public void enterDob(String dob)  {
		WebElement dobTextBox=browser.findElement(By.cssSelector(prop.getProperty("dobtxtBox")));
		dobTextBox.sendKeys(dob);
	}*/

	/**
	 * enter lname,zip and dob data to verify the employee
	 * @throws InterruptedException 
	 */	
	public void verifyEmployee(String lastname,String zip,String dob) throws InterruptedException {
		this.waitFor(By.cssSelector(prop.getProperty("lastNametxtBox")), 60);
		WebElement lnameTextBox=browser.findElement(By.cssSelector(prop.getProperty("lastNametxtBox")));
		lnameTextBox.sendKeys(lastname);
		WebElement zCodeTextBox=browser.findElement(By.cssSelector(prop.getProperty("zipCodetxtBox")));
		zCodeTextBox.sendKeys(zip);
		Thread.sleep(3000);
		WebElement doBTextBox=browser.findElement(By.cssSelector(prop.getProperty("dobtxtBox")));
		doBTextBox.sendKeys(dob);	
		//capthca code to delete
		
		//WebElement captchaButton=browser.findElement(By.cssSelector(prop.getProperty("captcha")));
		//captchaButton.click();
		
	}
	
	//** * click last name text box just to make date field calendar disappear so that error message of leess thatn age is visible	 *//*
	public void clickLname()  {
		WebElement lnameTextBox=browser.findElement(By.cssSelector(prop.getProperty("lastNametxtBox")));
		lnameTextBox.click();
	}
	
	
	
	
	/**
	 * enter email,password,security questions and answers to create customer
	 */	
	public void createAccountData(String emailAd,String pasword,String question1,String question2) throws InterruptedException {
		
		this.waitFor(By.cssSelector(prop.getProperty("customerEmail1")), 60);
		//Enter email address of the customer(userid)
		WebElement emailAddTextBox=browser.findElement(By.cssSelector(prop.getProperty("customerEmail1")));
		emailAddTextBox.sendKeys(emailAd);
		//Repeat email address of the customer
		WebElement emailAdd2TextBox=browser.findElement(By.cssSelector(prop.getProperty("customerEmail2")));
		emailAdd2TextBox.sendKeys(emailAd);
		
		//Enter password and repeat password
		if (pasword.isEmpty()){
			WebElement pwdTextBox=browser.findElement(By.cssSelector(prop.getProperty("customerPwd1")));
			pwdTextBox.sendKeys("1234@Abcd");
			WebElement pwd1TextBox=browser.findElement(By.cssSelector(prop.getProperty("customerPwd2")));
			pwd1TextBox.sendKeys("1234@Abcd");
		}
		else
		{
			WebElement pwdTextBox=browser.findElement(By.cssSelector(prop.getProperty("customerPwd1")));
			pwdTextBox.sendKeys(pasword);
			WebElement pwd1TextBox=browser.findElement(By.cssSelector(prop.getProperty("customerPwd2")));
			pwd1TextBox.sendKeys(pasword);
		}
		//Enter Security Questions
		this.selectQuestion1(question1);
		this.selectQuestion2(question2);
		
		//Enter Security answers		
		WebElement ans1TextBox=browser.findElement(By.cssSelector(prop.getProperty("customerSecAns1")));
		ans1TextBox.sendKeys("test");
		WebElement ans2TextBox=browser.findElement(By.cssSelector(prop.getProperty("customerSecAns2")));
		ans2TextBox.sendKeys("test");	
		//Enter Security phrase
		WebElement sPhaseTextBox=browser.findElement(By.cssSelector(prop.getProperty("customerSecPhrase")));
		sPhaseTextBox.sendKeys("test");
		
	}
	
	/*public String getQuestion1ElePropery() {
		String q1Property=prop.getProperty("selectQuestion1");
		return q1Property;
		
	}
	public String getQuestion1MenuPropery() {
		String q1MenuProperty=prop.getProperty("questionMenu1");
		return q1MenuProperty;		
	}

	public String getQuestion2ElePropery() {
		String q2Property=prop.getProperty("selectQuestion2");
		return q2Property;
	}
	
	public String getQuestion2MenuPropery() {
		String q2MenuProperty=prop.getProperty("questionMenu2");
		return q2MenuProperty;		
	}*/
	/**
	 * * verify whether navigated to Product Selection page after navigation
	 */
	public boolean veriProdSelPage() {
		boolean existflag=false;
		
		String headname =this.getTextVal(By.cssSelector(prop.getProperty("customersubmenuHeading")));
		if (headname.equalsIgnoreCase("Product Selection"))
		{
			Reporter.log("Product Selection page appears.");
			existflag=true;
		}else
		{
			Reporter.log("Product Selection page has not appeared.");
			existflag=false;
		}
		return existflag;	
	}
	

/**
 * verify whether navigated to app info page or not
 */
public boolean  verifyAppinfoNavigation() {
	this.waitFor(By.cssSelector(prop.getProperty("ssn")), 60);
	boolean existflag =this.chkElementExists(By.cssSelector(prop.getProperty("ssn")));
	return existflag;	
}
	
	/**
	 verify whether navigated to existing customer page
	 */	
	public boolean ChkCustomerExists() {
		boolean existflag =this.chkElementExists(By.cssSelector(prop.getProperty("existingCustomerLabel")));
		return existflag;	
	}
	/**
	 select question1 to create customer
	 */	
	public void selectQuestion1(String ques1) throws InterruptedException {
		//this.selectListValue(ques1, By.cssSelector(prop.getProperty("selectQuestion1")), By.cssSelector(prop.getProperty("questionMenu1")));
		
		this.selectByValMethod(By.cssSelector(prop.getProperty("customerSecQues1")),ques1);
	}
	/**
	 select question2 to create customer
	 */	
	public void selectQuestion2(String ques2) throws InterruptedException {
		
		//this.selectListValue(ques2, By.cssSelector(prop.getProperty("selectQuestion2")), By.cssSelector(prop.getProperty("questionMenu2")));
		this.selectByValMethod(By.cssSelector(prop.getProperty("customerSecQues2")),ques2);
	}
	/**
	 * select state for new customer
	 */
	public void selectState(String state) throws InterruptedException {
		this.selectListValue(state, By.cssSelector(prop.getProperty("selectState")), By.cssSelector(prop.getProperty("stateMenu")));
	}
	/**
	 * enter address for new customer
	 */
	public void enterAdd(String add) {
		WebElement addTextBox=browser.findElement(By.cssSelector(prop.getProperty("empAddress")));
		addTextBox.sendKeys(add);
	}
	/**
	 * enter city for new customer
	 */
	public void entercity(String city) {
		WebElement cityTextBox=browser.findElement(By.cssSelector(prop.getProperty("empCity")));
		cityTextBox.sendKeys(city);
	}
	/**
	 * get customer rejection pop up
	 */
	public String getCustError() throws InterruptedException {
		{
			boolean existflag =this.chkElementExists(By.cssSelector(prop.getProperty("EmpVerifyFailMessage")));
			if (existflag==true)
			{
			String custError=this.getTextVal(By.cssSelector(prop.getProperty("EmpVerifyFailMessage")));
			return custError;
			}
			else
			{
				return "error message does not exist";	
			}
		}
		}
		/**
		 * get customer less than 18 years age error
		 */
		public String getCustLessAgeError() throws InterruptedException {
			{
				boolean existflag =this.chkElementExists(By.cssSelector(prop.getProperty("customerlessageError")));
				if (existflag==true)
				{
				String custError=this.getTextVal(By.cssSelector(prop.getProperty("customerlessageError")));
				return custError;
				}
				else
				{
					return "error message does not exist";	
				}
			} 
	}
	}
	




