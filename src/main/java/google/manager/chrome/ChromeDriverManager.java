package google.manager.chrome;

import google.manager.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.InputStream;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chromeDriverService;

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
    protected void createWebDriver() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("test-type");
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        webDriver = new ChromeDriver(chromeDriverService, desiredCapabilities);
    }

    @Override
    protected void stopService() {
        if(chromeDriverService != null && chromeDriverService.isRunning()){
            chromeDriverService.stop();
        }
    }
}
