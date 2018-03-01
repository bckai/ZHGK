package com.hndfsj.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java url http封装
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author haoyingshuai@hndfsj.com,haoluziqi@126|gmail.com
 * @version 2013-3-1 上午11:42:27
 * @see com.hndfsj.app.utils.HttpUtils
 */
public class HttpUtils {

	private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 判断远程服务是否开启
	 * 
	 * @param url
	 * @return
	 * @author Mr.Hao
	 * @version 2011-12-31 下午02:15:37
	 */
	public static boolean judgeServerIsOpen(String url) {
		boolean result = Boolean.FALSE;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		try {
			HttpResponse res = client.execute(method);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
				result = true;
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		return result;
	}

	public static byte[] downloadImage(String url) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		try {
			method.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			method.addHeader("Accept-Encoding", "deflate");
			method.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
			method.addHeader("Cache-Control", "max-age=0");
			method.addHeader("Connection", "keep-alive");
			method.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0");
			HttpResponse res = client.execute(method);
			System.out.println(Arrays.toString(res.getAllHeaders()));
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = res.getEntity().getContent();
				System.err.println(res.getEntity().getContentLength());
				return IOUtils.toByteArray(is);
			}
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		return new byte[1];
	}

	public static void main(String[] args) {
		//System.err.println(Arrays.toString(downloadImage("http://i.tq121.com.cn/i/mobile/images/d02.png")));
		
		System.err.println(judgeServerIsOpen("http://192.168.10.30:82/extend/getNjdDataByDate?dDate=2017-11-10"));
		System.err.println(doGet("http://192.168.10.30:82/extend/getNjdDataByDate?dDate=2017-11-10","UTF-8"));
	}

	/**
	 * 下载验证码
	 *
	 * @param url
	 * @param CookieValue
	 *            为null
	 * @return
	 * @author Mr.Zheng
	 * @version 2015年6月11日 下午3:08:35
	 */
	public static ValidateImg downloadValidateImg(String url, String CookieValue) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		try {
			if (StringUtils.isNotBlank(CookieValue))
				method.addHeader("Cookie", CookieValue);
			HttpResponse res = client.execute(method);
			// System.out.println(Arrays.toString(res.getAllHeaders()));
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				InputStream is = res.getEntity().getContent();
				ValidateImg vi = new ValidateImg();
				// new Header(name, value)
				Header[] headers = res.getHeaders("Set-Cookie");
				if (headers == null || headers.length == 0) {
					if (StringUtils.isNotBlank(CookieValue)) {
						headers = new Header[] { new BasicHeader("Set-Cookie", CookieValue) };
					}
				}
				vi.setCookieValues(headers);
				// , "JSESSIONID=8B1DE2E955D486B0CF7E865BFBC22F93");
				byte[] b = IOUtils.toByteArray(is);
				vi.setImg(b);
				return vi;
			}
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 下载验证码
	 *
	 * @param url
	 * @param CookieValue
	 *            为null
	 * @return
	 * @author Mr.Zheng
	 * @version 2015年6月11日 下午3:08:35
	 */
	public static ValidateImg downloadValidate(String url, String charset) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		try {
			HttpResponse res = client.execute(method);
			// System.out.println(Arrays.toString(res.getAllHeaders()));
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				StringBuffer response = new StringBuffer();
				InputStream is = res.getEntity().getContent();
				ValidateImg vi = new ValidateImg();
				// new Header(name, value)
				Header[] headers = res.getHeaders("Set-Cookie");
				vi.setCookieValues(headers);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getEntity().getContent(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line).append(System.getProperty("line.separator"));
				}
				vi.setResult(response.toString());
				return vi;
			}
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		return null;
	}

	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @return 返回请求响应的HTML
	 */
	public static String doGet(String url, String charset) {
		StringBuffer response = new StringBuffer();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet method = new HttpGet(url);
		try {
			// 数据传输压缩提交信息
			// method.addHeader("accept-encoding", "gzip,deflate");
			HttpResponse res = client.execute(method);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getEntity().getContent(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line).append(System.getProperty("line.separator"));
				}
				reader.close();
			} else
				log.warn("状态返回码不是200!");
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response.toString();
	}

	/**
	 * 执行一个HTTP POST请求，返回请求响应的HTML
	 * 
	 * @param url
	 *            请求的URL地址
	 * @param params
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @return 返回请求响应的HTML
	 */
	public static String doPost(String url, String charset) {
		CloseableHttpClient client = HttpClients.createDefault();
		return doPost(client, RequestBuilder.post(url).build(), charset);
	}

	public static String doPost(CloseableHttpClient client, HttpUriRequest post, String charset) {
		StringBuffer response = new StringBuffer();
		post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 编码
		// 设置Http Post数据
		try {
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(res.getEntity().getContent(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line).append(System.getProperty("line.separator"));
				}
				reader.close();
			} else
				;
			// log.warn("状态返回码不是200!");
		} catch (ConnectException e) {
			log.error("服务没有开启！");
		} catch (IOException e) {
			log.error("执行HTTP Post请求" + post.getURI() + "时，发生异常！", e);
		} finally {
			try {
				client.close();
			} catch (IOException e) {
			}
		}
		return response.toString();
	}

}

/**
 * 验证码实体
 * 
 * @copyright {@link www.hndfsjsoft.com}
 * @author
 * @version 2015年6月11日 下午2:59:04
 * @see com.hndfsj.app.mobile.domain.ValidateImg
 */
class ValidateImg {

	byte[] img;
	Object result;
	Header[] CookieValues;

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public Header[] getCookieValues() {
		return CookieValues;
	}

	public void setCookieValues(Header[] cookieValues) {
		CookieValues = cookieValues;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}