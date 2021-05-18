package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EpamUaContactPage {

    private WebDriver driver;

    @FindBys(
            @FindBy(css = "a[class=\"contact-details-reference__phone\"]")
    )
    private List<WebElement> phoneElements;

    @FindBys(
            @FindBy(css = "a[class=\"contact-details-reference__email\"]")
    )
    private List<WebElement> emailElements;

    public EpamUaContactPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getPhonesElements(){
        return phoneElements;
    }

    public List<WebElement> getEmailElements(){
        return emailElements;
    }







}
