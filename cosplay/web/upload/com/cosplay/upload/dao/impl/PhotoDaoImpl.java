package com.cosplay.upload.dao.impl;

import org.springframework.stereotype.Component;

import com.cosplay.serviceserver.dao.abstracts.MongoDbOperaterBase;
import com.cosplay.upload.dao.IPhotoDao;
import com.cosplay.upload.entity.PhotoEntity;

@Component
public class PhotoDaoImpl extends MongoDbOperaterBase<PhotoEntity> implements IPhotoDao {

}
