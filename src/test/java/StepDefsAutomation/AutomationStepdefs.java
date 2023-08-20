package StepDefsAutomation;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AutomationStepdefs {

    WebDriver driver;

    private WebDriverWait wait;



    @Given("User is on the registration page")
    public void userIsOnTheRegistrationPage() {
        System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://login.mailchimp.com/signup/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @When("User fills in valid {string}, {string}, and {string}")
    public void userFillsInValidAnd(String email, String username, String password) {
        WebElement emailAddressInput = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailAddressInput.sendKeys(email);

        WebElement userNameInput = driver.findElement(By.xpath("//*[@id=\"new_username\"]"));
        userNameInput.sendKeys("");
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"new_password\"]"));
        passwordInput.sendKeys(password);
    }

    @And("User submits the registration form")
    public void userSubmitsTheRegistrationForm() {
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"create-account-enabled\"]"));
        signUpButton.click();
    }

    private String anErrorMessage() {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid-error")));
        return confirmationMessage.getText();

    }

    @Then("User should see a confirmation or an error {string}")
    public void userShouldSeeAConfirmationOrAnError(String message) {
            if (message.equalsIgnoreCase("signup-success")) {
                wait.until(ExpectedConditions.titleIs("Success | Mailchimp"));
                String expected = "Success | Mailchimp";
                String actual = driver.getTitle();
                assertEquals(expected, actual);
            } else if (message.equalsIgnoreCase("invalid-error")) {
                String actual = anErrorMessage();
                String expected = "invalid-error";
                assertEquals(expected, actual);
            }
        }


        @After
        public void closeBrowser() {

                driver.quit();

        }

}