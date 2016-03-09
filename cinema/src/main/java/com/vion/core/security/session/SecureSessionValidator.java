/**
 * 文件名：SecureSessionValidator.java  
 *  
 * 版本信息：  
 * 日期：2014年10月30日  
 * Copyright(c) 2014 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */	
 
package com.vion.core.security.session;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


/**
 * <b>功能描述</b> <br>
 * session 验证器
 * @author YUJB
 * @date 2014年10月30日 下午2:26:21
 */
public class SecureSessionValidator extends TimerTask{
	
	private ScheduledExecutorService service;
	 
	/** 默认半分钟，单位毫秒  */
	private long interval = 1000*30*1;
	 
	private ValidatingSecureSessionManager secureSessionManager;
	 
	private boolean isStart = false;
	 
	 
	 
	public SecureSessionValidator(ValidatingSecureSessionManager secureSessionManager) {
		super();
		this.secureSessionManager = secureSessionManager;
	}

	public void start() {
	 	if (isStart) {
			return;
		}
        if (this.interval > 0L) {
        	isStart = true;
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {  
	        public Thread newThread(Runnable r) {  
	            Thread thread = new Thread(r);  
	            thread.setDaemon(true);  
	            return thread;  
                }  
            });                  
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
        }
	}

	@Override
    public void run() {
        this.secureSessionManager.validateSessions();
    }

    public void stop() {
        this.service.shutdownNow();
    }
    
}
