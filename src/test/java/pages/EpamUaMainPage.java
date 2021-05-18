package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class EpamUaMainPage {

    private static final String EPAM_URL_TRAINING = "https://www.training.epam.ua/";

    private WebDriver driver;

    @FindBy(css = "a[href=\"/company/contact\"]")
    private WebElement linkToContacts;

    @FindBy(css = "a[href=\"/learning\"]")
    private WebElement learningSubMenu;

    @FindBy(css = "a[href=\"/learning/university-programs\"]")
    private WebElement universityPrograms;

    @FindBy(css = "a[href=\"" + EPAM_URL_TRAINING + "\"]")
    private WebElement linkToTrainingWebsite;

    @FindBy(css = "a[href=\"/company\"]")
    private WebElement companySubMenu;

    public EpamUaMainPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void goToCompanyContacts(){
        Actions hover = new Actions(driver);
        hover.moveToElement(companySubMenu).build().perform();
        linkToContacts.click();
    }

    public void goToUniversityPrograms(){
        Actions hover = new Actions(driver);
        hover.moveToElement(learningSubMenu).build().perform();
        universityPrograms.click();
    }

    public void goToTrainingWebsite(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", linkToTrainingWebsite);
    }

}
