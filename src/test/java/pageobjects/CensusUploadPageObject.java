package pageobjects;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import testcases.util.TestUtil; 
/**
 * @author Soundarya Lalitha
 *
 */
public class CensusUploadPageObject extends MasterPageObject{
		public CensusUploadPageObject() throws Exception {
			super();
		}
/*** Method to click on Upload Census***/
public void clickUploadCenus() {
	WebElement uploadCensus=browser.findElement(By.cssSelector(prop.getProperty("uploadcensus")));
	uploadCensus.click();	
}
/*** Method to click on Map Census Fields button
 * @throws InterruptedException ***/
public void clickMapCensus() throws InterruptedException {
	Thread.sleep(1000);
	this.waitFor(By.cssSelector(prop.getProperty("mapcensus")), 60);
	boolean exists=chkElementExists(By.cssSelector(prop.getProperty("mapcensus")));
	if (exists==true)
	{	
		WebElement mapCensus=browser.findElement(By.cssSelector(prop.getProperty("mapcensus")));
		mapCensus.click();
		Reporter.log("Clicked on the Map Census Fields Button Present in the Census Page.");
	}	
}


/**Upload Census file and verify Map Columns page
 * @throws AWTException **/
public boolean VerifymapColumns(String filepath,String browserType,String csvFieldList) throws IOException, InterruptedException, AWTException {
	boolean messMissMap=false,listChk=false,flag;
	//boolean mapMan=false;
	
	this.clickMapCensus();
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)
	{
		if(!windowkey.equals(parentWindow)) 
		   {
				browser.switchTo().window(windowkey);
				//---click on choose file in child window	
				Thread.sleep(1000);	
				//check for existence of browse button and click on it on the pop-up
				//boolean exists=chkElementExists(By.cssSelector(prop.getProperty("uploadMapButton")));
					/*if (exists==true)
					{						
						this.clkButtonwithJS(By.cssSelector(prop.getProperty("uploadMapButton")));	
						Reporter.log("Clicked on the Choose File Button on the Map Columns page.");
					}*/
				//upload census file
				Reporter.log("Select file "+ filepath +" to upload.");
				this.fileUploadonly(filepath);				
				Thread.sleep(1000);		
				Reporter.log("Verify the options of the Select Fields like Last Name.");	
				listChk=verifyListValues(By.cssSelector(prop.getProperty("mapcolLastName")),csvFieldList);
				System.out.println(listChk);
					if (listChk==true){	
						Reporter.log("CSV Fields are present as options in the dropdown.");	}
					else{
						Reporter.log("CSV Fields are not present as options in the dropdown.");
					}
				
				//click on map cols button  
				Reporter.log("Do not map any field and Click on the Map Columns button.");
				clkButton(By.cssSelector(prop.getProperty("mapcolButton")));		
				Thread.sleep(1000);					
				boolean ex1=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolFn")));
				boolean ex2=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolLn")));
				boolean ex3=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolPid")));
				boolean ex4=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolZip")));
				boolean ex5=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolDOH")));
				boolean ex6=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolEmpStat")));
				boolean ex7=chkElementExists(By.cssSelector(prop.getProperty("mapthiscolSal")));
				
					if(ex1 && ex2&& ex3 && ex4 && ex5 && ex6 && ex7)
					{	
						messMissMap=true;
						Reporter.log("Error mesages indicating mandatory mapping are displayed.");
						}
					else{
						messMissMap=false;
						Reporter.log("Error mesages indicating mandatory mapping are not displayed.");
					}
				//close browser and switch to parent window				
				browser.close();		   	
				browser.switchTo().window(parentWindow);			
		   }
		  }	
		flag=listChk&&messMissMap;	
	return flag;
}	
/*** Method to Map the columns for Census***/
public boolean mapColumns(String filepath, String exe1path, String exe2path, String browserType,String csvFieldList) throws IOException, InterruptedException {
	boolean flag=false;
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)		
	{
		if(!windowkey.equals(parentWindow)) 
		   {
			browser.switchTo().window(windowkey);
			//---clcik on choose file in child window	
			Thread.sleep(1000);
			WebElement uploadChooseMapButton=browser.findElement(By.cssSelector(prop.getProperty("uploadMapButton")));
			uploadChooseMapButton.click();	
			
			Thread.sleep(10000);
			Reporter.log("Clicked on the Choose File Button on the Map Columns page.");
			WebElement ts=browser.findElement(By.cssSelector(prop.getProperty("uploadMapButton")));
			ts.sendKeys(filepath);			
			Thread.sleep(10000);	
			
			
					
			
			String ex=this.getTextVal(By.cssSelector(prop.getProperty("uploadFilename")));
			System.out.println("Filename if file exists "+ex);
			
			
			
			
			String splt[] =csvFieldList.split("#");				
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolLastName")),splt[0]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolFirstName")),splt[1]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolEmpId")),splt[2]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolZipCode")),splt[3]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOB")),splt[4]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOH")),splt[5]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapEmpStatus")),splt[6]);
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapAnuSalary")),splt[7]);			
			//click on map cols button  
			WebElement mapcolButton=browser.findElement(By.cssSelector(prop.getProperty("mapcolButton")));
			mapcolButton.click();	
			boolean exists=chkElementExists(By.cssSelector(prop.getProperty("mapValScreenexists")));
			if (exists==true)
			{
				//select active option from active drop down
				WebElement activeDropdown=browser.findElement(By.cssSelector(prop.getProperty("activeMap")));
				activeDropdown.click();				
				WebElement activeChkBox=browser.findElement(By.xpath(prop.getProperty("activeCheckbox")));
				activeChkBox.click();				
				WebElement mapmessageforsync=browser.findElement(By.cssSelector(prop.getProperty("mapValScreenexists")));
				mapmessageforsync.click();
				Thread.sleep(3000);
				
				//select terminated option from terminated drop down
				WebElement terminatedDropdown=browser.findElement(By.cssSelector(prop.getProperty("termiMap")));
				terminatedDropdown.click();				
				WebElement termiChkBox=browser.findElement(By.xpath(prop.getProperty("termiCheckbox")));
				termiChkBox.click();					
				mapmessageforsync.click();
				Thread.sleep(3000);
				
				//select Garnishment option from Garnishment drop down
				WebElement garniDropdown=browser.findElement(By.cssSelector(prop.getProperty("garnishMap")));
				garniDropdown.click();				
				WebElement garniChkBox=browser.findElement(By.xpath(prop.getProperty("ganiCheckbox")));
				garniChkBox.click();				
				mapmessageforsync.click();
				Thread.sleep(3000);
				
				WebElement mapValButton=browser.findElement(By.cssSelector(prop.getProperty("mapValuesButton")));
				mapValButton.click();
				String mapValSucess=chkAndGetMessage(By.cssSelector(prop.getProperty("mapValuesSucces")));
				if (mapValSucess.trim().equals("Message not found")){	
					flag=false;}
				else{
					flag=true;
				}
			}
			else
			{
			
				String msgMapComplete=chkAndGetMessage(By.cssSelector(prop.getProperty("mapCompleteMessage")));
				if (msgMapComplete.trim().contains("You have successfully mapped your file columns.")){	
					flag=true;				
					}
				else{
					flag=false;				
				}
			}	
			//close browser and switch to parent window				
			browser.close();
			browser.switchTo().window(parentWindow);
		   }			
	}	
	return flag;
}
/*** Method not to map Garnishment status***/
public boolean mapColumnsNoGarnsih(String filepath,  String browserType) throws IOException, InterruptedException {
	boolean flag=false;
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)		
	{
		if(!windowkey.equals(parentWindow)) 
		   {
			browser.switchTo().window(windowkey);
			//---clcik on choose file in child window	
			Thread.sleep(1000);
			
			Reporter.log("Select file "+ filepath +" to upload.");
			this.fileUploadonly(filepath);				
			Thread.sleep(2000);	
					
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolLastName")),"LastName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolFirstName")),"FirstName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolEmpId")),"EmployeeID");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolZipCode")),"ZipCode");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOB")),"BirthDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOH")),"HireDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapEmpStatus")),"EmploymentStatus");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapAnuSalary")),"AnnualSalary");	
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapWorkState")),"WorkState");	
			//click on map cols button  
			WebElement mapcolButton=browser.findElement(By.cssSelector(prop.getProperty("mapcolButton")));
			mapcolButton.click();	
			boolean exists=chkElementExists(By.cssSelector(prop.getProperty("mapValScreenexists")));
			if (exists==true)
			{
				//select active option from active drop down
				WebElement activeDropdown=browser.findElement(By.cssSelector(prop.getProperty("activeMap")));
				activeDropdown.click();				
				WebElement activeChkBox=browser.findElement(By.xpath(prop.getProperty("activeCheckbox")));
				activeChkBox.click();				
				WebElement mapmessageforsync=browser.findElement(By.cssSelector(prop.getProperty("mapValScreenexists")));
				mapmessageforsync.click();
				Thread.sleep(1000);
				
				//select terminated option from terminated drop down
				WebElement terminatedDropdown=browser.findElement(By.cssSelector(prop.getProperty("termiMap")));
				terminatedDropdown.click();				
				WebElement termiChkBox=browser.findElement(By.xpath(prop.getProperty("termiCheckbox")));
				termiChkBox.click();					
				mapmessageforsync.click();
				Thread.sleep(1000);
				
				//select Garnishment option from Garnishment drop down
				WebElement garniDropdown=browser.findElement(By.cssSelector(prop.getProperty("garnishMap")));
				garniDropdown.click();				
				WebElement garniChkBox=browser.findElement(By.xpath(prop.getProperty("ganiCheckbox")));
				garniChkBox.click();				
				mapmessageforsync.click();
				Thread.sleep(3000);
				
				WebElement mapValButton=browser.findElement(By.cssSelector(prop.getProperty("mapValuesButton")));
				mapValButton.click();
				String mapValSucess=chkAndGetMessage(By.cssSelector(prop.getProperty("mapValuesSucces")));
				if (mapValSucess.trim().equals("Message not found")){	
					flag=false;}
				else{
					flag=true;
				}
			}			
			//close browser and switch to parent window				
			browser.close();
			browser.switchTo().window(parentWindow);
		   }			
	}	
	return flag;
}


