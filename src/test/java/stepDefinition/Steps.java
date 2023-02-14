package stepDefinition;


import DataBase.DbService;
import com.google.common.base.CharMatcher;
import com.google.common.collect.ImmutableMap;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testRunner.Runner;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Steps {

    public static String error_msg;
    private static String[] Excel_Content = new String[25];
    public static WebDriver driver; // = new ChromeDriver();
    static Properties prop = Runner.prop;  //new Properties();
    public static String ExcelFilepath;
    public static String RandnumberMail;
    public static String Current_Randnumber;
    public static String otpass;
    public static String otp;
    public static String APIUsage;
    public static String institutionId;
    //API  Functions  Example 1
    public static String Token_path;
    public static String Url_path;
    public static String BASE_URL;
    public static String User_name_contact;
    public static String User_Email_contact;
    public static String User_Phone_contact;
    public static String parentWindow;
    public static DbService dbService = new DbService();
    private Response response;
    String userName = "";
    String password = "";

    //     Mobile  Functions
//    Mobile  Functions
    ////   IOS
    @And("^Get users from DB$")
    public void getUsers() throws Throwable {
        dbService.getUsers();
    }

    @And("^IOS press done$")
    public void IOS_press_done() throws Throwable {
        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(318, 42)).perform();
        Thread.sleep(1000);
    }

    @And("^IOS press Edit this entry$")
    public void IOS_press_Edit_this_entry() throws Throwable {
        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(179, 512)).perform();
        Thread.sleep(1000);
    }

    @And("^IOS press Delete this entry$")
    public void IOS_press_Delete_this_entry() throws Throwable {
        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(185, 572)).perform();
        Thread.sleep(1000);
    }

    @And("^IOS press Warning/Aapproved/Delete/Continue$")
    public void IOS_press_warning_continue() throws Throwable {

        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(289, 627)).perform();
        Thread.sleep(1000);
    }

    @And("^IOS press Cancel Button$")
    public void IOS_press_Cancel_Button() throws Throwable {

        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(74, 631)).perform();
        Thread.sleep(1000);
    }

    @And("^IOSDriver Scroll to text and click \"(.*?)\" and click by xpath \"(.*?)\"$")
    public void IOSDriver_Scroll_to_text_and_click_by_xpath(String input, String Value) throws Throwable {
        ///   This function is good for text on buttons
        try {
            ((IOSDriver<?>) driver).findElementByIosClassChain(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                            + input + "\").instance(0))");

            Thread.sleep(3000);
            driver.findElement(By.xpath(Value)).click();
        } catch (InterruptedException e) {
            Thread.sleep(1000);
            e.printStackTrace();

        }
    }

    @And("^IOSDriver press the key clear$")
    public void IOSDriver_press_the_key_clear() {
        ((IOSDriver<?>) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Del"));
    }

    @And("^IOSDriver Close the current app$")
    public void IOSDriver_close_the_current_app() {
        ((IOSDriver<?>) driver).resetApp();
        ((IOSDriver<?>) driver).closeApp();
    }

    @And("^IOSDriver restart current app$")
    public void IOSDriver_restart_the_current_app() {
        ((IOSDriver<?>) driver).resetApp();
        ((IOSDriver<?>) driver).closeApp();
        ((IOSDriver<?>) driver).launchApp();

    }

    @And("^If the object by ios chain \"(.*?)\" is not exists try this one \"(.*?)\"$")
    public void if_the_object_by_ios_chain_does_not_exits_try_this_one(String input, String input2) {

        String isPresent, allobjects;
        isPresent = "";

        isPresent = driver.findElement(MobileBy.iOSClassChain(input)).getText();
        // System.out.println("the text on isPresent "+isPresent);

        allobjects = driver.findElement(MobileBy.iOSClassChain(input2)).getText();
        // System.out.println("the text on allobjects "+allobjects);

        if (isPresent.length() > allobjects.length() && allobjects.length() != 0) {
            try {
                driver.findElement(MobileBy.iOSClassChain(input2)).click();
                System.out.println("isPresent.length()  " + isPresent.length());

            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        } else {
            try {
                driver.findElement(MobileBy.iOSClassChain(input)).click();
                // System.out.println("User Press the Secound"+input);

            } catch (NoSuchElementException e) {
                e.printStackTrace();
            }
        }
    }

    @And("^User wait for \"(.*?)\" element to be presented \"(.*?)\"$")
    public void User_wait_for_element_to_be_presented(Integer Value, String input) {

        WebDriverWait wait = new WebDriverWait(driver, Value);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(input))));


    }

    @And("^User press ios chain value \"(.*?)\"$")
    public void User_press_ios_chain_value(String input) {

        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).click();

        driver.findElement(MobileBy.iOSClassChain(input)).click();
    }

    @And("^User press ios chain field \"(.*?)\" with the value \"(.*?)\"$")
    public void User_press_ios_chain_field_with_the_value(String input, String value) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).sendKeys(value);
        // Thread.sleep(1000);
        // driver.findElement(MobileBy.iOSClassChain(input)).sendKeys(value);
    }

    @And("^User press ios chain field \"(.*?)\" with the value int \"(.*?)\"$")
    public void User_press_ios_chain_field_with_the_value_int(String input, int value) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSClassChain(input))).sendKeys("" + value);

        System.out.println("User enter the value" + value);
    }

    @And("^User press ios AccessibilityId object \"(.*?)\"$")
    public void User_press_ios_AccessibilityId_object(String input) {

        MobileElement el3 = (MobileElement) ((IOSDriver<?>) driver).findElementByAccessibilityId(input);
        el3.click();
        // ((MobileElement) ((IOSDriver<?>) driver).findElementByAccessibilityId(input)).click();
    }

    @And("^IOS User paste the text \"(.*?)\" by AccessibilityId \"(.*?)\"$")
    public void IOS_User_paste_the_text_by_AccessibilityId(String value, String input) throws Throwable {

        ((IOSDriver<?>) driver).findElementByAccessibilityId(input).sendKeys(value);
    }

    @And("^IOS Click element by xpath \"(.*?)\"$")
    public void IOS_click_element_by_xpath(String input) {

        MobileElement el1 = (MobileElement) ((IOSDriver<?>) driver).findElementByXPath(input);
        el1.click();
    }

    @And("^User press ios iOSNsPredicateString value \"(.*?)\"$")
    public void User_press_ios_iOSNsPredicateString_value(String input) {

        // String selector= "type == 'XCUIElementTypeTextField' AND name == 'Email'";
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.elementToBeClickable(MobileBy.iOSNsPredicateString(input))).click();
        // ((IOSDriver<?>) driver).findElementsByIosNsPredicate(input).get(1).click();
        driver.findElement(MobileBy.iOSNsPredicateString(input)).click();

    }

    // @And("^press element by xpath \"(.*?)\" with the value by Text \"(.*?)\"$")
    // public void press_element_by_xpath_by_Text(String input, String value) throws InterruptedException {
    //    Select drpBox = new Select(driver.findElement(By.xpath(input)));
    //     drpBox.selectByIndex(1);
    //     drpBox.selectByVisibleText(value);
    //     drpBox.selectByIndex(1);
    //  }
    @And("^IOS hide keyboard for field by xpath \"(.*?)\"$")
    public void press_the_ios_hidekeyboard(String input) {

        driver.findElement(MobileBy.xpath(input)).sendKeys("\n");
    }

    @And("^Press the IOS hide keyboard$")
    public void press_the_ios_hidekeyboard() throws Throwable {
        ((IOSDriver<?>) driver).hideKeyboard();
        ((IOSDriver<?>) driver).getKeyboard().sendKeys(Keys.ENTER);
    }

    @And("^IOSDriver press key enter$")
    public void IOSDriver_press_keyEnter() throws Throwable {
        ((IOSDriver<?>) driver).getKeyboard().pressKey(Keys.ENTER);
    }

    @And("^IOS SendKeys Arrow_down$")
    public void ios_send_keys_Arrow_down() {
        ((IOSDriver<?>) driver).getKeyboard().sendKeys(Keys.ARROW_DOWN);
    }

    @And("^IOS SendKeys down$")
    public void ios_send_keys_down() {
        ((IOSDriver<?>) driver).getKeyboard().sendKeys(Keys.DOWN);
    }

    @And("^IOS SendKeys enter$")
    public void ios_send_keys_Enter() {
        ((IOSDriver<?>) driver).getKeyboard().sendKeys(Keys.ENTER);
    }

    @And("^IOS SendKeys Return$")
    public void ios_send_keys_return() {
        ((IOSDriver<?>) driver).getKeyboard().sendKeys(Keys.RETURN);
    }

    @And("^Press the appium driver key RETURN")
    public void press_the_appium_key_return() {
        ((AppiumDriver<?>) driver).getKeyboard().sendKeys(Keys.RETURN);
    }

    @And("^Press the appium driver key Enter")
    public void press_the_appium_key_enter() throws Throwable {
        ((AppiumDriver<?>) driver).getKeyboard().sendKeys(Keys.ENTER);
    }

    @And("^IOSUser Report \"(.*?)\"$")
    public void IOSUser_Report(String input) {
        System.out.println("User Report:    " + input);
        System.out.println(driver.getPageSource());
    }

    @And("^User enter random email to field by IOS AccessibilityId \"(.*?)\"$")
    public void user_enter_random_email_to_field_by_ios_AccessibilityId(String input) throws Throwable {

        Random random = new Random();
        int index = random.nextInt(10000000);
        Current_Randnumber = Integer.toString(index);
        RandnumberMail = "comply" + Current_Randnumber + "@mailinator.com";

        ((IOSDriver<?>) driver).findElementByAccessibilityId(input).click();
        Thread.sleep(2000);
        ((IOSDriver<?>) driver).findElementByAccessibilityId(input).sendKeys(RandnumberMail);
        System.out.println("User enter random email : " + RandnumberMail);

    }


    //   End  IOS Mobile  Functions
    //   End  IOS Mobile  Functions
    //   End  IOS Mobile  Functions

    ///Android  ///Android   ///Android
