package com.orangehrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverUtils {

	JavascriptExecutor js;

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
			throw new RuntimeException("Not able to find the element "+element.toString());
		}

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(element));
	}

	public void scrollIntoViewJS(EventFiringWebDriver driver, By element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
