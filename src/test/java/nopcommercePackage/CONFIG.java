package nopcommercePackage;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import nopcommercePackageBase.nopcommerceClassBase;

public class CONFIG extends nopcommerceClassBase{

	public CONFIG() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeSuite
	public void start()
	{
		extent = new ExtentReports("G:\\AutomationProject\\TestReport\\index.html" , true);
		extent.addSystemInfo("OS" , "Windows");
		extent.addSystemInfo("Owner" , "Niyra");
		extent.addSystemInfo("Test Name" , "nopcommerce demo store Test");
		extent.addSystemInfo("Language" , "Java");
		extent.addSystemInfo("Framework Design" , "Page Object");
	}
	@AfterSuite
	public void end()
	{
		extent.flush();
	}
}
