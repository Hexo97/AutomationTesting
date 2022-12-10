import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class completePage {

    @FindBy(id = "checkout_complete_container")
    private WebElement completeContainer;
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;
    public completePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean completeContainer() {
        return completeContainer.isDisplayed();
    }

    public void backHomeBtn() {
        backHomeButton.click();
    }

}
