/**
 * 文件名： CinemaService.java
 *  
 * 版本信息：  
 * 日期：2015年10月14日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.basic.map.MapLocation;
import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.excel.CinemaBo;
import com.ganjx.cinema.excel.CinemaDetailBo;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月14日 下午4:22:55
 */
@Service
@Transactional
public class CinemaService {

	@Autowired
	IGeneralDAO generalDAO;
	
	public Integer findMaxId(){
		return generalDAO.findByJPQL("select max(id)  from Cinema").uniqueResult(Integer.class);
	}
	
	public Cinema findByCinemaCode(String cinemaCode){
		Cinema findById = generalDAO.findByJPQL("from Cinema t where t.cinemaCode=?", new String[]{cinemaCode}).uniqueResult(Cinema.class);
		return findById;
	}
	
	public void update(CinemaBo cinemaBo){
		Cinema findById = generalDAO.findByJPQL("from Cinema t where t.cinemaCode=?", new String[]{cinemaBo.getCinemaCode()}).uniqueResult(Cinema.class);
		if(findById != null){
			findById.setTheatreChain(cinemaBo.getTheatreChain());
		}
	}
	
	public void update(MapLocation location,CinemaDetailBo cinemaBo){
		Cinema findById = generalDAO.findByJPQL("from Cinema t where t.cinemaCode=?", new String[]{cinemaBo.getCinemaCode()}).uniqueResult(Cinema.class);
		if(findById != null){
			findById.setCinemaLat(location.getLat());
			findById.setCinemaLng(location.getLng());
			findById.setAddress(cinemaBo.getAddress());
			findById.setConfidence(null);
		}
	}
	
	public void update(CinemaDetailBo cinemaBo){
		Cinema findById = generalDAO.findByJPQL("from Cinema t where t.cinemaCode=?", new String[]{cinemaBo.getCinemaCode()}).uniqueResult(Cinema.class);
		if(findById != null){
			findById.setAddress(cinemaBo.getAddress());
		}
	}
}
