/**
 * hndfsjSoft webMail project
 */

package com.hndfsj.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请在此加入你的功能说明
 *
 * @author 王富强
 * @date 2009-11-19 下午04:32:20
 */
public class RWAdminConfiguration {
	
	private static Logger log = LoggerFactory.getLogger(RWAdminConfiguration.class);

	private static RWAdminConfiguration config;
	private static Properties app = new Properties();
	

	private RWAdminConfiguration() {
		try {

	        URL appPropsUrl  = Thread.currentThread().getContextClassLoader().getResource(RWAdminConstant.CONFIG_APPPROPS_NAME + ".properties");
	   
	        if (appPropsUrl == null) {
	        	log.error("properties file missing");
	            throw new IllegalStateException("properties missing");
	        }
	        InputStream appStream = appPropsUrl.openStream();
	    

	        try {
	        	app.load(appStream);
	        	
	        } catch (IOException e) {
	            log.error("Could not load  properties:" + e);
	            
	        }finally{
	        	IOUtils.closeQuietly(appStream);
	        	
	        }
		} catch(Exception e) {
			 log.error("Could not load  properties:" + e);
		}
	}
	public static RWAdminConfiguration getInstance() {
		
		if(config == null) {
			config = new RWAdminConfiguration();
		}
		return config;
	}
	
	
	public String getAppPropsValue(String name ) {
		return app.getProperty(name );
	}
	public String getAppPropsValue(String name,String value) {
		return app.getProperty(name,value);
	}

	public String getRWAdminContextHolderStrategy() {
		return getAppPropsValue(RWAdminConstant.RWADMIN_CONTEXT_HOLDER_STRATEGY);
	}
	
	
}
