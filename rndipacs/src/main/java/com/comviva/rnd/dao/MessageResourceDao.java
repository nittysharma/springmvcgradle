package com.comviva.rnd.dao;

import java.util.List;

import com.comviva.rnd.configuration.MessageResource;

public interface MessageResourceDao {

	 public List<MessageResource> loadAllMessages();
public MessageResource getMessageResourcebyId(int id);
public void addMessageResource(MessageResource m);
public void updateMessageResource(MessageResource m);
public void removeMessageResource(String id);
}
