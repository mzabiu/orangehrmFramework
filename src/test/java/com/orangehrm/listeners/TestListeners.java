package com.orangehrm.listeners;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.ReadPropertiesFile;
import com.orangehrm.reporting.MyLog;

public class TestListeners implements ITestListener, Constants {

	ConcurrentMap<String, Map<String, String>> finalMap;
	ConcurrentMap<String, String> testCaseData;

	public void onTestStart(ITestResult result) {

		finalMap = new ConcurrentHashMap<String, Map<String, String>>();
		testCaseData = new ConcurrentHashMap<String, String>();

	}

	public void onTestSuccess(ITestResult result) {
		testCaseData.put("TestStatus", "Passed");
		finalMap.put(result.getName(), testCaseData);
	}

	public void onTestFailure(ITestResult result) {
		testCaseData.put("TestStatus", "Failed");
		finalMap.put(result.getName(), testCaseData);
		EventFiringWebDriver driver = (EventFiringWebDriver) result.getTestContext().getAttribute("WebDriver");
		String path = takeScreenshot(driver, result.getMethod().getMethodName());

		MyLog.onlyReport("<a href='" + path + "'>Screen shot</a>");

	}

	public void onTestSkipped(ITestResult result) {
		testCaseData.put("TestStatus", "Skipped");
		finalMap.put(result.getName(), testCaseData);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	/**
	 * This will takestheSCreen
	 * 
	 * @param driver
	 * @param methodName
	 * @return
	 */

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

	/**
	 * 
	 * Creating a unique screen shot name everytime
	 * 
	 * @param methodName
	 * @return
	 */
	private String getScreenShotName(String methodName) {
		Date date = new Date();
		return methodName + "_" + date.toString().replace(":", "_").replace(",", "_") + ".png";

	}

}
