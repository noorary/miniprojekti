package project.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:4566");
		sleep(2);
        WebElement element = driver.findElement(By.linkText("Lis&auml;&auml; lukuvinkki"));
        element.click();
        sleep(2);
		
		element = driver.findElement(By.name("title"));
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
        
        sleep(2);
        element.submit();

        sleep(3);
        
		element = driver.findElement(By.linkText("Koti"));
		
		element.click();
		
		sleep(2);
		
        driver.quit();
	}

	private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
