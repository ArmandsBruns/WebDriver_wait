import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriver_wait {

	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.gecko.driver","D:\\Jar files\\geckodriver-v0.11.1-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		//#### 1. #### Implicit Wait is global wait to all "driver."
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
		driver.get("http://google.com");
		
		driver.findElement(By.name("q")).sendKeys("selenium");
		
		//this wait method increase the time of script, try to don't use this method
		//Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//*[@id='sbse3']/div[2]")).click();
	
		//#### 2. #### Explicit Wait is only to one element wait (use particular condition)
		WebDriverWait wait = new WebDriverWait(driver, 10L);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sbse3']/div[2]"))).click();
		
		
		//#### 3. #### Fluent Wait use for definition of time for retry again (use particular condition)
		Wait<WebDriver> wait2 = new FluentWait<WebDriver>(driver)
			       .withTimeout(10, TimeUnit.SECONDS) //all time in 10 sec
			       .pollingEvery(2, TimeUnit.SECONDS) //after x sec try again, like a loop
			       .ignoring(NoSuchElementException.class)
			       .withMessage("User defined wait timed out after 10 seconds");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='sbse3']/div[2]"))).click();
		
	}

}
