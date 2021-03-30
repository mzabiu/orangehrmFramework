package com.orangehrm.framework.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * This class will read the whole proppert file and return a Map
 * 
 * @author Zabiulla_Pro
 *
 */

public class ReadPropertiesFile {

	private static final String PROPERTIES_PATH = System.getProperty("user.dir")
			+ "/src/main/resources/com/orangehrm/config/config.properties";

	private static Properties properties = new Properties();

	/**
	 * Initializing the static data members including properties
	 */

	static {
		InputStream file;
		try {
			file = new FileInputStream(new File(PROPERTIES_PATH));
			properties.load(file);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the value from properties for a given key
	 * 
	 * @param key
	 * @return
	 */
	private static String getPropertyValue(String key) {
		String value = "";
		if (key != null && !key.isEmpty()) {
			value = properties.getProperty(key);
		}
		return value;
	}

	/**
	 * 
	 * This is the method which will return the entire properties key, value pair as
	 * Map
	 * 
	 * @return
	 */
	public static Map<String, String> getProperties() {
		Map<String, String> data = new HashMap<String, String>();
		for (String key : properties.stringPropertyNames())
			data.put(key, getPropertyValue(key));
		return data;
	}

}
