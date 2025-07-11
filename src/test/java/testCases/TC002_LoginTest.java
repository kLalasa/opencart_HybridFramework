package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_LoginTest extends BaseClass{

    @Test(groups={"Sanity","Master"})

    public void verfiy_Login(){

        logger.info("****Starting TC002_LoginTest****");

        try {

            //HomePage
            HomePage hp = new HomePage(driver);

            hp.clickMyaccount();
            hp.clickLogin();

            //LoginPage

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(p.getProperty("email"));
            System.out.println("Email: " + p.getProperty("email"));

            lp.setPassword(p.getProperty("password"));
            System.out.println("Password: " + p.getProperty("password"));
            lp.clickLogin();


            //MyAccountPage

            MyAccountPage macc = new MyAccountPage(driver);

            boolean targetpage = macc.isMyAccountPageExists();

            Assert.assertTrue(targetpage);

        }catch (Exception e){

            Assert.fail();
        }

        logger.info("*** Finished TC002_LoginTest****");
    }




}
