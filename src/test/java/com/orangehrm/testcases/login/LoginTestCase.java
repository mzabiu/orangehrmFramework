package com.orangehrm.testcases.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.testcases.BaseTest;

public class LoginTestCase extends BaseTest {

	/**
	 * <Author>Zabiulla_Pro </author> <Date>Apr 3, 2021</date>
	 * <testcasename>checkLogin</testcasename> <testdata></testdata> <TestSteps>
	 * Step 1: Launch the app url Step 2: Enter the credentials Step 3: Verify the
	 * Dashboard screen Step: Logout </TestSteps>
	 * 
	 **/
	@Test(groups = { "sanity", "critical",
			"regression" }, description = "Verify the login fucntionality of orange hrm demo site")
	public void checkLogin() {
		Assert.fail();
	}
}
