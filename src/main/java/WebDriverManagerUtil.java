import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManagerUtil {
    public static WebDriver getChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);

        // Use WebDriverManager to download and setup the Chrome driver
        WebDriverManager.chromedriver().setup();

        // Return a new instance of the Chrome driver
        return new ChromeDriver(options);
    }

}
