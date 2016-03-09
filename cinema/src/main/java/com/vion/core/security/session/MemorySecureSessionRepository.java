/**
 * 文件名：MemorySessionRepository.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

/**
 * <b>功能描述</b> <br>
 * 基于JVM内存的session仓库
 * @author YUJB
 * @date 2014年10月30日 下午1:17:44
 */
public class MemorySecureSessionRepository extends AbstractSecureSessionRepository{
	
	
	private static final Logger logger = LoggerFactory.getLogger(MemorySecureSessionRepository.class);

    private ConcurrentMap<Serializable, SecureSession> sessions;
    

    public MemorySecureSessionRepository() {
        this.sessions = new ConcurrentHashMap<Serializable, SecureSession>();
    }
    
    @Override
	public SecureSession createSession() {
    	SecureSession session = getSecureSessionFactory().createSession();
    	storeSession(session.getId(), session);
    	logger.debug("session[" + session.getId() + "]创建！");
		return session;
	}


    protected SecureSession storeSession(Serializable id, SecureSession session) {
        if (id == null) {
            throw new NullPointerException("session id不能为空");
        }
        return sessions.putIfAbsent(id, session);
    }
    
    
    @Override
	public SecureSession getSession(Serializable sessionId) {
		return sessions.get(sessionId);
	}


    @Override
    public void remove(Serializable	id) {
        if (id != null) {
            sessions.remove(id);
        }
    }


	@Override
	public List<SecureSession> getSessions() {
		Collection<SecureSession> values = sessions.values();
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(new ArrayList<>(values));
        }
	}

	@Override
	public void updateSession(SecureSession session) {
		storeSession(session.getId(), session);
	}


	

}
