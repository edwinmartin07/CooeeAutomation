package org.cooee.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MyAccountPage {
    WebDriver driver;
    WebDriverWait wait;

    private final By myAccountLink = By.xpath("//*[text()='My Account']");
    private final By emailInput = By.xpath("//input[@placeholder='Email']");
    private final By updateEmailButton = By.xpath("//*[text()='Update']");
    // Updated locators below as per your provided XPaths
    private final By updatePasswordButton = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/main/div/div/div[3]/div/div/button/span");
    private final By passwordInput = By.xpath("/html/body/div/div/div[2]/div/div[2]/div/main/div/div/div[3]/div/div/div/div/input");
    private final By confirmYesUpdateButton = By.xpath("//*[text()='Yes, update']");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("✅ MyAccountPage initialized.");
    }

    public void clickMyAccount() {
        WebElement myAccount = wait.until(ExpectedConditions.elementToBeClickable(myAccountLink));
        myAccount.click();
        System.out.println("✅ Clicked on My Account.");
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
        System.out.println("✅ Entered email: " + email);
    }

    public void clickUpdateEmailButton() {
        WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(updateEmailButton));
        updateBtn.click();
        System.out.println("✅ Clicked on Update Email button.");
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("✅ Entered password.");
    }

    public void clickUpdatePasswordButton() {
        WebElement updatePassBtn = wait.until(ExpectedConditions.elementToBeClickable(updatePasswordButton));
        updatePassBtn.click();
        System.out.println("✅ Clicked on Update Password button.");
    }

    public void clickConfirmYesUpdate() {
        WebElement yesUpdateBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmYesUpdateButton));
        yesUpdateBtn.click();
        System.out.println("✅ Clicked on Yes, update confirmation button.");
    }
}
