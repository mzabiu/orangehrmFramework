package com.orangehrm.pages.leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.orangehrm.pages.BasePage;

public class AssignLeavePage extends BasePage {

	public AssignLeavePage(EventFiringWebDriver driver) {
		super(driver);
	}

	String calDate = "//a[text()='%date%']";

	public WebElement getElement(By by) {

		return driver.findElement(by);

	}

	@FindBy(id = "assignleave_txtEmployee_empName")
	private WebElement txtEmployeName;

	@FindBy(id = "assignleave_txtLeaveType")
	private WebElement drpDnLeaveType;

	@FindBy(id = "assignleave_txtFromDate")
	private WebElement txtFromDate;

	@FindBy(className = "ui-datepicker-year")
	private WebElement drpDnCalYear;

	@FindBy(className = "ui-datepicker-month")
	private WebElement drpDnCalMonth;

	@FindBy(xpath = "ui-datepicker-month")
	private WebElement drpDnCalMonth;

}
