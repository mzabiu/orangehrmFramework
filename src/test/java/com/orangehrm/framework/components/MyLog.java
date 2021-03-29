package com.orangehrm.framework.components;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLog implements Constants {

	public static Logger log = Logger.getLogger(MyLog.class.getName());

	private static final String propertyPath = CURRENT_DIR + ReadPropertiesFile.getProperties().get(LOGJ_PATH);

	static {
		PropertyConfigurator.configure(propertyPath);
	}

	public static void logInfo(String message) {
		log.info(message);
	}

	public static void logError(String message) {
		log.error(message);
	}
}
