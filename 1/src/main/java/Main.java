import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Main {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    //First test
    //URLs
    private String googleURL = "https://google.com";
    private String rozetkaURL = "https://rozetka.com.ua/";

    //Links
    private String firstLinkOnTheMainPageXpath = "//a[contains(@href,'http')]/h3";
    private String firstLinkOnTheLaptopPageXpath = "//span[@class='goods-tile__title']";

    //Clickable elements
    private String monitorClickable = "//span[@class='goods-tile__title']";
    private String buyButtonClickable = "//button[@aria-label='Купить']";
    private String checkoutButtonClickable = "//a[@data-testid='cart-receipt-submit-order']";

    //Fields
    private String searchFieldXpath = "//input[@name='q']";
    private String searchFieldRozetkaXpath = "//input[@name='search']";

    //Element presents
    private String basketPresents = "//div[@class='modal__content']";
    private String formCheckoutPresents = "//h1[contains(@class,'checkout-heading')]";
    private String monitorPagePresents = "//rz-product-navbar";

    //Logos
    private String logoXpath = "//img[@title='Pornhub']";





    //Second test
    //Buttons


    /**
     * Methods
     */
    public void setup(String URL){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @BeforeTest
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        actions = new Actions(driver);
    }

    /**
     * Test 1 - Assert that logo is displayed when we click to the first link on the main page
     */
    @Test
    public void test() {
        //Go to the main page
        //Set window to the maximize
        setup(googleURL);

        //Set search field
        WebElement input = driver.findElement(By.xpath(searchFieldXpath));
        input.sendKeys("pornhub" + Keys.ENTER);

        //Click the first link
        WebElement firstLink = driver.findElement(By.xpath(firstLinkOnTheMainPageXpath));
        firstLink.click();

        //Assert that logo is displayed
        WebElement logo = driver.findElement(By.xpath(logoXpath));
        Assert.assertTrue(logo.isDisplayed());
    }

    //hello
    @Test
    public void test2() {
        driver.get(rozetkaURL);

        //Set search field
        WebElement input = driver.findElement(By.xpath(searchFieldRozetkaXpath));
        input.sendKeys("монитор" + Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(monitorClickable)));

        //Click on th first link
        WebElement firstLink1 = driver.findElement(By.xpath(firstLinkOnTheLaptopPageXpath));
        firstLink1.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(monitorPagePresents)));

        //Click on Buy button
        WebElement buyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buyButtonClickable)));
        actions.moveToElement(buyButton).perform();
        buyButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(basketPresents)));

        //Click on Checkout button
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkoutButtonClickable)));
        checkoutButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(formCheckoutPresents)));

    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
