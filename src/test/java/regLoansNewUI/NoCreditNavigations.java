package regLoansNewUI;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders_stage.CustNavigationDataProvider;
import pageobjects.NavigationPageObject;

import pageobjects.ELSLoginPageObject;
import testcases.util.TestUtil;



public class NoCreditNavigations {
		
	@Test(dataProvider="customernavigation",dataProviderClass=CustNavigationDataProvider.class)  
	
	public void NoCreditNavigate(String email,String password,String path,String userType,String browserType) throws Exception
	{
		 //buffer to hold your errors 
		 StringBuffer errorBuffer = new StringBuffer();   
		 ELSLoginPageObject lpo=new ELSLoginPageObject(browserType,userType);
			TestUtil.elslogin(email, password, lpo);			
			Thread.sleep(3000);			
			NavigationPageObject npo=new NavigationPageObject();
			npo.setBrowser(lpo.getBrowser());				
			Thread.sleep(5000);   			   
		   
		    //No Credit Check Advance
		    String actualmessage1=npo.VerifyNoCreditNavigation();
		    	try{        
				Assert.assertEquals(actualmessage1,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}
		
		  //Pending apps is visible
		   String actualmessage2=npo.VerifyPendingApps();			    
				try{        
						Assert.assertEquals(actualmessage2,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}
				
			//Loan History page appears
		    String actualmessage3=npo.VerifyNavigations("Loan History","Employee Loan History / Balances");		    
				try{        
						Assert.assertEquals(actualmessage3,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}		
			 npo.ClinkLinkbyLnkTxt("Loans Home");
			 
			 //Verify New Loan link takes to app info page
			 String actualmessage4=npo.VerifyNewLoanNavigation();		    
				try{        
						Assert.assertEquals(actualmessage4,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}		
			 npo.ClinkLinkbyLnkTxt("My Account"); 	    
		    
			//Make a Payment page appears
		    String actualmessage5=npo.VerifyNavigations("Make a Payment","Make A Payment");		    
				try{        
						Assert.assertEquals(actualmessage5,"navigated to page");					
				}catch(AssertionError e){           
				        errorBuffer.append(e.getMessage() + "\n");      
				}		
			 npo.ClinkLinkbyLnkTxt("Loans Home");
			 
			 
			//Loan docs page appears
			    String actualmessag6=npo.VerifyNavigations("View Documents","Loan Documents");	    
					try{        
							Assert.assertEquals(actualmessag6,"navigated to page");					
					}catch(AssertionError e){           
					        errorBuffer.append(e.getMessage() + "\n");      
					}		
				 npo.ClinkLinkbyLnkTxt("Loans Home");
					 
				 
			//Update address page appears
			    String actualmessag7=npo.VerifyNavigations("Update","Update Address");	    
					try{        
							Assert.assertEquals(actualmessag7,"navigated to page");					
					}catch(AssertionError e){           
					        errorBuffer.append(e.getMessage() + "\n");      
					}		
				 npo.ClinkLinkbyLnkTxt("Loans Home");
			 
			//Electronic disclosure page appears
			    String actualmessag8=npo.VerifyEDNavigation();	 		    
					try{        
							Assert.assertEquals(actualmessag8,"navigated to page");					
					}catch(AssertionError e){           
					        errorBuffer.append(e.getMessage() + "\n");      
					}		
				 npo.ClinkLinkbyLnkTxt("Loans Home");		 
		
			Thread.sleep(2000);			    
			npo.clkLogOut();
			npo.close();
			
			if(errorBuffer.length() > 0){
					Reporter.log("No Credit Naviagations Failed.");
			        throw new AssertionError(errorBuffer.toString()); 
			}else
			{
				Reporter.log("No Credit Naviagations successful.");
			}
		 
	}
	
	
}



