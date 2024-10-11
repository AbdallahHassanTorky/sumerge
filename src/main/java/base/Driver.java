package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Driver {
    private static WebDriver driver;








    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        return driver;
    }






    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset driver
        }
    }
}
