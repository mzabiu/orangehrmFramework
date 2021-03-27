package com.orangehrm.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BasePage {

	public EventFiringWebDriver driver;

	/**
	 * constructor to intialize the driver
	 * 
	 * @param driver
	 * 
	 */
	public BasePage(EventFiringWebDriver driver) {
		this.driver = driver;
		init();
	}

	/**
	 * Method to initialise the web elements for a given page
	 */

	public void init() {
		PageFactory.initElements(driver, this);
	}

}
