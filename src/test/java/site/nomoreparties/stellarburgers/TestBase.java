package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public class TestBase {

    protected WebDriver driver;

    public void setupImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
// для теста в chrome
//          System.setProperty("webdriver.chrome.driver", "/Users/asafronov/Documents/yandexdriver");
//          driver = new ChromeDriver();
// для теста в yandex

        setupImplicitWait();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
