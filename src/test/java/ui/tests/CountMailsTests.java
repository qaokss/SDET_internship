package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ui.tests.appmanager.BaseHelper;

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
        loginWithCorrectLoginAndPassword();
        findAllMessagesOnPage();

        System.out.println(findAllMessagesOnPage());




        // пишем письмо самому себе
//        wd.findElement(By.cssSelector("div.T-I.T-I-KE.L3")).click();
//        wd.findElement(By.xpath("//textarea")).click();

        //
//        type(By.name("to"), "test.box.for.sdet.internship@gmail.com");
//        type(By.name("subjectbox"), "Simbirsoft theme");
//        type(By.id(":ay"), "Найдено " + countBeforeSendingLetter + " писем");


//        wd.findElement(By.id(":9j")).click();


//        int countAfterSendingLetter = findAllMessagesOnPage();
//
//        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
