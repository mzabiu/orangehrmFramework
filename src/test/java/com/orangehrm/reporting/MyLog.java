package com.orangehrm.reporting;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import com.orangehrm.framework.components.Constants;
import com.orangehrm.framework.components.ReadPropertiesFile;

/**
 * 
 * Log4j implementation
 * It create a new file for every execution of suite
 * @author Zabiulla_Pro
 *
 */

public class MyLog extends Reporter implements Constants  {

	private static final String propertyPath = CURRENT_DIR + ReadPropertiesFile.getProperties().get(LOGJ_PATH);

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		PropertyConfigurator.configure(propertyPath);

	}

	public static Logger log = Logger.getLogger(MyLog.class.getName());

	public static void logInfo(String message) {
		log.info(message);
		Reporter.log(message);
	}

	public static void logError(String message) {
		log.error(message);
		Reporter.log(message);
	}

	public static void onlyLog(String message) {
		log.info(message);
	}

	public static void onlyReport(String message) {
		Reporter.log(message);
	}

}
