package msgdemo;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCase3 {
	
	@Test
	public void test3() throws InterruptedException {
		
			

		 System.setProperty("webdriver.chrome.driver", "..\\MGSdemo\\driver\\chromedriver.exe");
		 
		 
		 WebDriver driver=new ChromeDriver();
		  driver.get("https://discover.freshthyme.com/");
		  
		  driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			driver.navigate().refresh();
			
			driver.findElement(By.xpath("//button[@id=\"shopping-selector-parent-process-modal-close-click\"]")).click();
			
			driver.findElement(By.xpath("//span[contains(text(),\"Log In / Register\")]")).click();
			
			driver.findElement(By.xpath("//input[@id=\"login-email\"]")).sendKeys("James.Tester@bullara.com");
			driver.findElement(By.xpath("//input[@id=\"login-password\"]")).sendKeys("PA28-161");
			driver.findElement(By.xpath("//button[@id=\"login-submit\"]")).click();
			Thread.sleep(4000);		
			WebElement menu = driver.findElement(By.xpath("//div[contains(text(),\"MyThyme\")]")); 	
			Actions builder = new Actions(driver); 		
			builder.moveToElement(menu).build().perform(); 		
					
			driver.findElement(By.xpath("//i[@class=\"faf fa-coupon\"]/parent::div")).click();
					 
			String clipText = driver.findElement(By.xpath("//span[@class=\"cell-title-text\"]")).getText();
			
			try {
				List<WebElement> ele=driver.findElements(By.xpath("//button[@coupon=\"offer\"]")) ;
				ele.get(0).click();
				
			} catch (Exception e) {
				System.out.println("There are no new items to be clicked");
			}
			 
			driver.findElement(By.xpath("//span[contains(text(),\"Clipped\")]")).click();
			 
			String clippedText = driver.findElement(By.xpath("//span[@class=\"cell-title-text\"]")).getText();
			 
			 assertEquals(clippedText, clipText, "No match!!");
		
					
			//driver.quit();


	}

}
