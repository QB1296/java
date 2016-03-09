/**
 * 文件名： KdmCinemaService.java
 *  
 * 版本信息：  
 * 日期：2015年11月30日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.entity.KdmCinema;
import com.google.common.collect.Lists;
import com.sun.corba.se.spi.orb.StringPair;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年11月30日 下午6:09:47
 */
@Service
@Transactional
public class KdmCinemaService {

	Logger logger = LoggerFactory.getLogger(KdmCinemaService.class);

	@Autowired
	IGeneralDAO generalDAO;
	@Autowired
	CinemaService cinemaService;

	@Transactional(readOnly = false)
	public void process() {
		List<KdmCinema> findAll = generalDAO.findAll(KdmCinema.class);
		for (KdmCinema kdmCinema : findAll) {
			// single(kdmCinema);
			amendScreenCode(kdmCinema);
		}
	}

	@Transactional(readOnly = false)
	public void amendScreenCode(KdmCinema kdmCinema) {
		List<String> zhStrings = Lists.newArrayList("一","二","三","四","五","六","七","八","九","十");
		String pattern = "[\\d一二三四五六七八九十]{1}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(kdmCinema.getScreenCode());
		List<String> containList = Lists.newArrayList();
		while (m.find()) {
			containList.add(m.group(0));
		}
		if(!containList.isEmpty()){
			List<String> result = Lists.newArrayList();
			for (String string : containList) {
				if(NumberUtils.isDigits(string)){
					result.add(string);
				}
				else{
					int indexOf = zhStrings.indexOf(string);
					result.add(String.valueOf(indexOf+1));
				}
			}
			String join = StringUtils.join(result,null);
			if(!result.isEmpty() && result.size() >=2 && Integer.valueOf(result.get(0))>2){
				join = result.get(0);
			}
			kdmCinema.setNscreenCode(join);
		}
	}

	@Transactional(readOnly = false)
	public void single(KdmCinema kdmCinema) {
		Cinema findByCinemaCode = cinemaService.findByCinemaCode(kdmCinema
				.getCinemaCode());
		if (findByCinemaCode == null) {
			logger.info("{}", kdmCinema.getCinemaCode());
		} else {
			kdmCinema.setCinemaName(findByCinemaCode.getCinemaName());
			kdmCinema.setLstatus(1);
		}
	}
}