/*** Method not to map Garnishment status***/
public boolean mapAllColumns(String filepath,  String browserType) throws IOException, InterruptedException {
	boolean flag=false;
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)		
	{
		if(!windowkey.equals(parentWindow)) 
		   {
			browser.switchTo().window(windowkey);
			//---clcik on choose file in child window	
			Thread.sleep(1000);
			
			Reporter.log("Select file "+ filepath +" to upload.");
			this.fileUploadonly(filepath);				
			Thread.sleep(2000);	
					
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolLastName")),"LastName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolFirstName")),"FirstName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolEmpId")),"EmployeeID");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolZipCode")),"ZipCode");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOB")),"BirthDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOH")),"HireDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapEmpStatus")),"EmploymentStatus");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapAnuSalary")),"AnnualSalary");	
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapWorkState")),"WorkState");	
			//click on map cols button  
			WebElement mapcolButton=browser.findElement(By.cssSelector(prop.getProperty("mapcolButton")));
			mapcolButton.click();	
			boolean exists=chkElementExists(By.cssSelector(prop.getProperty("mapValScreenexists")));
			if (exists==true)
			{
				//select active option from active drop down
				WebElement activeDropdown=browser.findElement(By.cssSelector(prop.getProperty("activeMap")));
				activeDropdown.click();				
				WebElement activeChkBox=browser.findElement(By.xpath(prop.getProperty("activeCheckbox")));
				activeChkBox.click();				
				WebElement mapmessageforsync=browser.findElement(By.cssSelector(prop.getProperty("mapValScreenexists")));
				mapmessageforsync.click();
				Thread.sleep(1000);
				
				//select terminated option from terminated drop down
				WebElement terminatedDropdown=browser.findElement(By.cssSelector(prop.getProperty("termiMap")));
				terminatedDropdown.click();				
				WebElement termiChkBox=browser.findElement(By.xpath(prop.getProperty("termiCheckbox")));
				termiChkBox.click();					
				mapmessageforsync.click();
				Thread.sleep(1000);
				
				WebElement mapValButton=browser.findElement(By.cssSelector(prop.getProperty("mapValuesButton")));
				mapValButton.click();
				String mapValSucess=chkAndGetMessage(By.cssSelector(prop.getProperty("mapValuesSucces")));
				if (mapValSucess.trim().equals("Message not found")){	
					flag=false;}
				else{
					flag=true;
				}
			}			
			//close browser and switch to parent window				
			browser.close();
			browser.switchTo().window(parentWindow);
		   }			
	}	
	return flag;
}


