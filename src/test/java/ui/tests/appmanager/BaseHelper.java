package ui.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class BaseHelper extends SessionHelper {


    // обходим ошибку "Element is not clickable..."
    private Actions safeClickToElement(By locator) {
        Actions actions = new Actions(wd);
        actions.moveToElement(wd.findElement(locator)).click().perform();
        return actions;
    }


    protected void type(By locator, String text) {
        this.safeClickToElement(locator);
        if (text != null) {
            this.wd.findElement(locator).clear();
            this.wd.findElement(locator).sendKeys(text);
        }
    }


    /**
     * поиск всех писем на странице
     **/
    protected List<WebElement> findAllMessagesOnPage() {
        List<WebElement> messages = wd.findElements(By.cssSelector("table[role='grid'] tr[role='row']"));
        return messages;
    }



    protected long countMessagesWithTheme(String theme, List<WebElement> messages) {
        long lenth = messages.stream().filter(webElement -> webElement.getText().contains(theme)).count();
        return lenth;
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
        safeClickToElement(By.xpath("//button/span[text()='Далее']"));

        // ввод пароля
        type(By.name("password"), "testbox1");

        safeClickToElement(By.xpath("//button/span[text()='Далее']"));

    }




    /** пишем письмо самому себе **/
    protected void writingLetterToMyself(long countBeforeSendingLetter) {
        wd.findElement(By.xpath("//div[text()='Написать']")).click();

        type(By.name("to"), "test.box.for.sdet.internship@gmail.com");

        type(By.name("subjectbox"), "Simbirsoft theme");

        type(By.xpath("div[role='textbox']"), "Найдено " + countBeforeSendingLetter + " писем");

        wd.findElement(By.xpath("//div[text()='Отправить']")).click();
    }


}
