import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Test2 {

    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");

        capabilities.setCapability("app","C:\\Users\\FBI\\IdeaProjects\\ApiumTraining\\JavaAppiumAutomation\\apks\\org.wikipedia.apk"); //Дом
        //capabilities.setCapability("app","D:\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");  //Работа


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After


    public void tearDawn(){
        driver.quit();
    }

    @Test

    public void findSearch(){
//Жмём поиск
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can Not Find Serch Bar",
                5
        );
//Ждём стартовую картинку
        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Can not Find start image",
                5
        );
//Вводим слово в поиск
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Can not Find search input",
                5);
//Убеждаемся что появился список статей (если статьи найдены стартовая картинка исчезнет)
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Can Find start image",
                5
        );
//Отменяем поиск
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Search bar is not empty",
                5
        );
//После отмены видим стартовую картинку
        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "Can not Find start image",
                5
        );

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver,timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSecond){

        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSecond){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSecond){
        WebElement element = waitForElementPresent(by,error_message,timeoutInSecond);
        element.clear();
        return element;
    }
}
