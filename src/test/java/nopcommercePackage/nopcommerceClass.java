package nopcommercePackage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import nopcommercePackageBase.nopcommerceClassBase;

public class nopcommerceClass extends nopcommerceClassBase{
	
	public nopcommerceClass() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static ATUTestRecorder recorder;
	@BeforeMethod
	public void login(Method method) throws ATUTestRecorderException
	{
		
		recorder = new ATUTestRecorder("G:\\AutomationProject\\TestReport",method.getName(),false);
		recorder.start();
		logger = extent.startTest(method.getName());
		Browser(prop.getProperty("URL"));
	}
	
	@SuppressWarnings("deprecation")
	@Test (priority=1)
	public void Checkh_Header_Upper() throws InterruptedException
	{
		Rgister();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		Login();
		Wishlist();
		ShoppingCart();
	}
	
	@Test (priority=2)
	public void Search_Without_Text()
	{
		Search_Without_Text_fun();
	}
	
	
	@Test (dataProvider = "testdata" , priority=3)
	public void Search_With_Text(String search) throws InterruptedException
	{
		Search_With_Text_fun(search);
	}
	
	
	@Test (priority=4)
	public void Check_Top_Menu_NotMobile() throws InterruptedException
	{
		
		open_giftcards();
		open_Jewelry();
		open_Books();
		open_Digitaldownloads();
		
		movecursor_apparel();
		movecursor_electronics();
		movecursor_computers();
		
	}
	
	@Test (priority=5)
	public void Check_Home_Page_Category_Grid()
	{
		img_Electronics();
		img_Apparel();
		img_Digitaldownloads();
	}
	
	@Test (priority=6)
	public void Check_Home_Page_Product_Grid()
	{
		img_Build_your_own_computer();
		img_Apple_MacBook_Pro_13inch();
		img_HTC_One_M8_Android();
		img_$25_Virtual_Gift_Card();
	}
	
	
	@Test (priority=7)
	public void Check_News_List_HomePage() throws InterruptedException
	{
		News_New_online_store_is_open();
		News_nopCommerce_new_release();
		News_About_nopCommerce();
		News_View_News_Archive();
	}
	
	@Test (priority=8)
	public void Add_To_CompareList() throws InterruptedException
	{
		CompareList_fun();
	}
	
	
	@Test (dataProvider = "Producttestdata" , priority=9)
	public void Add_to_Cart_with_all_data(String RN, String RE , String MN , String ME, String MESS)
	{
		Add_to_Cart_fun(RN,RE,MN,ME,MESS , true);
	}
	
	@Test (dataProvider = "Producttestdata" , priority=10)
	public void NotAdd_to_Cart_without_FirstField(String RN, String RE , String MN , String ME, String MESS)
	{
		Add_to_Cart_fun("",RE,MN,ME,MESS , false);
	}
	
	@Test (dataProvider = "Producttestdata" , priority=11)
	public void NotAdd_to_Cart_without_SecondField(String RN, String RE , String MN , String ME, String MESS)
	{
		Add_to_Cart_fun(RN,"",MN,ME,MESS , false);
	}
	
	@Test (dataProvider = "Producttestdata" , priority=12)
	public void NotAdd_to_Cart_without_ThirdField(String RN, String RE , String MN , String ME, String MESS)
	{
		Add_to_Cart_fun(RN,RE,"",ME,MESS , false);
	}
	
	@Test (dataProvider = "Producttestdata" , priority=13)
	public void NotAdd_to_Cart_without_FourthField(String RN, String RE , String MN , String ME, String MESS)
	{
		Add_to_Cart_fun(RN,RE,MN,"",MESS , false);
	}
	
	@DataProvider
	public Object[][] testdata() throws Throwable
	{
		Object data[][] = nopcommerceClassBase.import_from_excel("Sheet1");
		return data;
	}
	
	
	@DataProvider
	public Object[][] Producttestdata() throws Throwable
	{
		Object data[][] = nopcommerceClassBase.import_from_ProductExcel("Sheet1");
		return data;
	}
	
	@AfterMethod
	public void tearDown(Method method , ITestResult result) throws ATUTestRecorderException, InterruptedException
	{
		Thread.sleep(5000);
		
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Pass");
			logger.log(LogStatus.PASS, "<a href='"+result.getName()+".mov" + "'><span class='lable info'>Download Video</span></a>");
		}
		else if (result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test Failed");
			logger.log(LogStatus.FAIL, "<a href='"+result.getName()+".mov" + "'><span class='lable info'>Download Video</span></a>");
		}
		else
		{
			logger.log(LogStatus.SKIP, "Test Skipped");
		}
		
		driver.quit();
		recorder.stop();
	}

}
