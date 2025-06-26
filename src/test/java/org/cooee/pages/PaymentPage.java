package org.cooee.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Existing elements
    private By backIcon = By.xpath("/html/body/div[1]/div/div/div[1]/header/div/div/a/div/div/div[1]/svg");
    private By paymentFailedMsg = By.xpath("//*[text()='Payment Failed']");
    private By gotItButton = By.xpath("/html/body/div[3]/main/div[2]/button");

    // New payment form elements
    private By cardNumberInput = By.cssSelector("[aria-label='Card number']");
    private By expiryInput = By.cssSelector("[placeholder='MM / YY']");
    private By cvcInput = By.cssSelector("[placeholder='CVC']");
    private By cardNameInput = By.name("billingName");
    private By stripeCheckbox = By.id("enableStripePass");
    private By payButton = By.className("SubmitButton-IconContainer");

    // --- Existing ---
    public void clickBackIcon() {
        try {
            By backAnchor = By.cssSelector("#root > div > div > div.App-Overview > header > div > div > a");
            WebElement anchorElement = wait.until(ExpectedConditions.presenceOfElementLocated(backAnchor));
            new Actions(driver).moveToElement(anchorElement).pause(Duration.ofMillis(500)).perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", anchorElement);
            System.out.println("✅ Back icon anchor clicked via JS after hover");
        } catch (TimeoutException e) {
            System.out.println("❌ Failed to activate or click the Back icon anchor.");
            throw e;
        }
    }

    public boolean isPaymentFailedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFailedMsg)).isDisplayed();
    }

    public void clickGotItButton() {
        wait.until(ExpectedConditions.elementToBeClickable(gotItButton)).click();
        System.out.println("❌ Clicked 'Got it' on payment failed message");
    }

    // --- New Payment Methods ---

    public void enterCardNumber(String cardNumber) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(cardNumberInput));
        field.sendKeys(cardNumber);
        System.out.println("💳 Entered Card Number");
    }

    public void enterExpiry(String mmYY) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(expiryInput));
        field.sendKeys(mmYY);
        System.out.println("📅 Entered Expiry MM/YY");
    }

    public void enterCVC(String cvc) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(cvcInput));
        field.sendKeys(cvc);
        System.out.println("🔐 Entered CVC");
    }

    public void enterCardHolderName(String name) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(cardNameInput));
        field.sendKeys(name);
        System.out.println("🧾 Entered Billing Name");
    }

    public void enableStripeCheckbox() {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(stripeCheckbox));
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("✅ Enabled Stripe Pass Checkbox");
        }
    }

    public void clickPayButton() {
        WebElement payBtn = wait.until(ExpectedConditions.elementToBeClickable(payButton));
        payBtn.click();
        System.out.println("💰 Clicked Pay Button");
    }
}
