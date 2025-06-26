package org.cooee.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlansPage {
    WebDriver driver;
    WebDriverWait wait;

    public PlansPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By myCooeePlansModule = By.xpath("//*[text()='My Cooee Plans']");
    private By smsAndCallBalance = By.xpath("//*[@id='root']/div/div[2]/div/div[2]/div/main/div/div/div[1]/div[2]/div/div[3]/div/div[2]");
    private By topUpButton = By.xpath("//*[contains(text(),'Min/SMS Top-up')]");
    private By plansHeader = By.xpath("//*[contains(text(),'Available Plans') or contains(text(),'Top-up')]");

    private By sevenDollarPlan = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/div/div[1]/div/div[1]");
    private By thirteenDollarPlan = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/div/div[2]/div/div[1]");
    private By twentySixDollarPlan = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/div/div[3]/div/div[1]");

    private By checkoutButton = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div/div/div[4]/div/div[2]/button");
    private By backFromPaymentButton = By.xpath("/html/body/div[1]/div/div/div[1]/header/div/div/a/div/div/div[1]/svg");
    private By paymentFailedMessage = By.xpath("//*[text()='Payment Failed']");
    private By gotItButton = By.xpath("/html/body/div[3]/main/div[2]/button");
    

    public void openMyCooeePlans() {
        wait.until(ExpectedConditions.elementToBeClickable(myCooeePlansModule)).click();
    }

    public String getBalanceText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(smsAndCallBalance)).getText();
    }

    public void clickTopUpCTA() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(plansHeader));
        wait.until(ExpectedConditions.elementToBeClickable(topUpButton)).click();
    }

    public void selectSevenDollarPlan() {
        WebElement planElement = wait.until(ExpectedConditions.elementToBeClickable(sevenDollarPlan));
        System.out.println("✅ Clicking $7 Plan: " + planElement.getText());
        planElement.click();
    }

    public void selectThirteenDollarPlan() {
        WebElement planElement = wait.until(ExpectedConditions.elementToBeClickable(thirteenDollarPlan));
        System.out.println("✅ Clicking $13 Plan: " + planElement.getText());
        planElement.click();
    }

    public void selectTwentySixDollarPlan() {
        WebElement planElement = wait.until(ExpectedConditions.elementToBeClickable(twentySixDollarPlan));
        System.out.println("✅ Clicking $26 Plan: " + planElement.getText());
        planElement.click();
    }

    public void clickCheckout() {
        try {
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            System.out.println("✅ Clicking Checkout");
            checkoutBtn.click();
        } catch (TimeoutException e) {
            System.out.println("❌ Checkout button not found or not clickable.");
            throw e;
        }
    }
    public void clickBackFromPaymentPage() {
        wait.until(ExpectedConditions.elementToBeClickable(backFromPaymentButton)).click();
        System.out.println("↩️ Clicked Back icon from payment screen");
    }

    public boolean isPaymentFailedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFailedMessage)).isDisplayed();
    }

    public void clickGotItOnPaymentFailed() {
        WebElement gotIt = wait.until(ExpectedConditions.elementToBeClickable(gotItButton));
        System.out.println("❌ Payment failed. Clicking 'Got it'...");
        gotIt.click();
    }

}
