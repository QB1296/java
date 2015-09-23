/**
 * 文件名： TcinemaService.java
 *  
 * 版本信息：  
 * 日期：2015年9月23日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.Tcinema;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年9月23日 上午11:33:32
 */
@Service
@Transactional(readOnly=false)
public class TcinemaService {

	Logger logger = LoggerFactory.getLogger(TcinemaService.class);
	@Autowired
	IGeneralDAO generalDAO;
	
	@Transactional(readOnly=false)
	public void save(Tcinema tcinema) {
		generalDAO.merge(tcinema);
	}
}
