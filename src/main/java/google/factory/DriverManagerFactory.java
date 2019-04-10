package google.Factory;

import google.manager.DriverManager;
import google.manager.chrome.ChromeDriverManager;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType) {

        DriverManager driverManager;

        switch (driverType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;
    }
}
