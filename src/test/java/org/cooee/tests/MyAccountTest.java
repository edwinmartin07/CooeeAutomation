package org.cooee.tests;

import org.testng.annotations.*;
import org.cooee.base.BaseTest;
import org.cooee.pages.LoginPage;
import org.cooee.pages.MyAccountPage;
import org.cooee.utils.JsonDataReader;

import java.util.HashMap;
import java.util.List;

public class MyAccountTest extends BaseTest {

    private HashMap<String, String> validLoginData;
    private HashMap<String, String> validUpdateData;
    private HashMap<String, String> invalidEmailUpdateData;
    private HashMap<String, String> invalidPasswordUpdateData;

    @BeforeClass
    public void loadData() throws Exception {
        List<HashMap<String, String>> testData = JsonDataReader.getJsonData(System.getProperty("user.dir") + "/test-data/myAccountData.json");
        validLoginData = testData.get(0);
        validUpdateData = testData.get(1);
        invalidEmailUpdateData = testData.get(2);
        invalidPasswordUpdateData = testData.get(3);
    }

    private void loginWithValidCredentials() {
        launchApp(validLoginData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(validLoginData.get("username"));
        loginPage.enterPassword(validLoginData.get("password"));
        if (loginPage.isSignInClickable()) {
            loginPage.clickLogin();
        }
    }

    @Test(priority = 1)
    public void testUpdateEmailWithValidEmail() {
        loginWithValidCredentials();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickMyAccount();

        myAccountPage.enterEmail(validUpdateData.get("email"));
        myAccountPage.clickUpdateEmailButton();
        myAccountPage.clickConfirmYesUpdate();
    }

    @Test(priority = 2)
    public void testUpdateEmailWithInvalidEmail() {
        loginWithValidCredentials();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickMyAccount();

        myAccountPage.enterEmail(invalidEmailUpdateData.get("email"));
        myAccountPage.clickUpdateEmailButton();
        myAccountPage.clickConfirmYesUpdate();
    }

    @Test(priority = 3)
    public void testUpdatePasswordWithValidPassword() {
        loginWithValidCredentials();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickMyAccount();

        myAccountPage.enterPassword(validUpdateData.get("password"));
        myAccountPage.clickUpdatePasswordButton();
        myAccountPage.clickConfirmYesUpdate();
    }

    @Test(priority = 4)
    public void testUpdatePasswordWithInvalidPassword() {
        loginWithValidCredentials();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.clickMyAccount();

        myAccountPage.enterPassword(invalidPasswordUpdateData.get("password"));
        myAccountPage.clickUpdatePasswordButton();
        myAccountPage.clickConfirmYesUpdate();
    }
}
