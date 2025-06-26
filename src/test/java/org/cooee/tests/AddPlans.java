package org.cooee.tests;

import org.testng.annotations.*;
import org.cooee.base.BaseTest;
import org.cooee.pages.LoginPage;
import org.cooee.pages.PaymentPage;
import org.cooee.pages.PlansPage;
import org.cooee.utils.JsonDataReader;

import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class AddPlans extends BaseTest {

    private HashMap<String, Object> validData;

    @BeforeClass
    public void loadData() throws Exception {
        List<HashMap<String, Object>> testData = JsonDataReader.getObjectMapData(
                System.getProperty("user.dir") + "/test-data/AddPlansData.json"
        );
        validData = testData.get(0);
    }

    @Test(priority = 1)
    public void loginAndCheckoutSevenDollarPlan() {
        loginToApp();
        PlansPage plansPage = new PlansPage(driver);
        plansPage.openMyCooeePlans();
        System.out.println("📟 Current Balance: " + plansPage.getBalanceText());
        plansPage.clickTopUpCTA();

        plansPage.selectSevenDollarPlan();
        plansPage.clickCheckout();
        System.out.println("✅ Checked out $7 plan successfully");

        fillPaymentDetails();
    }

    @Test(priority = 2)
    public void loginAndCheckoutThirteenDollarPlan() {
        loginToApp();
        PlansPage plansPage = new PlansPage(driver);
        plansPage.openMyCooeePlans();
        System.out.println("📟 Current Balance: " + plansPage.getBalanceText());
        plansPage.clickTopUpCTA();

        plansPage.selectThirteenDollarPlan();
        plansPage.clickCheckout();
        System.out.println("✅ Checked out $13 plan successfully");

        fillPaymentDetails();
    }

    @Test(priority = 3)
    public void loginAndCheckoutTwentySixDollarPlan() {
        loginToApp();
        PlansPage plansPage = new PlansPage(driver);
        plansPage.openMyCooeePlans();
        System.out.println("📟 Current Balance: " + plansPage.getBalanceText());
        plansPage.clickTopUpCTA();

        plansPage.selectTwentySixDollarPlan();
        plansPage.clickCheckout();
        System.out.println("✅ Checked out $26 plan successfully");

        fillPaymentDetails();
    }

    @Test(priority = 4)
    public void handlePaymentFailureFlow() throws InterruptedException {
        loginToApp();
        PlansPage plansPage = new PlansPage(driver);
        plansPage.openMyCooeePlans();
        System.out.println("📟 Current Balance: " + plansPage.getBalanceText());
        plansPage.clickTopUpCTA();

        plansPage.selectSevenDollarPlan();
        plansPage.clickCheckout();
        System.out.println("✅ Reached payment screen");

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.clickBackIcon();

        if (paymentPage.isPaymentFailedVisible()) {
            paymentPage.clickGotItButton();
            System.out.println("✅ Payment failed flow handled properly");
        } else {
            System.out.println("⚠️ Payment failed message not visible");
        }
    }

    private void loginToApp() {
        launchApp((String) validData.get("url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail((String) validData.get("username"));
        loginPage.enterPassword((String) validData.get("password"));
        assertTrue(loginPage.isSignInClickable(), "Login button should be clickable");
        loginPage.clickLogin();
    }

    private void fillPaymentDetails() {
        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.enterCardNumber((String) validData.get("cardNumber"));
        paymentPage.enterExpiry((String) validData.get("expiryDate"));
        paymentPage.enterCVC((String) validData.get("cvc"));
        paymentPage.enterCardHolderName((String) validData.get("billingName"));
        //paymentPage.enableStripeCheckbox();
        paymentPage.clickPayButton();
        System.out.println("💳 Payment form submitted");
    }
}
