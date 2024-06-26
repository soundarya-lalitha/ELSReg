package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.NavigationDataProviderProd;
import pageobjects.NavigationPageObject;

import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;



public class AdminNavigations {
	
	@Test(dataProvider="navigate",dataProviderClass=NavigationDataProviderProd.class)  
	
	public void NavigationCheck(String email,String password,String path,String userType,String browserType) throws Exception
	{
		 //buffer to hold your errors 
		 StringBuffer errorBuffer = new StringBuffer();   
		 ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
			TestUtil.elsloginAdmin(email, password, lpo);			
			Thread.sleep(1000);			
			NavigationPageObject npo=new NavigationPageObject();
			npo.setBrowser(lpo.getBrowser());
						
			String paths[]=path.split("#");
			
			for (String p:paths){
			
			//validating path1
			
			    String actualmessage1=npo.navigatetoPath(p,userType);
			    Thread.sleep(5000);	
				try{        
						Assert.assertEquals(actualmessage1,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}
			
			
			}
				
				npo.clkLogOutAdmin();
				npo.close();
				Thread.sleep(2000);	
				
				 if(errorBuffer.length() > 0){
				        throw new AssertionError(errorBuffer.toString()); 
				 }	
	  
		 
	}
	
	
}
	
	


