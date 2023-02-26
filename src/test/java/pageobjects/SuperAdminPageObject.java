package pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;


/**
 * @author Soundarya Lalitha
 *
 */
public class SuperAdminPageObject extends MasterPageObject{
	public SuperAdminPageObject() throws Exception {
		super();
	}
	
  /**
 * Click on Census Search link--obsolete this link is removed
 */
public void clickcensusSearch() {
	WebElement cenusSearchLink=browser.findElement(By.linkText(prop.getProperty("censusSearch")));
	cenusSearchLink.click();
	
}
/** select employer based on the input**/

/*public void SelectEmployer(String employer) throws InterruptedException {
	WebElement employerdropdown = browser.findElement(By.cssSelector(prop.getProperty("selectEmployerCenSearch")));
	employerdropdown.click();
	WebElement autocomplete=browser.findElement(By.cssSelector(prop.getProperty("employerMenu")));
	Thread.sleep(1000);
	List<WebElement> lielements=autocomplete.findElements(By.tagName("li"));
	for(WebElement var :lielements){
		String optionname;
		optionname=var.getText();
		if (optionname.trim().equals(employer))
		{
			var.click();
		}
	}
}*/
/** select employer based on the input**/

public void SelectEmployerCensus(String employer) throws InterruptedException {
	selectByValMethod((By.cssSelector(prop.getProperty("selectEmployerCenSearch"))),employer);
}

/** Enter last name or first name of the employee*/
public void enterName(String employee) {
	WebElement nameTextBox=browser.findElement(By.cssSelector(prop.getProperty("lnameorfname")));
	nameTextBox.sendKeys(employee);	
}

/** Enter last name or first name of the employee in census search screen*/
public void enterNameCensusScreen(String employee) {
	Reporter.log("Enter Employee name: "+employee +" in Census Search.");
	WebElement nameTextBox=browser.findElement(By.cssSelector(prop.getProperty("CensusSearcName")));
	nameTextBox.clear();
	nameTextBox.sendKeys(employee);	
}
/** Click Census Search button*/
public void clkSearch() {
	WebElement searchButton=browser.findElement(By.cssSelector(prop.getProperty("SearchbuttonCenSearPage")));
	searchButton.click();	
}
/** Get unique emp id of the employee*/
public String getEmployeeID() {
	WebElement parentContainerElement = browser.findElement(By.cssSelector(prop.getProperty("tablecontainer")));
	WebElement tableElement = parentContainerElement.findElement(By.tagName("table"));
	// get all rows of the table
	List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
	// get the first row
	WebElement FirstRowElement = rows.get(1);
	// get all cells of first row
	List<WebElement> lastRowCells = FirstRowElement.findElements(By.tagName("td"));
	//get the value of primary id
	WebElement empidCellElement = lastRowCells.get(5);
	String empidStringValue = empidCellElement.getText();
	return empidStringValue;
}


//** To get the **/
public String getEmpStatus() throws InterruptedException {	
	String empStatus=this.getValueFromTable(By.cssSelector(prop.getProperty("searchResultsTable")),1,12);
	//System.out.println("empStatus "+empStatus);
	return empStatus;	
}

public String getEmpName() throws InterruptedException {
	String empName=this.getValueFromTable(By.cssSelector(prop.getProperty("searchResultsTable")),1,1);
	return empName;	
}

public String getEmpZip() throws InterruptedException {
	String empZip=this.getValueFromTable(By.cssSelector(prop.getProperty("searchResultsTable")),1,9);
	return empZip;	
}

public String CalcEmpAnnSalary() throws InterruptedException {
	String baseSal=this.getValueFromTable(By.cssSelector(prop.getProperty("searchResultsTable")),1,13);
	String payPeriods=this.getValueFromTable(By.cssSelector(prop.getProperty("searchResultsTable")),1,14);
	String empAnSal = String.valueOf(Float.valueOf(baseSal) * Float.valueOf(payPeriods));
	return empAnSal;	
}


}
