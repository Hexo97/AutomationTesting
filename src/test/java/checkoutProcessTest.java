import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

public class checkoutProcessTest {
    private WebDriver driver;

    private WebElement cart;

    private String itemName;

    @BeforeTest
    public void setupDriver() {
        driver = WebDriverManagerUtil.getChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

    @Test(groups = {"loginTest"})
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        WebElement myCart = driver.findElement(By.id("shopping_cart_container"));
        myCart.isDisplayed();
    }

//    @Test(groups = {"loginTest"})
//    public void invalidLogin() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login("hehehe","oooooo");
//
//        WebElement errorMsg = driver.findElement(By.className("error-message-container"));
//        assert errorMsg.isDisplayed();
//    }

    @Test(dependsOnMethods = "validLogin")
    public void addRandomItem() {
        InventoryPage inventorypage = new InventoryPage(driver);

        // getting all items
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        assertEquals(items.size(), 6);

        // picking random item to add to cart
        Random random = new Random();
        int randIdx = random.nextInt(items.size());
        itemName = items.get(randIdx).getText();
        inventorypage.addToCart(itemName);

        // ensure an item has been added to cart
        cart = driver.findElement(By.className("shopping_cart_badge"));
        assertEquals(cart.getText(), "1");

        // ensure same item has been added to the cart
        inventorypage.viewCart();
        WebElement inventoryItem = driver.findElement(By.xpath("//*[text()='" + itemName + "']"));
        assertEquals(inventoryItem.getText(), itemName);
    }

    @Test(dependsOnMethods = {"addRandomItem"})
    public void checkoutFirstStep() {
        CartPage cartpage = new CartPage(driver);

        // ensure there is items in cart before continue to next step
        assertTrue(cartpage.cartHasItem());

        // picking random item to add to cart
        cartpage.checkoutBtn();
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");

    }

    @Test(dependsOnMethods = {"checkoutFirstStep"})
    public void checkoutInfo() {
        CheckoutInformationPage checkoutInfo = new CheckoutInformationPage(driver);
        checkoutInfo.fillInformation("James", "Bond", "00007");
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

//    @Test(dependsOnMethods = {"checkoutFirstStep"})
//    public void checkoutInvalidInfo() {
//        CheckoutInformationPage checkoutInfo = new CheckoutInformationPage(driver);
//
//        checkoutInfo.fillInformation("P@ss","T3st","022");
//        assertTrue(checkoutInfo.errorMsg());
//    }

    @Test(dependsOnMethods = {"checkoutInfo"})
    public void checkoutInfoOverView() {
        overviewPage overview = new overviewPage(driver);
        assertEquals(overview.getInventoryItem(), itemName);
        overview.finishOrder();
    }

    @Test(dependsOnMethods = {"checkoutInfoOverView"})
    public void orderCompletion() {
        completePage complete = new completePage(driver);
        assertTrue(complete.completeContainer());
        assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        complete.backHomeBtn();
    }


}
