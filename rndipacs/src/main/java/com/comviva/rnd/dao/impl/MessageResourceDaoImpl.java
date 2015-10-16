package com.comviva.rnd.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comviva.rnd.configuration.MessageResource;
import com.comviva.rnd.dao.MessageResourceDao;

;
@Repository
@Service
public class MessageResourceDaoImpl implements MessageResourceDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<MessageResource> loadAllMessages() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from MessageResource").list();
	}

	@Override
	@Transactional
	public MessageResource getMessageResourcebyId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		MessageResource messageResource=(MessageResource) session.load(MessageResource.class, id);
		 Hibernate.initialize(messageResource);
		return messageResource;
	}

	@Override
	@Transactional
	public void addMessageResource(MessageResource m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(m);
	}

	@Override
	@Transactional
	public void updateMessageResource(MessageResource m) {
		System.out.println("id for update element->"+m.getId());
		Session session = this.sessionFactory.getCurrentSession();
		session.update(m);

	}

	@Override
	@Transactional
	public void removeMessageResource(String id) {
		   Session session = this.sessionFactory.getCurrentSession();
		   MessageResource p = (MessageResource) session.load(MessageResource.class, id);
	        if(null != p){
	            session.delete(p);
	        }

	}

}
