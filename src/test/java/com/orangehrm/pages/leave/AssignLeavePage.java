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

	
	public WebElement getTxtEmployeName() {
		return txtEmployeName;
	}

	public WebElement getDrpDnLeaveType() {
		return drpDnLeaveType;
	}

	public WebElement getTxtFromDate() {
		return txtFromDate;
	}

	public WebElement getDrpDnCalYear() {
		return drpDnCalYear;
	}

	public WebElement getDrpDnCalMonth() {
		return drpDnCalMonth;
	}

	public WebElement getDrpDnCalDate() {
		return drpDnCalDate;
	}

	public WebElement getTxtComment() {
		return txtComment;
	}

	public WebElement getBtnAssign() {
		return btnAssign;
	}

	public WebElement getImgFromCalender() {
		return imgFromCalender;
	}

	public WebElement getElement(By by) {

		return driver.findElement(by);

	}
	
	public WebElement getSectionHeader() {
		return sectionHeader;
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

	@FindBy(xpath = "//a[text()='22']")
	private WebElement drpDnCalDate;
	
	@FindBy(id = "assignleave_txtComment")
	private WebElement txtComment;
	
	@FindBy(id = "assignBtn")
	private WebElement btnAssign;
	
	@FindBy(xpath = "//span[@for='assignleave_txtFromDate']//following-sibling::img[contains(@class,'ui-datepicker-trigger')]")
	private WebElement imgFromCalender;
	

	@FindBy(xpath = "//h1[text()='Assign Leave']")
	private WebElement sectionHeader;
	

}
