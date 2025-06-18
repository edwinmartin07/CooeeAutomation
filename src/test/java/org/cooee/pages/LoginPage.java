package org.cooee.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    private final By emailInput = By.xpath("//input[@placeholder='Enter your email address']");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By errorMessage = By.xpath("//*[contains(text(),'Wrong email or password')]"); // ✅ locator

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("✅ LoginPage initialized.");
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.click();
        emailField.sendKeys(email);
        System.out.println("✅ Entered email: " + email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.click();
        passwordField.sendKeys(password);
        System.out.println("✅ Entered password: " + password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
        System.out.println("✅ Clicked on Login button.");
    }

    public boolean isSignInClickable() {
        try {
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
            boolean clickable = loginBtn.isEnabled() && loginBtn.isDisplayed();
            System.out.println("✅ Login button clickable status: " + clickable);
            return clickable;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ✅ New method to get login error message
    public String getLoginErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String message = error.getText();
            System.out.println("❌ Login failed: " + message);
            return message;
        } catch (TimeoutException e) {
            System.out.println("⚠️ Error message not displayed.");
            return null;
        }
    }
}
