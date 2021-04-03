package com.orangehrm.pages.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.pages.BasePage;
import com.orangehrm.utils.PasswordUtil;

public class LoginPage extends BasePage {

	public LoginPage(EventFiringWebDriver driver) {
		super(driver);
	}

	@FindBy(id = "txtUsername")
	private WebElement txtUserName;

	@FindBy(id = "txtPassword")
	private WebElement txtPassword;

	@FindBy(id = "btnLogin")
	private WebElement btnLogin;

	public WebElement getTxtUserName() {
		return txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public void login() {
		getTxtUserName().sendKeys(configData.get(APP_USERID));
		getTxtPassword().sendKeys(PasswordUtil.decrypt(configData.get(APP_PASSWORD)));
		getBtnLogin().click();
	}

	public void login(String userName, String password) {

	}

	public void logout() {
		commonUtils.clickUsingJs(driver, linkLogout);
	}

}