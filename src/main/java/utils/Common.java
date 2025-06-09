package utils;

import java.util.Date;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;


public class Common {
	public static String generateNewEmail() {
	    return new Date().toString().replaceAll("[\\s:]", "") + "@gmail.com";
		}
	
	
	public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {
		BufferedImage acutualBImg = null;
		BufferedImage expectedBImg = null;
		try {
			acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+actualImagePath));
			expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+expectedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageDiffer imgDiffer = new ImageDiffer();
		ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

		return imgDifference.hasDiff();

	}
}
