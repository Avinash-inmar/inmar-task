package msgdemo;

import static org.testng.Assert.assertEquals;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Testcase2 {
	
	@Test
	public void test2() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "..\\MGSdemo\\driver\\chromedriver.exe");
		  WebDriver driver=new ChromeDriver(); //initializing driver
		  
		  driver.get("https://mgsdemo.mgscoder.com/mgscode/regform/index-2.html"); // loading url
		  
		  driver.manage().window().maximize(); //maximizing window
			driver.manage().deleteAllCookies(); // deleting cookies 
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); //waiting for pageloadtime
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); //implicit time to wait for each element before clicking
			//Test data generation
			Fairy fairy = Fairy.create();
			Person person = fairy.person();
			
			driver.findElement(By.id("uname")).sendKeys(person.getUsername());
			driver.findElement(By.id("email")).sendKeys("test@");
			driver.findElement(By.id("pass")).sendKeys("123");
			driver.findElement(By.id("cpass")).sendKeys("123"); 
			driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
			String errorMessageInPopup = driver.findElement(By.xpath("(//h2)[2]//following-sibling::p")).getText();
			 assertEquals(errorMessageInPopup, "Please Fill the Form Properly!", "Error message is not correct in Popup!!");
			driver.findElement(By.xpath("//button[@class='confirm']")).click();
			String errorMessageForEmail = driver.findElement(By.xpath("//li[contains(text(),'Please enter valid email')]")).getText();
			assertEquals(errorMessageForEmail, "Please enter valid email", "Error message is not correct for email!!");
			String errorMessageForPassword = driver.findElement(By.xpath("//li[contains(text(),'Password should at least 6 character')]")).getText();
			assertEquals(errorMessageForPassword, "Password should at least 6 character", "Error message is not correct in password!!");
			
			//driver.quit();
			

	}

}
