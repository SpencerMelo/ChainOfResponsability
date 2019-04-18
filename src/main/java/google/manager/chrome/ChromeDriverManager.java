package google.manager.chrome;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverChain {

    private ChromeDriverService chromeDriverService;

    public ChromeDriverManager() {
        super(IdBrowsers.CHROME);
    }

    @Override
    protected void startService() {
        try {
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File("chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(chromeDriverService, desiredCapabilities);
    }

    @Override
    protected void stopService() {
        if(chromeDriverService != null && chromeDriverService.isRunning()){
            chromeDriverService.stop();
        }
    }
}
