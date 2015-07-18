package com.cosplay.cosbar.dao.impl;

import org.springframework.stereotype.Component;
import com.cosplay.cosbar.dao.IChatBarDao;
import com.cosplay.cosbar.entity.ChatBarEntity;
import com.cosplay.serviceserver.dao.abstracts.MongoDbOperaterBase;

@Component
public class ChatBarDaoImpl extends MongoDbOperaterBase<ChatBarEntity> implements IChatBarDao {
	

}
