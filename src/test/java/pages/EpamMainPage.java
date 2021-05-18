package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class EpamMainPage {
    private static final String EPAM_URL = "http://www.epam.com";
    private static final String EPAM_URL_UA = "https://careers.epam.ua";
    private static final String EPAM_URL_IN = "https://welcome.epam.in";
    private static final String EPAM_URL_PRIVACY_POLICY = "https://www.epam.com/privacy-policy";
    private static final String EPAM_URL_FAQ = "https://investors.epam.com/investors/faq";

    private WebDriver driver;

    @FindBy(className = "location-selector__button")
    private WebElement locationSelectorButton;

    @FindBy(css = "a[href='" + EPAM_URL_UA + "']")
    private WebElement linkToEpamUa;

    @FindBy(css = "a[href='" + EPAM_URL_IN + "']")
    private WebElement linkToEpamIn;

    @FindBy(css = "button[class=\"header-search__button header__icon\"]")
    private WebElement magnifierElement;

    @FindBy(css = "button[class=\"header-search__submit\"]")
    private WebElement headerSearchSubmit;

    @FindBy(xpath = "//*[text()=\"CONTACT US\"]")
    private WebElement contactUsButton;

    @FindBy(css = "a[href=\"" + EPAM_URL_PRIVACY_POLICY + "\"]")
    private WebElement linkToPrivacyPolicy;

    @FindBy(css = "a[href=\"/about/investors/faq\"]")
    private WebElement linkToFaq;

    @FindBy(css = "a[href=\"/about\"]")
    private WebElement aboutSubMenu;

    public EpamMainPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void switchLocationToUa(){
        locationSelectorButton.click();
        linkToEpamUa.click();
    }

    public void switchToLocationIn(){
        locationSelectorButton.click();
        linkToEpamIn.click();
    }

    public void goToFAQ(){
        Actions hover = new Actions(driver);
        locationSelectorButton.click();
        hover.moveToElement(aboutSubMenu).build().perform();
        linkToFaq.click();
    }

    public void openSearchArea(){
        magnifierElement.click();
    }

    public Boolean isSearchAreaDisplayed(){
        return headerSearchSubmit.isDisplayed();
    }

    public void goToContactUs(){
        contactUsButton.click();
    }

    public void goToPrivacyPolicySite(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",linkToPrivacyPolicy);
    }
}

