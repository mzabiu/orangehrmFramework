package com.orangehrm.testcases.leave;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangehrm.pages.dashboard.DashBoardPage;
import com.orangehrm.pages.leave.AssignLeavePage;
import com.orangehrm.testcases.BaseTest;

public class AssignLeaveTestCase extends BaseTest {

	@Test
	public void verifyAssignLeave(Method method) throws Exception {

		Map<String, String> testCaseData = excelData.get(method.getName());
		String emp = testCaseData.get(EXCEL_EMPLOYEE_NAME);
		String leaveType  = testCaseData.get(EXCEL_LEAVE_TYPE);
		String leaveFrom = testCaseData.get(EXCEL_LEAVE_FROM);
		String leaveTo  = testCaseData.get(EXCEL_LEAVE_TO);
		String noOfDays  = testCaseData.get(EXCEL_HOW_MANY_DAYS_LEAVE);
		
		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.getLinkAssignLeave().click();
		
		AssignLeavePage assignLeavePage = new AssignLeavePage(driver);
		Assert.assertEquals(assignLeavePage.getSectionHeader().getText().equals("Assign Leave"), true);
		
		
		
		
		
		
		
	}

}
