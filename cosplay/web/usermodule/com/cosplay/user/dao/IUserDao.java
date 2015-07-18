package com.cosplay.user.dao;

import com.cosplay.serviceserver.dao.base.MongoDbOption;
import com.cosplay.serviceserver.dao.interfaces.IOperation;
import com.cosplay.user.entity.UserEntity;


public interface IUserDao extends IOperation<UserEntity, MongoDbOption> {

}
