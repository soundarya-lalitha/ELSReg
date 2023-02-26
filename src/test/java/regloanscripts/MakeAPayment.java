package regloanscripts;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import dataproviders.MakeAPaymentDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;
public class MakeAPayment {
	
	@Test(dataProvider="makepayment",dataProviderClass=MakeAPaymentDataProvider.class)
	public void MakePayment(String email,String password,String loanno,String paymentType,String routNo,String acno,String acType,String userType,String browserType) throws Exception
	{		
		  //login to the ELS application and click on Start Application
		  String paymentStatus;	
		  ELSLoginPageObject lpo=new ELSLoginPageObject(browserType, userType);
		  TestUtil.elslogin(email, password, lpo); 
		  MakeAPaymentPageObject mp= new MakeAPaymentPageObject(); 
		  mp.setBrowser(lpo.getBrowser());		  
		  mp.navigateMenu("MAKE A PAYMENT"); 
		  Thread.sleep(5000);
		  
		  boolean multivalues=loanno.contains(";");
			if (multivalues==true)
				{
						String loan[]=loanno.split(";");			
						int i;
						boolean flag=false;
						for(i=0;i<loan.length;i++)						
						{
						
							  System.out.println("loan[i] "+loan[i]);
							  System.out.println("flag before "+flag);
							  Thread.sleep(4000);
							  mp.clkPayButton(loan[i]);
							  Thread.sleep(4000);
							  mp.clkRadio("Pay with your bank account");	
							  Thread.sleep(4000);
							  mp.clkConti();
							  Thread.sleep(4000);		 
									  if(paymentType.equals("Pay off Loan")) 
									   {
										  Reporter.log("Pay off Loan flow started.");
										  mp.payoffLoan("RBS",acno,routNo,acType);  			  			  
									   }
									  else if(paymentType.equals("Make loan payment")) 
									   {
										  Reporter.log("Make loan payment flow started.");
										  mp.makeLoanPayment("RBS",acno,routNo,acType);  			  
									   }
									  else 
									   {
										  Reporter.log("Other payment flow started.");
										  mp.makeOtherPayment("RBS",acno,routNo,acType,"100"); 			  
									   }  	
							  
									  paymentStatus=mp.validatePayment(loan[i]);
									  if (paymentStatus.equals ("Pending"))
									  {
										  flag=true;
									  }
									  else
									  {
										  flag=false;
									  }
							  							
							}
						System.out.println("flag after "+flag);
						
						 Thread.sleep(3000);
					      mp.clkLogOut();
					      mp.close();						
						 Assert.assertEquals(flag,true);
				  
			  
				}		  
			else
			{				
				  mp.clkPayButton(loanno);
				  Thread.sleep(3000);
				  mp.clkRadio("Pay with your bank account");	
				  Thread.sleep(3000);
				  mp.clkConti();
				  Thread.sleep(3000);		 
				  if(paymentType.equals("Pay off Loan")) 
				   {
					  Reporter.log("Pay off Loan flow started.");
					  mp.payoffLoan("RBS",acno,routNo,acType);  			  			  
				   }
				  else if(paymentType.equals("Make loan payment")) 
				   {
					  Reporter.log("Make loan payment flow started.");
					  mp.makeLoanPayment("RBS",acno,routNo,acType);  			  
				   }
				  else 
				   {
					  Reporter.log("Other payment flow started.");
					  mp.makeOtherPayment("RBS",acno,routNo,acType,"100"); 			  
				   }  	
				  
				  paymentStatus=mp.validatePayment(loanno);
				  Thread.sleep(4000);
			      mp.clkLogOut();
			      mp.close();
				  Assert.assertEquals(paymentStatus,"Pending"); 
			}	 	 
		
			
			  
		
		
	}
}

		

