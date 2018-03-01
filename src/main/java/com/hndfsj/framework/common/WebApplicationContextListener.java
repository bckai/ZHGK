/**
 * hndfsjSoft CERP project
 */

package com.hndfsj.framework.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;



/**
 * 获取spring
 *
 * @author 王富强
 * @date 2009-6-26 下午02:24:26
 */
public class WebApplicationContextListener implements ServletContextListener  {

	private static Logger logger = LoggerFactory.getLogger(WebApplicationContextListener.class);
	
	private static WebApplicationContext context;

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
 
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
				context = (WebApplicationContext)servletContextEvent.getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
				logger.info("start");
				while(context == null){
					try {
						logger.info("sleep");
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					
					context = (WebApplicationContext)servletContextEvent.getServletContext().getAttribute("ContextLoaderPlugIn.CONTEXT.");
				}
				logger.info("ok");
	}
	/**
	 * 确定获得不为空的上下文
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext() {
		while(context == null){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return context;
	}



}
