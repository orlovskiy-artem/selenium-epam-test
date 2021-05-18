package cucumber;

import cucumber.annotation.After;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EpamMainPage;
import pages.EpamUaContactPage;
import pages.EpamUaMainPage;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SeleniumChecker {
    private static final String EPAM_URL = "http://www.epam.com/";
    private static final String EPAM_URL_UA = "https://careers.epam.ua/";
    private static final String EPAM_URL_IN = "https://welcome.epam.in/";
    private static final String EPAM_URL_TRAINING = "https://training.epam.ua/";
    private static final String EPAM_URL_PRIVACY_POLICY = "https://www.epam.com/privacy-policy";
    private static final String EPAM_URL_FAQ = "https://investors.epam.com/investors/faq";

    private static final String EPAM_UA_SITE_TITLE = "EPAM Ukraine - найбільша ІТ-компанія в Україні | Вакансії";
    private static final String EPAM_IN_SITE_TITLE = "Welcome to EPAM in India | Software Development, Design & Consulting";
    private static final String EPAM_PRIVACY_POLICY_SITE_TITLE = "Privacy Policy";
    private static final String EPAM_FAQ_SITE_TITLE = "FAQ | EPAM Systems";

    WebDriver driver;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(EPAM_URL);
    }

    @When("^I switch location to Ukraine$")
    public void iSwitchLocationToUkraine(){
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.switchLocationToUa();
    }

    @Then("^I should be redirected to https://careers\\.epam\\.ua/$")
    public void iShouldBeRedirectedToHttpsCareersEpamUa(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(EPAM_UA_SITE_TITLE));
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_UA,url);
    }

    @When("^I switch location to India \\(English\\)$")
    public void iSwitchLocationToIndiaEnglish(){
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.switchToLocationIn();
    }

    @Then("^I should be redirected to https://welcome\\.epam\\.in/$")
    public void iShouldBeRedirectedToHttpsWelcomeEpamIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(EPAM_IN_SITE_TITLE));
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_IN,url);
    }

    @When("^I go to company's contacts$")
    public void iGoToCompanySContacts() {
        EpamUaMainPage uaMainPage = new EpamUaMainPage(driver);
        uaMainPage.goToCompanyContacts();
    }

    @Then("^I should be able to call by click on phone number$")
    public void iShouldBeAbleToCallByClickOnPhoneNumber() {
        EpamUaContactPage uaContactPage = new EpamUaContactPage(driver);
        assertTrue(uaContactPage.getPhonesElements().size()>0);
    }

    @Then("^I should be able to mail to email by click$")
    public void iShouldBeAbleToMailToByClick() {
        EpamUaContactPage uaContactPage = new EpamUaContactPage(driver);
        assertTrue(uaContactPage.getEmailElements().size()>0);
    }

    @And("^I go to education info for students$")
    public void iGoToEducationInfoForStudents() throws InterruptedException {
        EpamUaMainPage uaMainPage = new EpamUaMainPage(driver);
        uaMainPage.goToUniversityPrograms();
    }


    @Then("^I should be redirected to https://training\\.epam\\.ua/$")
    public void iShouldBeRedirectedToHttpsTrainingEpamUa() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // To switch tabs, because new one opens
        String urlStr = driver.getCurrentUrl();
        URI uri = URI.create(urlStr); // to get rid off some queries and fragments
        String host = uri.getHost();
        String trainingGeneralUrl = "https://"+host+"/";
        assertEquals(EPAM_URL_TRAINING,trainingGeneralUrl);

    }


    @When("^I click on link in the first step to become junior$")
    public void iClickOnLinkInTheFirstStepToBecomeJunior() {
        EpamUaMainPage uaMainPage = new EpamUaMainPage(driver);
        uaMainPage.goToTrainingWebsite();
    }

    @When("^I click on magnifier symbol$")
    public void iClickOnMagnifierSymbol(){
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.openSearchArea();
    }

    @Then("^I should see search area$")
    public void iShouldSeeSearchArea() {
        EpamMainPage mainPage = new EpamMainPage(driver);
        assertTrue(mainPage.isSearchAreaDisplayed());
    }

    @When("^I press on contact us button$")
    public void iPressOnContactUsButton() throws InterruptedException {
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.goToContactUs();
    }

    @When("^I press on link to read Privacy policy$")
    public void iPressOnLinkToReadPrivacyPolicy() throws InterruptedException {
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.goToPrivacyPolicySite();
    }

    @Then("^I should be redirected to Privacy policy site$")
    public void iShouldBeRedirectedToPrivacyPolicySite() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(EPAM_PRIVACY_POLICY_SITE_TITLE));
        String urlStr = driver.getCurrentUrl();
        assertEquals(EPAM_URL_PRIVACY_POLICY,urlStr);
    }

    @When("^I click on FAQ option$")
    public void iClickOnFAQOption() {
        EpamMainPage mainPage = new EpamMainPage(driver);
        mainPage.goToFAQ();
    }

    @Then("^I should be redirected to page with FAQ$")
    public void iShouldBeRedirectedToPageWithFAQ(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains(EPAM_FAQ_SITE_TITLE));
        String url = driver.getCurrentUrl();
        assertEquals(EPAM_URL_FAQ,url);
    }
}