/*** Method to Map duplicate columns for Census
 * @throws AWTException ***/
public boolean mapDuplicateColumns(String filepath,String browserType) throws IOException, InterruptedException, AWTException {
	boolean flag=false;
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)		
	{
		if(!windowkey.equals(parentWindow)) 
		   {
			browser.switchTo().window(windowkey);
			Thread.sleep(1000);
			
			Reporter.log("Select file "+ filepath +" to upload.");
			this.fileUploadonly(filepath);				
			Thread.sleep(1000);					
			
			Reporter.log("Map duplicate coloumns ie Map Lastname and First Name with First Name, Emp id and ZIP with ZIP, Date of Hire and Base Amount with Annual.");
			String ex=this.getTextVal(By.cssSelector(prop.getProperty("uploadFilename")));
			System.out.println("Filename if file exists "+ex);
				
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolLastName")),"FirstName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolFirstName")),"FirstName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolEmpId")),"ZipCode");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolZipCode")),"ZipCode");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOH")),"AnnualSalary");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapAnuSalary")),"AnnualSalary");			
			//click on map cols button  
			WebElement mapcolButton=browser.findElement(By.cssSelector(prop.getProperty("mapcolButton")));
			mapcolButton.click();
			//Check for map duplicate errors			
			String messageduplicate=chkAndGetMessage(By.cssSelector(prop.getProperty("duplicateMapError")));
				if (messageduplicate.trim().equals("Message not found")){	
					flag=false;}
				else{
					flag=true;
				}
			//close browser and switch to parent window				
			browser.close();
			browser.switchTo().window(parentWindow);
		   }			
	}	
	return flag;
}

