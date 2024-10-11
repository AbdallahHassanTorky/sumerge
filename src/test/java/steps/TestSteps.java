package steps;

import base.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.LoginPage;

public class TestSteps  {

    LoginPage login = new LoginPage();
    @BeforeMethod
    public void setUp() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://www.saucedemo.com/");
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }


    /** 1. Check if the username and password fields are on the main screen of the application:
     •	Username input has id equal to user-name
     •	Password input has id equal to password
     •	Login button has id equal to login-buttonn
     */
    @Test
    public void checkLoginElements() {
        boolean elementsPresent = login.areLoginElementsPresent();
        Assert.assertTrue(elementsPresent, "Login elements are not present on the main screen.");
    }

/** 2. Check if the given valid credentials work:
 •	Use credentials (Username: standard_user ,Password: secret_sauce).
 •	After a successful login attempt, a div containing text: Swag Labs is visible.
 */
    @Test
    public void ValidLogin() {

        login.login("standard_user", "secret_sauce");
        boolean isSwagLabsVisible = login.isSwagLabsVisible();
        Assert.assertTrue(isSwagLabsVisible, "Swag Labs text is not visible after successful login.");
        System.out.println(isSwagLabsVisible+"\t:Swag Labs is visible");
    }

    /** 3. Check if the given wrong credentials work:
      •	Use invalid credentials
      •	After an unsuccessful login attempt, a div with class error-message-container error and containing a message
     Epic sadface: Username and password do not match any user in this service is visible.
     */
    @Test
    public void InValidLogin() {

        login.login("Abdallah", "01200900573");
        boolean isErrorVisible = login.isErrorMessageVisible();
        Assert.assertTrue(isErrorVisible, "Error message is not visible after invalid login.");

        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMessage = login.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message does not match expected.");
        System.out.println(actualErrorMessage);

    }

    /** 4. Check for empty credentials:

      •	After an unsuccessful login attempt, a div with class error-message-container error and containing a message
     Epic sadface: Username is required is visible.
      •	After an unsuccessful login attempt, a div with class error-message-container error and containing a message
     Epic sadface: Password is required is visible.
     */
    @Test
    public void EmptyInValidLogin() {

        login.login("", "");

        // Check if the error message container is visible
        boolean isErrorVisible = login.isErrorMessageVisible();
        Assert.assertTrue(isErrorVisible, "Error message is not visible after attempting to log in with empty credentials.");

        // Get the actual error message
        String errorMessage = login.getErrorMessage();

        // Check for both specific error messages
        String expectedUsernameError = "Epic sadface: Username is required";
        String expectedPasswordError = "Epic sadface: Password is required";

        if (errorMessage.contains("Username is required") && errorMessage.contains("Password is required")) {
            System.out.println("Both error messages are present.");
            Assert.assertTrue(true); // Both messages are displayed
        } else {
            // If only one of the messages is displayed
            if (errorMessage.equals(expectedUsernameError)) {
                System.out.println("Error: Username is required.");
                Assert.assertEquals(errorMessage, expectedUsernameError, "Error message does not match expected for empty username.");
            } else if (errorMessage.equals(expectedPasswordError)) {
                System.out.println("Error: Password is required.");
                Assert.assertEquals(errorMessage, expectedPasswordError, "Error message does not match expected for empty password.");
            } else {
                Assert.fail("Unexpected error message: " + errorMessage);
            }
        }
    }


    @AfterMethod
    public void tearDown() {

        Driver.quitDriver();
    }
}
