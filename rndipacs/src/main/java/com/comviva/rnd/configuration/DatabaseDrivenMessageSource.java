package com.comviva.rnd.configuration;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.comviva.rnd.dao.MessageResourceDao;

public class DatabaseDrivenMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

	  private static final Logger logger = LoggerFactory.getLogger(DatabaseDrivenMessageSource.class);

    private ResourceLoader resourceLoader;

    private final Map<String, Map<String, String>> properties = new HashMap<String, Map<String, String>>();

    @Autowired
    private MessageResourceDao messageResourceService;

    public DatabaseDrivenMessageSource() {
    	System.out.println("property file reloaded");
        reload();
    }

    public DatabaseDrivenMessageSource(MessageResourceDao messageResourceService) {
        this.messageResourceService = messageResourceService;
        reload();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        return getText(code, locale);
    }

    private String getText(String code, Locale locale) {
        Map<String, String> localized = properties.get(code);
        String textForCurrentLanguage = null;
        if (localized != null) {
            textForCurrentLanguage = localized.get(locale.getLanguage());
            if (textForCurrentLanguage == null) {
                textForCurrentLanguage = localized.get(Locale.FRANCE.getLanguage());
            }
        }
        if (textForCurrentLanguage==null) {
            //Check parent message
            logger.debug("Fallback to properties message");
            try {
                textForCurrentLanguage = getParentMessageSource().getMessage(code, null, locale);
            } catch (Exception e) {
                logger.error("Cannot find message with code: " + code);
            }
        }
        return textForCurrentLanguage != null ? textForCurrentLanguage : code;
    }

    public void reload() {
        properties.clear();
        properties.putAll(loadTexts());
    }

    protected Map<String, Map<String, String>> loadTexts() {
    	System.out.println("loading all porperties again");
        logger.debug("loadTexts");
        Map<String, Map<String, String>> m = new HashMap<String, Map<String, String>>();
        List<MessageResource> texts = messageResourceService.loadAllMessages();
        for (MessageResource text : texts) {
            Map<String, String> v = new HashMap<String, String>();
            v.put("en", text.getEnglish());
            v.put("de", text.getGerman());
            v.put("fr", text.getFrench());
            v.put("en_US", text.getAmerican());
            m.put(text.getMessageKey(), v);
        }
        return m;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }
    
    
	
    
}

