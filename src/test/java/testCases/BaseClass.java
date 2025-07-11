package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties p;
    @Parameters({"os","browser"})

    @BeforeClass(groups={"Sanity","Regression","Master"})
    public void setUp(String os, String br) throws IOException {

        //loading config.properties file
        FileReader file=new FileReader("./src/main/resources/config.properties");
        p=new Properties();
        p.load(file);

        if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();
            if(os.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.WIN11);
                
            } else if (os.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);


            } else {
                System.out.println("No matching os");
                return;
            }

            switch (br.toLowerCase()){

                case "chrome":capabilities.setBrowserName("chrome");break;
                case "edge":capabilities.setBrowserName("edge");break;
                case "firefox":capabilities.setBrowserName("firefox");break;
                default:
                    System.out.println("No matching browser");return;
            }

            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }
        if(p.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (br.toLowerCase()){

                case "chrome":driver=new ChromeDriver();break;
                case "edge":driver=new EdgeDriver();break;
                case "firefox":driver=new FirefoxDriver();break;
                default:
                    System.out.println("Invalid browser name");return;

            }

        }



        logger= LogManager.getLogger(this.getClass());
       // driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL"));//reading URL from properties file
        driver.manage().window().maximize();


    }
    @AfterClass(groups={"Sanity","Regression","Master"})
    public void  tearDown(){
        driver.quit();

    }

    public String randomeString(){
        String generatedString= RandomStringUtils.randomAlphabetic(5);
        return  generatedString;

    }

    public  String randomeNumber(){
        String generatedNumber= RandomStringUtils.randomNumeric(10);
        return generatedNumber;
    }

    public String randomeAlphaNumeric(){
        String generatedstring= RandomStringUtils.randomAlphabetic(3);
        String generatednumber= RandomStringUtils.randomNumeric(3);
        return (generatednumber+"@"+generatedstring);
    }

    public String captureScreen(String tname)throws IOException{
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File sourcefile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+ "" +timeStamp+".png";
        File targetFile=new File(targetFilePath);
        sourcefile.renameTo(targetFile);
        return targetFilePath;
    }


}
