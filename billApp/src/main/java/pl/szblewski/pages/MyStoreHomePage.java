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

public class MyStoreHomePage
{
    private WebDriver driver;


    public MyStoreHomePage (WebDriver driver){
        this.driver = driver;
    }

    public boolean getNumber()
    {
        return driver.findElement(By.xpath("//STRONG[text()='0123-456-789']/self::STRONG")) != null;
    }
    public boolean getCartLing()
    {
        return driver.findElement(By.xpath("//B[text()='Cart']/self::B")) != null;

    }
    public List<WebElement> getProducts()
    {

        return driver.findElement(By.xpath("//*[@id=\"blockbestsellers\"]")).findElements(By.tagName("li"));
    }

    public void clickBestSellers()
    {
        driver.findElement(By.xpath("//*[@id=\"home-page-tabs\"]/li[2]/a")).click();
    }


    public boolean isProductOnSellersList() {
        WebElement productLink = driver.findElement( By.cssSelector("#blockbestsellers"));
        return productLink.findElement(By.linkText("Printed Chiffon Dress")) != null;
    }

    public boolean areBestSellersVisible() {
        List< WebElement > sellers = driver.findElement(By.xpath("//*[@id=\"blockbestsellers\"]")).findElements(By.tagName("li"));

        for ( WebElement element : sellers) {
            if(!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public List<WebElement> pointerOverLink() throws IOException {
        Actions builder = new Actions(driver);
        Action mouseOverHome = builder.moveToElement(driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]"))).build();
        mouseOverHome.perform();
        File f = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(f,new File("build/homePage.png"));



        return driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]")).findElements(By.tagName("li"));
    }


    public String goToLoginPage()
    {
            driver.findElement(By.xpath("/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")).click();
            return driver.getCurrentUrl();
    }

    public void open() {
        driver.get("http://automationpractice.com");
    }

}


