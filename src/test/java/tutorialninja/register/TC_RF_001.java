package tutorialninja.register;

import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Common;

public class TC_RF_001 {
	WebDriver driver;
	@BeforeMethod
	public void setup() {

		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();	
	}
	@AfterMethod
	public void teardown() {
		if(driver!=null) {driver.quit();}
		
	}
	
	@Test(priority=1)
	public void verifyRegisteration() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Lalit");
		driver.findElement(By.id("input-email")).sendKeys(Common.generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("132165445");
		driver.findElement(By.id("input-password")).sendKeys("ArunLalit123");
		driver.findElement(By.id("input-confirm")).sendKeys("ArunLalit123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		String expectedHeading ="Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);
		
		String properDetailsOne="Congratulations! Your new account has been successfully created!";
		String properDetailsTwo="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String properDetailsThree="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String properDetailsFour="contact us";
		
		String expectedProperDetails=driver.findElement(By.id("content")).getText();
		
		Assert.assertTrue(expectedProperDetails.contains(properDetailsOne));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsTwo));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsThree));
		Assert.assertTrue(expectedProperDetails.contains(properDetailsFour));
		
		driver.findElement(By.xpath("//a[text()='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
}
	

}