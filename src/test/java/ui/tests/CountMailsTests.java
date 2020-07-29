package ui.tests;

import api.tests.helpers.TestInfo;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ui.tests.PageObjects.InboxPage;
import ui.tests.PageObjects.LoginPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountMailsTests extends BaseTest {


    @Test
    @DisplayName("Проверка отправки письма самому себе и сравнение кол-ва писем до и после с определённой темой")
    @TestInfo(preconditions = "С помощью Selenium открыть браузер, открыть gmail.com, авторизоваться, зайти на почту \n" +
            "перейти во «Входящие» и определить, сколько писем с темой из шага 2 этапа подготовки нашлось \n",
            summary = "С помощью Selenium и интерфейса почты автоматически написать и отправить письмо самому себе \n" +
                    "в тексте которого указать найденное количество писем"
    )
    public void countMassagesTest() throws IllegalMonitorStateException, IOException {


        goTo().loginPage();

        lp().loginWithCorrectLoginAndPassword();

        int countBeforeSendingLetter = ip().countMessagesWithTheme("Simbirsoft theme");

        ip().writingLetterToMyself(countBeforeSendingLetter);

        ip().waitNewMessages(countBeforeSendingLetter);

        int countAfterSendingLetter = ip().countMessagesWithTheme("Simbirsoft theme");

        assertEquals(countBeforeSendingLetter + 1, countAfterSendingLetter);
    }


}
