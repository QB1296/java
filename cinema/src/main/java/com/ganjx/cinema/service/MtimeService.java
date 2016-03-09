/**
 * 文件名： MtimeService.java
 *  
 * 版本信息：  
 * 日期：2015年10月26日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import com.ganjx.cinema.entity.MtimeCity;
import com.ganjx.cinema.entity.TCinemaAddress;
import com.ganjx.cinema.util.HttpUtils;
import com.ganjx.cinema.vo.mtime.Address;
import com.ganjx.cinema.vo.mtime.list.MList;
import com.google.common.collect.Lists;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月26日 下午5:06:18
 */
@Service
@Transactional
public class MtimeService {
	Logger logger = LoggerFactory.getLogger(MtimeService.class);
	
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
				List<TCinemaAddress> addresses = generalDAO.findByJPQL("from TCinemaAddress t where t.cinemaName=? and t.source=?",new Object[]{cinemaName,"mtime"}).result(TCinemaAddress.class);
				if(addresses != null && !addresses.isEmpty()){
					if(addresses.size() == 1){
						OneAddress(cinema, addresses);
					}
				}
			}
		}
	}

	private void OneAddress(Cinema cinema, List<TCinemaAddress> addresses) {
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
	
	public void readToAddress() {
		List<MtimeCity> findAll = generalDAO.findAll(MtimeCity.class);
		for (MtimeCity mtimeCity : findAll) {
			try {
				String string = HttpUtils.get("http://service.theater.mtime.com/Cinema.api?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Cinema.Services&Ajax_CallBackMethod=GetCinemaChannelPageUrlByCityId&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Ftheater.mtime.com%2FChina_Beijing%2F&t=2015102617462564948&Ajax_CallBackArgument0="+mtimeCity.getId());
				logger.info("{},{}",mtimeCity.getName(),string);
				if(StringUtils.isNotBlank(string)){
					String[] split = string.split("\"\\w+\"");
					String string3 = split[2];
					String leftPad = StringUtils.left(string3, string3.length()-3);
					String urlString = leftPad.substring(2);
					readOne(urlString);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			break;
		}
	}
	
	public void readOne(String url){
		Document doc;
        try {
        	Connection connect = Jsoup.connect(url);
        	connect.timeout(60*1000);
            doc = connect.get();
            Pattern pattern = Pattern.compile("\\{\"totalcount[\\s\\S]*};");
            Matcher matcher = pattern.matcher(doc.html());
            ObjectMapper objectMapper = new ObjectMapper();
            String[] ignore = {"（影院停业）","（暂停营业）","（即将开业）","（停业）"};
            while(matcher.find()) {
              String val = matcher.group();
              Address readValue = objectMapper.readValue(val, Address.class); 
              if(readValue != null && readValue.getList()!=null){
            	  List<TCinemaAddress> tCinemaAddresses = Lists.newArrayList();
            	  for (MList mList : readValue.getList()) {
            		String cname = mList.cname;
            		String tempRemove = cname;
            		for (String string : ignore) {
            			tempRemove = StringUtils.remove(tempRemove, string);
					}
					logger.info("{},{},{}",mList.cname,tempRemove,mList.address);
					
					TCinemaAddress tCinemaAddress = new TCinemaAddress();
	        		tCinemaAddress.setAddress(mList.address);
	        		tCinemaAddress.setBaiduLat(null);
	        		tCinemaAddress.setBaiduLng(null);
	        		tCinemaAddress.setCinemaName(tempRemove);
	        		tCinemaAddress.setCity(null);
	        		tCinemaAddress.setCityId(null);
	        		tCinemaAddress.setCounty(null);
	        		tCinemaAddress.setCountyId(null);
	        		tCinemaAddress.setGoogleLat(null);
	        		tCinemaAddress.setGoolgeLng(null);
	        		tCinemaAddress.setSource("mtime");
	        		tCinemaAddress.setTel(null);
	        		tCinemaAddress.setTheatreChain(null);
	        		tCinemaAddresses.add(tCinemaAddress);
				}
            	  generalDAO.batchSave(tCinemaAddresses.toArray(new TCinemaAddress[]{}));
              }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
	public void readCity(){
		Document doc;
        try {
        	doc = Jsoup.parse(new File("E:\\mtime.html"), "UTF-8");
//        	Connection connect = Jsoup.connect("http://theater.mtime.com/China_Beijing/");
//        	connect.timeout(60*1000);
//            doc = connect.get();
            Elements select = doc.select(".p15 a");
            for (Element element : select) {
            	logger.info(element.attr("value")+","+element.text());
            	MtimeCity mtimeCity = new MtimeCity(Integer.valueOf(element.attr("value")),element.text());
            	generalDAO.save(mtimeCity);
			}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
