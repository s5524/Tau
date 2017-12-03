package pom.test;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pl.szblewski.pages.MyStoreHomePage;
import pl.szblewski.pages.MyStoreLoginPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class TestClass {
    private static WebDriver driver;
    WebElement element;
    private MyStoreHomePage myStoreHomePage;
    private MyStoreLoginPage myStoreLoginPage;

    @BeforeClass
    public static void driverSetup() {

        PhantomJsDriverManager.getInstance().setup();


        //driver = new PhantomJSDriver(capabilities);
        driver = new PhantomJSDriver();
        driver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1800,700));

    }

    @Before
    public void before() {
        myStoreHomePage = new MyStoreHomePage(driver);
        myStoreLoginPage = new MyStoreLoginPage(driver);
        //loginPage = new LoginPage(driver);

    }
    @Test
    public void checkNumber(){
        myStoreHomePage.open();
        Assert.assertTrue(myStoreHomePage.getNumber());
    }
    @Test
    public void checkCartLink(){
        myStoreHomePage.open();
        Assert.assertTrue(myStoreHomePage.getCartLing());
    }

    @Test
    public void checkProductsCount(){
        myStoreHomePage.open();
        myStoreHomePage.clickBestSellers();
        Assert.assertEquals(myStoreHomePage.getProducts().size(),7);
    }

    @Test
    public void checkProduct(){
        myStoreHomePage.open();
        myStoreHomePage.clickBestSellers();
        Assert.assertTrue(myStoreHomePage.isProductOnSellersList());
    }

    @Test
    public void checkIfProductsAreVisible(){
        myStoreHomePage.open();
        myStoreHomePage.clickBestSellers();

        Assert.assertTrue(myStoreHomePage.areBestSellersVisible());
    }

    @Test
    public void checkPointerOverTable() throws IOException {
        myStoreHomePage.open();
        List<WebElement> list = myStoreHomePage.pointerOverLink();
        Assert.assertTrue(list.get(0).isDisplayed());
        Assert.assertTrue(list.get(1).isDisplayed());
        Assert.assertTrue(list.get(2).isDisplayed());
        //Assert.assertEquals(muStoreHomePage.pointerOverLink().get(0),3);
        //Assert.assertEquals(muStoreHomePage.pointerOverLink().get(0),3);
        //System.out.println(myStoreHomePage.pointerOverLink().get(0).getText());
    }

    @Test
    public void checkLoginUrl(){
        myStoreHomePage.open();
        myStoreHomePage.goToLoginPage();

        Assert.assertEquals(myStoreHomePage.goToLoginPage(),"http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @Test
    public void checkInvalidDataInput() throws IOException, InterruptedException {
        myStoreLoginPage.open();
        myStoreLoginPage.inputMail();
        myStoreLoginPage.CreateAccountButton();

        myStoreLoginPage.submitAccountButton();

        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/homePage.png"));

       // myStoreLoginPage.submitAccountButton();
       // myStoreLoginPage.submitAccountButton();
       // myStoreLoginPage.submitAccountButton();
       // myStoreLoginPage.submitAccountButton();


        Assert.assertTrue(myStoreLoginPage.alertMassage());

    }





}
