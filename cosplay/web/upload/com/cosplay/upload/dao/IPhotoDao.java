package com.cosplay.upload.dao;

import com.cosplay.serviceserver.dao.base.MongoDbOption;
import com.cosplay.serviceserver.dao.interfaces.IOperation;
import com.cosplay.upload.entity.PhotoEntity;

public interface IPhotoDao extends IOperation<PhotoEntity, MongoDbOption> {

}
