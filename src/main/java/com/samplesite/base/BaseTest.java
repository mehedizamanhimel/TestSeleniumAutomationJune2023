package com.samplesite.base;

import com.samplesite.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.get("browser").toLowerCase();
        logger.info("Launching browser: {}", browser);

        // Honour the CI HEADLESS env var — forces headless mode on CI runners
        boolean headless = "true".equalsIgnoreCase(System.getenv("HEADLESS"));

        switch (browser) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOpts = new FirefoxOptions();
                if (headless) {
                    ffOpts.addArguments("-headless");
                }
                driver = new FirefoxDriver(ffOpts);
                break;
            }
            case "chrome":
            default: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpts = new ChromeOptions();
                if (headless) {
                    // --headless=new is the modern flag (Chrome 112+)
                    chromeOpts.addArguments("--headless=new");
                }
                // Required on Linux CI runners regardless of headless mode
                chromeOpts.addArguments("--no-sandbox", "--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOpts);
                break;
            }
        }

        // Use explicit size instead of maximize() — safer on headless/CI runners
        driver.manage().window().setSize(new Dimension(1920, 1080));

        String baseUrl = ConfigReader.get("baseUrl");
        logger.info("Navigating to base URL: {}", baseUrl);
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing browser.");
            driver.quit();
        }
    }
}
