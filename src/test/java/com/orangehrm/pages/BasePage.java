package com.orangehrm.pages;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.ReadPropertiesFile;
import com.orangehrm.utils.DriverUtils;

public class BasePage implements Constants {

	public static Map<String, String> configData;
	static {
		configData = ReadPropertiesFile.getProperties();
	}

	public static Logger log = Logger.getLogger(BasePage.class.getName());

	public DriverUtils commonUtils = new DriverUtils();

	public EventFiringWebDriver driver;

	/**
	 * constructor to intialize the driver
	 * 
	 * @param driver
	 * 
	 */

	@FindBy(id = "menu_dashboard_index")
	protected WebElement linkDashboard;

	@FindBy(xpath = "//div[@class='head']/*[text()='Dashboard']")
	protected WebElement lblDashboard;

	@FindBy(id = "welcome")
	protected WebElement linkWelcome;

	@FindBy(xpath = "//div[@id='welcome-menu']//a[text()='Logout']")
	protected WebElement linkLogout;

	public BasePage(EventFiringWebDriver driver) {
		this.driver = driver;
		init();
	}

	/**
	 * Method to initialize the web elements for a given page
	 */

	public void init() {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getElement(By by) {
		
		return driver.findElement(by);
		
	}
}
