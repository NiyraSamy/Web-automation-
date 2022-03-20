package tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.ExcelReader;
import pages.RegisterPage;

public class CreateAccountTest extends BaseTest{
//Happy Scenario
@DataProvider(name = "ExcelData")
	public Object[][] userRegisterData() throws IOException{
	ExcelReader ER=new ExcelReader();
		return ER.getExcelData();
	}
@Test(dataProvider = "ExcelData")
	public void CreateAccountWithValidData(String Firstname,String Lastname,
			String Day,String Month,String Year,String Email,String CompanyName,
			String Password,String ConfirmPassword) {
	RegisterPage Registerpage=homePage.ClickOnRegister();
	Registerpage.ClickOnFemale();
	Registerpage.SendFirstName(Firstname);
	Registerpage.SendLastName(Lastname);
	Registerpage.SelectDay(Day);
	Registerpage.SelectMonth(Month);
	Registerpage.SelectYear(Year);
	Registerpage.SendEmail(Email);
	Registerpage.SendCompanyName(CompanyName);
	Registerpage.SendPassword(Password);
	Registerpage.SendConfirmPassword(ConfirmPassword);
	Registerpage.ClickOnRegister();
	assertTrue(Registerpage.GetRegistrationMessage().contains("Your registration completed"));
	Registerpage.ClickOnContinue();
	Registerpage.ClickOnLogout();
	}
	
//Leave Any Field Empty(FirstName)
@Test
	public void LeaveAnyFieldEmpty() {
	RegisterPage Registerpage=homePage.ClickOnRegister();
	Registerpage.SendLastName("yy");
	Registerpage.SendEmail("m514@tmails.net");
	Registerpage.SendPassword("123456");
	Registerpage.SendConfirmPassword("123456");
	Registerpage.ClickOnRegister();
	assertTrue(Registerpage.GetNameErrorMessage().contains("First name is required."));
	}

//Enter InValid Email
@Test
	public void InValidEmail() {
	RegisterPage Registerpage=homePage.ClickOnRegister();
	Registerpage.SendFirstName("xx");
	Registerpage.SendLastName("yy");
	Registerpage.SendEmail("qweda.com");
	Registerpage.SendPassword("123456");
	Registerpage.SendConfirmPassword("123456");
	Registerpage.ClickOnRegister();
	assertTrue(Registerpage.GetEmailErrorMessage().contains("Wrong email"));
	}


//Enter InValid Password (Less Than 6 characters)
@Test
	public void InValidPassword() {
	RegisterPage Registerpage=homePage.ClickOnRegister();
	Registerpage.SendFirstName("xx");
	Registerpage.SendLastName("yy");
	Registerpage.SendEmail("m5184@tmails.net");
	Registerpage.SendPassword("123");
	Registerpage.SendConfirmPassword("123569");
	Registerpage.ClickOnRegister();
	assertTrue(Registerpage.GetInvalidPasswordMessage().contains("must have at least 6 characters"));
	}

//Enter Different Passwords
@Test
	public void DifferentPasswords() {
	RegisterPage Registerpage=homePage.ClickOnRegister();
	Registerpage.SendFirstName("xx");
	Registerpage.SendLastName("yy");
	Registerpage.SendEmail("m514@tmails.net");
	Registerpage.SendPassword("123456");
	Registerpage.SendConfirmPassword("564278");
	Registerpage.ClickOnRegister();
	assertTrue(Registerpage.GetDifferentPasswordMessage().contains("The password and confirmation password do not match."));
	}
}
