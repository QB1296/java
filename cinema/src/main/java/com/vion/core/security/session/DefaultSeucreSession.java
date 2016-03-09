/**
 * 文件名：SecureSimpleSession.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <b>功能描述</b> <br>
 *
 * @author YUJB
 * @date 2014年10月30日 下午12:57:03
 */
public class DefaultSeucreSession extends AbstractMapSecureSession{

    private transient static final Logger logger = LoggerFactory.getLogger(DefaultSeucreSession.class);

    protected static final long MILLIS_PER_SECOND = 1000;
    
    protected static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    
    protected static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    private  Serializable id;
    
    private  Date startTimestamp;
    
    private  Date lastAccessTime;
    
    private  long timeout;
    
    private  boolean expired;
    

    public DefaultSeucreSession() {
        this.startTimestamp = new Date();
        this.lastAccessTime = this.startTimestamp;
    }

    public DefaultSeucreSession(String host) {
        this();
    }

    public Serializable getId() {
        return this.id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Date getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public long getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }


    public void touch() {
        this.lastAccessTime = new Date();
    }


    public boolean isValid() {
    	boolean isValid = true;
    	if(isExpired()){
    		isValid = false;
    	}else {
			isValid = !isTimedOut();
		}
        if(!isValid){
        	logger.debug("session[" + getId() + "]超时！");
        }
        return isValid;
    }

    
    protected boolean isTimedOut() {
        if (isExpired()) {
            return true;
        }

        long timeout = getTimeout();

        if (timeout >= 0l) {

            Date lastAccessTime = getLastAccessTime();

            long expireTimeMillis = System.currentTimeMillis() - timeout*MILLIS_PER_MINUTE;
            Date expireTime = new Date(expireTimeMillis);
            return lastAccessTime.before(expireTime);
        }
        return false;
    }


    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            attributes = new HashMap<Object, Object>();
            setAttributes(attributes);
        }
        return attributes;
    }

    public Collection<Object> getAttributeKeys() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return Collections.emptySet();
        }
        return attributes.keySet();
    }

    public Object getAttribute(Object key) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    public void setAttribute(Object key, Object value) {
        if (value == null) {
            removeAttribute(key);
        } else {
            getAttributesLazy().put(key, value);
        }
    }

    public Object removeAttribute(Object key) {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        } else {
            return attributes.remove(key);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DefaultSeucreSession) {
            DefaultSeucreSession other = (DefaultSeucreSession) obj;
            Serializable thisId = getId();
            Serializable otherId = other.getId();
            if (thisId != null && otherId != null) {
                return thisId.equals(otherId);
            } 
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append(",id=").append(getId());
        return sb.toString();
    }





}

