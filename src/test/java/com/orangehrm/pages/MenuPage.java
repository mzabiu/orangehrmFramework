package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.orangehrm.pages.BasePage;

public class MenuPage extends BasePage {

	public MenuPage(EventFiringWebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public final String MENU_ADMIN = "menu_admin_viewAdminModule";
	public final String MENU_DASHBOARD = "menu_dashboard_index";

	public final String SUB_MENU_USER_MANAGEMENT_ADMIN = "";

	public final String SUB_MENU_LINK_USERS_USER_MANAGEMENT="";

	public WebElement getItem(String item) {
		return driver.findElement(By.id(item));
	}

}
