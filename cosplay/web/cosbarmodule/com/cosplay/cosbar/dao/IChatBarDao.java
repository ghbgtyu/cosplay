package com.cosplay.cosbar.dao;

import com.cosplay.cosbar.entity.ChatBarEntity;
import com.cosplay.serviceserver.dao.base.MongoDbOption;
import com.cosplay.serviceserver.dao.interfaces.IOperation;

public interface IChatBarDao  extends IOperation<ChatBarEntity, MongoDbOption>  {

}