///Android  ///Android   ///Android
    @And("^Android click by id \"(.*?)\"$")
    public void android_click_by_id(String input) {
        WebElement element = ((AndroidDriver<?>) driver).findElementByAccessibilityId(input);
        element.click();
    }

    @And("^Press the object mobileby xpath \"(.*?)\"$")
    public void Press_the_object_mobileby_xpath(String input) throws Throwable {
        driver.findElement(MobileBy.xpath(input)).click();
    }

    @And("^Android press the key clear$")
    public void Android_press_the_key_clear() {
        ((AndroidDriver<?>) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "Del"));
    }

    @And("^IOS Scroll UP$")
    public void IOS_scroll_UP() throws Throwable {
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "up");
        ((IOSDriver<?>) driver).executeScript("mobile:scroll", scrollObject);
    }

    @And("^IOS Scroll Down$")
    public void IOS_scroll_down() throws Throwable {
        // WebElement element = driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"acceptContinueButton\"]"));
        // String element2= element.getText();

        HashMap<String, String> scrollObject = new HashMap<String, String>();
        // scrollObject.put("element", element2);
        scrollObject.put("direction", "down");
        ((IOSDriver<?>) driver).executeScript("mobile:scroll", scrollObject);
//    ((IOSDriver<?>) driver).execute("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"//XCUIElementTypeOther[@name=\"acceptContinueButton\"]\").instance(0))");
//   ((IOSDriver<?>) driver).executeScript( "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)");
        // ((IOSDriver<?>) driver).execute( "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)");
    }

    @And("^IOS Scroll to xpath \"(.*?)\"$")
    public void IOS_scroll_to_xpath(String Value) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("xpath", Value);
        js.executeScript("mobile: scroll", scrollObject);
    }

    @And("^Android Scroll Down$")
    public void android_scroll_down() throws Throwable {
        //  try {
        //   driver.findElement(MobileBy.AndroidUIAutomator(
        //           "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)");
        //   } catch (InvalidSelectorException e) {
        // ignore
        //    }
        //   try {
        //       driver.findElement(MobileBy.AndroidUIAutomator(
        //               "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(10)"));
        //   } catch (InvalidSelectorException e) {
        // ignore
        //    }

    }

    @And("^IOS Scroll to text \"(.*?)\" and click by xpath \"(.*?)\"$")
    public void IOS_Scroll_to_text_and_click_by_xpath(String input, String Value) throws Throwable {
        //
        ((IOSDriver<?>) driver).execute("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                + input + "\").instance(0))");
        Thread.sleep(3000);
        //driver.findElement(MobileBy.xpath(Value)).click();
        ((IOSDriver<?>) driver).findElement(By.xpath(Value)).click();
    }

    @And("^Android Scroll to text \"(.*?)\" and click$")
    public void android_Scroll_to_text_and_click(String input) throws Throwable {

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                        + input + "\").instance(0))").click();
    }

    @And("^Android Scroll to text \"(.*?)\" and Choose by xpath \"(.*?)\"$")
    public void android_Scroll_to_text_and_choose_by_xpath(String input, String Value) throws Throwable {
        ///   This function is good for text on Combo buttons ListBoxs
        try {
            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                            + input + "\").instance(0))");
            Thread.sleep(1000);
            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                            + input + "\").instance(0))").click();

            Thread.sleep(3000);
            //driver.findElement(MobileBy.xpath(Value)).click();
            driver.findElement(By.xpath(Value)).click();
        } catch (InterruptedException e) {
            Thread.sleep(1000);
            // driver.findElement(MobileBy.xpath(Value)).click();
            e.printStackTrace();
        }
    }

    @And("^Scroll down and press the object byxpath \"(.*?)\" and value \"(.*?)\"$")
    public void Scroll_down_and_press_the_xpath_and_value(String input, String value1) throws Throwable {

        WebElement element = driver.findElement(By.xpath(input));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
        Actions action = new Actions(driver);
        action.click(element).build().perform();

        Thread.sleep(2000);
        driver.findElement(By.xpath(value1)).click();

    }

    @And("^User Scroll page and press By linkText \"(.*?)\"$")
    public void Scroll_page_and_press_By_linkText(String input) throws Throwable {

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(" + input + "));").click();
    }

    @And("^Android press the object by text \"(.*?)\"$")
    public void android_press_the_object_by_text(String input) throws Throwable {

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator("new UiSelector().textContains(" + input + ")").click();

    }

    @Then("^Scroll to validate the text by xpath \"(.*?)\" that the Value is \"(.*?)\"$")
    public void Scroll_to_validate_the_text_by_xpath_value_is(String input, String value1) throws Throwable {
        WebElement element = driver.findElement(By.xpath(input));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
        Actions action = new Actions(driver);
        action.click(element).build().perform();


        String heading_text = driver.findElement(By.xpath(input)).getText();
        //String heading_text = driver.findElement(By.xpath(input)).getAttribute("value");
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    @And("^Android restart the current app$")
    public void Android_restart_the_current_app() throws Throwable {
        // ((AndroidDriver<?>) driver).quit();
        ((AndroidDriver<?>) driver).resetApp();
        Thread.sleep(3000);
        //((AndroidDriver<?>) driver).resetInputState();
        ((AndroidDriver<?>) driver).closeApp();
        Thread.sleep(3000);
        ((AndroidDriver<?>) driver).launchApp();
        Thread.sleep(3000);
        //driver.close();
    }

    @And("^Android Close the current app$")
    public void Android_close_the_current_app() throws Throwable {
        ((AndroidDriver<?>) driver).resetApp();
        Thread.sleep(3000);
        ((AndroidDriver<?>) driver).closeApp();
        Thread.sleep(3000);
    }

    @And("^Press the Back button$")
    public void press_the_screen() throws Throwable {
        driver.navigate().back();
    }

    @And("^User press by location value \"(.*?)\" \"(.*?)\"$")
    public void User_press_bylocation_value(int point1, int point2) throws Throwable {

        TouchAction startStop = new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(point1, point2))
                .perform();
    }

    @Then("^Check if the element is enabled by xpath \"(.*?)\"$")
    public void Check_if_the_element_is_enabled_by_xpath(String input) throws Throwable {
        WebElement element = driver.findElement(By.xpath(input));

        boolean heading_text = element.isEnabled();
        // Assert.assertTrue(heading_text);
        if (heading_text) {
            System.out.println("The Element is Enabled:    " + heading_text + " ");
        } else {
            System.out.println("The Element is Disabled:    " + heading_text + " ");
        }
    }

    @And("^Android hide keyboard$")
    public void Android_hidekeyboard() throws Throwable {

        ((AndroidDriver<?>) driver).hideKeyboard();
    }

    @And("^Android presskey enter$")
    public void Android_presskey_enter() throws Throwable {

        ((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
        //((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.BUTTON_SELECT));

    }

    @And("^Press the field \"(.*?)\" by xpath and the Value is \"(.*?)\"$")
    public void press_the_field_by_xpath_and_the_value_is(String input1, String value) throws Throwable {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(MobileBy.xpath(input1)).click();
        Thread.sleep(1000);
        driver.findElement(MobileBy.xpath(value)).click();
        Thread.sleep(2000);

    }

    @And("^User press the object by xpath \"(.*?)\"$")
    public void User_press_the_object(String input) throws Throwable {

        driver.findElement(By.xpath(input)).click();
        //Thread.sleep(4000);
    }

    @And("^User press the object by xpath for disabled button \"(.*?)\"$")
    public void User_press_the_object_for_disabled_button(String input) throws Throwable {

        WebElement element = driver.findElement(By.xpath(input));
        boolean actualValue = element.isEnabled();
        //!heading_text.equals(input)) {
        if (!actualValue == true) {
            throw new IllegalStateException("The validation has failed");
        } else {
            System.out.println("The button is disabled");
        }
    }

    @And("^User enter the text to field by xpath \"(.*?)\" With the Value \"(.*?)\"$")
    public void user_enter_the_text_to_field_by_xpath_with_the_value(String input1, String value) throws Throwable {

        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(MobileBy.xpath(input1)).sendKeys(value);
            System.out.println("User enter the text to field:    " + input1 + "    " + value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @And("^User enter the text to field xpath \"(.*?)\" With the Value from ConfigFile \"(.*?)\"$")
    public void user_enter_the_text_to_field_by_Android_with_the_value_from_ConfigFile(String input1, String value)
            throws Throwable {

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("config/Configuration.properties");
            prop.load(input);

            String varibletofind1 = prop.getProperty(value);
            Thread.sleep(2000);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElement(MobileBy.xpath(input1)).sendKeys(varibletofind1);

            System.out.println("User enter the text to field:    " + input1 + "    " + varibletofind1);
            input.close();
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @And("^Android get snapshot and save to location in config \"(.*?)\" with the value \"(.*?)\"$")
    public void Android_get_snapshot_and_save_to(String input, String value) throws Throwable {
        //Random random = new Random();
        //int index = random.nextInt(10000);
        //String Randnumber = Integer.toString(index);

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream inputfile = loader.getResourceAsStream("config/Configuration.properties");
        prop.load(inputfile);

        String urlpath = prop.getProperty(input);
        urlpath = urlpath + value + ".png";

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("srcFile" + scrFile + value + urlpath);
        try {
            FileUtils.copyFile(scrFile, new File(urlpath));
        } catch (IOException e) {
            inputfile.close();
            e.printStackTrace();
        }
        inputfile.close();
    }

    @And("^Android_swipe down$")
    public void Android_swipe_down() throws Throwable {

        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        action.press(PointOption.point(810, 1725));
        action.moveTo(PointOption.point(810, 200));
        action.release();
        action.perform();

    }

    @And("^User clear the data from textbox Byxpath \"(.*?)\"$")
    public void user_clear_the_data_from_textbox_byxpath(String input) throws Throwable {

        driver.findElement(By.xpath(input)).clear();
        Thread.sleep(1000);
    }

    @And("^User clear the data from textbox classname \"(.*?)\"$")
    public void user_clear_the_data_from_textbox_classname(String input) throws Throwable {

        driver.findElement(By.className(input)).clear();
        Thread.sleep(1000);
    }
//Mobile  Functions   End
//Mobile  Functions   End
//Mobile  Functions   End
//Mobile  Functions   End


    @And("^if the checkbox is not check check it by xpath \"(.*?)\"$")
    public void if_the_checkbox_is_not_check_check_it_by_xpath(String input) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(input)));
        if (!driver.findElement(By.xpath(input)).isSelected()) {
            Thread.sleep(1000);
            driver.findElement(By.xpath(input)).click();
        }
    }

    @And("^if the checkbox is checked then uncheck it by xpath \"(.*?)\"$")
    public void if_the_checkbox_is_checked_then_uncheck_it_by_xpath(String input) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(input)));
        if (driver.findElement(By.xpath(input)).isSelected()) {
            Thread.sleep(1000);
            driver.findElement(By.xpath(input)).click();
            driver.findElement(By.xpath(input)).clear();
        }
    }

    @Given("^User navigate to a config \"(.*?)\" Page$")
    public void user_navigate_to_a_config_url_file_Page(String input2) throws Throwable {

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("config/Configuration.properties");
            prop.load(input);

            System.out.println(prop.getProperty(input2));
            String urlpath = prop.getProperty(input2);

            driver.get(urlpath);
            Thread.sleep(2000);
            driver.navigate().refresh();

            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @When("^I wait for \"(.*?)\" milliseconds$")
    public void i_wait_for_x_milliseconds(String milliseconds) {
        try {
            Thread.sleep(Integer.parseInt(milliseconds));

        } catch (Exception ignored) {
        }
    }

    @And("^Move the driver to iframe num \"(.*?)\" name of iframe \"(.*?)\"$")
    public void move_the_driver_to_iframe_num(int value, String input1) throws Throwable {

        //List<WebElement> iframeElement = driver.findElements(By.tagName(input1));
        driver.findElement(By.tagName(input1));
        driver.switchTo().frame(value);

        //System.out.println(" The total number of iframes are:   " + iframeElements.size());
    }

    @And("^find how many iframe on page put the correct name of iframe \"(.*?)\"$")
    public void the_user_find_iframe(String input1) throws Throwable {

        int iframenum;

        List<WebElement> iframeElements = driver.findElements(By.tagName(input1));

        Thread.sleep(1000);
        iframenum = iframeElements.size();
        Thread.sleep(1000);
        System.out.println("The total number of iframes are " + iframenum);

    }

    @And("^Users press the button by className \"(.*?)\"$")
    public void users_press_the_button_by_classname(String input) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.className(input)));
        driver.findElement(By.className(input)).click();
        Thread.sleep(1000);

        System.out.println("Users press the button by className:    " + input + " ");
    }

    @And("^User enter the saved random value to field by css \"(.*?)\"$")
    public void User_enter_the_saved_random_value_to_field_by_css(String input) throws Throwable {

        driver.findElement(By.cssSelector(input)).sendKeys(User_name_contact);
        System.out.println("the saved random value is  : " + User_name_contact);

    }

    @And("^User enter the text to field by css \"(.*?)\" with the value from config \"(.*?)\"$")
    public void user_enter_the_text_to_field_by_css_with_the_value_from_config(String input, String value1) {

        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input33 = loader.getResourceAsStream("config/Configuration.properties");
            prop.load(input33);

            String varibletofind2 = prop.getProperty(value1);
            System.out.println("The value is" + varibletofind2);

            driver.findElement(By.cssSelector(input)).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(input)).sendKeys(varibletofind2);

            input33.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^User Send the Keyboard value \"(.*?)\"$")
    public void user_send_the_Keyboard_value(String input) throws Throwable {
        // ((AndroidDriver<?>) driver).getKeyboard();
        // ((AndroidDriver<?>) driver).findElementByXPath().click();

        Actions action = new Actions(driver);
        action.sendKeys(input).perform();

//Keyboard keyboard = ((HasInputDevices) driver).getKeyboard();
//keyboard.sendKeys(input);
//System.out.println("User Sends the KeyboardValue:  " + input);

//.SendKeys(Keys.Enter);
    }


    @And("^User copy the selected text$")
    public void user_send_keyboard_key_copy() throws Throwable {

        Actions action = new Actions(driver);
        action = new Actions(driver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("c");
        action.keyUp(Keys.CONTROL);
        action.build().perform(); // copy is performed
    }

    @And("^User paste the selected text$")
    public void user_send_keyboard_key_paste() throws Throwable {

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("v");
        action.keyUp(Keys.CONTROL);
        action.build().perform(); // paste is performed
    }

    @And("^User paste the text \"(.*?)\"$")
    public void user_paste_the_text(String input) throws Throwable {

        StringSelection str = new StringSelection(input);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL);
        action.sendKeys("v");
        action.keyUp(Keys.CONTROL);
        action.build().perform(); // paste is performed
    }

    @And("^User Send keyboard key Ctrl A$")
    public void user_send_keyboard_key_Ctrl_A() throws Throwable {

        Actions builder = new Actions(driver);
        builder.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).perform();
        //builder.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        builder.release().perform();
    }

    @And("^User Send keyboard Delete$")
    public void user_send_keyboard_Delete() throws Throwable {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.DELETE).build().perform();
        builder.release().perform();

    }

    @And("^User Send keyboard key Arrow Down$")
    public void user_send_keyboard_key_ARROW_DOWN() throws Throwable {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_DOWN).build().perform();
        builder.release().perform();
    }

    @And("^User Send keyboard key Tab$")
    public void user_send_keyboard_key_tab() throws Throwable {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.TAB).build().perform();
        builder.release().perform();
    }

    @And("^Choose file to upload \"(.*?)\" to xpath \"(.*?)\"$")
    public void choose_file_to_upload(String filepath, String xpath) throws Throwable {
        WebElement chooseFile = driver.findElement(By.xpath(xpath));
        chooseFile.sendKeys(filepath);
    }

    @And("^User press browser refresh$")
    public void User_press_browser_refresh() throws Throwable {
        driver.navigate().refresh();
    }

    @And("^User Send keyboard key space$")
    public void user_send_keyboard_key_space() throws Throwable {

        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.SPACE).build().perform();
        builder.release().perform();
    }

    @And("^User Send keyboard key Enter$")
    public void user_send_keyboard_key_enter() throws Throwable {

        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ENTER).build().perform();
        builder.release().perform();
    }

    @And("^User Send keyboard key Esc$")
    public void user_send_keyboard_key_Escap() throws Throwable {

        //Actions action = new Actions(driver);
        //action.sendKeys(Keys.ESCAPE).build().perform();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE);
    }

    @And("^Users press the combobutton by xpath \"(.*?)\" and value \"(.*?)\"$")
    public void users_press_the_combobutton_by_xpath_and_value(String input, String value1) throws Throwable {
        driver.findElement(By.xpath(input)).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(value1)).click();
