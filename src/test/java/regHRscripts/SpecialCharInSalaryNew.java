package regHRscripts;
import org.testng.Assert;

import org.testng.annotations.Test;

import dataproviders.HRSplCharSalDataProvider;
import pageobjects.CensusUploadPageObject;
import pageobjects.ELSLoginPageObject;

import testcases.util.TestUtil;

public class SpecialCharInSalaryNew {
		
@Test(dataProvider="HRSplCharSal",dataProviderClass=HRSplCharSalDataProvider.class)
public void UploadSplCharInSalary(String email,String password,String filepath,String userType,String browserType) throws Exception
	{
		boolean upload=false;
		ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);

		TestUtil.elsloginAdmin(email, password, lpo); 
		
		CensusUploadPageObject cupo = new CensusUploadPageObject();
		cupo.setBrowser(lpo.getBrowser());
		//navigate to upload census file 
		
		cupo.navigateMenu("HR Management-Upload Census File");
		
		Thread.sleep(1000);
		//---click map census Fields button	
		upload=cupo.uploadNew(filepath);
		cupo.clkLogOutAdmin();	
		Thread.sleep(2000); 
		cupo.close();	
				
		Assert.assertEquals(upload,false);	 
	}
}
