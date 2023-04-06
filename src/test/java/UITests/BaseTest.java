package UITests;

import UI.AdditionalLogger;
import UI.AuthPage;
import UI.JunitExtension;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.io.ByteArrayInputStream;

@Story(("Тестим GeekTest"))

public class BaseTest {
    WebDriver driver;
    AuthPage authPage;
    @RegisterExtension
    JunitExtension testWatcher =new JunitExtension();

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
    }

    @BeforeEach
    void  getPage() {
        driver = new EventFiringDecorator(new AdditionalLogger()).decorate(new ChromeDriver());
        authPage = new AuthPage(driver);
        driver.get("https://test-stand.gb.ru/");
    }

    @AfterEach
    void tearDown () {
        LogEntries logs = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logs){
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }
        testWatcher.setScreenshot(new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        driver.quit();
    }
}