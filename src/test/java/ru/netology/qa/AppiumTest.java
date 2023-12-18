package ru.netology.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

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
        var originalText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        originalText.isDisplayed();
        var userInput = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.click();

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(551, 1703);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        var buttonChange = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        buttonChange.click();
        var finalText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        finalText.isDisplayed();

        Assertions.assertEquals(originalText.getText(), finalText.getText());
    }

    @Test
    public void changeActivity() {
        var userInput = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        userInput.click();

        final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        var tapPoint = new Point(1021, 1252);
        var tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), tapPoint.x, tapPoint.y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(50)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

        var buttonActivity = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        buttonActivity.click();
        var finalText = driver.findElementById("ru.netology.testing.uiautomator:id/text");
        finalText.isDisplayed();
        Assertions.assertEquals("p", finalText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
