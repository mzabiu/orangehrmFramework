package com.orangehrm.pages.dashboard;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DashoboardPage {

	EventFiringWebDriver driver;

	public DashoboardPage(EventFiringWebDriver driver) {
		this.driver = driver;
		init();
	}

	public void init() {
		PageFactory.initElements(driver, this);
	}

}
