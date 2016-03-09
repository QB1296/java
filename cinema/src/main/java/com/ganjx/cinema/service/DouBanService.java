/**
 * 文件名： DouBanService.java
 *  
 * 版本信息：  
 * 日期：2015年10月26日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.IOException;
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
import com.ganjx.cinema.entity.DoubanCity;
import com.ganjx.cinema.entity.TCinemaAddress;
import com.google.common.collect.Lists;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月26日 下午3:56:58
 */
@Service
@Transactional
public class DouBanService {

	Logger logger = LoggerFactory.getLogger(DouBanService.class);
	
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
				List<TCinemaAddress> addresses = generalDAO.findByJPQL("from TCinemaAddress t where t.cinemaName=? and t.source=?",new Object[]{cinemaName,"douban"}).result(TCinemaAddress.class);
				if(addresses != null && !addresses.isEmpty()){
					if(addresses.size() == 1){
						TCinemaAddress tCinemaAddress = addresses.get(0);
						if(StringUtils.isNotBlank(tCinemaAddress.getAddress())){
							logger.info("{},{}",tCinemaAddress.getCinemaName(),tCinemaAddress.getAddress());
							BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(tCinemaAddress.getAddress());
							if(locationByAddress != null && locationByAddress.getResult() !=null &&  locationByAddress.getResult().getConfidence()>=70){
								MapLocation location = locationByAddress.getResult().getLocation();
								cinema.setCinemaLat(location.getLat());
								cinema.setCinemaLng(location.getLng());
								cinema.setAddress(tCinemaAddress.getAddress());
								cinema.setConfidence(null);
							}
							else{
								cinema.setAddress(tCinemaAddress.getAddress());
							}
						}
					}
				}
			}
		}
	}
	
	public void readAddress(){
		List<DoubanCity> findAll = generalDAO.findAll(DoubanCity.class);
		for (DoubanCity doubanCity : findAll) {
			Integer totalInteger = 1;
			totalInteger = oneHref(doubanCity,1);
			if(totalInteger>1){
				for (int i = 2; i <= totalInteger; i++) {
					oneHref(doubanCity,i);
				}
			}
		}
	}
	
	private Integer oneHref(DoubanCity lsCity,Integer pageNum) {
		Document doc;
		try {
			System.err.println(lsCity.getName()+"---"+pageNum);
			Connection connect = Jsoup.connect("http://movie.douban.com/cinemas/"+lsCity.getUid()+"/?start="+((pageNum-1)*20)+"&q=&city_id="+lsCity.getId());
			connect.timeout(60*1000);
		    doc = connect.get();
		    Elements select = doc.select(".item");
		    List<TCinemaAddress> cinemaAddresses = Lists.newArrayList();
		    for (Element element : select) {
				String cinemaName = element.select(".offer a").text();
				String address = element.select("address").text().split(" ")[0];
				logger.info("{},{}",cinemaName,address);
				
				TCinemaAddress tCinemaAddress = new TCinemaAddress();
        		tCinemaAddress.setAddress(address);
        		tCinemaAddress.setBaiduLat(null);
        		tCinemaAddress.setBaiduLng(null);
        		tCinemaAddress.setCinemaName(cinemaName);
        		tCinemaAddress.setCity(null);
        		tCinemaAddress.setCityId(null);
        		tCinemaAddress.setCounty(null);
        		tCinemaAddress.setCountyId(null);
        		tCinemaAddress.setGoogleLat(null);
        		tCinemaAddress.setGoolgeLng(null);
        		tCinemaAddress.setSource("douban");
        		tCinemaAddress.setTel(null);
        		tCinemaAddress.setTheatreChain(null);
//        		generalDAO.save(tCinemaAddress);
        		cinemaAddresses.add(tCinemaAddress);
			}
		    generalDAO.batchSave(cinemaAddresses.toArray(new TCinemaAddress[]{}));
		    Elements select2 = doc.select(".paginator a");
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
	
	public void readCity(){
		Document doc;
        try {
        	Connection connect = Jsoup.connect("http://movie.douban.com/cinemas/beijing/?q=");
        	connect.timeout(60*1000);
            doc = connect.get();
            Elements select = doc.select(".cities-list-bd a");
            for (Element element : select) {
            	logger.info(element.attr("id")+","+element.attr("uid")+","+element.text());
            	DoubanCity doubanCity = new DoubanCity();
            	doubanCity.setId(Integer.valueOf(element.attr("id")));
            	doubanCity.setUid(element.attr("uid"));
            	doubanCity.setName(element.text());
            	generalDAO.save(doubanCity);
			}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
