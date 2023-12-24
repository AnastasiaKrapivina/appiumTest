package ru.netology.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

public class AppiumTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel 2 API 29");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");


        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void changeTest() {
        var textToSetOne = "   ";
        var textToBeChanged = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        textToBeChanged.isDisplayed();
        var originalText = textToBeChanged.getText();
        var userInput = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.sendKeys(textToSetOne);
        userInput.click();
        var buttonChange = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        buttonChange.click();
        var finalText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        finalText.isDisplayed();

        Assertions.assertEquals(originalText, finalText.getText());
    }

    @Test
    public void changeActivity() throws InterruptedException {
        var textToSetTwo = "Netology";
        var userInput = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.sendKeys(textToSetTwo);
        userInput.click();
        var buttonActivity = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        buttonActivity.click();
        Thread.sleep( 5000 );
        var finalText = driver.findElementById("ru.netology.testing.uiautomator:id/text");
        finalText.isDisplayed();
        Thread.sleep( 5000 );

        Assertions.assertEquals(textToSetTwo, finalText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
