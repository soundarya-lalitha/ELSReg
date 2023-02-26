package regHRscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.HRUploadDataProvider;
import pageobjects.CensusUploadPageObject;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class UploadCensusPageVerificationNew {
	
@Test(dataProvider="HRUploaddata",dataProviderClass=HRUploadDataProvider.class)
public void VerifyCensusAndMapPages(String email,String password,String censusFilePath,String Employer, String csvFieldList,String userType,String browserType) throws Exception
{
	ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
	
	TestUtil.elsloginAdmin(email, password, lpo); 
	
	CensusUploadPageObject cupo = new CensusUploadPageObject();
	cupo.setBrowser(lpo.getBrowser());
		
	//navigate to upload census file 
	cupo.navigateMenu("Employers-Upload Census File");
	Thread.sleep(5000); 

	
	cupo.selectEmployer(Employer);
	
	Thread.sleep(5000);	
	
	
	//Upload Census file and verify Map Columns page
	
	boolean res=cupo.VerifymapColumns(censusFilePath,browserType,csvFieldList);
	// Thread.sleep(1000);		 		 
	cupo.clkLogOutAdmin();
	// Thread.sleep(1000);	 
	cupo.close();		
	// assert the values	  
	Assert.assertEquals(res,true); 
		 
}
}

