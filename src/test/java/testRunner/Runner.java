package testRunner;

import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import stepDefinition.Steps;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@RunWith(Cucumber.class)
@CucumberOptions
        (features = { "Feature" },
                glue = { "stepDefinition" },
                tags = { "@Sanity" },  ///  "@Service","~@Regression"  IOS
                plugin = { "pretty",
                        "html:target/Cucumber-htmlreport", "json:target/cucumber-report.json",
                        "com.cucumber.listener.ExtentCucumberFormatter:target/ExtentReport.html" },
                dryRun = false,
                monochrome = true)

public class Runner {

    public static Properties prop = new Properties();
    public static WebDriver driver;
    public static String BrowserName;


    @BeforeClass
    public static void init() {

        BrowserName = null;

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("config/Configuration.properties");
        try {
            prop.load(input);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String ApkWindows = prop.getProperty("ApkWindows");
        String MacAPKFileEmpre = prop.getProperty("MacAPKFileEmpre");
        String Chrome_Driver = prop.getProperty("Chrome_Driver");
        String FireFoxDriver=prop.getProperty("FireFoxDriver");
        String EdgeDriver=prop.getProperty("EdgeDriver");
        String And_Chrome=prop.getProperty("And_Chrome");



        try {
            assert input != null;
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BrowserName = "Android_App";

        if (BrowserName == "Android_App") {

            // File ApplicationToTest =new
            // File("C:\\Spring\\WorkSpace\\Appuim_Cucumber\\Appium_Cucumber\\ApplicationToTest\\app-handz-release.apk");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android_12");
            capabilities.setCapability(MobileCapabilityType.APP, ApkWindows);//MacAPKFileParti//MacAPKFileEmpre
            // capabilities.setCapability(MobileCapabilityType.APP,
            // ApplicationToTest.getAbsolutePath());
            //capabilities.setCapability("unicodeKeyboard", "true");
            //capabilities.setCapability("resetKeyboard", "true");
            //capabilities.setCapability(MobileCapabilityType.UDID, "1215fcdb10830205");
            capabilities.setCapability("nativeWebScreenshot", "true");
            //capabilities.setCapability("locationServicesAuthorized", true);
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.englishdiscoveries");// AppPackageParti //AppPackageEmpre//"caixaeconomica.androidmobile");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                    "com.englishdiscoveries.MainActivity");

            try {
                driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Steps.defaulturl = "Mobile";
            Steps.driver = driver;
        }

        else if (BrowserName == "IOS") {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5"); //  PLATFORM_VERSION, "11.8");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Comply's iphone");  /// DEVICE_NAME, "iPhone11 XR");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            capabilities.setCapability(MobileCapabilityType.UDID, "b2f855a8bcd8ccfbf2e4a11f75cac2248b2ef179"); //UDID, "00008020-001405841E3A002E");
            capabilities.setCapability("xcodeOrgId", "2HB98Y672J");
            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
            capabilities.setCapability("bundleId", "org.LumiiMG.qa");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
            //capabilities.setCapability("autoGrantPermissions", true);
            capabilities.setCapability("locationServicesAuthorized", true);
            // capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("nativeWebScreenshot", true);
            //capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
            //capabilities.setCapability(MobileCapabilityType.APP, MacIPAFile);   //  "/Users/qualitest/Downloads/APK_IPA/LumiiMG.ipa");
            try {
                driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Steps.defaulturl = "Mobile";
            Steps.driver = driver;
        }
        else if (BrowserName == "Chrome") {
            System.setProperty("webdriver.chrome.driver", Chrome_Driver);

            //  Another way to show ChromeDriver location
            // System.setProperty("webdriver.chrome.driver",
            // "C:/chromedriver/chromedriver");

            ///    open Chrome incognito
            //ChromeOptions options = new ChromeOptions();
            //options.addArguments("-incognito");
            //options.addArguments("--disable-popup-blocking");
            //ChromeDriver driver = new ChromeDriver(options);

            //     Open chrome Headless
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
//            WebDriver driver = new ChromeDriver(options);

            //   Open Chrome for whatsup Web
            //Map<String, Object> prefs = new HashMap<String, Object>();
            //prefs.put("profile.default_content_setting_values.notifications", 2);
            //ChromeOptions options = new ChromeOptions();
            //options.setExperimentalOption("prefs", prefs);
            // WebDriver driver = new ChromeDriver(options);

            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            driver.manage().window().maximize();
            //Steps.defaulturl = "Web";
            Steps.driver = driver;

        }

        else if (BrowserName == "API") {

            System.setProperty("webdriver.chrome.driver", Chrome_Driver);
            WebDriver driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
            driver.manage().window().maximize();
            //Steps.defaulturl = "Web";
            Steps.APIUsage="true";
            Steps.driver = driver;
        }
    }

    @AfterClass
    public static void after() throws IOException {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("config/Configuration.properties");

        prop.load(input);
        String AndVersion = prop.getProperty("Android_Version");
        String IOSVersion = prop.getProperty("Ios_Version");
        String AutomationVer = prop.getProperty("Automation_Version");

        if(!BrowserName.equals("Android_App")) {
            Reporter.setSystemInfo("Author", "QA Automation");
            Reporter.setSystemInfo("Environment","Test");
            Reporter.setSystemInfo("Ios Version: ", IOSVersion);
            Reporter.setSystemInfo("Automation Version: ", AutomationVer);
            Reporter.loadXMLConfig("src/main/resources/config/EnglishDiscoveries.xml");
            System.out.println("The user see the text: ");
        }else{
            Reporter.setSystemInfo("Author", "QA Automation");
            Reporter.setSystemInfo("Environment","Test");
            Reporter.setSystemInfo("Android Version: ", AndVersion);
            Reporter.setSystemInfo("Automation Version: ", AutomationVer);
            Reporter.loadXMLConfig("src/main/resources/config/EnglishDiscoveries.xml");
        }

        assert input != null;
        input.close();

        // return;
        driver.quit();
        //}

    }
}