/**Methods to upload Census File
 * @throws InterruptedException 
 * @throws IOException **/
public boolean uploadCensusFile(String filepath, String exe1path, String exe2path, String browserType) throws InterruptedException, IOException
{
	  boolean result1=false;
	//---click browse button to upload file on census upload page
	  clkbrowse();
		if (browserType.equalsIgnoreCase("internetexplorer"))
		{
		  	IEAction();
		    Thread.sleep(1000);				  	  
		}
	  //upload file again now to upload
	  TestUtil.fileupload(filepath, exe1path, exe2path, browserType);	  
	  Thread.sleep(10000);
	  // get text of the message
	  String actualImportMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("importORConfirmMessage")));
	  // proceed to upload file only if there are no errors in census file
	  boolean result=actualImportMessge.trim().contains("There are no errors in your census file.");
		 if (result==true){	
			
			clickConfUpload();
			alertAccept();
			//Retrieve  message after confirmation
			String actualConfirmMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("successMessage")));
			result1=actualConfirmMessge.trim().equals("You have successfully uploaded your census file.");			 
		}	
		 else{
			 result1=false; 
			 Reporter.log("Census File has errors, correct and upload again:");
			 Thread.sleep(5000);		
			 			 
			 String resultmsg;			
			 resultmsg=chkAndGetMessage(By.cssSelector(prop.getProperty("mapInvalidValError")));
			 if (resultmsg.contains("Oops"))
			 {
				 Reporter.log("Data/mapping Error displayed.");
			 }
			 else if (resultmsg.contains("Problem Records :"))
			 {
				 resultmsg=chkAndGetMessage(By.cssSelector(prop.getProperty("birthdateInvalidMessage")));
			 }
			 			
			 Thread.sleep(5000);
			String resultmsg2=chkAndGetMessage(By.cssSelector(prop.getProperty("birthdateInvalidMessage")));
			if (resultmsg2.contains("Annual Salary is Invalid"))
			 {
				 Reporter.log("Annual Salary is Invalid message is displayed.");
			 }
			 
		 }		 
	 return result1;
}

