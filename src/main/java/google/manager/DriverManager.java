package google.manager;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
    protected WebDriver webDriver;
    protected abstract void startService();
    protected abstract void createWebDriver();
    protected abstract void stopService();

    public void quitDriver(){
        if (null != webDriver){
            webDriver.quit();
            webDriver = null;
        }
    }

    public WebDriver getWebDriver(){
        if(null == webDriver){
            startService();
            createWebDriver();
        }
        return webDriver;
    }
}
