package com.comviva.rnd.util;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Repository
@PropertySource({"file:${ext.properties.dir}/dbRoadmap.properties"})
public class StartUpUtil implements InitializingBean {
	   private static final Logger logger = LoggerFactory.getLogger(StartUpUtil.class);
	   public static int count=0;
	    private SessionFactory sessionFactory;
	    
    
public static String[] ProfSlidePost;
public static String[] ProfSlidePre;
public static String[] ProfSrvCombo;

		public static Logger getLogger() {
			return logger;
		}

		public static int getCount() {
			return count;
		}

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

	

		public void setSessionFactory(SessionFactory sf){
	        this.sessionFactory = sf;
	    }

	@Transactional
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

System.out.println("counter->"+count++);	

try{
    
    logger.info("left menu has been set");
}
catch(Exception e)
{
	e.printStackTrace();
	
}
	}


	
}