/*******************New upload with Robot or other easy methods
 * @throws AWTException ************************************************/
 public boolean uploadNew(String filepath) throws InterruptedException, IOException, AWTException
{
	  boolean result1=false;
	
	  WebElement uploadFileButtonUp=browser.findElement(By.cssSelector(prop.getProperty("uplaodFileButton")));
	  uploadFileButtonUp.sendKeys(filepath);
	  	  
	  Thread.sleep(1000);
	  // get text of the message
	  String actualImportMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("importORConfirmMessage")));
	  // proceed to upload file only if there are no errors in census file
	  boolean result=actualImportMessge.trim().contains("Census Data is valid. Click the Confirm Census File Upload button to upload your census data.");
		 if (result==true){	
			
			clickConfUpload();
			alertAccept();
			//Retrieve  message after confirmation
			String actualConfirmMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("successMessage")));
			result1=actualConfirmMessge.trim().equals("You have successfully uploaded your census file.");			 
		}	
		 else{
			 result1=false; 
			 Reporter.log("Census File has errors, correct and upload again:");
			 
			 Thread.sleep(1000);		
			 			 
			 String resultmsg;			
			 resultmsg=chkAndGetMessage(By.cssSelector(prop.getProperty("mapInvalidValError")));
			 if (resultmsg.contains("Oops"))
			 {
				 Reporter.log("Data/mapping Error displayed.");
			 }
			 else if (resultmsg.contains("Problem Records :"))
			 {
				 Reporter.log("Data contains invalid records.");
			 }
			 			
			 
			String resultmsg2=chkAndGetMessage(By.cssSelector(prop.getProperty("birthdateInvalidMessage")));
			if (resultmsg2.contains("Annual Salary is Invalid"))
			 {
				 Reporter.log("Annual Salary is Invalid message is displayed.");
			 }
			else if(resultmsg2.contains("BirthDate is Invalid"))
				
			{
				 Reporter.log("BirthDate is Invalid message is displayed.");
			}
			
			 
		 }		 
	 return result1;
}
 
 /****UploadNew by logging as super admin**********/

 public boolean uploadNewSuperAdmin(String filepath) throws InterruptedException, IOException, AWTException
 {
 	  boolean result1=false;
 	  
 	 this.selectListValue("Employee Loan Solutions", By.cssSelector(prop.getProperty("selectEmplCensUploadPage")), By.cssSelector(prop.getProperty("selectEmplCenUploadMenu")));
 	
 	  WebElement uploadFileButtonUp=browser.findElement(By.cssSelector(prop.getProperty("uplaodFileButton")));
 	  uploadFileButtonUp.sendKeys(filepath);
 	  	  
 	  Thread.sleep(1000);
 	  // get text of the message
 	  String actualImportMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("importORConfirmMessage")));
 	  // proceed to upload file only if there are no errors in census file
 	  boolean result=actualImportMessge.trim().contains("Census Data is valid. Click the Confirm Census File Upload button to upload your census data.");
 		 if (result==true){	
 			
 			clickConfUpload();
 			alertAccept();
 			//Retrieve  message after confirmation
 			String actualConfirmMessge= chkAndGetMessage(By.cssSelector(prop.getProperty("successMessage")));
 			result1=actualConfirmMessge.trim().equals("You have successfully uploaded your census file.");			 
 		}	
 		 else{
 			 result1=false; 
 			 Reporter.log("Census File has errors, correct and upload again:");
 			 
 			 Thread.sleep(1000);		
 			 			 
 			 String resultmsg;			
 			 resultmsg=chkAndGetMessage(By.cssSelector(prop.getProperty("mapInvalidValError")));
 			 if (resultmsg.contains("Oops"))
 			 {
 				 Reporter.log("Data/mapping Error displayed.");
 			 }
 			 else if (resultmsg.contains("Problem Records :"))
 			 {
 				 Reporter.log("Data contains invalid records.");
 			 }
 			 			
 			String resultmsg2=chkAndGetMessage(By.cssSelector(prop.getProperty("birthdateInvalidMessage")));
 			if (resultmsg2.contains("Annual Salary is Invalid"))
 			 {
 				 Reporter.log("Annual Salary is Invalid message is displayed.");
 			 }
 			else if(resultmsg2.contains("BirthDate is Invalid"))
 				
 			{
 				 Reporter.log("BirthDate is Invalid message is displayed.");
 			}
 			
 			 
 		 }		 
 	 return result1;
 }

 

