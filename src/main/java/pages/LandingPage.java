package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	public void clickOnMyAccount() {
//		elementUtils.clickOnElement(myAccountDropMenu);
		myAccountDropMenu.click();
	}
	
	public RegisterPage selectRegisterOption() {
//		elementUtils.clickOnElement(registerOption);
//		return new RegisterPage(driver);
		registerOption.click();
		return new RegisterPage(driver);
	}
}
