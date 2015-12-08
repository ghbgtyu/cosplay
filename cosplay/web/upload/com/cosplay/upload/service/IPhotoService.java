package com.cosplay.upload.service;

import java.io.IOException;
import java.io.InputStream;

public interface IPhotoService {

	void uploadOnePhoto(InputStream inputStream, String name) throws IOException;

}
