package bddAndSelenium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import pl.szblewski.pages.MyStoreHomePage;
import pl.szblewski.pages.MyStoreLoginPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestInValidLogin {
    private static WebDriver driver;
    WebElement element;
    private MyStoreHomePage myStoreHomePage;
    private MyStoreLoginPage myStoreLoginPage;

    @Given("^User is at the Starting Page$")
    public void enterMail() {

        PhantomJsDriverManager.getInstance().setup();


        driver = new PhantomJSDriver();
        driver.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1800, 700));
        myStoreHomePage = new MyStoreHomePage(driver);
        myStoreLoginPage = new MyStoreLoginPage(driver);

        myStoreHomePage.open();
    }

    @Given("^Click in the Log In button$")
    public void clickSignInButton() {

        myStoreHomePage.goToLoginPage();

    }

    @Given("^User enter Invalid login data (.*) (\\w.*)$")
    public void enterRandomMail(String email,String psw) {

        myStoreLoginPage.inputLoginMail(email);
        myStoreLoginPage.inputLoginPasw(psw);
    }


    @When("^Click Login button$")
    public void clickRegButton() throws InterruptedException, IOException {
        myStoreLoginPage.clickLoginButton();
        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/clickReg.png"));

    }
    @Then("^allert LogIN message should be displaed$")
    public void checkScreenAfterCreatinAccount() throws InterruptedException, IOException {
        Assert.assertTrue(myStoreLoginPage.alertMassage());
        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/tessst.png"));

    }
}

