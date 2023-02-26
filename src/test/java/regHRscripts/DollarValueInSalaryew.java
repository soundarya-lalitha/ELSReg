
package regHRscripts;
import org.testng.Assert;

import org.testng.annotations.Test;

import dataproviders.HRUploadDataProvider;
import pageobjects.CensusUploadPageObject;
import pageobjects.ELSLoginPageObject;

import testcases.util.TestUtil;

public class DollarValueInSalaryew {
		
	@Test(dataProvider="HRUploaddata",dataProviderClass=HRUploadDataProvider.class)
public void Upload$valueInSalary(String email,String password,String filepath,String Employer,String CSvFields,String userType,String browserType) throws Exception
	{
		boolean upload=false;
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		
		TestUtil.elsloginAdmin(email, password, lpo); 
		
		
		CensusUploadPageObject cupo = new CensusUploadPageObject();
		cupo.setBrowser(lpo.getBrowser());
		//navigate to upload census file 
		
		cupo.navigateMenu("Employers-Upload Census File");
		
		Thread.sleep(5000); 		
		cupo.selectEmployer(Employer);
		Thread.sleep(5000); 
		
		upload=cupo.uploadNew(filepath);
		cupo.clkLogOutAdmin();	
		Thread.sleep(2000); 
		cupo.close();	
		Assert.assertEquals(upload,true);	 
		
	
	}
}
