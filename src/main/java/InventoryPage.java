import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart(String itemName) {
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-"+itemName.replace(" ", "-").toLowerCase()));
        addToCartButton.click();
    }

    public void viewCart() {
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();
    }

}