//System.out.println("Users press the button by cssselector: "+ input +" ");
    }

    @And("^Users press the combobox by xpath \"(.*?)\" and value by text \"(.*?)\"$")
    public void users_press_the_combobox_by_xpath_and_value(String input, String value1) throws Throwable {
        String toSearch = "//*[text()[contains(.," + value1 + ")]]";
        driver.findElement(By.xpath(input)).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(toSearch)).click();
    }

    @And("^Users press the combobox by xpath \"(.*?)\" and value by xpath \"(.*?)\"$")
    public void users_press_the_combobox_by_xpath_and_value_by_xpath(String input, String value1) throws Throwable {
        driver.findElement(By.xpath(input)).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath(value1)).click();
    }

    @And("^Users press the button by css \"(.*?)\"$")
    public void users_press_the_button_by_csss(String input) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(input)));
        driver.findElements(By.cssSelector(input)).get(0).click();
        System.out.println("Users press the button:    " + input + " ");

    }

    @And("^User enter the text to field by classname \"(.*?)\" With the Value \"(.*?)\"$")
    public void user_enter_the_text_to_field_by_className_with_the_value(String input, String value1) throws Throwable {

        driver.findElement(By.className(input)).click();
        Thread.sleep(1000);
        driver.findElement(By.className(input)).sendKeys(value1);
        System.out.println("User enter the text to field by classname :    " + input + "with the value: " + value1);

    }

    @And("^User enter the text to field css \"(.*?)\" with the value \"(.*?)\"$")
    public void user_enter_the_text_to_field_with_the_value_css(String input, String value1) throws Throwable {

        driver.findElement(By.cssSelector(input)).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(input)).sendKeys(value1);