/**Click on Browse button under Upload File Section**/
public void clkbrowse() {
	WebElement uploadFileButtonUp=browser.findElement(By.cssSelector(prop.getProperty("uplaodFileButton")));
	uploadFileButtonUp.click();	
	Reporter.log("Clicked on the Browse button under Upload File Section.");
	
}
/**Click on Browse button under Upload File Section**/
public void clkbrowseJS() {

	this.clkButtonwithJS(((By.cssSelector(prop.getProperty("uplaodFileButton")))));
	
	Reporter.log("Clicked on the Browse button under Upload File Section.");
	
}
/**Perform double click**/
public void IEAction() {
	 Actions action = new Actions(browser);						
	 Action doubleClick = action.doubleClick().build();
	 doubleClick.perform();
	
}
/**Get the text of import/confirm**/
public String getMessageText() {
	WebElement messageElement=browser.findElement(By.cssSelector(prop.getProperty("importORConfirmMessage")));
	String uploadMessage=messageElement.getText();
	return uploadMessage;
}
/**Click on Confirm upload button**/
public void clickConfUpload() {
	WebElement censusConfirmButton=browser.findElement(By.cssSelector(prop.getProperty("ConfirmButton")));
	censusConfirmButton.click();
	Reporter.log("Clicked on Confirm Upload Button.");
}
/**Accept any alert**/
public void alertAccept() {
	browser.switchTo().alert().accept();
}
public boolean mapInCorrectColumns(String filepath, String browserType) throws InterruptedException, IOException {
	boolean flag=false;
	String parentWindow = browser.getWindowHandle();
	Set<String> windowHandles=browser.getWindowHandles();
	for (String windowkey:windowHandles)		
	{
		if(!windowkey.equals(parentWindow)) 
		   {
			browser.switchTo().window(windowkey);
			Thread.sleep(1000);
			
			Reporter.log("Select file "+ filepath +" to upload.");
			this.fileUploadonly(filepath);				
			Thread.sleep(1000);	
			
			Reporter.log("Map Incorrect coloumns ie Map Emp Status with Annual.");
			//map emp status to annual			
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolLastName")),"LastName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolFirstName")),"FirstName");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolEmpId")),"EmployeeID");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolZipCode")),"ZipCode");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOB")),"BirthDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapcolDOH")),"HireDate");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapEmpStatus")),"AnnualSalary");
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapAnuSalary")),"EmploymentStatus");	
			this.selectByValMethod(By.cssSelector(prop.getProperty("mapWorkState")),"WorkState");	
			//click on map cols button  
			WebElement mapcolButton=browser.findElement(By.cssSelector(prop.getProperty("mapcolButton")));
			mapcolButton.click();
			//click on Map Values button
			this.clkButton(By.cssSelector(prop.getProperty("mapValuesButton")));
			String mapValSucess=chkAndGetMessage(By.cssSelector(prop.getProperty("mapValuesSucces")));
				if (mapValSucess.trim().equals("Message not found")){	
					flag=false;}
				else{
					flag=true;
					
				}
			//close browser and switch to parent window				
			browser.close();
			browser.switchTo().window(parentWindow);
		   }			
	}	
	return flag;
}
public String loadcenusFilePath(String rtvepath) {
	
	String fp=ClassLoader.getSystemResource("Automation").getFile();
	String scriptfilepath= fp+"\\"+rtvepath;
	String finalpath = new File(scriptfilepath).getAbsolutePath();
	System.out.println(finalpath);;
	return finalpath;
}

/**methpd to select employer in census upload page**/
public void selectEmployer(String employer) throws InterruptedException {
	this.selectListValue(employer, By.cssSelector(prop.getProperty("selectEmplCensUploadPage")), By.cssSelector(prop.getProperty("selectEmplCenUploadMenu")));
	
}

/**upload file without using other tools**/
public void fileUploadonly(String filepath) throws InterruptedException {
	 WebElement uploadFileButtonUp=browser.findElement(By.cssSelector(prop.getProperty("uploadMapButton")));
	  uploadFileButtonUp.sendKeys(filepath);
	  	  
	
}


}
