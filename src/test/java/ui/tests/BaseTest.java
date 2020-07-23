package ui.tests;

import org.junit.After;
import org.junit.Before;
import ui.tests.appmanager.BaseHelper;

public class BaseTest extends BaseHelper {

    @Before
    public void setUp() {
        initChromeWebDriver();
    }

    @After
    public void tearDown() {
        wd.quit();

    }
}
