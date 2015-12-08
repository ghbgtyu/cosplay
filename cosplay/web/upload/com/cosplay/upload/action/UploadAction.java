package com.cosplay.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cosplay.base.util.WebUtil;
import com.cosplay.upload.model.Test;
import com.cosplay.upload.service.IPhotoService;

@Controller
public class UploadAction {
	
	@Autowired
	private IPhotoService photoService;

	@RequestMapping(value="/upload")
	public void photoUpload(@RequestParam MultipartFile file,Test test,HttpServletRequest request,HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		try {
			photoService.uploadOnePhoto(file.getInputStream(),file.getName());
		} catch (IOException e) {
			
		}
		WebUtil.writeJSON(response, json);
	}
}
