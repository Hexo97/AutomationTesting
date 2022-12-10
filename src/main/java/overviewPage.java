import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class overviewPage {
    @FindBy(className = "inventory_item_name")
    private WebElement inventoryItem;
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(id = "finish")
    private WebElement finishButton;
    public overviewPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void finishOrder() {
        finishButton.click();
    }

    public String getInventoryItem() {
        return inventoryItem.getText();
    }
}
