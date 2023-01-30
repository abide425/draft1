package Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class DriverUtil {
    private static WebDriver driver;
    private static Properties prop;
    private static Actions ac;
    private static WebDriverWait wait;
    public static Select se;

    public static WebDriver getWebDriver() {
        //If the driver is already initialized, return the driver
        if (driver != null && !driver.toString().contains("(null)")) {
            return driver;
        }

        //If the driver is not initialized find the browser type and initialize the driver.
        String browser = initializeProperties().getProperty("browser");
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "chrome_headless":
                var chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1280,800");
                chromeOptions.addArguments("--allow-insecure-localhost");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "safari":
                driver = WebDriverManager.safaridriver().create();
                break;
            case "firefox":
            case "mozilla":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "ie":
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            case "edge_headless":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome_incognito":
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--incognito");
                DesiredCapabilities d = new DesiredCapabilities();
                d.setCapability(ChromeOptions.CAPABILITY, option);
                driver = new ChromeDriver(option.merge(d));
                break;
            default:
                throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser in the configuration file");
        }
        initiateVariables();
        return driver;
    }

    public static void initiateVariables(){
        ac = new Actions(driver);
        int max_wait_second = Integer.parseInt( prop.getProperty("MAX_WAIT") );
        wait = new WebDriverWait(driver, Duration.ofSeconds( max_wait_second ));
    }

    public static Properties initializeProperties() {
        if (prop != null) {
            System.out.println("prop is already initialized");
            return prop;
        }
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("project.properties");
            prop.load(ip);
        } catch (Exception e) {
            System.out.println("Exception occurred during config initialization. " + e.getMessage());
            Reporter.log("Failed to load properties file. Error: " + e.getMessage());
        }
        return prop;
    }

    public static void clickUsingJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void clickUsingJS(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElementAndClick(By selector) {
        ac.moveToElement(driver.findElement(selector)).click().build().perform();
    }

    public static void waitAndClick(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public static void waitAndClick(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }


    public static void zoomOutToPercentage(double percentage) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.body.style.zoom = '" + percentage + "'");
    }

    /* To ScrollUp using JavaScript Executor */
    public static void scrollUp() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, -100);");
    }

    /* To ScrollDown using JavaScript Executor */
    public static void scrollDown() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 3500);");
    }

    public static WebElement scrolltoElement(By locator) {
        WebElement el = driver.findElement(locator);
        ac.moveToElement(el).build().perform();
        return el;
    }

    public static WebElement scrolltoElement(WebElement element) {
        ac.moveToElement(element).build().perform();
        return element;
    }

    /* To Accept the Alert Dialog Message */
    public static void alertAccept() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    /* To Dismiss the Alert Dialog Message */
    public static void alertDismiss() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    /* To Get the Alert Messages */
    public static String getAlertText() {
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }

    /* To Perform a WebAction of Mouse Over */
    public static void mouseHover(WebElement element) {
        ac.moveToElement(element).build().perform();
    }

    public static void mouseHover(By locator) {
        mouseHover(driver.findElement(locator));
    }

    public static void selectByVisibleText(WebElement element, String value) {
        se = new Select(element);
        se.selectByVisibleText(value);
    }

    public static void selectByVisibleText(By selector, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(selector));
        se = new Select(driver.findElement(selector));
        se.selectByVisibleText(value);
    }

    public static void selectByIndex(WebElement element, int value) {
        se = new Select(element);
        se.selectByIndex(value);
    }

    public static void selectByIndex(By selector, int value) {
        se = new Select(driver.findElement(selector));
        se.selectByIndex(value);
    }

    public static void selectByValue(WebElement element, String value) {
        se = new Select(element);
        se.selectByValue(value);
    }

    public static void selectByValue(By selector, String value) {
        se = new Select(driver.findElement(selector));
        se.selectByValue(value);
    }



}
