package com.orangehrm.framework.components;

public interface Constants {

	// current directory
	String CURRENT_DIR = System.getProperty("user.dir");
	String APP_DATA = System.getProperty("user.home");

	// excel parameters
	String EXCEL_EMPLOYEE_NAME = "Employee_Name";
	String EXCEL_LEAVE_TYPE = "LeaveType";
	String EXCEL_LEAVE_FROM = "LeaveFrom";
	String EXCEL_LEAVE_TO = "LeaveTo";
	String EXCEL_HOW_MANY_DAYS_LEAVE = "HowManyDayLeave";

	// variable names for config property files
	String APP_PASSWORD = "encrypted.app.password";
	String APP_USERID = "app.username";
	String BASEURL = "baseUrl";
	String SCREENSHOT_PATH = "screenshot.path";
	String LOGJ_PATH = "log4j.path";
	String TEST_DATA_PATH = "testdata.path";
	String IMPLICIT_WAIT_TIME = "implicit.wait";
	String WEBDRIVER_WAIT_TIME = "webdriver.wait";
	String DRIVERS_PATH = "driver.path";
	String CHROME_BINARY_PATH = "chrome.binary.path";
}
