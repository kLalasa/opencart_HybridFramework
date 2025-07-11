package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC003_LoginDDT extends BaseClass{

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
    public void verify_LoginDDT(String email, String pwd, String exp){
        logger.info("*** Starting TC003_LoginDDT***");
        try{

        HomePage hp=new HomePage(driver);
        hp.clickMyaccount();
        hp.clickLogin();

        LoginPage lp=new LoginPage(driver);
        lp.setEmail(email);
        lp.setPassword(pwd);
        lp.clickLogin();

        MyAccountPage macc=new MyAccountPage(driver);
        boolean targetPage=macc.isMyAccountPageExists();

        if(exp.equalsIgnoreCase("Valid")){
            if(targetPage==true){
                macc.clickLogout();
                Assert.assertTrue(true);

            }
            else{
                Assert.assertTrue(false);
            }
        }

        if(exp.equalsIgnoreCase("Invalid")){
            if(targetPage==true){
                macc.clickLogout();
                Assert.assertTrue(false);
            }else{
                Assert.assertTrue(true);
            }
        }}catch (Exception e){
            Assert.fail();
        }

        logger.info("***Finished TC003 Login_DDT****");



    }
}
