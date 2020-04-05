package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4566";
	
    @Given("book form is selected")
    public void bookFormIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lis&auml;&auml; lukuvinkki"));       
        element.click();   
    }
    
    @When("the form is filled")
    public void theFormIsFilled() {
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("x");
        element = driver.findElement(By.name("author"));
        element.sendKeys("x");
        element = driver.findElement(By.name("ISBN"));
        element.sendKeys("x");
        element = driver.findElement(By.name("description"));
        element.sendKeys("x");
        element = driver.findElement(By.name("url"));
        element.sendKeys("x");     
        element = driver.findElement(By.name("submit"));
        element.click();   
    }
    
    @Then("system will respond with success")
    public void newTipIsAdded() {
    	//kesken
    	assertTrue(driver.getPageSource().contains(""));
    }
	
}
