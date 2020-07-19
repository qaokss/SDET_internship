package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseHelper {

    protected WebDriver wd;


    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }
}
