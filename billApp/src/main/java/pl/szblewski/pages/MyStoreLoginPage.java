package pl.szblewski.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.Thread.sleep;

public class MyStoreLoginPage {

    private WebDriver driver;


    public MyStoreLoginPage (WebDriver driver){
        this.driver = driver;
    }

    public void open()
    {

        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    public void inputMail()
    {
       driver.findElement(By.cssSelector("#email_create")).sendKeys("sz@sz.sz");
    }

    public void CreateAccountButton() throws InterruptedException {

        driver.findElement(By.id("SubmitCreate")).click();
        sleep(3000);
    }

    public void submitAccountButton() throws InterruptedException {
        driver.findElement(By.id("submitAccount")).submit();
    sleep(3000);

    }

    public String getUrl()
    {
        return driver.getCurrentUrl().toString();
    }
    public boolean alertMassage()
    {
        return driver.findElement(By.className("alert")).isDisplayed()==true;
    }




}
