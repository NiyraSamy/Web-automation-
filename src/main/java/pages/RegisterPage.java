package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
	private WebDriver driver;
	private By MaleBtn=By.id("gender-male");
	private By FemaleBtn=By.id("gender-female");
	private By FirstName=By.id("FirstName");
	private By LastName=By.id("LastName");
	private By Day=By.name("DateOfBirthDay");
	private By Month=By.name("DateOfBirthMonth");
	private By Year=By.name("DateOfBirthYear");
	private By Email=By.id("Email");
	private By CompanyName=By.id("Company");
	private By NewsletterBtn=By.id("Newsletter");
	private By Password=By.id("Password");
	private By ConfirmPassword=By.id("ConfirmPassword");
	private By RegisterBtn=By.id("register-button");
	private By ContinueBtn=By.className("register-continue-button");
	private By registrationMSG=By.className("result");
	private By NameErrorMSG=By.id("FirstName-error");
	private By EmailErrorMSG=By.id("Email-error");
	private By DifferentPasswordMSG=By.id("ConfirmPassword-error");
	private By InvalidPasswordMSG=By.id("Password-error");
	private By LogoutBtn=By.className("ico-logout");
	
	public void ClickOnMale() {
		driver.findElement(MaleBtn).click();
	}
	public void ClickOnFemale() {
		driver.findElement(FemaleBtn).click();
	}
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void SendFirstName(String Fname) {
		driver.findElement(FirstName).sendKeys(Fname);
	}
	public void SendLastName(String Lname) {
		driver.findElement(LastName).sendKeys(Lname);
	}
	public void SelectDay(String day) {
		Select dayelements=new Select(driver.findElement(Day));
		dayelements.selectByVisibleText(day);
	}
	public void SelectMonth(String month) {
		Select dayelements=new Select(driver.findElement(Month));
		dayelements.selectByVisibleText(month);
	}
	public void SelectYear(String year) {
		Select dayelements=new Select(driver.findElement(Year));
		dayelements.selectByVisibleText(year);
	}
	public void SendEmail(String email) {
		driver.findElement(Email).sendKeys(email);
	}
	public void SendCompanyName(String company) {
		driver.findElement(CompanyName).sendKeys(company);
	}
	public void ClickOnNewsletter() {
		driver.findElement(NewsletterBtn).click();
	}
	public void SendPassword(String pass) {
		driver.findElement(Password).sendKeys(pass);
	}
	public void SendConfirmPassword(String cpass) {
		driver.findElement(ConfirmPassword).sendKeys(cpass);
	}
	public void ClickOnRegister() {
		driver.findElement(RegisterBtn).click();
	}
	public String GetRegistrationMessage() {
		return driver.findElement(registrationMSG).getText();
	}
	public String GetNameErrorMessage() {
		return driver.findElement(NameErrorMSG).getText();
	}
	public String GetEmailErrorMessage() {
		return driver.findElement(EmailErrorMSG).getText();
	}
	public String GetDifferentPasswordMessage() {
		return driver.findElement(DifferentPasswordMSG).getText();
	}
	public String GetInvalidPasswordMessage() {
		return driver.findElement(InvalidPasswordMSG).getText();
	}
	public HomePage ClickOnContinue() {
		driver.findElement(ContinueBtn).click();
		return new HomePage(driver);
	}
	public void ClickOnLogout() {
		driver.findElement(LogoutBtn).click();
	}

}
