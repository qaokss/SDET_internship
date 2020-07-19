package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.tests.appmanager.SessionHelper;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CountMailsTests {

    protected final SessionHelper app = new SessionHelper();
    private WebDriver wd;
    private String baseUrl;

    @Before
    public void setUp() {

        wd = new ChromeDriver();
        baseUrl = "https://www.google.com/intl/ru/gmail/about/#";
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        wd.quit();

    }

    @Test
    public void countMassagesTest() {


        wd.get(baseUrl);
        wd.findElement(By.linkText("Войти")).click();

        // переключение на активную вкладку
        ArrayList<String> tabs2 = new ArrayList<String> (wd.getWindowHandles());
        wd.switchTo().window(tabs2.get(1));

        // ввод логина
        wd.findElement(By.id("identifierId")).sendKeys("test.box.for.sdet.internship@gmail.com");

        // далее
        wd.findElement(By.xpath("//button/div[2]")).click();;

        // ввод пароля
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).sendKeys("testbox1");
        wd.findElement(By.xpath("//button/div[2]")).click();

        // подтверждение в предупреждающем окне
        wd.findElement(By.xpath("//div[2]/div/span/span")).click();



    }


}
