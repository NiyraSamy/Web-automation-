package nopcommercePackageBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import nopcommercePackageUTILE.nopcommerceClassWebListner;

public class nopcommerceClassBase {
	
	public static Properties prop ;
	public static WebDriver driver ;
	@SuppressWarnings("deprecation")
	public static EventFiringWebDriver e_driver ;
	public static nopcommerceClassWebListner weblistner ;
	public static Actions actions ;
	
	public static ExtentReports extent ;
	public static ExtentTest logger ;
	
	public nopcommerceClassBase() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream("G:\\AutomationProject\\src\\main\\java\\nopcommercePackageCONFIG\\nopcommerceClassConfigProperties");
		prop.load(fis);
		
	}
	
	public void Browser(String URL)
	{
		System.setProperty("webdriver.chrome.driver" , "G:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver(); 
		
		e_driver = new EventFiringWebDriver(driver) ;
		weblistner = new nopcommerceClassWebListner();
		e_driver.register(weblistner);
		driver = e_driver ;
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.MINUTES);
	}
	
	public static Object[][] import_from_excel(String SheetName) throws IOException 
	{
		
		File file = new File("G:\\AutomationProject\\src\\main\\java\\nopcommercePackageBase\\nopcommerceSEARCHFILE.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(SheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		Object data[][] = new Object[rows][cols]; 
		
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
			{
				data[r][c] = sheet.getRow(r+1).getCell(c).toString();
			}
		return data ;
	}
	public static Object[][] import_from_ProductExcel(String SheetName) throws IOException 
	{
		
		File file = new File("G:\\AutomationProject\\src\\main\\java\\nopcommercePackageBase\\nopcommerceSEARCHFILE - Copy.xlsx");
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(SheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		Object data[][] = new Object[1][cols]; 
		
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
			{
				data[r][c] = sheet.getRow(r+1).getCell(c).toString();
			}
		return data ;
	}
	
	public void Search_With_Text_fun(String search) throws InterruptedException
	{
		WebElement SearchTextBox = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		SearchTextBox.sendKeys(search);
		WebElement SearchButton = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[2]/form[1]/button[1]"));
		SearchButton.click();
		
		Thread.sleep(1000);
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[3]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
	
	public void Search_Without_Text_fun()
	{
		WebElement SearchTextBox = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		SearchTextBox.sendKeys("");
		WebElement SearchButton = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[2]/form[1]/button[1]"));
		SearchButton.click();
		
		String expectedresult = "exist";
		String actualresult ;
		
		WebDriverWait w = new WebDriverWait(driver, 50);
		
		if(w.until(ExpectedConditions.alertIsPresent())==null)
		{
			actualresult = "exist" ;
		    System.out.println("Alert not exists");
		}
	     else
	     {
	    	 actualresult = "notexist" ;
	    	 System.out.println("Alert exists");
	     }
	      
		 Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
	}
	
	public void movecursor_computers_desktops() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/a[1]"));
		actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/desktops";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_computers_notebooks() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/a[1]"));
		actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[2]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/notebooks";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_computers_software() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/a[1]"));
		actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/software";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_computers() throws InterruptedException
	{
		movecursor_computers_desktops();
		movecursor_computers_notebooks();
		movecursor_computers_software();
	}
	
	public void movecursor_electronics_cameraphoto() throws InterruptedException
	{
		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/a[1]"));
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[1]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/camera-photo";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_electronics_cellphones() throws InterruptedException
	{
		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/a[1]"));
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/cell-phones";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_electronics_others() throws InterruptedException
	{
		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/a[1]"));
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/others";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_electronics() throws InterruptedException
	{
		movecursor_electronics_cameraphoto();
		movecursor_electronics_cellphones();
		movecursor_electronics_others();
	}
	
	public void movecursor_apparel_shoes() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/a[1]"));
		actions = new Actions(driver);
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/ul[1]/li[1]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/shoes";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_apparel_clothing() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/a[1]"));
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/ul[1]/li[2]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/clothing";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_apparel_accessories() throws InterruptedException
	{
		WebElement menuOption = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/a[1]"));
		actions.moveToElement(menuOption).perform();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[3]/ul[1]/li[3]/a[1]")).click();
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/accessories";
		String actualresult = driver.getCurrentUrl();
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	public void movecursor_apparel() throws InterruptedException
	{
		movecursor_apparel_shoes();
		movecursor_apparel_clothing();
		movecursor_apparel_accessories();
	}
	
	public void open_giftcards() throws InterruptedException
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[7]/a[1]")).click();
		
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/gift-cards";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	
	public void open_Jewelry() throws InterruptedException
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[6]/a[1]")).click();
		
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/jewelry";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	
	public void open_Books() throws InterruptedException
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[5]/a[1]")).click();
		
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/books";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	
	public void open_Digitaldownloads() throws InterruptedException
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/ul[1]/li[4]/a[1]")).click();
		
		Thread.sleep(2000);
		String expectedresult = "https://demo.nopcommerce.com/digital-downloads";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		Thread.sleep(2000);
		WebElement down_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[4]/div[1]"));
		actions = new Actions(driver);
		actions.moveToElement(down_element).build().perform();
		
		Thread.sleep(2000);
		WebElement up_element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[1]/div[2]/div[1]/a[1]/img[1]"));
		actions = new Actions(driver);
		actions.moveToElement(up_element).build().perform();
		driver.navigate().back();
	}
	
	// Category
	public void img_Electronics()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/electronics";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		driver.navigate().back();
	}
	public void img_Apparel()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/apparel";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		driver.navigate().back();
	}
	public void img_Digitaldownloads()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[3]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/digital-downloads";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		driver.navigate().back();
	}

	//Featured products
	public void img_Build_your_own_computer()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[1]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/build-your-own-computer";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[9]/div[1]/button[1]"));
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		driver.navigate().back();
	}
	public void img_Apple_MacBook_Pro_13inch()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[2]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/apple-macbook-pro-13-inch";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[9]/div[2]/button[1]"));
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		driver.navigate().back();
	}
	public void img_HTC_One_M8_Android()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[3]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/htc-one-m8-android-l-50-lollipop";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[9]/div[2]/button[1]"));
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		driver.navigate().back();
	}
	public void img_$25_Virtual_Gift_Card()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[4]/div[1]/div[1]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/25-virtual-gift-card";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		WebElement element = driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[7]/div[1]/button[1]"));
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		driver.navigate().back();
	}
	
	//NEWS	
	public void News_New_online_store_is_open() throws InterruptedException
	{
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[1]/div[3]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/new-online-store-is-open";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void News_nopCommerce_new_release() throws InterruptedException
	{
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[2]/div[3]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/nopcommerce-new-release";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void News_About_nopCommerce() throws InterruptedException
	{
		driver.findElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[5]/div[2]/div[3]/div[3]/a[1]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/about-nopcommerce";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void News_View_News_Archive() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[contains(text(),'View News Archive')]")).click();
		
		String expectedresult = "https://demo.nopcommerce.com/news";
		String actualresult = driver.getCurrentUrl();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	
	//header_links_wrapper
	public void Rgister() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[contains(text(),'Register')]")).click();
		
		String expectedresult = "nopCommerce demo store. Register";
		String actualresult = driver.getTitle();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void Login() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[contains(text(),'Log in')]")).click();
		
		String expectedresult = "nopCommerce demo store. Login";
		String actualresult = driver.getTitle();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void Wishlist() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[contains(text(),'Wishlist')]")).click();
		
		String expectedresult = "nopCommerce demo store. Wishlist";
		String actualresult = driver.getTitle();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void ShoppingCart() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[contains(text(),'Shopping cart')]")).click();
		
		String expectedresult = "nopCommerce demo store. Shopping Cart";
		String actualresult = driver.getTitle();
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
		Thread.sleep(2000);
		driver.navigate().back();
	}
	public void CompareList_fun()
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[4]/div[1]/div[2]/div[3]/div[2]/button[2]")).click();
		boolean eleSelected= driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]")).isDisplayed();
		Assert.assertTrue(eleSelected,"ActualResult NOT EQUAL ExpectedResult");
	}
	
	public void Add_to_Cart_fun(String RN , String RE , String MN , String ME , String MESS , boolean ER)
	{
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[1]/div[4]/div[2]/div[4]/div[1]/div[2]/div[3]/div[2]/button[1]")).click();
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[1]/input[1]")).sendKeys(RN);
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[2]/input[1]")).sendKeys(RE);
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[3]/input[1]")).sendKeys(MN);
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[4]/input[1]")).sendKeys(ME);
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[5]/div[5]/textarea[1]")).sendKeys(MESS);
		
		driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[7]/div[1]/button[1]")).click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean exists = driver.findElements(By.xpath("//a[contains(text(),'shopping cart')]")).size() != 0 ;
		
		boolean expectedresult = ER;
		boolean actualresult = exists ;
		
		Assert.assertEquals(actualresult,expectedresult,"ActualResult NOT EQUAL ExpectedResult");
	}
}
