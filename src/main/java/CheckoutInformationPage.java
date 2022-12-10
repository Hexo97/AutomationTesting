import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
    @FindBy(name = "firstName")
    private WebElement firstnameInput;
    @FindBy(id = "last-name")
    private WebElement lastnameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement continueButton;
    @FindBy(id = "error-message-container")
    private WebElement errorMessage;
    public CheckoutInformationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void fillInformation(String first, String last, String code) {
        firstnameInput.sendKeys(first);
        lastnameInput.sendKeys(last);
        postalCodeInput.sendKeys(code);
        continueButton.click();
    }

    public boolean errorMsg() {
        return errorMessage.isDisplayed();
    }
}
