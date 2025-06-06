package tutorialninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_010 {

    @Test
    public void verifyRegisteringAccountUsingInvalidEmail() throws IOException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys("amotoori");
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='form-horizontal']")));

        takeScreenshot(driver, "sc1Actual.png");
        Assert.assertFalse(compareTwoScreenshots("sc1Actual.png", "sc1Expected.png"));

        // Second attempt
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='form-horizontal']")));
        takeScreenshot(driver, "sc2Actual.png");

        Assert.assertFalse(compareTwoScreenshots("sc2Actual.png", "sc2Expected.png"));

        // Third attempt
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        WebElement warning = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//input[@id='input-email']/following-sibling::div")));
        String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(warning.getText(), expectedWarningMessage);

        // Final attempt
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@class='form-horizontal']")));
        takeScreenshot(driver, "sc3Actual.png");

        BufferedImage actualImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png"));
        BufferedImage expectedImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff ImgDifference = imgDiffer.makeDiff(expectedImage, actualImg);

        System.out.println("Difference Found: " + ImgDifference.hasDiff());

        driver.quit();
    }

    public static boolean compareTwoScreenshots(String actualFileName, String expectedFileName) throws IOException {
        BufferedImage actualImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\" + actualFileName));
        BufferedImage expectedImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\" + expectedFileName));
        ImageDiffer differ = new ImageDiffer();
        ImageDiff diff = differ.makeDiff(expectedImg, actualImg);
        return !diff.hasDiff();
    }

    private void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File srcScreenshot = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + "\\Screenshots\\" + fileName));
    }
}
