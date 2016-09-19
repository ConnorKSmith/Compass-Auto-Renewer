package login;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RenewProcess {

	String username;
	String password;
	WebDriver driver;
	
	//Initialize WebDriver and set University username and password
	public RenewProcess(String username, String password){
		System.setProperty(
	            "webdriver.chrome.driver",
	            "C:/Users/Connor/Desktop/Selenium/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		this.username = username;
		this.password = password;
	}
	
	//Returns true if U-Pass is renewed, false otherwise
	public boolean renewCompass() {
		
		
		driver.get("https://upassbc.translink.ca/");
		
		new Select(driver.findElement(By.id("PsiId"))).selectByVisibleText("University of British Columbia");
		
		driver.findElement(By.id("goButton")).click();
		
		driver.findElement(By.id("j_username")).sendKeys(username);
		
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@value='Continue' and @type='submit']")).click();
		
		// Allow 3 seconds on U-Pass website
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		// If renewable, request renewal and return true. else kill the browser and return false
		if(!driver.findElements(By.id("...")).isEmpty()){
		
		driver.findElement(By.id("chk_1")).click();
		
		driver.findElement(By.id("requestButton")).click();
		
		return true;
		}
		
		else {
			
			driver.close();
			return false;
		}
		



	}

}
