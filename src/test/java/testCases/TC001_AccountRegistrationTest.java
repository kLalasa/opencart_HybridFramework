package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistrationPage;
import pageObject.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})

    public void verify_account_registration() {
        logger.info("******Starting TC001_AccountRegistrationTest*****");
        try {



        HomePage hp = new HomePage(driver);
        hp.clickMyaccount();
        logger.info("Clicked on MyAccount link");
        hp.clickRegister();
        logger.info("Clicked on Register Link");

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

        logger.info("Providing Customer Details");

        regpage.setFirstname(randomeString().toUpperCase());
        regpage.setLastname(randomeString().toUpperCase());
        regpage.setEmail(randomeString()+"@gmail.com");
        regpage.setTelephone(randomeNumber());

        String password=randomeAlphaNumeric();

        regpage.setPassword(password);
        regpage.setConfirmPassword(password);

        regpage.setPrivatePolicy();
        regpage.clickContinue();

        logger.info("validating expected message");

        String confmsg = regpage.getConfirmationMsg();

        if(confmsg.equals("Your Account Has Been Created!")){
            Assert.assertTrue(true);
        }
        else{
            logger.error("Test failed");
            logger.debug("Debug logs:");
            Assert.assertTrue(false);
        }

      //  Assert.assertEquals(confmsg, "Your Account");



    } catch (Throwable t) {
            logger.error("Test failed with error: " + t.getMessage(), t); // <-- logs full stack trace
            Assert.fail("Test failed: " + t.getMessage());
        } finally {
            logger.info("******TC001_AccountRegistrationTest finished *****");
        }
    }



}
