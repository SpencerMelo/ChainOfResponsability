package google.manager.internetexplorer;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class InternetExplorerDriverManager extends DriverChain {

    private InternetExplorerDriverService internetExplorerDriverService;

    public InternetExplorerDriverManager() {
        super(IdBrowsers.IEXPLORER);
    }

    @Override
    protected void startService() {
        try {
            internetExplorerDriverService = new InternetExplorerDriverService.Builder()
                    .usingDriverExecutable(new File("IEDriverServer.exe"))
                    .usingAnyFreePort()
                    .build();
            internetExplorerDriverService.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected WebDriver createWebDriver() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
        return new InternetExplorerDriver(internetExplorerDriverService, desiredCapabilities);
    }

    @Override
    protected void stopService() {
        if(internetExplorerDriverService != null && internetExplorerDriverService.isRunning()){
            internetExplorerDriverService.stop();
        }
    }
}
