package com.orangehrm.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import com.orangehrm.framework.components.ReadExcelTestData;
import com.orangehrm.framework.components.ReadPropertiesFile;

public class BaseTest {

	public Map<String, String> configPropertyData;
	public Map<String, HashMap<String, String>> excelData;

	@BeforeSuite(alwaysRun = true)
	public void initialize(XmlTest testngXml) throws Exception {
		configPropertyData = ReadPropertiesFile.getProperties();
		excelData = ReadExcelTestData.getSuiteData("TestData", "Data");
	}
	
	

}
