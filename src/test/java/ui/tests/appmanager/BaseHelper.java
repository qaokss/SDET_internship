package ui.tests.appmanager;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class BaseHelper extends SessionHelper {


    // обходим ошибку "Element is not clickable..." для гугл хром
    protected static Actions safeClickToElement(By locator) {
        Actions actions = new Actions(wd);
        actions.pause(300).moveToElement(wd.findElement(locator)).click().perform();
        return actions;
    }

    protected static void type(By locator, String text) {
        safeClickToElement(locator);
        if (text != null) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }





//    protected void waitNewMessages() {
//        Wait<WebDriver> fluentWait = new FluentWait<>(wd)
//                .ignoring(StaleElementReferenceException.class)
//                .pollingEvery(Duration.of(500, MILLIS))
//                .withTimeout(Duration.of(10, SECONDS))
//                .withMessage("not found");
//
//        fluentWait.until(webDriver -> webDriver.findElement(MAILS_TABLE));
//    }
}
