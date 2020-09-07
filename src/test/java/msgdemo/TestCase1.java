package msgdemo;

import static org.testng.Assert.assertEquals;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class TestCase1 {

	@Test
	public void test1() throws InterruptedException {
		


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
			String password = person.getPassword();
			
			driver.findElement(By.id("uname")).sendKeys(person.getUsername());
			driver.findElement(By.id("email")).sendKeys(person.getEmail());
			driver.findElement(By.id("pass")).sendKeys(password);
			driver.findElement(By.id("cpass")).sendKeys(password); 
			driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
			
			
			driver.findElement(By.id("fname")).sendKeys(person.getFirstName());
			
			driver.findElement(By.id("lname")).sendKeys(person.getLastName());
			
			Select slc = new Select(driver.findElement(By.id("gender")));
			slc.selectByVisibleText("Male");
			
			
			driver.findElement(By.id("birthdate")).sendKeys(person.getDateOfBirth().toString());
			
			driver.findElement(By.id("address")).sendKeys(person.getAddress().toString().substring(0, 13));
			driver.findElement(By.id("phone")).sendKeys("9821738721");
			
			driver.findElement(By.xpath("//label[contains(text(),\"email\")]")).click();
						
			
			driver.findElement(By.xpath("//button[@onclick=\"nextStep3()\"]")).click();
			
			
			Select slc1 = new Select(driver.findElement(By.id("paymenttype")));
			slc1.selectByVisibleText("Master Card");
			
			driver.findElement(By.id("hname")).sendKeys(person.getFullName());
			driver.findElement(By.id("cardnumber")).sendKeys("3412124567879888");
			driver.findElement(By.id("cvc")).sendKeys("221");
			
			driver.findElement(By.id("expirydate")).sendKeys("June, 2024");
			
			JavascriptExecutor executor = (JavascriptExecutor)driver; 
			executor. executeScript("arguments[0]. click();", driver.findElement(By.xpath("//label[contains(text(),'Aggre with')]"))); 
			
//			driver.findElement(By.xpath("//label[contains(text(),'Aggre with')]")).click();						
					
		   driver.findElement(By.xpath("(//button[contains(text(),'Next')])[3]")).click();
		   
		   int firstnum = Integer.parseInt(driver.findElement(By.xpath("//*[@id='mathfirstnum']")).getAttribute("value").toString());
		   int secondnum = Integer.parseInt(driver.findElement(By.xpath("//*[@id='mathsecondnum']")).getAttribute("value").toString());
          driver.findElement(By.xpath("//*[@id='humanCheckCaptchaInput']")).sendKeys(String.valueOf(firstnum+secondnum));	   
					
          driver.findElement(By.xpath("//*[@id='Submit']")).click();
          
          String errorMessage = driver.findElement(By.xpath("//*[@id='mgsFormSubmit']")).getText();
          assertEquals(errorMessage, "Please fill in the form properly!", "Error message is not correct!!");
					
	     //driver.quit();		
			
			
		
		
	}

}
