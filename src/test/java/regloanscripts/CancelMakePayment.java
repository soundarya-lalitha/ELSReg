package regloanscripts;
import org.testng.annotations.Test;

import dataproviders.MakeAPaymentDataProvider;
import pageobjects.*;
import testcases.util.TestUtil;
public class CancelMakePayment {
	
	@Test(dataProvider="makepayment",dataProviderClass=MakeAPaymentDataProvider.class)
	public void MakePayment(String email,String password,String loanno,String paymentType,String routNo,String acno,String acType,String userType,String browserType) throws Exception
	{		
		  //login to the ELS application and click on Start Application		 
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
							  mp.clkMPCanclButton(loan[i]);							  
						} 		
				}
			Thread.sleep(4000);
			mp.clkLogOut();
			mp.close();
	}
}

		

