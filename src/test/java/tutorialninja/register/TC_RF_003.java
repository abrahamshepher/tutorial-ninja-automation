package tutorialninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_003 {

	@Test
	
	public void verifyRegistrationWithAllFields() {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		
		driver.findElement(By.id("input-lastname")).sendKeys("Lalit");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("132165445");
		driver.findElement(By.id("input-password")).sendKeys("ArunLalit123");
		driver.findElement(By.id("input-confirm")).sendKeys("ArunLalit123");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
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
		driver.quit();
	}
	
	public static String generateNewEmail() {
	    return new Date().toString().replaceAll("[\\s:]", "") + "@gmail.com";
}

}
