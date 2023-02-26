package regHRscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.HRUploadDataProvider;
import pageobjects.CensusUploadPageObject;
import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;

public class MapDuplicateColumnsNew {
	
@Test(dataProvider="HRUploaddata",dataProviderClass=HRUploadDataProvider.class)
public void MapDuplicateColumn(String email,String password,String filepath,String Employer,String csvFieldList,String userType,String browserType) throws Exception
{
	ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
	TestUtil.elsloginAdmin(email, password, lpo); 
	
	CensusUploadPageObject cupo = new CensusUploadPageObject();
	cupo.setBrowser(lpo.getBrowser());
	
	//navigate to upload census file 
	cupo.navigateMenu("Employers-Upload Census File");
	Thread.sleep(5000);
		
	cupo.selectEmployer(Employer);
	Thread.sleep(5000); 	
	
	//---click map census Fields button
	cupo.clickMapCensus();
	Thread.sleep(1000); 
	
	//Upload Census file and verify Map Columns page
	boolean res=cupo.mapDuplicateColumns(filepath,browserType);
	cupo.clkLogOutAdmin();
	Thread.sleep(2000); 
	cupo.close();		
	// assert the values	  
	Assert.assertEquals(res,true); 

}
}
