package org.cooee.tests;

import org.testng.annotations.*;
import org.cooee.base.BaseTest;
import org.cooee.pages.LoginPage;
import org.cooee.utils.JsonDataReader;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    private HashMap<String, String> invalidData;
    private HashMap<String, String> validData;
    private HashMap<String, String> invalidPasswordData;
    private HashMap<String, String> invalidEmailCorrectPasswordData;

    @BeforeClass
    public void loadData() throws Exception {
        List<HashMap<String, String>> testData = JsonDataReader.getJsonData(System.getProperty("user.dir") + "/test-data/loginData.json");
        invalidData = testData.get(0);
        validData = testData.get(1);
        invalidPasswordData = testData.get(2);
        invalidEmailCorrectPasswordData = testData.get(3);
    }

    @Test(priority = 1)
    public void testLoginButtonDisabledForInvalidEmailFormat() {
        launchApp(invalidData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(invalidData.get("username"));
        loginPage.enterPassword(invalidData.get("password"));

        assertFalse(loginPage.isSignInClickable(), "Login button should be disabled for invalid email format");
    }

    @Test(priority = 2)
    public void testLoginWithValidCredentials() {
        launchApp(validData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(validData.get("username"));
        loginPage.enterPassword(validData.get("password"));

        assertTrue(loginPage.isSignInClickable(), "Login button should be enabled for valid email");
        loginPage.clickLogin();
    }

    @Test(priority = 3)
    public void testLoginWithInvalidPassword() {
        launchApp(invalidPasswordData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(invalidPasswordData.get("username"));
        loginPage.enterPassword(invalidPasswordData.get("password"));
        loginPage.clickLogin();

        String errorMessage = loginPage.getLoginErrorMessage();
        assertEquals(errorMessage, "Wrong email or password.", "Expected error message not displayed.");
    }

    @Test(priority = 4)
    public void testLoginWithIncorrectEmailAndValidPassword() {
        launchApp(invalidEmailCorrectPasswordData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(invalidEmailCorrectPasswordData.get("username"));
        loginPage.enterPassword(invalidEmailCorrectPasswordData.get("password"));
        loginPage.clickLogin();

        String errorMessage = loginPage.getLoginErrorMessage();
        assertEquals(errorMessage, "Wrong email or password.", "Expected error message not displayed.");
    }
}
