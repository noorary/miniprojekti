package project;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {

    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4566";

    @Before
    public void setup() {
        this.driver = new HtmlUnitDriver();
        this.baseUrl = "http://localhost:4566";
//        driver.get(baseUrl);
    }

    @Given("page with reading tip form is selected")
    public void tipFormIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
    }

    @Given("frontpage is opened")
    public void frontpageIsOpened() {
        driver.get(baseUrl);
    }

    @When("the tip form is filled correctly")
    public void tipformIsFilled() {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("Harry Porter");
        element = driver.findElement(By.name("author"));
        element.sendKeys("J.K. Rowling");
        element = driver.findElement(By.name("description"));
        element.sendKeys("A book with magic");
        element = driver.findElement(By.name("url"));
        element.sendKeys("www.jk.com");
        element = driver.findElement(By.name("submit"));
        element.click();
    }

    @When("the tip form is filled without title")
    public void theBookformIsFilledWithoutTitle() {
        WebElement element = driver.findElement(By.name("author"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("description"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("url"));
        element.sendKeys("titletest");
        element = driver.findElement(By.name("submit"));
        element.click();
    }

    @When("all tips are added")
    public void theTipAreAdded() {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys("Dear diary");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Dianna");
        element = driver.findElement(By.name("description"));
        element.sendKeys("A diary");
        element = driver.findElement(By.name("url"));
        element.sendKeys("www.dd.com");
        element = driver.findElement(By.name("submit"));
        element.click();

        element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();

        element = driver.findElement(By.name("title"));
        element.sendKeys("Without you");
        element = driver.findElement(By.name("author"));
        element.sendKeys("Mariah Carey");
        element = driver.findElement(By.name("description"));
        element.sendKeys("A beautiful song");
        element = driver.findElement(By.name("url"));
        element.sendKeys("www.wy.com");
        element = driver.findElement(By.name("submit"));
        element.click();

    }

    @When("the tip is deleted")
    public void theTipIsDeleted() throws InterruptedException {
        WebElement element = driver.findElement(By.id("poisto1"));
        WebElement a = element.findElement(By.linkText("Poista lukuvinkki"));
        a.click();

    }

    @Then("system will respond with success")
    public void newTipIsAdded() {
        String content = "Harry Porter";
        assertTrue(driver.getPageSource().contains(content));
    }

    @Then("system will respond with failure")
    public void newTipIsAddedFailure() {
        String content = "titletest";
        assertFalse(driver.getPageSource().contains(content));
    }

    @Then("system will respond with correct list")
    public void listWorksCorrectly() {
        String content1 = "Dear diary";
        String content2 = "Without you";
        assertTrue(driver.getPageSource().contains(content1) && driver.getPageSource().contains(content2));
    }

    @Then("system will respond with delete success")
    public void deleteIsSuccessful() {

        String content = "Kukkakaali";
        assertFalse(driver.getPageSource().contains(content));

    }

    @Given("a reading tip with title {string}, author {string}, description {string} and url {string} is added")
    public void addNewTip(String title, String author, String description, String url) {
        goToTipForm();
        addTip(title, author, description, url);
    }

    @And("a reading tip with title {string}, author {string}, description {string} and url {string} is added")
    public void addAnotherTip(String title, String author, String description, String url) {
        goToTipForm();
        addTip(title, author, description, url);
    }

    @When("a tag with name {string} is added to the reading tip with title {string}")
    public void addNewTag(String name, String title) {
        addTag(name, title);
    }

    @And("a tag with name {string} is added to the reading tip with title {string}")
    public void addAnotherTag(String name, String title) {
        addTag(name, title);
    }

    @Then("one tag with name {string} can be found in the tag column of the reading tip with title {string}")
    public void findOneTag(String name, String title) {
        findTag(name, title);
    }

    @And("one tag with name {string} can be found in the tag column of the reading tip with title {string}")
    public void findAnotherTag(String name, String title) {
        findTag(name, title);
    }

    private void goToTipForm() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("Lisää lukuvinkki"));
        element.click();
    }

    private void addTip(String title, String author, String description, String url) {
        WebElement element = driver.findElement(By.name("title"));
        element.sendKeys(title);
        element = driver.findElement(By.name("author"));
        element.sendKeys(author);
        element = driver.findElement(By.name("description"));
        element.sendKeys(description);
        element = driver.findElement(By.name("url"));
        element.sendKeys(url);
        element = driver.findElement(By.name("submit"));
        element.click();
    }

    private void addTag(String name, String title) {

    }

    private void findTag(String name, String title) {

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
