package com.orangehrm.listeners;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.MyLog;
import com.orangehrm.framework.components.ReadPropertiesFile;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class TestListeners implements ITestListener, Constants {

	public void onTestStart(ITestResult result) {

		MyLog.logInfo("==============================Starting Test case " + result.getName()
				+ "================================");

	}

	public void onTestSuccess(ITestResult result) {

		MyLog.logInfo("============================== Test case is successfull " + result.getName()
				+ "================================");

	}

	public void onTestFailure(ITestResult result) {

		EventFiringWebDriver driver = (EventFiringWebDriver) result.getTestContext().getAttribute("WebDriver");
		String path = takeScreenshot(driver, result.getMethod().getMethodName());

		MyLog.onlyReport("<a href='" + path + "'>Screen shot</a>");

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	private String takeScreenshot(EventFiringWebDriver driver, String methodName) {

		String fileName = getScreenShotName(methodName);

		File f = null;

		String dir = ReadPropertiesFile.getProperties().get(SCREENSHOT_PATH);
		new File(dir).mkdirs();

		String path = dir + fileName;
		try {
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			f = new File(path);
			FileUtils.copyFile(screenShot, f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (f != null)
			return f.getAbsolutePath().toString();
		else
			return "error while capturing screen shot";
	}

	private String getScreenShotName(String methodName) {
		Date date = new Date();
		return methodName + "_" + date.toString().replace(":", "_").replace(",", "_") + ".png";

	}

}
