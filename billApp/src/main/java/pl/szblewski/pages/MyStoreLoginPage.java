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
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

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
        Random r = new Random();

       driver.findElement(By.cssSelector("#email_create")).sendKeys(r.nextInt()+"@sz.sz");
    }

    public void CreateAccountButton() throws InterruptedException {

        driver.findElement(By.id("SubmitCreate")).click();
        sleep(3000);
    }

    public void submitAccountButton() throws InterruptedException {
        driver.findElement(By.id("submitAccount")).click();
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
    public void putValidDataToRegisterPage()
    {
        driver.findElement(By.id("customer_firstname")).sendKeys("Adam");
        driver.findElement(By.id("customer_lastname")).sendKeys("Adamowski");
        driver.findElement(By.id("passwd")).sendKeys("12345");
        driver.findElement(By.id("address1")).sendKeys("Wiórkowo");
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText("Colorado");
        driver.findElement(By.id("city")).sendKeys("Wiórkowo");
        driver.findElement(By.id("postcode")).sendKeys("12345");
        new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
        driver.findElement(By.id("phone_mobile")).sendKeys("123456789");
    }

    public void putValidDataToRegisterPageFromString(String name,String lastName,String pas,String adres,String state,String city,String postcode,String phone)
    {
        driver.findElement(By.id("customer_firstname")).sendKeys(name);
        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
        driver.findElement(By.id("passwd")).sendKeys(pas);
        driver.findElement(By.id("address1")).sendKeys(adres);
        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("postcode")).sendKeys(postcode);
        new Select(driver.findElement(By.id("id_country"))).selectByVisibleText("United States");
        driver.findElement(By.id("phone_mobile")).sendKeys(phone);
    }

     public boolean checkUserPage()
    {
        return driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/h1")).isDisplayed()==true;
    }

    public boolean clickLogout()
    {
        driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a")).click();
        return driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a")).isDisplayed();

    }
    public void inputLoginMail(String mail)
    {
        driver.findElement(By.id("email")).sendKeys(mail);

    }
    public void inputLoginPasw(String psw) {


        driver.findElement(By.id("passwd")).sendKeys(psw);

    }

    public void clickLoginButton() {


        driver.findElement(By.id("SubmitLogin")).click();

    }
    public boolean alertWrongDataMassage()
    {
        return driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[1]")).isDisplayed();
    }

}
