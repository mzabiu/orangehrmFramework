package com.orangehrm.framework.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLog implements Constants {

	private static final String propertyPath = CURRENT_DIR + ReadPropertiesFile.getProperties().get(LOGJ_PATH);

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		PropertyConfigurator.configure(propertyPath);

	}

	public static Logger log = Logger.getLogger(MyLog.class.getName());

	public static void logInfo(String message) {
		log.info(message);
	}

	public static void logError(String message) {
		log.error(message);
	}
}