//System.out.println("User enter the text to field : "+ input +"with the value:
//"+ value1);
    }

    @And("^User enter random email to field \"(.*?)\"$")
    public void user_enter_random_email_to_field(String input) throws Throwable {

        Random random = new Random();
        int index = random.nextInt(10000000);
        Current_Randnumber = Integer.toString(index);
        RandnumberMail = "comply" + Current_Randnumber + "@mailinator.com";

        driver.findElement(By.cssSelector(input)).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(input)).sendKeys(RandnumberMail);
        System.out.println("User enter random email : " + RandnumberMail);

    }

    @And("^User enter random email to field by xpath \"(.*?)\"$")
    public void user_enter_random_email_to_field_byxpath(String input) throws Throwable {

        Random random = new Random();
        int index = random.nextInt(10000000);
        Current_Randnumber = Integer.toString(index);
        RandnumberMail = "comply" + Current_Randnumber + "@mailinator.com";

        driver.findElement(By.xpath(input)).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(input)).sendKeys(RandnumberMail + "\n");
        System.out.println("User enter random email : " + RandnumberMail);
    }

    @And("^User paste the random Keyboard value$")
    public void user_paste_the_random_Keyboard_value() throws Throwable {

        Random random = new Random();
        int index = random.nextInt(10000000);
        String Randnumber = Integer.toString(index);
        Randnumber = "Auto" + Randnumber;

        Actions action = new Actions(driver);
        action.sendKeys(Randnumber).perform();
    }

    @And("^User enter the text to fieldxpath \"(.*?)\" with the value \"(.*?)\"$")
    public void user_enter_the_text_to_fieldxpath_with_the_value(String input, String value1) throws Throwable {

//driver.findElement(By.cssSelector(input)).sendKeys(value1);
        driver.findElement(By.xpath(input)).sendKeys(value1);
        System.out.println("User enter the text to field :    " + input + "with the value: " + value1);
    }

    @And("^User enters a random number to field by css \"(.*?)\" With random string$")
    public void User_enters_random_number_to_field_by_css_with_the_value(String input) throws Throwable {

        String uuid = UUID.randomUUID().toString().substring(1, 5);
        String uuid2 = UUID.randomUUID().toString().substring(1, 5);
        String uuid3 = uuid + " " + uuid2;

        driver.findElement(By.cssSelector(input)).sendKeys(uuid3);
        System.out.println("User sendKeys:   " + uuid3);
    }

    @And("^User enters a random number to field by xpath \"(.*?)\"$")
    public void User_enters_random_number_to_field_by_xpath(String input) throws Throwable {

        Random random = new Random();
        int index = random.nextInt(10000000);
        String Randnumber = Integer.toString(index);

        driver.findElement(By.xpath(input)).sendKeys(Randnumber);
        System.out.println("User sendKeys:   " + Randnumber);
    }

    @And("^Users press the enter key on \"(.*?)\"$")
    public void users_press_the_enter_key(String input) throws Throwable {
        driver.findElement(By.cssSelector(input)).sendKeys(Keys.RETURN);
        Thread.sleep(1000);
        System.out.println("Users press the enter key on:  " + input + " ");
    }

    @And("^User press the PageDown$")
    public void user_press_the_pagedown() throws Throwable {

        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.PAGE_DOWN).build().perform();
        builder.release().perform();
    }

    @And("^User press the keyDown$")
    public void user_press_key_down() throws Throwable {

        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.DOWN).build().perform();
        builder.release().perform();
    }

    @And("^User press the browser maximizer$")
    public void user_press_the_browser_maximizer() throws Throwable {
        driver.manage().window().maximize();
    }

    @And("^User Report \"(.*?)\"$")
    public void User_Report(String input) throws Throwable {
        System.out.println("User Report:    " + input);
        //System.out.println(driver.getPageSource());
    }

    @And("^user enter value to text field by xpath \"(.*?)\" to field \"(.*?)\"$")
    public void userEntervalueFromTextbyXpath(String value, String input) throws Throwable {

        driver.findElement(By.xpath(input)).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(input)).sendKeys(value);
        Thread.sleep(1000);
    }

    //// InnerText
    @Then("^I validate the input Text by CssSelector InnerText \"(.*?)\" With the Value \"(.*?)\"$")
    public void i_validate_the_input_by_CssSelector_innertext_With_the_value(String input, String value1)
            throws Throwable {

        WebElement element = driver.findElement(By.cssSelector(input));
        String val = element.getAttribute("innerText");

        System.out.println("Validating A value on the page:    " + val + " ");

        if (!val.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }

    }

    @Then("^I get the input Text by xpath InnerText \"(.*?)\"$")
    public void I_get_the_input_by_xpath_InnerText(String input) throws Throwable {
        String val = driver.findElement(By.xpath(input)).getText();
        System.out.println("Validating A value on the page:    " + val + " ");
    }

    //  get Atribute is good for this condition: "   value="gadi+4@ddd.co">   "
