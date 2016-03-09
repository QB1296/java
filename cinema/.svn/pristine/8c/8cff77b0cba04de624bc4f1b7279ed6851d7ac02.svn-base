/**
 * 文件名： MaizuoService.java
 *  
 * 版本信息：  
 * 日期：2015年10月20日 
 * Copyright(c) 2015 VIONVISION &  CO.,LTD , http://www.vion-tech.com/ <br>
 * 版权所有  
 */
package com.ganjx.cinema.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.MzCity;
import com.ganjx.cinema.entity.TCinemaAddress;
import com.ganjx.cinema.vo.maizuo.MzAddress;
import com.ganjx.cinema.vo.maizuo.data.Data;
import com.vion.core.dao.IGeneralDAO;

/**
 * <b>功能描述</b> <br>
 * 
 * @author GANJX
 * @date 2015年10月20日 上午9:30:06
 */
@Service
@Transactional
public class MaizuoService {

	@Autowired
	IGeneralDAO generalDAO;
	public void readCity(){
		Document doc;
        try {
			doc = Jsoup.parse(new File("E:\\city.txt"), "UTF-8");
			Elements select = doc.select(".city a");
			for (Element element : select) {
				MzCity mzCity = new MzCity();
				mzCity.setCityid(Integer.valueOf(element.attr("cityid")));
				mzCity.setName(element.text());
				generalDAO.save(mzCity);
			};
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readFromMaizuo() throws IOException{
		List<MzCity> findAll = generalDAO.findAll(MzCity.class);
		for (MzCity mzCity : findAll) {
			Document doc;
	        try {
	        	Connection connect = Jsoup.connect("http://www.maizuo.com/list/queryCinemas.htm");
	        	connect.timeout(60*1000);
	        	connect.cookie("mzCityId", String.valueOf(mzCity.getCityid()));
	            doc = connect.get();
	            ObjectMapper objectMapper = new ObjectMapper();
	            MzAddress readValue = objectMapper.readValue(doc.text(), MzAddress.class);
	            if(readValue != null){
	            	List<Data> data = readValue.getData();
	            	for (Data data2 : data) {
	            		TCinemaAddress tCinemaAddress = new TCinemaAddress();
	            		tCinemaAddress.setAddress(data2.getAddress());
	            		if(StringUtils.isNotBlank(data2.getMapInfo()) && !data2.getMapInfo().equals(":")){
	            			System.err.println(data2.getMapInfo());
	            			String[] split = data2.getMapInfo().split(":");
	            			tCinemaAddress.setBaiduLat(new BigDecimal(split[1].trim()));
	            			tCinemaAddress.setBaiduLng(new BigDecimal(split[0].trim()));
	            		}
	            		tCinemaAddress.setCinemaName(data2.getName());
	            		tCinemaAddress.setCity(null);
	            		tCinemaAddress.setCityId((int)data2.getCityId());
	            		tCinemaAddress.setCounty(data2.getRegionName());
	            		tCinemaAddress.setCountyId(null);
	            		tCinemaAddress.setGoogleLat(null);
	            		tCinemaAddress.setGoolgeLng(null);
	            		tCinemaAddress.setSource("maizuo");
	            		tCinemaAddress.setTel(data2.getTel());
	            		tCinemaAddress.setTheatreChain(null);
	            		generalDAO.save(tCinemaAddress);
					}
	            }
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
		}
		
	}
}
