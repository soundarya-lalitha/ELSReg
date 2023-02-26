package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.HRUploadDataProvider;
import pageobjects.CensusUploadPageObject;
import pageobjects.ELSLoginPageObject;
import pageobjects.SuperAdminPageObject;
import testcases.util.TestUtil;

public class HRFileUpload {
	
	@Test(dataProvider="HRUploaddata",dataProviderClass=HRUploadDataProvider.class)
	public void hrMapandUploadData(String email,String password,String filepath,String exe1path,String exe2path,String sadminEmail,String sadminPassword,String	employer,String	employee,String	expectedVal,
			String userType,String browserType) throws Exception
	{
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		TestUtil.elslogin(email, password, lpo);
		
		//---click on upload census link
		  CensusUploadPageObject cupo = new CensusUploadPageObject();
		  cupo.setBrowser(lpo.getBrowser());
		  Thread.sleep(1000);
		 
		  cupo.navigateMenu("HR Management-Upload Census File");
		//---click map census link and map the fields in the displayed window and close the window
		  cupo.clickMapCensus();
		 //Map columns by File upload,selecting them from drop down and click on map columns button
		//  cupo.mapColumns(filepath,exe1path,exe2path,browserType);
		  Thread.sleep(1000);
		//---click browse button to upload file on census upload page
		  cupo.clkbrowse();
		if (browserType.equalsIgnoreCase("internetexplorer"))
		{
		  	cupo.IEAction();
		    Thread.sleep(1000);				  	  
		}
		  //upload file again now to upload
		  TestUtil.fileupload(filepath, exe1path, exe2path, browserType);
		  
		  Thread.sleep(15000);//----replace with wait.until
		 // get text of the message
		  String actualImportMessge= cupo.getMessageText();
		// assert the values
		  boolean result=actualImportMessge.trim().contains("There are no errors in your census file.");
		  Assert.assertEquals(result, true);
		  
		  cupo.clickConfUpload();
		  Thread.sleep(2000);
		  cupo.alertAccept();
		  Thread.sleep(3000);
		//Retrieve  message after confirmation
		  String actualConfirmMessge= cupo.getMessageText();
		// assert the values
		  boolean result1=actualConfirmMessge.trim().equals("You have successfully uploaded your census file.");
		  Assert.assertEquals(result1, true);
		  cupo.clkLogOut();
		  Thread.sleep(1000);	 
		  cupo.close();		
		  //login as superadmin
		  ELSLoginPageObject slpo=new ELSLoginPageObject(browserType, userType);
		  TestUtil.elslogin(sadminEmail, sadminPassword, slpo);
		  SuperAdminPageObject sa = new SuperAdminPageObject();
		  sa.setBrowser(slpo.getBrowser());
		  Thread.sleep(1000);
		  //click on Census Search link
		  sa.clickcensusSearch();				
		  Thread.sleep(3000);
		//select employer
		 // sa.SelectEmployer(employer);
		//enter value in text box and click on search
			sa.enterName(employee);
			sa.clkSearch();			
			Thread.sleep(4000);
			//get the employee id value
			String actualEmpId=sa.getEmployeeID();
			Assert.assertEquals(actualEmpId.trim(),expectedVal.trim());
			sa.clkLogOut();
			Thread.sleep(1000);	 
			sa.close();	
		}
}
