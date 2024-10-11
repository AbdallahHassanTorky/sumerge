package pageobjects;

import base.Driver; // Ensure this import is present
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public boolean areLoginElementsPresent() {
        WebDriver driver = Driver.getDriver();

        boolean isUsernamePresent = isElementPresent(By.id("user-name"));
        boolean isPasswordPresent = isElementPresent(By.id("password"));
        boolean isLoginButtonPresent = isElementPresent(By.id("login-button"));

        return isUsernamePresent && isPasswordPresent && isLoginButtonPresent;
    }

    private boolean isElementPresent(By by) {
        try {
            WebElement element = Driver.getDriver().findElement(by);
            boolean isDisplayed = element.isDisplayed();
            System.out.println("Element displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("Element not found: " + by);
            return false;
        }
    }


    public void login(String username, String password) {
        WebDriver driver = Driver.getDriver();
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public boolean isSwagLabsVisible() {
        try {
            WebDriver driver = Driver.getDriver();
            return driver.findElement(By.xpath("//div[contains(text(), 'Swag Labs')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getErrorMessage() {
        WebDriver driver = Driver.getDriver();
        return driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
    }

    public boolean isErrorMessageVisible() {
        try {
            WebDriver driver = Driver.getDriver();
            return driver.findElement(By.className("error-message-container")).isDisplayed();
        } catch (Exception e) {
            return false; // Return false if the element is not found
        }
    }
}