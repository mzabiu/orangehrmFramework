package com.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.ReadPropertiesFile;

public class DriverUtils implements Constants {

	JavascriptExecutor js;
	WebDriverWait wait;

	long timeout = Long.valueOf(ReadPropertiesFile.getProperties().get(WEBDRIVER_WAIT_TIME));

	public void clickUsingJs(EventFiringWebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void scrollIntoViewJS(EventFiringWebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void clickUsingJs(EventFiringWebDriver driver, By element) {

		try {
			driver.findElement(element);
		} catch (Exception e) {
			throw new RuntimeException("Not able to find the element " + element.toString());
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(element));
	}

	public void scrollIntoViewJS(EventFiringWebDriver driver, By element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void waitForElement(EventFiringWebDriver driver, WebElement ele) {
		wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public boolean isElementNotPresent(EventFiringWebDriver driver, By by) throws InterruptedException {

		boolean present = false;

		long time = System.currentTimeMillis() + 10000;

		while (System.currentTimeMillis() < time && present) {
			try {
				driver.findElement(by);
				present = true;
			} catch (org.openqa.selenium.NoSuchElementException e) {
			} finally {
				Thread.sleep(500);
			}
		}
		return present;
	}
}
