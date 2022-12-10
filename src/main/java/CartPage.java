import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void removeItemFromCart(String itemName) {
        WebElement removeFromCartBtn = driver.findElement(By.id("remove-"+itemName.replace(" ", "-").toLowerCase()));
        removeFromCartBtn.click();
    }

    public boolean cartHasItem() {
        WebElement item = driver.findElement(By.className("cart_item"));
        return item.isDisplayed();
    }

    public void continueShopping() {
        WebElement continueShoppingBtn = driver.findElement(By.name("continue-shopping"));
        continueShoppingBtn.click();
    }

    public void checkoutBtn() {
        WebElement checkoutBtn = driver.findElement(By.name("checkout"));
        checkoutBtn.click();
    }
}
