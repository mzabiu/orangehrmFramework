package com.orangehrm.testcases;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.MyLog;
import com.orangehrm.framework.components.ReadExcelTestData;
import com.orangehrm.framework.components.ReadPropertiesFile;
import com.orangehrm.listeners.DriverListeners;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements Constants {

	public Map<String, String> configPropertyData;
	public Map<String, HashMap<String, String>> excelData;

	public EventFiringWebDriver driver;
	public WebDriver driver1;

	@BeforeSuite(alwaysRun = true)
	public void initialize(XmlTest testngXml) throws Exception {
		configPropertyData = ReadPropertiesFile.getProperties();
		excelData = ReadExcelTestData.getSuiteData("TestData", "Data");
	}

	@BeforeTest(alwaysRun = true)
	public void initDriver(XmlTest xmlData, ITestContext context) {

		DriverListeners eventListener = new DriverListeners();
		String browserName = xmlData.getParameter("browser").toLowerCase();

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver1 = new ChromeDriver();
		} else if ((browserName.equalsIgnoreCase("edge"))) {
			System.setProperty("webdriver.edge.driver", "/src/main/resources/com/orangehrm/drivers/msedgedriver.exe");
			driver1 = new EdgeDriver();
		} else if ((browserName.equalsIgnoreCase("firefox"))) {
			WebDriverManager.firefoxdriver().setup();
			driver1 = new FirefoxDriver();
		} else {
			throw new RuntimeException("Please valid browser name");
		}

		String appUrl = configPropertyData.get(configPropertyData.get(BASEURL));
		driver = new EventFiringWebDriver(driver1);
		driver.register(eventListener);
		driver.manage().window().maximize();
		int implicitWait = Integer.valueOf(configPropertyData.get(IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
		driver.get(appUrl);
		MyLog.logInfo("Launching the URL: " + appUrl);

		context.setAttribute("WebDriver", driver);
	}

	@AfterMethod(alwaysRun = true)
	public void gotoHomePage() {
		driver.switchTo().defaultContent();
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		MyLog.logInfo("Quiting the driver");
		driver.quit();
	}
}
