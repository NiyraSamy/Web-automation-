package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;
	private By RegisterBtn=By.className("ico-register");

	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public RegisterPage ClickOnRegister() {
		driver.findElement(RegisterBtn).click();
		return new RegisterPage(driver);
	}

}
