package tests;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;


public class BaseTest {
	private WebDriver driver;
	protected HomePage homePage;
	
@BeforeClass
	public void SetUP()
	{
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver=new ChromeDriver();	
		driver.manage().window().setSize(new Dimension(1024, 768));
		goHome();		
	}

@BeforeMethod
public void goHome(){
    driver.get("https://demo.nopcommerce.com/");  
    homePage = new HomePage(driver);
}

/*@AfterClass
public void tearDown(){
    driver.quit();
}*/

}
