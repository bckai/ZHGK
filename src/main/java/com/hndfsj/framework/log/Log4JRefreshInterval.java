package com.hndfsj.framework.log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.Log4jConfigurer;

import com.hndfsj.framework.config.RWAdminConfiguration;

public class Log4JRefreshInterval {

	/**
	 * 
	 */
    public static String DEFAULT_LOCATION = null;
    public static String DEFAULT_REFRESHINTERVAL = null;
    private static String RUNING_LOCATION =null;
    public boolean REFRESHDAEMON = false;

    
//    private String CLASSPATH = "classpath:";
//    private long refreshSecond = 0;  
//    private long refreshMinute = 0;   
//    private long refreshHour = 0;    

	public boolean isREFRESHDAEMON() {
		return REFRESHDAEMON;
	}


	public void  stopRefreshInterval(){
		REFRESHDAEMON = false;
		destroy();
		initLog4j(RUNING_LOCATION, null);
	}
	public void  startRefreshInterval(){
		REFRESHDAEMON = true;
		destroy();
		initLog4j(RUNING_LOCATION, DEFAULT_LOCATION);
	}
	
	/**
	* 停止日志
	*
	* @author wfq
	*/
	public void destroy() {
		Log4jConfigurer.shutdownLogging();
	}
	
	/**
	* 获得当前运行的日志配置
	*
	* @return
	* @throws Exception
	* @author wfq
	*/
	public static String getRunningConfing() throws Exception {
		Document logDocument;
		if(RUNING_LOCATION!=null){
			 logDocument = readXMLFile(RUNING_LOCATION);
			
		}else {
			throw new IllegalArgumentException("RUNING_LOCATION is null: " );
		}
		return logDocument.toString();
	}
	
	
	
	
	/**
	* 加载Log4j配置
	*
	* @param location 配置文件位置
	* @param refreshIntervalStr 定时刷新周期
	* @author wfq
	*/
	public static void  initLog4j(String location,String refreshIntervalStr) {

		
		try {
		if (refreshIntervalStr != null) {
			// Initialize with refresh interval, i.e. with log4j's watchdog thread,
			// checking the file in the background.
			try {
				long refreshInterval = Long.parseLong(refreshIntervalStr);
				Log4jConfigurer.initLogging(location, refreshInterval);
			}
			catch (NumberFormatException ex) {
				throw new IllegalArgumentException("Invalid 'log4jRefreshInterval' parameter: " + ex.getMessage());
			}
		}
		else {
			// Initialize without refresh check, i.e. without log4j's watchdog thread.
				Log4jConfigurer.initLogging(location);
		}
		} catch (FileNotFoundException ex) {
			throw new IllegalArgumentException("Invalid 'log4jConfigLocation' parameter: " + ex.getMessage());
		}
		RUNING_LOCATION = location;
	}
	
	
	/**
	* 按默认配置加载Log4j配置
	*
	* @author wfq
	*/
	public static void  initLog4j(){
		initLog4j(DEFAULT_LOCATION,DEFAULT_REFRESHINTERVAL);
	}
	
	
	
	/**
	* 立即刷新Log4j配置
	*
	* @author wfq
	*/
	public static void refreshIntervalImmediately() {
		Log4jConfigurer.shutdownLogging();
		initLog4j();
	}
	/**
	* 加载指定位置的Log4j配置并刷新
	*
	* @param log4jFilePath
	* @author wfq
	*/
	public void refreshIntervalImmediatelyByFilePath(String log4jFilePath) {
		Log4jConfigurer.shutdownLogging();
		
		initLog4j(log4jFilePath,DEFAULT_REFRESHINTERVAL);
		
	}
	
	
	/**
	 * 读取配置log4j.Xml文件
	 * 读取配置文件并将输出日志文件的相对路径替换为绝对路径
	 * @param prefix
	 * @param filePath
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void replaceOutPathXml2Log4j(String prefix, String filePath) throws DocumentException,
			IOException {
		// 读取配置文件并将输出日志文件的相对路径替换为绝对路径
		Document logDocument = readXMLFile(filePath);
		List<Node> params = logDocument.getRootElement().selectNodes("//appender/param");

		for (Node param : params) {
			if (param.valueOf("@name").equals("File")) {
				if (param.getParent().valueOf("@name").equals("DebugLogger")) {
					String suffix = RWAdminConfiguration.getInstance().getAppPropsValue(
							"log4j.xmlconfig.appender.debug.File");
					String file = StringUtils.replace(prefix + suffix, "\\", "/");
					((Attribute) param.selectObject("@value")).setValue(file);
				}
				//
				else if (param.getParent().valueOf("@name").equals("InfoLogger")) {
					String suffix = RWAdminConfiguration.getInstance().getAppPropsValue(
							"log4j.xmlconfig.appender.info.File");
					String file = StringUtils.replace(prefix + suffix, "\\", "/");
					((Attribute) param.selectObject("@value")).setValue(file);
				}
				//
				else if (param.getParent().valueOf("@name").equals("ErrorLogger")) {
					String suffix = RWAdminConfiguration.getInstance().getAppPropsValue(
							"log4j.xmlconfig.appender.error.File");
					String file = StringUtils.replace(prefix + suffix, "\\", "/");
					((Attribute) param.selectObject("@value")).setValue(file);
				}
			}
		}

		XMLWriter output = new XMLWriter(new FileWriter(new File(filePath)));
		output.write(logDocument);
		output.close();
		
		// 加载log4j配置文件
//		DOMConfigurator.configure(filePath);
		
		
	}

	/**
	 * 加载配置log4j.properties文件到Log4j
	 * 
	 * @param prefix
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private void replaceOutPathProps2Log4j(String prefix, String filePath) throws FileNotFoundException,
			IOException {
		// 读取配置文件并将输出日志文件的相对路径替换为绝对路径
		Properties props = new Properties();
		FileInputStream istream = new FileInputStream(filePath);
		props.load(istream);
		istream.close();
		// ERRORS //设置路径
		String errorFile = prefix + props.getProperty("log4j.appender.error.File");
		// 要和properties里面的键名对应
		props.setProperty("log4j.appender.error.File", errorFile);

		// INFOS //设置路径
		String infoFile = prefix + props.getProperty("log4j.appender.info.File");
		// 要和properties里面的键名对应
		props.setProperty("log4j.appender.info.File", infoFile);

		// DEBUGS 设置路径
		String debugFile = prefix + props.getProperty("log4j.appender.debug.File");
		// 要和properties里面的键名对应
		props.setProperty("log4j.appender.debug.File", debugFile);

//		PropertyConfigurator.configure(props);// 装入log4j配置信息
	}

	private static Document readXMLFile(String path) throws FileNotFoundException, DocumentException {
		// 获得webroot的位置，System.getProperty属性在过滤器中设置
		File file = new File(path);
		InputStream is = null;
		Document document = null;
		FileInputStream fileInputStream = new FileInputStream(file);
		is = new BufferedInputStream(fileInputStream);
		document = new SAXReader().read(is);
		try {
			fileInputStream.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
}
