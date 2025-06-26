package org.cooee.tests;

import org.testng.annotations.*;
import org.cooee.base.BaseTest;
import org.cooee.pages.LoginPage;
import org.cooee.pages.RegisterPage;
import org.cooee.utils.JsonDataReader;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class RegisterTest extends BaseTest {

    private HashMap<String, String> newUserData;

    @BeforeClass
    public void loadData() throws Exception {
        List<HashMap<String, String>> testData = JsonDataReader.getJsonData(
            System.getProperty("user.dir") + "/test-data/registerData.json"
        );
        newUserData = testData.get(0);
    }

    @Test(priority = 1)
    public void testRegisterWithAvailableCountryAndNumber() throws InterruptedException {
        launchApp(newUserData.get("url"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegister();

        RegisterPage registerPage = new RegisterPage(driver);
        String[] countries = newUserData.get("preferredCountries").split("\\|");

        boolean found = registerPage.selectFirstAvailableCountry(countries);

        assertTrue(found, "❌ No available numbers found in preferred countries.");
        
        registerPage.clickContinueWithScroll();
        
        
        

        registerPage.clickViewPlans();
        Thread.sleep(2000); // Wait for plans to load

        registerPage.printMonthlyPlanDetails();
        registerPage.printAnnualPlanDetails();

        // Example: click on Monthly plan checkout
        registerPage.clickMonthlyCheckout();
    }
    
}
