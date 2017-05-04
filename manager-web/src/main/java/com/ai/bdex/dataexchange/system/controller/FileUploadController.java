package com.ai.bdex.dataexchange.system.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ai.paas.util.ImageUtil;
import com.ai.paas.utils.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value="/fileupload")
public class FileUploadController {

	@RequestMapping(value = "/imgupload")
	@ResponseBody
	public String fileUpload(HttpServletRequest request) throws Exception{
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		try{
			//获取文件
	        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	        String size = request.getParameter("imgsize");
	        if(multipartRequest.getFileMap() != null){
	        	for (MultipartFile file : multipartRequest.getFileMap().values()) {
	        		
	        		String fileName = file.getOriginalFilename();
	        		
	        		//获取文件名后缀
					int lastPoint = fileName.lastIndexOf(".");
					String fileType = fileName.substring(lastPoint+1, fileName.length());
					if (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("gif")) {
					}else{
						result.put("status", "0");
						result.put("msg", "仅支持jpg,png,gif格式！");
						return this.toJsonString(result);
					}
					
					byte[] fileios = file.getBytes();
					if(fileios.length>1024*1000){
						result.put("status", "0");
						result.put("msg", "不允许上传大于1M的文件！");
						return this.toJsonString(result);
					}
					String fileId = ImageUtil.upLoadImage(fileios, fileName);
					String imageUrl = ImageUtil.getImageUrl(fileId);
					if(!StringUtil.isBlank(size)){
						imageUrl = ImageUtil.getImageUrl(fileId+size);
					}
	        	    
	        	    result.put("status", "1");
	        	    result.put("fileId", fileId);
	        	    result.put("fileName", fileName);
	        	    result.put("fileType", fileType);
	        	    result.put("imageUrl", imageUrl);
	        	    
	        	    return this.toJsonString(result);
				}
	        }
	        
		}catch(Exception e){
			result.put("status", "0");
			result.put("msg", "上传异常："+e.getMessage());
			return this.toJsonString(result);
		}
		
		result.put("status", "0");
		result.put("msg", "未取到任何文件");
		return this.toJsonString(result);
	}
	
	private String toJsonString(Object obj){
    	try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		}
    	return "{}";
    }
}
