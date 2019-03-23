import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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
import java.util.ArrayList;
import java.util.List;

public class Test2 {

    private String magickWord = "Java";
    private String magickWordMini = "java";

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

    public void findSearch() {
//Жмём поиск
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Can Not Find Serch Bar",
                5
        );

//Вводим слово в поиск
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                magickWord,
                "Can not Find search input",
                5);

//Заполняем List элементами на эккране и проверяем наличие элемента поиска
        List<WebElement> elements =driver.findElementsById("org.wikipedia:id/page_list_item_title");
        for (WebElement webElement : elements) {
            System.out.println(webElement.getText());

            Assert.assertTrue(
                    "Not all state include word "+magickWord,
                    webElement.getText().matches(magickWord+".+?")
                            || webElement.getText().matches(magickWordMini+".+?")
                            || webElement.getText().matches(magickWord)
                            || webElement.getText().matches(magickWordMini));
        }




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
