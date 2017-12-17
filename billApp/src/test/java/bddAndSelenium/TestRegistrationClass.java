package bddAndSelenium;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import pl.szblewski.pages.MyStoreHomePage;
import pl.szblewski.pages.MyStoreLoginPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestRegistrationClass {
    private static WebDriver driver;
    WebElement element;
    private MyStoreHomePage myStoreHomePage;
    private MyStoreLoginPage myStoreLoginPage;

    @Given("^User is at the Home Page$")
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

    @Given("^Click in the Sign in button$")
    public void clickSignInButton() {

        myStoreHomePage.goToLoginPage();

    }

    @Given("^User enter random email$")
    public void enterRandomMail() {
        myStoreLoginPage.inputMail();
    }

    @Given("^Click Create an account button$")
    public void clickCreateAccountButton() throws InterruptedException {
        myStoreLoginPage.CreateAccountButton();
    }

    @Given("^enter valid data (\\w*) (\\w.*) (\\w.*) (\\w.*) (\\w.*) (\\w.*) (\\w.*) (\\w.*)$")
    public void enterValidDataOnRegistrationPage(String firstName,String lastName,String pas,String addres,String state,String city,String zipCode,String phone) throws InterruptedException {
        //System.out.println(firstName+" "+lastName);
        myStoreLoginPage.putValidDataToRegisterPageFromString(firstName,lastName,pas,addres,state,city,zipCode,phone);
    }

    @When("^Click on the Register button$")
    public void clickRegButton() throws InterruptedException, IOException {
        myStoreLoginPage.submitAccountButton();
        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/clickReg.png"));

    }
    @Then("^Successful LogIN message should display$")
    public void checkScreenAfterCreatinAccount() throws InterruptedException, IOException {
        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/testPage.png"));

        Assert.assertEquals(myStoreLoginPage.getUrl(),"http://automationpractice.com/index.php?controller=my-account");
        Assert.assertTrue(myStoreLoginPage.checkUserPage());
        Assert.assertTrue(myStoreLoginPage.clickLogout());

    }
}
