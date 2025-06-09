package tutorialninja.register;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utils.Common;
public class TC_RF_027 {
	@Test
	public void verifyUIOfRegisterAccountPage() throws IOException {
		//If check was to tackle each and everycase i would go through the steps defined in this picture but here i choose to take screenshots approach
		// https://drive.google.com/file/d/1X6EPJW-Ojl3Xpv99qrnOV4wU8FuekmtO/view
		
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//		driver.get("https://tutorialsninja.com/demo/");
//		
//		driver.findElement(By.xpath("//span[text()='My Account']")).click();
//		driver.findElement(By.linkText("Register")).click();
//		
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
//		
//		try {
//			FileHandler.copy(srcScreenshot,new File(System.getProperty("user.dir")+"\\Screenshots\\actualRegisterPageUI.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		Assert.assertTrue(Common.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualRegisterPageUI.png", System.getProperty("user.dir")+"\\Screenshots\\expectedRegisterPageUI.png"));
		
//		driver.quit();
		
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='My Account']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register"))).click();

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png"));
		Assert.assertTrue(compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\actualRegisterPageUI.png", System.getProperty("user.dir")+"\\Screenshots\\expectedRegisterPageUI.png"));

		driver.quit();

		
	}
	
	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage acutualBImg = null;
		BufferedImage expectedBImg = null;
		try {
			acutualBImg = ImageIO.read(new File(actualImagePath));
			expectedBImg = ImageIO.read(new File(expectedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

		return imgDifference.hasDiff();

	}
}
