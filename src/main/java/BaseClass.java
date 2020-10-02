import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static DesiredCapabilities desiredCapabilities;
    public static Properties pagesElementsProperties;
    public static Properties log4jProperties;
    public static FileInputStream fileInputStream;
    public static AppiumDriver appiumDriver;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelBaseClass excelBaseClass;


    @BeforeSuite
    public void applicationSetup() {
        log.debug("Application has been started!");
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "HUAWEI P20 Pro");
        desiredCapabilities.setCapability("udid", "WCR7N18B12001363");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "10");
        desiredCapabilities.setCapability("appPackage", "com.android.chrome");
        desiredCapabilities.setCapability("appActivity", "org.chromium.chrome.browser.document.ChromeLauncherActivity");
        desiredCapabilities.setCapability("unlockType", "pin");
        desiredCapabilities.setCapability("unlockKey", "910117");

        log.debug("Capabilities have been set!");

        pagesElementsProperties = new Properties();

        try {
            fileInputStream = new FileInputStream("C:\\Users\\Martin Lichev\\Downloads\\VerizonWirelessDataDrivenAppiumProject\\src\\test\\resources\\properties\\pagesElements.properties");

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        try {
            pagesElementsProperties.load(fileInputStream);
            log.debug("Pages Property File has been loaded!");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        log4jProperties = new Properties();

        try {
            fileInputStream = new FileInputStream("C:\\Users\\Martin Lichev\\Downloads\\VerizonWirelessDataDrivenAppiumProject\\src\\test\\resources\\properties\\log4j.properties");

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();

        }

        try {
            log4jProperties.load(fileInputStream);
            log.debug("Log4j Property File has been loaded!");

        } catch (IOException ioException) {
            ioException.printStackTrace();

        }

        appiumDriver = new AppiumDriver<MobileElement>(desiredCapabilities);
        appiumDriver.navigate().to(pagesElementsProperties.getProperty("homePageURL"));
        log.debug("Driver successfully navigated to: " + System.getProperty("homePageURL"));

    }

    @AfterSuite
    public void applicationTearDown() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }

        log.debug("Application exit successfully");

    }

    public void maximizeBrowser(AppiumDriver appiumDriver) {
        appiumDriver.manage().window().maximize();
    }

    public void implicitWait(AppiumDriver appiumDriver, long time, TimeUnit timeUnit) {
        appiumDriver.manage().timeouts().implicitlyWait(time, timeUnit);
    }

    public void explicitWait(AppiumDriver appiumDriver, long time, String locator) {
        Wait wait = new WebDriverWait(appiumDriver, time);

        if (locator.endsWith("_XPATH")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pagesElementsProperties.getProperty(locator))));

        } else if (locator.endsWith("_CSS")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(pagesElementsProperties.getProperty(locator))));

        } else if (locator.endsWith("_ID")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pagesElementsProperties.getProperty(locator))));

        }

    }

    public void fluentWait(AppiumDriver appiumDriver, long time, TimeUnit timeUnit) {
        Wait webDriverWait = new FluentWait<WebDriver>(appiumDriver);

        try {
            webDriverWait.wait(time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void click(AppiumDriver appiumDriver, String locator) {
        if (locator.endsWith("_XPATH")) {
            appiumDriver.findElement(By.xpath(pagesElementsProperties.getProperty(locator))).click();

        } else if (locator.endsWith("_CSS")) {
            appiumDriver.findElement(By.cssSelector(pagesElementsProperties.getProperty(locator))).click();

        } else if (locator.endsWith("_ID")) {
            appiumDriver.findElement(By.id(pagesElementsProperties.getProperty(locator))).click();

        }

    }

    public void sendKeys(AppiumDriver appiumDriver, String locator) {
        if (locator.endsWith("_XPATH")) {
            appiumDriver.findElement(By.xpath(pagesElementsProperties.getProperty(locator))).sendKeys();

        } else if (locator.endsWith("_CSS")) {
            appiumDriver.findElement(By.cssSelector(pagesElementsProperties.getProperty(locator))).sendKeys();

        } else if (locator.endsWith("_ID")) {
            appiumDriver.findElement(By.id(pagesElementsProperties.getProperty(locator))).sendKeys();

        } else if (locator.endsWith("_TAGNAME")) {
            appiumDriver.findElement(By.tagName(pagesElementsProperties.getProperty(locator))).sendKeys();

        }

    }

    public void dropDown(){

    }
}



