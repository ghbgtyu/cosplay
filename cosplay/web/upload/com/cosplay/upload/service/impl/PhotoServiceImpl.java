package com.cosplay.upload.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosplay.upload.dao.IPhotoDao;
import com.cosplay.upload.service.IPhotoService;
@Service
public class PhotoServiceImpl implements IPhotoService {
	
	@Autowired
	private IPhotoDao photoDao;

	@Override
	public void uploadOnePhoto(InputStream inputStream, String name) throws IOException {
		
		File file = new File("E://test");
		OutputStream os = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
		os.write(buffer, 0, bytesRead);
		}
		os.close();
		ObjectId oId = photoDao.insertFile(file, "test1");
		
	}
	
}
