package com.cosplay.user.dao.impl;

import org.springframework.stereotype.Component;

import com.cosplay.serviceserver.dao.abstracts.MongoDbOperaterBase;
import com.cosplay.user.dao.IUserDao;
import com.cosplay.user.entity.UserEntity;

@Component
public class UserDaoImpl extends MongoDbOperaterBase<UserEntity> implements IUserDao {

}
