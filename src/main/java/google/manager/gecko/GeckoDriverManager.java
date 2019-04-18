package google.manager.gecko;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class GeckoDriverManager extends DriverChain {

    public GeckoDriverManager() {
        super(IdBrowsers.FIREFOX);
    }

    @Override
    protected void startService() {
        try {
            File file = new File("geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        desiredCapabilities.setCapability("marionette", true);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        WebDriver webDriver = new FirefoxDriver(desiredCapabilities);
        webDriver.manage().window().maximize();
        return webDriver;
    }

    @Override
    protected void stopService() {

    }
}
