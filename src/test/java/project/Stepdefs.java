package project;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//import cucumber.api.java.After;
//import cucumber.api.java.Before;

public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String  baseUrl = "http://localhost:4566";;

    
    @Before
    public void setup(){
        this.driver = new HtmlUnitDriver();
        this.baseUrl = "http://localhost:4566";
    }

    @Given("form is selected")
    public void bookFormIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));       
        element.click();   
    }
    
    @When("the bookform is filled")
    public void theBookformIsFilled() {
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("booktitle"));
        element.sendKeys("x");
        element = driver.findElement(By.name("bookauthor"));
        element.sendKeys("x");
        element = driver.findElement(By.name("ISBN"));
        element.sendKeys("x");
        element = driver.findElement(By.name("bookdescription"));
        element.sendKeys("x");
        element = driver.findElement(By.name("bookurl"));
        element.sendKeys("x");     
        element = driver.findElement(By.name("booksubmit"));
        element.click();   
    }

    @When("the bookform is filled without title")
    public void theBookformIsFilledWithoutTitle() {
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("booktitle"));
        element.sendKeys("");
        element = driver.findElement(By.name("bookauthor"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("ISBN"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("bookdescription"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("bookurl"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("booksubmit"));
        element.click();
    }

    @When("the videoform is filled")
    public void theVideoformIsFilled() {
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("videotitle"));
        element.sendKeys("x");
        element = driver.findElement(By.name("videoauthor"));
        element.sendKeys("x");
        element = driver.findElement(By.name("videodescription"));
        element.sendKeys("x");
        element = driver.findElement(By.name("videourl"));
        element.sendKeys("x");
        element = driver.findElement(By.name("videosubmit"));
        element.click();
    }

    @When("book and video tips are added")
    public void theTipAreAdded(){
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("booktitle"));
        element.sendKeys("listtestbook");
        element = driver.findElement(By.name("bookauthor"));
        element.sendKeys("listtestbook");
        element = driver.findElement(By.name("ISBN"));
        element.sendKeys("listtestbook");
        element = driver.findElement(By.name("bookdescription"));
        element.sendKeys("listtestbook");
        element = driver.findElement(By.name("bookurl"));
        element.sendKeys("listtestbook");
        element = driver.findElement(By.name("booksubmit"));
        element.click();

        element = driver.findElement(By.name("videotitle"));
        element.sendKeys("listtestvideo");
        element = driver.findElement(By.name("videoauthor"));
        element.sendKeys("listtestvideo");
        element = driver.findElement(By.name("videodescription"));
        element.sendKeys("listtestvideo");
        element = driver.findElement(By.name("videourl"));
        element.sendKeys("listtestvideo");
        element = driver.findElement(By.name("videosubmit"));
        element.click();

    }
    @When("the videoform is filled without title")
    public void theVideoformIsFilledWithoutTitle() {
        driver.get(baseUrl + "/allTips");
        WebElement element = driver.findElement(By.name("videotitle"));
        element.sendKeys("");
        element = driver.findElement(By.name("videoauthor"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("videodescription"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("videourl"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("videosubmit"));
        element.click();
    }

    @Then("system will respond with success")
    public void newTipIsAdded() {
        String content = "x";
    	assertTrue(driver.getPageSource().contains(content));
    }

    @Then("system will respond with failure")
    public void newTipIsAddedFailure() {
        String content = "titletest";
        assertFalse(driver.getPageSource().contains(content));
    }

    @Then("system will respond with correct list")
    public void listWorksCorrectly() {
        String content1 = "listtestvideo";
        String content2 = "listtestbook";
        assertTrue(driver.getPageSource().contains(content1) && driver.getPageSource().contains(content2));
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
