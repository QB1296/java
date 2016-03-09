/**
 * 文件名： LashouService.java
 *  
 * 版本信息：  
 * 日期：2015年10月20日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.basic.map.BaiduMapResponse;
import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.basic.map.MapLocation;
import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.entity.LsCity;
import com.ganjx.cinema.entity.TCinemaAddress;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月20日 下午12:58:19
 */
@Service
@Transactional
public class LashouService {

	Logger logger = LoggerFactory.getLogger(LashouService.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	
	@Autowired
	BaiduMapService baiduMapService;
	
	public void readFromCinemaAndAddress(){
		List<Cinema> result = generalDAO.findByJPQL("from Cinema").result(Cinema.class);
		for (Cinema cinema : result) {
			if (cinema.getCinemaLat() == null) {
				///根据影院名称查询
				String cinemaName = cinema.getCinemaName();
				List<TCinemaAddress> addresses = generalDAO.findByJPQL("from TCinemaAddress t where t.cinemaName=? and t.source=?",new Object[]{cinemaName,"lashou"}).result(TCinemaAddress.class);
				if(addresses != null && !addresses.isEmpty()){
					if(addresses.size() == 1){
						TCinemaAddress tCinemaAddress = addresses.get(0);
						if(tCinemaAddress.getBaiduLat() != null && tCinemaAddress.getBaiduLat().compareTo(new BigDecimal(0)) !=0){
							cinema.setCinemaLat(tCinemaAddress.getBaiduLat());
							cinema.setCinemaLng(tCinemaAddress.getBaiduLng());
							cinema.setAddress(tCinemaAddress.getAddress());
							cinema.setConfidence(null);
						}
					}
				}
			}
		}
	}
	
	public void readFromCinemaAndBaidu(){
		List<Cinema> result = generalDAO.findByJPQL("from Cinema t where t.confidence=10").result(Cinema.class);
		for (Cinema cinema : result) {
			if (cinema.getCinemaLat() == null  && cinema.getConfidence()!=null && cinema.getConfidence().equals(10)) {
				BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(cinema.getCinemaName());
				if(locationByAddress != null && locationByAddress.getResult() != null){
					Integer confidence = locationByAddress.getResult().getConfidence();
					if(confidence >=60){
						logger.info("{}",locationByAddress.getResult().getLocation());
						MapLocation location = locationByAddress.getResult().getLocation();
						cinema.setCinemaLat(location.getLat());
						cinema.setCinemaLng(location.getLng());
//						cinema.setConfidence(null);
					}
				}
			}
		}
	}
	
	public void readByCity() {
		List<LsCity> findAll = generalDAO.findAll(LsCity.class);
		for (LsCity lsCity : findAll) {
			Integer totalInteger = 1;
			totalInteger = oneHref(lsCity,1);
			System.err.println(totalInteger);
			if(totalInteger>1){
				for (int i = 2; i <= totalInteger; i++) {
					oneHref(lsCity,i);
				}
			}
		}
	}

	private Integer oneHref(LsCity lsCity,Integer pageNum) {
		Document doc;
		try {
			System.err.println(lsCity.getName()+"---"+pageNum);
			Connection connect = Jsoup.connect(lsCity.getHref()+"/movies/cinemalist/type0/page"+pageNum);
			connect.timeout(60*1000);
		    doc = connect.get();
		    Elements select = doc.select(".cliema-info");
		    for (Element element : select) {
				String cinemaName = element.select("h3 a").text();
				Elements select2 = element.select("dl dd");
				String address = select2.select("span").text();
				Elements select3 = select2.select("a");
				String lng = select3.attr("data-lng");
				String lat = select3.attr("data-lat");
				logger.info("{},{},{},{}",cinemaName,address,lng,lat);
				
				TCinemaAddress tCinemaAddress = new TCinemaAddress();
        		tCinemaAddress.setAddress(address);
        		tCinemaAddress.setBaiduLat(new BigDecimal(lat));
        		tCinemaAddress.setBaiduLng(new BigDecimal(lng));
        		tCinemaAddress.setCinemaName(cinemaName);
        		tCinemaAddress.setCity(null);
        		tCinemaAddress.setCityId(null);
        		tCinemaAddress.setCounty(null);
        		tCinemaAddress.setCountyId(null);
        		tCinemaAddress.setGoogleLat(null);
        		tCinemaAddress.setGoolgeLng(null);
        		tCinemaAddress.setSource("lashou");
        		tCinemaAddress.setTel(null);
        		tCinemaAddress.setTheatreChain(null);
        		generalDAO.save(tCinemaAddress);
			}
		    
		    Elements select2 = doc.select(".page2 a");
		    if(select2.size() >2){
			    Element element2 = select2.get(select2.size()-2);
			    String totalString = element2.text();
			    return Integer.parseInt(totalString);
		    }
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		return 1;
	}
}
