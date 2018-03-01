/**
 * hndfsj ccls project
 */

package com.hndfsj.framework.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 文件下载工具类
 * 
 * @author zhengwenquan
 * @date 2010-9-29 下午02:40:13
 */
public class FileDownLoadUtil {

	/**
	 * 下载生成的word文档
	 * 
	 * @param response
	 * @param dateMap
	 * @param t
	 * @author liushuo
	 */
	public static void downLoadDOC(HttpServletResponse response, Map dateMap, Template t) {
		// 设置响应头和下载保存的文件名
		response.reset();
		response.resetBuffer();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		String fileName = TranscodeUtil.ISO8859_1FromGBK(dateMap.get("name").toString());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".doc" + "\"");
		// 设置模板
		t.setEncoding("utf-8");
		try {
			java.io.OutputStream ops = response.getOutputStream();

			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(ops, "utf-8"));
			t.process(dateMap, out);
			out.close();
			ops.flush();
			ops.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 程序控制下载文件
	 * 
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @author zhengwenquan
	 */
	public static void downLoadAttachement(HttpServletResponse response, String filePath, String fileName) {
		// 设置响应头和下载保存的文件名
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Encoding", "gzip");// 增加GZIP压缩
		try {
			java.io.OutputStream ops = response.getOutputStream();
			// 打开指定文件的流信息
			java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filePath);
			byte[] b = new byte[1024];
			int i;
			GZIPOutputStream gzipos = new GZIPOutputStream(ops);
			// 写出流信息
			while ((i = fileInputStream.read(b)) != -1) {
				gzipos.write(b, 0, i);
			}
			gzipos.finish();
			fileInputStream.close();
			gzipos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void downLoadAttachement(HttpServletResponse response,byte[] file, String fileName) {
		// 设置响应头和下载保存的文件名
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + TranscodeUtil.ISO8859_1FromGBK(fileName) + "\"");
		response.setHeader("Content-Encoding", "gzip");// 增加GZIP压缩
		try {
			java.io.OutputStream ops = response.getOutputStream();
			GZIPOutputStream gzipos = new GZIPOutputStream(ops);
			// 写出流信息
			gzipos.write(file);
			gzipos.finish();
			gzipos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 程序控制下载文件
	 * 
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @author zhengwenquan
	 */
	public static void downLoadAttachement(HttpServletResponse response, InputStream inputStream, String fileName) {
		// 设置响应头和下载保存的文件名
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Encoding", "gzip");// 增加GZIP压缩
		try {
			java.io.OutputStream ops = response.getOutputStream();
			// 打开指定文件的流信息
			byte[] b = new byte[1024];
			int i;
			GZIPOutputStream gzipos = new GZIPOutputStream(ops);
			// 写出流信息
			while ((i = inputStream.read(b)) != -1) {
				gzipos.write(b, 0, i);
			}
			gzipos.finish();
			gzipos.finish();
			inputStream.close();
			gzipos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 程序控制下载downLoadQrcode
	 * 
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @author zhengwenquan
	 */
	public static void downLoadQrcode(HttpServletResponse response, InputStream inputStream, String fileName,String qrcodeContent) {
		// 设置响应头和下载保存的文件名
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("qrcode", qrcodeContent);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Encoding", "gzip");// 增加GZIP压缩
		try {
			java.io.OutputStream ops = response.getOutputStream();
			// 打开指定文件的流信息
			byte[] b = new byte[1024];
			int i;
			GZIPOutputStream gzipos = new GZIPOutputStream(ops);
			// 写出流信息
			while ((i = inputStream.read(b)) != -1) {
				gzipos.write(b, 0, i);
			}
			gzipos.finish();
			gzipos.finish();
			inputStream.close();
			gzipos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件到临时目录
	 * 
	 * @param request
	 * @param response
	 * @param dateMap
	 * @param t
	 */
	public static String downLoadExcelToTempFile(HttpServletRequest request, HttpServletResponse response, Map dateMap, Template t) {
		String filePath = "";
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String ymd = sdf.format(new Date());
		savePath += "excelFileTemp/" + ymd + "/";
		filePath = ymd + "/";
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 设置模板
		t.setEncoding("utf-8");
		String fileName = dateMap.get("name").toString();
		try {
			filePath += fileName + ".xls";
			fileName = savePath + fileName + ".xls";
			// 导出时设定编码格式，否则会出现错误。
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
			t.process(dateMap, out);
			out.close();
			return filePath;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void downloadFile(String path, HttpServletResponse response) {
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			System.out.println("filename: " + filename);
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();

			// 清空response
			response.reset();
			// 设置response的Header
			response.setHeader("contentType", "text/html; charset=utf-8");
			filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
			response.setContentType("multipart/form-data");
			response.addHeader("Content-Disposition", "attachment;filename=" + filename + "");
			// response.setHeader("content-disposition", "attachment;filename="
			// + URLEncoder.encode(filename, "UTF-8") + "");
			response.addHeader("Content-Length", "" + file.length() + "");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
