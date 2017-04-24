package com.ai.bdex.dataexchange.busi.demo.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;

/**
 * ckeditor使用
 *
 */
@Controller
@RequestMapping(value="/ck")
public class CkeditorController {
	protected final Logger logger = Logger.getLogger(CkeditorController.class);
	
	@RequestMapping(value="/to")
	public String to(){
		return "/demo/ckeditorDemo";
	}
	
	@RequestMapping(value="/upload")
	public void upload(ModelMap modelMap, HttpServletRequest request,  
            HttpServletResponse response){
		 // 判断提交的请求是否包含文件  
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);  
  
        if (!isMultipart) {  
            return;  
        }  
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest
				.getFile("upload");// 表单中对应的文件名；
		if (orginalFile != null && !orginalFile.isEmpty()) {// 如果有文章中带有附件
			InputStream is = null;// 附件输入流
			DataOutputStream out = null;
			PrintWriter pw = null;
			try {
				String filename = orginalFile.getOriginalFilename();
				filename = filename.toLowerCase();
				is = orginalFile.getInputStream();
				byte[] b = new byte[is.available()];
				is.read(b);
				//将jpg图片存入MongoDB
				//String id = MongoFileUtil.saveFile(b, filename, "jpg");
				/**
				 * 路径参数表获取,文件上传到mongodb中
				 */
				String id = ImageUtil.upLoadImage(b, filename);
				
				response.setContentType("text/html; charset=UTF-8");  
	            response.setHeader("Cache-Control", "no-cache");  
	            pw = response.getWriter();
	         // 将上传的图片的url返回给ckeditor ，用于显示图片 
                String callback = request.getParameter("CKEditorFuncNum");  
                pw.println("<script type=\"text/javascript\">");  
                pw.println("window.parent.CKEDITOR.tools.callFunction("  
                        + callback + ",'"+ImageUtil.getImageUrl(id)+"',''" + ")");  
                pw.println("</script>");
				
                pw.flush();
			} catch (IOException exception) {
				exception.printStackTrace();
			} finally {
				pw.close();
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 保存ckeditor编辑的页面到mongodb，并返回提取页面的url
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public String saveHtmlToMongoDb(HttpServletResponse response,HttpServletRequest req){
		String id = "";
		String html = req.getParameter("html");//获取参数片段
		StringBuffer sbUrl = null;
		try {
			id = MongoFileUtil.saveFile(html.getBytes(),"goodsDetails", ".html");
			sbUrl = new StringBuffer();
			String url = ImageUtil.getStaticDocUrl(id, "html");
			sbUrl.append(url);
//			sbUrl.append(id);
//			sbUrl.append(".html");
			System.out.println("---------------url-------------------"+sbUrl.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sbUrl.toString();
	}
	
}
