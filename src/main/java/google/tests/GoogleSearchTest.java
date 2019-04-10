package google.tests;

import google.Factory.DriverManagerFactory;
import google.Factory.DriverType;
import google.manager.DriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTest {

    private static DriverManager driverManager;
    WebDriver webDriver;

    @BeforeClass
    public static void setup(){
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @Before
    public void before() {
        webDriver = driverManager.getWebDriver();
    }

    @After
    public void after() {
        driverManager.quitDriver();
    }

    @Test
    public void launchGoogleTest() {
        webDriver.get("https://www.google.com");
        assertEquals("Google", webDriver.getTitle());
    }
}
