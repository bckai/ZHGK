/**
 * hndfsj CCLS project
 */

package com.hndfsj.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目（使用该框架的项目）properties配置文件读取类 使用的配置文件为Constant中配置的文件，只读取项目专用的参数
 * 
 * @author wfq
 * @date 2010-9-9 上午10:34:27
 */
public class Configuration {

	private Log log = LogFactory.getLog(Configuration.class);

	private static Configuration config;
	// private Properties settings = new Properties();
	private static Properties app = new Properties();

	private Configuration() {
		try {

			URL appPropsUrl = Thread.currentThread().getContextClassLoader().getResource(
					Constants.CONFIG_APPPROPS_NAME + ".properties");

			if (appPropsUrl == null) {
				log.error("project_application.properties properties file missing");
				throw new IllegalStateException("project_application.properties properties missing");
			}
			InputStream appStream = appPropsUrl.openStream();

			try {
				app.load(appStream);

			} catch (IOException e) {
				log.error("Could not load  properties:" + e);

			} finally {
				IOUtils.closeQuietly(appStream);

			}
		} catch (Exception e) {
			log.error("Could not load  properties:" + e);
		}
	}

	public static Configuration getInstance() {

		if (config == null) {
			config = new Configuration();
		}
		return config;
	}

	public String getAppPropsValue(String name) {
		return app.getProperty(name);
	}

	public String getAppPropsValue(String name, String value) {
		return app.getProperty(name, value);
	}

}
