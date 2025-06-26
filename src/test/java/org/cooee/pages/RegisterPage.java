package org.cooee.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    private final By noNumbersMessage = By.xpath("//*[contains(text(),'No numbers are available for')]");
    private final By continueButton = By.xpath("//*[normalize-space(text())='Continue']");
    private final By stepTwoHeader = By.xpath("//*[contains(text(),'Step') and contains(text(),'2/4')]");
    private final By viewPlansButton = By.xpath("//*[normalize-space(text())='View Plans']");
    private final By monthlyPlanHeader = By.xpath("//*[normalize-space(text())='MONTHLY PLAN']");
    private final By annualPlanHeader = By.xpath("//*[normalize-space(text())='ANNUAL PLAN']");
    private final By monthlyPlanCheckoutBtn = By.xpath("(//*[normalize-space(text())='Checkout'])[1]");
    private final By annualPlanCheckoutBtn = By.xpath("(//*[normalize-space(text())='Checkout'])[2]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("✅ RegisterPage initialized.");
    }

    public void selectCountryByName(String countryName) {
        By countryOption = By.xpath("//*[normalize-space(text())='" + countryName + "']");
        WebElement countryElement = wait.until(ExpectedConditions.elementToBeClickable(countryOption));
        countryElement.click();
        System.out.println("✅ Clicked on country: " + countryName);
    }

    public boolean selectFirstAvailableCountry(String[] countries) {
        for (String country : countries) {
            try {
                selectCountryByName(country);
                Thread.sleep(1000); // Wait for numbers or message to load

                if (driver.findElements(noNumbersMessage).size() > 0) {
                    System.out.println("❌ No numbers available for: " + country);
                } else {
                    System.out.println("✅ Numbers available for: " + country);
                    String number = clickOnFirstAvailableNumber();
                    if (number != null) {
                        System.out.println("✅ Selected number: " + number);
                        return true;
                    }
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error checking country: " + country + " - " + e.getMessage());
            }
        }

        System.out.println("❌ No countries had numbers available.");
        return false;
    }

    public String clickOnFirstAvailableNumber() {
        try {
            By numberListLocator = By.xpath("//*[starts-with(text(), '+')]");
            List<WebElement> numbers = driver.findElements(numberListLocator);

            if (!numbers.isEmpty()) {
                String selectedNumber = numbers.get(0).getText();
                numbers.get(0).click();
                return selectedNumber;
            } else {
                System.out.println("⚠️ No numbers available.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("❌ Error while selecting number: " + e.getMessage());
            return null;
        }
    }

    public boolean verifyStepTwoVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(stepTwoHeader));
            System.out.println("✅ Step 2/4 is visible.");
            return true;
        } catch (TimeoutException e) {
            System.out.println("❌ Step 2/4 not found.");
            return false;
        }
    }

    public void clickViewPlans() {
        try {
            WebElement viewBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(viewPlansButton));
            try {
                viewBtn.click(); // Try normal click first
                System.out.println("✅ Clicked on 'View Plans' using regular click.");
            } catch (ElementClickInterceptedException e) {
                System.out.println("⚠️ Regular click failed. Trying JavaScript click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", viewBtn);
                Thread.sleep(500);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewBtn);
                System.out.println("✅ Clicked on 'View Plans' using JavaScript.");
            }
        } catch (TimeoutException e) {
            System.out.println("❌ View Plans button not found within timeout.");
        } catch (Exception ex) {
            System.out.println("❌ Unexpected error clicking 'View Plans': " + ex.getMessage());
        }
    }

    public void printMonthlyPlanDetails() {
        try {
            WebElement monthlyPlan = wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyPlanHeader));
            System.out.println("✅ Found Monthly Plan section");
            String details = monthlyPlan.findElement(By.xpath("..")).getText();
            System.out.println("📦 Monthly Plan Details:\n" + details);
        } catch (Exception e) {
            System.out.println("❌ Monthly Plan details not found.");
        }
    }

    public void printAnnualPlanDetails() {
        try {
            WebElement annualPlan = wait.until(ExpectedConditions.presenceOfElementLocated(annualPlanHeader));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", annualPlan);
            Thread.sleep(500);
            String details = annualPlan.findElement(By.xpath("..")).getText();
            System.out.println("📦 Annual Plan Details:\n" + details);
        } catch (Exception e) {
            System.out.println("❌ Annual Plan details not found. Error: " + e.getMessage());
        }
    }

    public void clickMonthlyCheckout() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(monthlyPlanCheckoutBtn));
            checkout.click();
            System.out.println("✅ Clicked Monthly Plan Checkout.");
        } catch (TimeoutException e) {
            System.out.println("❌ Monthly Plan checkout not clickable.");
        }
    }

    public void clickAnnualCheckout() {
        try {
            WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(annualPlanCheckoutBtn));
            checkout.click();
            System.out.println("✅ Clicked Annual Plan Checkout.");
        } catch (TimeoutException e) {
            System.out.println("❌ Annual Plan checkout not clickable.");
        }
    }

    public void clickContinueWithScroll() throws InterruptedException {
        int maxScrolls = 5;
        boolean clicked = false;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
                if (continueBtn.isDisplayed() && continueBtn.isEnabled()) {
                    try {
                        continueBtn.click();
                        System.out.println("✅ Clicked on 'Continue' button.");
                        clicked = true;
                        break;
                    } catch (ElementClickInterceptedException e) {
                        System.out.println("⚠️ Click intercepted, trying JavaScript click...");
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueBtn);
                        Thread.sleep(500);
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueBtn);
                        System.out.println("✅ Clicked on 'Continue' using JavaScript.");
                        clicked = true;
                        break;
                    }
                }
            } catch (TimeoutException e) {
                System.out.println("⚠️ 'Continue' not visible yet, scrolling down...");
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
                Thread.sleep(500);
            }
        }

        if (!clicked) {
            System.out.println("❌ Failed to click 'Continue' after retries.");
        }
    }
}
