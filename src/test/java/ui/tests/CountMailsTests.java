package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.tests.appmanager.BaseHelper;
import ui.tests.appmanager.SessionHelper;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountMailsTests extends BaseHelper {




    @Before
    public void setUp() {
        initChromeWebDriver();
    }

    @After
    public void tearDown() {
        wd.quit();

    }

    @Test
    public void countMassagesTest() {


        goTo().loginPage();


        // переключение на активную вкладку
        ArrayList<String> tabs2 = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs2.get(1));

        // ввод логина
        wd.findElement(By.id("identifierId")).sendKeys("test.box.for.sdet.internship@gmail.com");

        // далее
        wd.findElement(By.xpath("//button/div[2]")).click();

        // ввод пароля
        type(By.name("password"), "testbox1");


        wd.findElement(By.xpath("//button/div[2]")).click();

        // подтверждение в предупреждающем окне
        // wd.findElement(By.xpath("//div[2]/div/span/span"));


        // ищем кол-во писем с темой
        int countBeforeSendingLetter = countMessages();


        // пишем письмо самому себе
        wd.findElement(By.cssSelector("div.T-I.T-I-KE.L3")).click();
        wd.findElement(By.xpath("//textarea")).click();

        //
        type(By.name("to"), "test.box.for.sdet.internship@gmail.com");
        type(By.name("subjectbox"), "Simbirsoft theme");
        type(By.id(":ay"), "Найдено " + countBeforeSendingLetter + " писем");


        wd.findElement(By.id(":9j")).click();


        int countAfterSendingLetter = countMessages();

        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
