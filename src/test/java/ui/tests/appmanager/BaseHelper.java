package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui.tests.Models.Message;

import java.util.ArrayList;
import java.util.List;

public class BaseHelper extends SessionHelper {


    private void click(By locator) {
        this.wd.findElement(locator).click();
    }


    protected void type(By locator, String text) {
        this.click(locator);
        if (text != null) {
            this.wd.findElement(locator).clear();
            this.wd.findElement(locator).sendKeys(text);
        }
    }


    /**
     * поиск всех писем на странице
     **/
    protected long findAllMessagesOnPage() {
        List<WebElement> messages = wd.findElements(By.cssSelector("table[role='grid'] tr[role='row']"));
       long lenth = messages.stream().filter(webElement -> webElement.getText().contains("Simbirsoft theme")).count();

        return lenth;
    }



    protected int countMessagesWithTheme(String theme, List<WebElement> messages) {
        return 1;
    }


    /**
     * логинимся с корректным логином и паролем
     **/
    protected void loginWithCorrectLoginAndPassword() {
        // переключение на активную вкладку
        ArrayList<String> tabs2 = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs2.get(1));

        // ввод логина
        wd.findElement(By.id("identifierId")).sendKeys("test.box.for.sdet.internship@gmail.com");

        // далее
        WebElement acceptLogin = wd.findElement(By.xpath("//button/span[text()='Далее']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(acceptLogin).click().perform();

        // ввод пароля
        type(By.name("password"), "testbox1");

        // обходим ошибку "Element is not clickable..." с помощью JavascriptExecutor
        WebElement acceptPassword = wd.findElement(By.xpath("//button/span[text()='Далее']"));
        actions.moveToElement(acceptPassword).click().perform();
    }
}
