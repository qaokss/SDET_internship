package ui.tests.appmanager;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseHelper extends SessionHelper{


    protected void click(By locator) {
        this.wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        this.click(locator);
        if (text != null) {
            this.wd.findElement(locator).clear();
            this.wd.findElement(locator).sendKeys(text);
        }
    }

    protected int countMessages() {
        WebElement element = this.wd.findElement(By.id(":1"));
        List<WebElement> list = element.findElements(By.xpath("//span[@name='Simbirsoft theme']"));
        return list.size();
    }


}
