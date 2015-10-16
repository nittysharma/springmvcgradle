package com.comviva.rnd.configuration;



import javax.persistence.*;  

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * MessageResource for DatabaseDriven Messages
 */
@Entity
@Table(name = "UI_MESSAGE_RESOURCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MessageResource implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3103542723330608189L;
	private Long id;
    private String messageKey;
    private String french;
    private String english;
    private String german;
    private String american;
    private String description;
    public MessageResource() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "messageKey", nullable = false)
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Column(name = "french", nullable = true)
    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    @Column(name = "english")
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Column(name = "german", nullable = true)
    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }

    @Column(name = "american", nullable = true)
    public String getAmerican() {
        return american;
    }

    public void setAmerican(String american) {
        this.american = american;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageResource that = (MessageResource) o;

        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;

        return true;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public int hashCode() {
        return messageKey != null ? messageKey.hashCode() : 0;
    }
}