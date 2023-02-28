import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleSSLCertificate {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        WebDriver webdriver = new ChromeDriver(options);
        webdriver.get("www.google.com");
        System.out.println("This is the title of page: "+webdriver.getTitle());
        webdriver.quit();
    }
}
