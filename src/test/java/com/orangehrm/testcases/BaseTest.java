package com.orangehrm.testcases;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.ReadExcelTestData;
import com.orangehrm.framework.components.ReadPropertiesFile;
import com.orangehrm.listeners.DriverListeners;
import com.orangehrm.pages.MenuPage;
import com.orangehrm.pages.dashboard.DashBoardPage;
import com.orangehrm.pages.login.LoginPage;
import com.orangehrm.reporting.MyLog;
import com.orangehrm.utils.DriverUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements Constants {

	public Map<String, String> configPropertyData;
	public Map<String, HashMap<String, String>> excelData;

	public EventFiringWebDriver driver;
	public WebDriver driver1;

	boolean jenkinsRun = false;

	@BeforeSuite(alwaysRun = true)
	public void initialize(XmlTest testngXml) throws Exception {
		configPropertyData = ReadPropertiesFile.getProperties();
		excelData = ReadExcelTestData.getSuiteData("TestData", "Data");
		if (System.getProperty("jenkins.buildurl") != null) {
			jenkinsRun = true;
			MyLog.logInfo(
					"The execution is triggered from jenkins and the url is " + System.getProperty("jenkins.buildurl"));
		} else
			MyLog.logInfo("The execution is triggered from Local ");
	}

	@BeforeTest(alwaysRun = true)
	public void initDriver(XmlTest xmlData, ITestContext context) {

		DriverListeners eventListener = new DriverListeners();
		String browserName = xmlData.getParameter("browser").toLowerCase().trim();

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();

			if (jenkinsRun) {
				MyLog.logInfo("The execution is triggered from jenkins and the url is "
						+ System.getProperty("jenkins.buildurl"));

				chromeOptions
						.setBinary("C:\\Users\\Zabiulla_Pro\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			}
			driver1 = new ChromeDriver(chromeOptions);
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
	public void gotoHomePage(Method m, ITestResult r) throws Exception {

		Reporter.setCurrentTestResult(r);

		MenuPage menu = new MenuPage(driver);
		DriverUtils commonUtils = new DriverUtils();
		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		if (commonUtils.isElementNotPresent(driver, dashBoardPage.dashBoardLabel)) {
			menu.getItem(menu.MENU_DASHBOARD).click();
			assertTrue(dashBoardPage.getLblDashboard().isDisplayed(), "Dashboard is not displayed");
		}
		MyLog.logInfo("Currently in Dashboard page");
		MyLog.logInfo("============================== Executed " + m.getName() + "================================");
	}

	@BeforeMethod(alwaysRun = true)
	public void setLog(Method m, ITestResult r, ITestContext c) throws UnknownHostException {

		StringBuilder b = new StringBuilder();
		Reporter.setCurrentTestResult(r);

		MyLog.logInfo("============================== Starting Test case " + m.getName()
				+ "================================");
		MyLog.logInfo("The test case is executed on the machine name: " + InetAddress.getLocalHost().getHostName());

		// code to get the grouping of the test case
		for (String s : m.getAnnotation(Test.class).groups())
			b.append(s + ", ");
		c.setAttribute("groups", b.toString());
		MyLog.logInfo("Test grouping: " + b);
		MyLog.logInfo("The application url is " + driver.getCurrentUrl());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login();
		Reporter.log("Login to application successfull");
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		MyLog.logInfo("Quiting the driver");
		//driver.quit();
	}
}
