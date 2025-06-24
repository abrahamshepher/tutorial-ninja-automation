package tutorialninja.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LandingPage;
import tutorialninja.base.Base;
import utils.Common;

public class Login extends Base{

	WebDriver driver;
	Properties prop;
	LandingPage landingPage;
	
	@BeforeMethod
	
	public void setup() {
	driver = openBrowserAndApplication();
	prop=Common.loadProperties();
	LandingPage landingPage= new LandingPage(driver);
	landingPage.clickOnMyAccount();
	loginPage=landingPage.selectLoginOption();
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	@Test
	public void verifyLoginWithValidCredentials() {
		Assert.assertTrue(loginPage.didWeNaviateToLoginPage());
		loginPage.enterEmail(prop.getProperty("existingEmail"));
		loginPage.enterPassword(prop.getProperty("validPassword"));
		accountPage=loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.isUserLoggedIn());
		Assert.assertTrue(accountPage.didWenavigateToAccountPage());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
