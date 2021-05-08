package cucumber;

import cucumber.annotation.After;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SeleniumChecker {
    private static final String EPAM_URL = "http://www.epam.com/";
    private static final String EPAM_URL_UA = "https://careers.epam.ua/";
    private static final String EPAM_URL_IN = "https://welcome.epam.in/";
    private static final String EPAM_URL_TRAINING = "https://training.epam.ua/";
    private static final String EPAM_URL_PRIVACY_POLICY = "https://www.epam.com/privacy-policy";
    private static final String EPAM_URL_FAQ = "https://investors.epam.com/investors/faq";

    WebDriver driver = null;

    @After()
    public void closeBrowser(){
        driver.quit();
    }

    @Given("^I have entered site epam\\.com$")
    public void iHaveEnteredSiteEpamCom() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.navigate().to(EPAM_URL);
        Thread.sleep(500);
    }

    @When("^I switch location to Ukraine$")
    public void iSwitchLocationToUkraine() throws InterruptedException {
        driver.findElement(
                By.className("location-selector__button"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.cssSelector("a[href=\"https://careers.epam.ua\"]"))
                .click();
        Thread.sleep(500);
    }

    @Then("^I should be redirected to https://careers\\.epam\\.ua/$")
    public void iShouldBeRedirectedToHttpsCareersEpamUa() throws InterruptedException {
        Thread.sleep(1000);
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_UA,url);
    }


    @When("^I switch location to India \\(English\\)$")
    public void iSwitchLocationToIndiaEnglish() throws InterruptedException {
        driver.findElement(
                By.className("location-selector__button"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.cssSelector("a[href=\"https://welcome.epam.in\"]"))
                .click();
        Thread.sleep(500);
    }

    @Then("^I should be redirected to https://welcome\\.epam\\.in/$")
    public void iShouldBeRedirectedToHttpsWelcomeEpamIn() throws InterruptedException {
        Thread.sleep(500);
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_IN,url);
    }


    @When("^I go to company's contacts$")
    public void iGoToCompanySContacts() throws InterruptedException {
        Thread.sleep(500);
        Actions hover = new Actions(driver);
        WebElement Elem_to_hover = driver.findElement(By.cssSelector("a[href=\"/company\"]"));
        hover.moveToElement(Elem_to_hover).build().perform();
        driver.findElement(
                By.cssSelector("a[href=\"/company/contact\"]"))
                .click();
        Thread.sleep(500);
    }

    @Then("^I should be able to call by click on phone number$")
    public void iShouldBeAbleToCallByClickOnPhoneNumber() {
        List<WebElement> contactPhones = driver.findElements(
                By.cssSelector("a[class=\"contact-details-reference__phone\"]"));
        assertTrue(contactPhones.size()>0);
    }

    @Then("^I should be able to mail to \"([^\"]*)\" by click$")
    public void iShouldBeAbleToMailToByClick(String email) {
        WebElement emailElement = driver.findElement(
                By.xpath("//*[text()=\"" + email + "\"]"));
        assertNotNull(emailElement);
    }

    @And("^I go to education info for students$")
    public void iGoToEducationInfoForStudents() throws InterruptedException {
        Thread.sleep(500);
        Actions hover = new Actions(driver);
        WebElement Elem_to_hover = driver
                .findElement(By.cssSelector("a[href=\"/learning\"]"));
        hover.moveToElement(Elem_to_hover).build().perform();
        driver.findElement(
                By.cssSelector("a[href=\"/learning/university-programs\"]"))
                .click();
        Thread.sleep(500);
    }


    @Then("^I should be redirected to https://training\\.epam\\.ua/$")
    public void iShouldBeRedirectedToHttpsTrainingEpamUa() throws InterruptedException {
        Thread.sleep(1000); // sleep until web site will be ready to interact with
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // To switch tabs, because new one opens
        String urlStr = driver.getCurrentUrl();
        URI uri = URI.create(urlStr); // to get rid off some queries and fragments
        String host = uri.getHost();
        String trainingGeneralUrl = "https://"+host+"/";
        assertEquals(EPAM_URL_TRAINING,trainingGeneralUrl);

    }


    @When("^I click on link in the first step to become junior$")
    public void iClickOnLinkInTheFirstStepToBecomeJunior() throws InterruptedException {
        Thread.sleep(500);
        WebElement linkTraining = driver.findElement(
                By.cssSelector("a[href=\"https://www.training.epam.ua/\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", linkTraining);
        Thread.sleep(500);
    }

    @When("^I click on magnifier symbol$")
    public void iClickOnMagnifierSymbol() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(
                By.cssSelector("button[class=\"header-search__button header__icon\"]"))
                .click();
        Thread.sleep(500);
    }

    @Then("^I should see search area$")
    public void iShouldSeeSearchArea() throws InterruptedException {
        WebElement searchButton = driver.findElement(
                By.cssSelector("button[class=\"header-search__submit\"]"));
        assertTrue(searchButton.isDisplayed());
    }

    @When("^I press on contact us button$")
    public void iPressOnContactUsButton() throws InterruptedException {
        driver.findElement(
                By.xpath("//*[text()=\"CONTACT US\"]")).click();
        Thread.sleep(500);
    }

    @When("^I press on link to read Privacy policy$")
    public void iPressOnLinkToReadPrivacyPolicy() throws InterruptedException {
        WebElement privacyPolicyLink = driver.findElement(
                By.cssSelector("a[href=\"" + EPAM_URL_PRIVACY_POLICY + "\"]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", privacyPolicyLink);
        Thread.sleep(500);

    }

    @Then("^I should be redirected to Privacy policy site$")
    public void iShouldBeRedirectedToPrivacyPolicySite() {
        String urlStr = driver.getCurrentUrl();
        assertEquals(EPAM_URL_PRIVACY_POLICY,urlStr);
    }

    @When("^I click on FAQ option$")
    public void iClickOnFAQOption() throws InterruptedException {
        Actions hover = new Actions(driver);
        WebElement elemToHover = driver
                .findElement(By.cssSelector("a[href=\"/about\"]"));
        hover.moveToElement(elemToHover).build().perform();
        driver.findElement(
                By.cssSelector("a[href=\"/about/investors/faq\"]")).click();
        Thread.sleep(500);
    }

    @Then("^I should be redirected to page with FAQ$")
    public void iShouldBeRedirectedToPageWithFAQ() throws InterruptedException {
        Thread.sleep(500);
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_FAQ,url);
    }
}
