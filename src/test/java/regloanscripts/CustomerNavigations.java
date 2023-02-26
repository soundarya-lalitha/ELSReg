package regloanscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import dataproviders.CustNavigationDataProvider;
import pageobjects.NavigationPageObject;

import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;



public class CustomerNavigations {
		
	@Test(dataProvider="customernavigation",dataProviderClass=CustNavigationDataProvider.class)  
	
	public void NavigationCheckCustomer(String email,String password,String path,String userType,String browserType) throws Exception
	{
		 //buffer to hold your errors 
		 StringBuffer errorBuffer = new StringBuffer();   
		 ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
			TestUtil.elslogin(email, password, lpo);			
			Thread.sleep(3000);			
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
				
				npo.clkLogOut();
				npo.close();
				Thread.sleep(2000);	
				
				 if(errorBuffer.length() > 0){
				        throw new AssertionError(errorBuffer.toString()); 
				 }	
	  
		 
	}
	
	
}
	
	

/*@Test
public void verifyTest(){  

     buffer to hold your errors 
    StringBuffer errorBuffer = new StringBuffer();      

     verification 1 
    try{        
        Assert.assertEquals("value1", "value!");            
    }catch(AssertionError e){           
        errorBuffer.append(e.getMessage() + "\n");      
    }       

     verification 2 
    try{            
        Assert.assertEquals("value2", "value!");            
    }catch(AssertionError e){           
        errorBuffer.append(e.getMessage());     
    }

    if(errorBuffer.length() > 0){
        throw new AssertionError(errorBuffer.toString()); 
    }

}*/