//  input aria-invalid="false" autocomplete="off" class="jss623 jss618 jss791" disabled="" name="email" type="text" data-test-id="users-settings-username" value="gadi+4@ssss.co">
    @Then("^I validate the input Text by ID \"(.*?)\" With the Value \"(.*?)\"$")
    public void i_validate_the_input_by_id_on_the_page(String input, String value1) throws Throwable {

        String heading_text = driver.findElement(By.id(input)).getAttribute("value");
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    @Then("^I validate the input Text by CSS \"(.*?)\" get Atribute \"(.*?)\" With the Value \"(.*?)\"$")
    public void i_validate_the_input_by_CssSelector_get_Atribute_with_the_value(String input, String value1, String value2)
            throws Throwable {

        String heading_text = driver.findElement(By.cssSelector(input)).getAttribute(value1);
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value2)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

//getText is good for this condition: "
//data-test-id="account-setting-plan-name">essential</h3>"
//<h3 class="jss151 jss158 jss509"
//data-test-id="account-setting-plan-name">essential</h3>

    @Then("^I validate the input Text by CssSelector getText \"(.*?)\" With the Value \"(.*?)\"$")
    public void i_validate_the_input_by_CssSelector_getText_With_the_value(String input, String value1)
            throws Throwable {

        String heading_text = driver.findElement(By.cssSelector(input)).getText();
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    @Then("^I validate the input Text by className \"(.*?)\" With the Value \"(.*?)\"$")
    public void i_validate_the_input_by_className_on_the_page(String input, String value1) throws Throwable {

        String heading_text = driver.findElement(By.className(input)).getAttribute("title");
        //String heading_text = driver.findElement(By.cssSelector(input)).getText();
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    @Then("^I validate the input by cssse \"(.*?)\"$")
    public void i_validate_the_input_by_cssse(String input) throws Throwable {

        int count = driver.findElements(By.cssSelector(input)).size();
        if (count == 1) {
            System.out.println("Element found using text");
        } else {
            System.out.println("Element not found");
            throw new IllegalStateException("The validation has failed");
        }
    }

    @Then("^I validate the text by xpath \"(.*?)\" that the Value is \"(.*?)\"$")
    public void i_validate_the_text_by_xpath_and_the_value_is(String input, String value1) throws Throwable {

        String heading_text = driver.findElement(By.xpath(input)).getText();
        //String heading_text = driver.findElement(By.xpath(input)).getAttribute("value");
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    @Then("^I validate the text by xpath \"(.*?)\" that the Value contains \"(.*?)\"$")
    public void i_validate_the_text_by_xpath_and_the_value_contains(String input, String value1) throws Throwable {

        WebElement el = waitForElementAndReturnHim(input, 10);
        // WebDriverWait wait = new WebDriverWait(driver, 30);
        // wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(input))));

        String heading_text = el.getText(); //driver.findElement(By.xpath(input)).getText();
        //String heading_text = driver.findElement(By.xpath(input)).getAttribute("value");
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.contains(value1)) {
            throw new IllegalStateException("The validation has failed");
        }
    }

    private WebElement waitForElementAndReturnHim(String input, int seconds) {
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(input))));
            element = driver.findElement(By.xpath(input));
        }catch (Exception e){
            return null;
        }
        return element;
    }

    @Then("^Go through Privacy Statement by sign checkBox xpath \"(.*?)\" and click Continue button by xpath \"(.*?)\"$")
    public void skipOptIn(String input1, String input2) {
        WebElement checkBox = waitForElementAndReturnHim(input1, 10);
        if (checkBox == null) {
            return;
        }
        checkBox.click();
        WebElement continueButton = waitForElementAndReturnHim(input2, 10);
        continueButton.click();
    }


    @Then("^Android scroll to text \"(.*?)\" and Validate by xpath \"(.*?)\"$")
    public void android_scroll_to_textv_and_validate_byxpath(String value, String input) throws Throwable {

        ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                        + value + "\").instance(0))");

        String heading_text = driver.findElement(By.xpath(input)).getText();
        System.out.println("Validating A value on the page:    " + heading_text + " ");

        if (!heading_text.equals(value)) {
            throw new IllegalStateException("The validation has failed");
        }

    }

    @Then("^Android Validate the text exists on page \"(.*?)\"$")
    public void android_validate_the_text_exists_on_page(String input) throws Throwable {

        try {
            ((AndroidDriver<?>) driver).findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
                            + input + "\").instance(0))");

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Element not found using text");
            e.printStackTrace();
        }
    }

    @And("^Enter the otp to validation field by xpath \"(.*?)\"$")
    public void Enter_the_otp_to_validation_field_by_xpath(String input) throws Throwable {
        // Enter the otp to validation field
        driver.findElement(By.xpath(input)).click();
        Thread.sleep(3000);

        //driver.findElement(MobileBy.xpath(input1)).sendKeys(value);
        driver.findElement(By.xpath(input)).sendKeys(otpass);
        System.out.println("User enter the otp : " + otpass);

        //String clipb=((AndroidDriver<?>) driver).getClipboardText();
        //System.out.println("getClipboardText    : " + clipb);

    }

    @And("^Reverse the order of OTP And past it to field by xpath \"(.*?)\"$")
    public void reverse_the_order_of_otp_and_send_it(String input) throws Throwable {
        StringBuffer sbfr = new StringBuffer(otpass);
        sbfr.reverse();
        driver.findElement(MobileBy.xpath(input)).sendKeys(sbfr);
        System.out.println("Reverse otp : " + sbfr + "    " + otpass);
    }

    @And("^scroll down$")
    public void Scroll_down() throws Throwable {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
    }

    @And("^scroll Up$")
    public void Scroll_Up() throws Throwable {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
    }

    @And("^Scroll down page$")
    public void Scroll_down_page() throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @And("^Scroll down and press the object by css \"(.*?)\"$")
    public void Scroll_down_and_press_the_object_by_css(String input) throws Throwable {

        WebElement element = driver.findElement(By.cssSelector(input));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", element);
        Actions action = new Actions(driver);
        action.click(element).build().perform();
        //driver.findElement(MobileBy.cssSelector(input)).click();
    }

    @And("^Open Chrome get OTP or PassCode \"(.*?)\"$")
    public void Open_Chrome_browser_get_otp_or_passcode(String value) throws Throwable {
        String mailAddress = "";
        otpass = "";

        // Open chrome browser in Headless mode  and bring by param OTP or PassCode please state:value=OTP or value=PassCode
        System.setProperty("webdriver.chrome.driver",
                "/Users/qualitest/Documents/ChromeDriver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--silent");
        WebDriver driver2 = new ChromeDriver(options);

        //WebDriver driver2 = new ChromeDriver();
        driver2.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
        driver2.manage().window().maximize();

        if (!value.equals("PassCode")) {
            // Preper Mailinator address
            mailAddress = "https://www.mailinator.com/v4/public/inboxes.jsp?to=comply" + Current_Randnumber;
            System.out.println("mail Address is: " + mailAddress);

        } else {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("config/Configuration.properties");
            prop.load(input);
            System.out.println(prop.getProperty("Reset_pass_user"));
            String Reset_pass_user = prop.getProperty("Reset_pass_user");

            // Preper Mailinator address
            mailAddress = "https://www.mailinator.com/v4/public/inboxes.jsp?to=" + Reset_pass_user;
            System.out.println("mail Address is: " + mailAddress);
            input.close();
        }

        //  lunch the mailinator address
        driver2.get(mailAddress);
        Thread.sleep(2000);

        //   Open the First mail in mailinator with the Otp code
        driver2.findElement(By.cssSelector("tbody>tr:nth-child(1)[ng-repeat='email in emails']")).click();
        Thread.sleep(2000);
        //driver.switchTo().frame(driver.findElement(By.id("texthtml_msg_body")));

        int iframenum;
        List<WebElement> iframeElements = driver2.findElements(By.tagName("iframe"));
        Thread.sleep(1000);
        iframenum = iframeElements.size();
        System.out.println("The total number of iframes are " + iframenum);
        driver2.switchTo().frame(0);
        Thread.sleep(2000);


        if (!value.equals("PassCode")) {
            String heading_text = (driver2.findElement(By.cssSelector("body,iframe>#document>html>body")).getAttribute("innerText"));

            otpass = CharMatcher.inRange('0', '9').retainFrom(heading_text);
            System.out.println("Otp is: " + otpass);

        } else {
            String heading_text = (driver2.findElement(By.cssSelector("body,iframe>#document>html>body")).getAttribute("innerText"));
            String[] arrOfStr = heading_text.split("\n");
            System.out.println("otpass is:  " + arrOfStr[4]);
            otpass = arrOfStr[4];

        }
        driver2.close();
    }

    @And("^Update config file for key \"(.*?)\" with value \"(.*?)\"$")
    public void update_config_value(String Key, String Value1) throws Throwable {

        //PropertiesConfiguration config = new PropertiesConfiguration("src/main/resources/config/Configuration.properties");
        // config.setProperty(Key, Value1);
        // config.save();
        FileInputStream in = new FileInputStream("src/main/resources/config/Configuration.properties");
        Properties prop = new Properties();
        prop.load(in);
        in.close();

        OutputStream output = new FileOutputStream("src/main/resources/config/Configuration.properties");

        prop.setProperty(Key, "" + Value1);
        // save properties to project root folder
        prop.store(output, null);
        output.close();

    }

    @And("^Update config for key \"(.*?)\" with value RandnumberMail$")
    public void update_config_for_key_value_RandnumberMail(String Key) throws Throwable {

        FileInputStream in = new FileInputStream("src/main/resources/config/Configuration.properties");
        Properties prop = new Properties();
        prop.load(in);
        Thread.sleep(3000);
        in.close();

        OutputStream output = new FileOutputStream("src/main/resources/config/Configuration.properties");
        Thread.sleep(3000);
        prop.setProperty(Key, "" + RandnumberMail);
        // save properties to project root folder
        prop.store(output, null);
        Thread.sleep(3000);
        output.close();
    }

    @And("^Check If the app still Runs \"(.*?)\"$")
    public void Check_if_the_apps_still_runs(String Value) throws Throwable {
        ///   Call this function with -And or Ios

        boolean flag = false;
        while (!flag == true) {
            Thread.sleep(3000);

            if (!Value.equals("And")) {
                String Results = String.valueOf(((IOSDriver<?>) driver).queryAppState("org.LumiiMG.qa"));
                Thread.sleep(3000);
                System.out.println("The App is :  " + Results);
                if (!Results.equals("RUNNING_IN_FOREGROUND")) {
                    //((IOSDriver<?>) driver).resetApp();
                    //((IOSDriver<?>) driver).closeApp();
                    ((IOSDriver<?>) driver).launchApp();
                    Thread.sleep(3000);
                    String heading_text = driver.findElement(By.xpath("//UIAStaticText[contains(@value,\"Welcome to LumiiMG\")]")).getText();
                    System.out.println("Validating A value on the page:    " + heading_text + " ");
                    if (!heading_text.equals("Welcome to LumiiMG")) {
                        flag = false;
                        ((IOSDriver<?>) driver).resetApp();
                        ((IOSDriver<?>) driver).closeApp();
                        ((IOSDriver<?>) driver).launchApp();
                        Thread.sleep(3000);
                    } else {
                        flag = true;
                    }

                }
            } else {
                String Results2 = String.valueOf(((AndroidDriver<?>) driver).queryAppState("com.lumiimg.qa"));
                Thread.sleep(3000);
                System.out.println("The App is :  " + Results2);
                if (!Results2.equals("RUNNING_IN_FOREGROUND")) {
                    //((AndroidDriver<?>) driver).resetApp();
                    //((AndroidDriver<?>) driver).closeApp();
                    ((AndroidDriver<?>) driver).launchApp();
                    Thread.sleep(3000);
                    String heading_text = driver.findElement(By.xpath("//*[contains(@text,'Welcome to LumiiMG')]")).getText();
                    System.out.println("Validating A value on the page:    " + heading_text + " ");
                    if (!heading_text.equals("Welcome to LumiiMG")) {
                        flag = false;
                        ((AndroidDriver<?>) driver).resetApp();
                        ((AndroidDriver<?>) driver).closeApp();
                        ((AndroidDriver<?>) driver).launchApp();

                    } else {
                        flag = true;
                    }
                } else {
                    flag = true;
                }
            }
        }
    }

    @And("^Android Turn off wifi connection$")
    public void Android_Turn_off_wifi_connection() throws Throwable {
        ConnectionState state = ((AndroidDriver<?>) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
        Assert.assertFalse(state.isWiFiEnabled());
        System.out.println("WiFi turned off");
    }

    @And("^Android Turn ON wifi connection$")
    public void Android_Turn_on_wifi_connection() throws Throwable {
        ConnectionState state = ((AndroidDriver<?>) driver).setConnection(new ConnectionStateBuilder().withWiFiEnabled().build());
        Assert.assertTrue(state.isWiFiEnabled());
        System.out.println("WiFi turned ON");
    }

    @And("^Android Turn ON Camera$")
    public void Android_Turn_on_camera() throws Throwable {
        //((AndroidDriver<?>) driver).startRecordingScreen(new AndroidStartScreenRecordingOptions().enableForcedRestart());  //.withTimeLimit(Duration.ofHours(2)));
        ((AndroidDriver<?>) driver).startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        // .withTimeLimit(Duration.ofHours(1))
                        //  .withBitRate(500000) // 500k/s
                        .withVideoSize("1080x2280")
                        .enableForcedRestart()
        );
    }

    @And("^Android Turn OFF Camera \"(.*?)\"$")
    public void Android_Turn_OFF_camera(String fileName) throws Throwable {

        String result = ((AndroidDriver<?>) driver).stopRecordingScreen();
        byte[] data = Base64.getDecoder().decode(result);
        String destinationPath = "target/Android_Clip/" + fileName + ".mp4";

        Path path = Paths.get(destinationPath);
        Files.write(path, data);

    }

    @And("^IOS Turn ON Camera$")
    public void IOS_Turn_on_camera() throws Throwable {
        //  ((IOSDriver<?>) driver).startRecordingScreen(new IOSStartScreenRecordingOptions()
        //          .withVideoQuality(IOSStartScreenRecordingOptions.VideoQuality.LOW)
        //          .withVideoType("H264")
        //          .enableForcedRestart()
        //  );
        /// 'ffmpeg' binary is not found in PATH. Install it using 'brew install ffmpeg'.
        BaseStartScreenRecordingOptions options;
        options = new IOSStartScreenRecordingOptions();
        ((IOSDriver<?>) driver).startRecordingScreen(options);

    }

    @And("^IOS Turn OFF Camera \"(.*?)\"$")
    public void IOS_Turn_OFF_camera(String fileName) throws Throwable {

        String result = ((IOSDriver<?>) driver).stopRecordingScreen();
        byte[] data = Base64.getDecoder().decode(result);
        String destinationPath = "target/A_IOS_Clip/" + fileName + ".mp4";

        Path path = Paths.get(destinationPath);
        Files.write(path, data);
    }


    @And("^open excel file from config \"(.*?)\"$")
    public void open_excel_file_from_config(String input2) throws Throwable {
        // Open config file and take the path to Excel file

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("config/Configuration.properties");
        prop.load(input);
        // System.out.println(prop.getProperty(input2));
        ExcelFilepath = prop.getProperty(input2);

        assert input != null;
        input.close();

    }


    @And("^Get value from Row num \"(.*?)\" and Cell num \"(.*?)\"$")
    public void get_value_from_Row_And_Cell(int input1, int input2) throws Throwable {

        //Create an object of File class to open xlsx file
        File file = new File(ExcelFilepath);
        //Create an object of FileInputStream class to read excel file
        //FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(input1);
        Cell cell = row.getCell(input2);

        //System.out.println(cell);
        System.out.println(sheet.getRow(input1).getCell(input2));
        //String cellval = cell.getStringCellValue();
        //System.out.println(cellval);
        //System.out.print(row.getCell(input2).getStringCellValue());
        workbook.close();
    }

    @And("^Get value in loop from Row num \"(.*?)\" and Cell num \"(.*?)\"$")
    public void get_value_in_loop_from_Row_And_Cell(int input1, int input2) throws Throwable {

        //Create an object of File class to open xlsx file
        File file = new File(ExcelFilepath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int i = input1;
        Row row = sheet.getRow(i);

        for (int j = 0; j <= input2; j++) {
            System.out.println(sheet.getRow(i).getCell(j).getStringCellValue() + ",");
        }

        workbook.close();
    }

    @And("^I get value from excel for \"(.*?)\"$")
    public void I_get_value_from_excel_by(String input1) throws Throwable {
        Boolean Flag = false;
        File file = new File(ExcelFilepath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            if (Flag.booleanValue())
                break;
            for (Cell cell : row) {
                if (Flag.booleanValue())
                    break;

                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                // System.out.print(cellRef.formatAsString());
                // System.out.print(" - ");
                // get the text that appears in the cell by getting the cell value and applying
                // any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                String text = formatter.formatCellValue(cell);
                // System.out.println(text);
                if (text.equalsIgnoreCase(input1)) {
                    int i = row.getRowNum();
                    Flag = true;
                    row = sheet.getRow(i);
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        String Cell_Value = formatter.formatCellValue(row.getCell(j));
                        Excel_Content[j] = Cell_Value;
//                System.out.print(Excel_Content[j] + " ,  ");
                        // System.out.print(Cell_Value + " , ");
                    }
                }
            }
        }
        if (Flag != true) {
            System.out.print("The Search data was not found");
            assert false : "Error: " + error_msg;
        }
        workbook.close();
    }

    @And("^Reverse the order of string \"(.*?)\" And past it to field by xpath \"(.*?)\"$")
    public void reverse_the_order_of_otp_and_send_it(String input1, String input2) throws Throwable {
        StringBuffer sbfr = new StringBuffer(input1);
        sbfr.reverse();
        driver.findElement(MobileBy.xpath(input2)).sendKeys(sbfr);
        System.out.println("Reverse string : " + sbfr + "    " + input1);
    }

    @Then("^The user press accept on Allert message$")
    public void the_user_press_accept_on_Allert_message() throws Throwable {

        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.alertIsPresent());
            boolean hasAlert = ExpectedConditions.alertIsPresent().apply(driver) != null;
            if (hasAlert) {
                driver.switchTo().alert().accept();
            }
        } catch (NoAlertPresentException ex) {
            System.out.println("No Alert Present ");
        }
    }

    @Given("^Get user from DB and login$")
    public void getExistingStudentAndLogin() throws Exception {
        institutionId = prop.getProperty("institutionId");
        List<String[]> list = dbService.getUsers();
        String[] student = list.get(new Random().nextInt(list.size()-1));
        userName = student[2];
        password = student[13];
        WebElement nameField = waitForElementAndReturnHim("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText", 10);
        nameField.sendKeys(userName);
        WebElement passworField = waitForElementAndReturnHim("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText",10);
        passworField.sendKeys(password);
    }
}
