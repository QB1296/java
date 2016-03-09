/**   
* @Title: TCinemaAddressService.java 
* @Package com.ganjx.cinema.service 
* @Description: TODO
* @author ganjianxin   
* @date 2015年10月16日 下午10:04:36 
* @version V1.0   
*/
package com.ganjx.cinema.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.basic.map.BaiduMapResponse;
import com.ganjx.cinema.basic.map.BaiduMapService;
import com.ganjx.cinema.basic.map.MapLocation;
import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.entity.TCinemaAddress;
import com.ganjx.cinema.entity.TCity;
import com.ganjx.cinema.util.HttpUtils;
import com.ganjx.cinema.vo.address1905.AddressInfo;
import com.ganjx.cinema.vo.address1905.arealist.Arealist;
import com.ganjx.cinema.vo.address1905.info.Info;
import com.vion.core.dao.IGeneralDAO;
/** 
 * @ClassName: TCinemaAddressService 
 * @Description: TODO
 * @author ganjx 
 * @date 2015年10月16日 下午10:04:36 
 *  
 */
@Service
@Transactional
public class TCinemaAddressService {

	Logger logger  = LoggerFactory.getLogger(TCinemaAddressService.class);
	
	@Autowired
	BaiduMapService baiduMapService;
	@Autowired
	IGeneralDAO generalDAO;
	BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public void readCinemaFromDb(){
		List<Cinema> result = generalDAO.findByJPQL("from Cinema").result(Cinema.class);
		for (Cinema cinema : result) {
			if (cinema.getCinemaLat() == null) {
				///根据影院名称查询
				String cinemaName = cinema.getAddress();
				if(StringUtils.isNotBlank(cinemaName)){
					BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(cinemaName);
					if(locationByAddress.getResult() != null){
						Integer precise = locationByAddress.getResult().getPrecise();
						if(precise >0){
							logger.info("{}",locationByAddress.getResult().getLocation());
							MapLocation location = locationByAddress.getResult().getLocation();
							cinema.setCinemaLat(location.getLat());
							cinema.setCinemaLng(location.getLng());
							cinema.setConfidence(null);
						}
					}
				}
			}
		}
	}
	
	public void readCinema(){
		Integer i = 0;
		Integer j = 0;
		List<Cinema> result = generalDAO.findByJPQL("from Cinema").result(Cinema.class);
		for (Cinema cinema : result) {
			if(cinema.getCinemaLat() == null){
				String cinemaName = cinema.getCinemaName();
				if(cinemaName.indexOf("省") != -1){
					String[] split = cinemaName.split("省");
					if(split.length>1){
						cinemaName=split[0];
					}
				}
				List<TCinemaAddress> result2 = generalDAO.findByJPQL("from TCinemaAddress t where t.cinemaName=? and t.source=?",new Object[]{cinemaName,"lashou"}).result(TCinemaAddress.class);
				int size = result2.size();
				if(size>0){
					if(size == 1){
						TCinemaAddress tCinemaAddress = result2.get(0);
						System.err.println(tCinemaAddress.getCinemaName());
//						if(StringUtils.isNotBlank(tCinemaAddress.getAddress()) && !tCinemaAddress.getBaiduLng().equals(0)){
//							logger.info("{}",tCinemaAddress);	
//							cinema.setCinemaLng(tCinemaAddress.getBaiduLng());
//							cinema.setCinemaLat(tCinemaAddress.getBaiduLat());
//							cinema.setAddress(tCinemaAddress.getAddress());
//						}
					}
				}
				
				//可信度为80
//				String address = cinema.getAddress();
//				if(address != null){
//					BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(address);
//					if(locationByAddress.getResult() != null){
//						Integer confidence = locationByAddress.getResult().getConfidence();
////						if(confidence>=70){
//							logger.info("{}",cinema.getCinemaName());
//							MapLocation location = locationByAddress.getResult().getLocation();
//							cinema.setCinemaLat(location.getLat());
//							cinema.setCinemaLng(location.getLng());
//							cinema.setConfidence(confidence);
////						}
//					}
//				}
				i++;
			}
		}
		System.err.println(i);
		System.err.println(j);
	}
	
	public void readCinemaAddress(){
		List<TCinemaAddress> result = generalDAO.findByJPQL("from TCinemaAddress t where t.source=?",new Object[]{"lashou"}).result(TCinemaAddress.class);
		for (TCinemaAddress tCinemaAddress : result) {
			
			try {
				BaiduMapResponse locationByAddress = baiduMapService.getLocationByAddress(tCinemaAddress.getAddress());
				if(locationByAddress.getResult() !=null && locationByAddress.getResult().getPrecise() >0){
					logger.info("{}",tCinemaAddress);
					MapLocation location = locationByAddress.getResult().getLocation();
					tCinemaAddress.setBaiduLat(location.getLat());
					tCinemaAddress.setBaiduLng(location.getLng());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void readCity(){
		Document doc;
        try {
        	Connection connect = Jsoup.connect("http://www.1905.com/yx/cinema_1/c1.html");
        	connect.timeout(60*1000);
            doc = connect.get();
            Elements select = doc.select(".wrapper_nav_1905all .clearfix_smile ul");
            Elements select2 = select.select("a");
            for (Element element : select2) {
            	String attr = element.attr("href");
            	String def = "http://www.1905.com/yx/cinema_1/c";
            	if(attr.startsWith(def)){
            		String right = StringUtils.substring(attr,def.length());
            		TCity tCity = new TCity();
            		tCity.setCityid(Integer.parseInt(right.split("\\.")[0]));
            		tCity.setName(element.text());
            		generalDAO.merge(tCity);
            	}
			}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void readFromCity(){
		List<TCity> result = generalDAO.findByJPQL("from TCity").result(TCity.class);
		for (TCity tCity : result) {
			readFrom1905(tCity.getCityid());
		}
	}
	
	public void readFrom1905(Integer cityid){
		try {
			String source = "1905";
			String string = HttpUtils.get("http://www.1905.com/api/yx/yxforcinema.php?cityid="+cityid+"&callback=&type=list&s=0&n=500&_="	+ System.currentTimeMillis());
			ObjectMapper objectMapper = new ObjectMapper();
			AddressInfo readValue = objectMapper.readValue(string, AddressInfo.class);
			if(readValue != null){
				List<Arealist> arealist = readValue.getArealist();
				if(arealist != null){
					for (Arealist arealist2 : arealist) {
						TCity tCity = new TCity();
						BeanUtils.copyProperties(arealist2, tCity);
						tCity.setPid(cityid);
						generalDAO.merge(tCity);
					}
				}
				List<Info> info = readValue.getInfo();
				if(info != null){
					for (Info info2 : info) {
						TCinemaAddress tCinemaAddress = new TCinemaAddress();
						tCinemaAddress.setAddress(info2.getAddr());
						tCinemaAddress.setBaiduLat(info2.getBaidu_latitude());
						tCinemaAddress.setBaiduLng(info2.getBaidu_longitude());
						tCinemaAddress.setCinemaName(info2.getName());
						tCinemaAddress.setCityId(cityid);
						tCinemaAddress.setCountyId(info2.getCityid());
						try {
							String strip = StringUtils.strip(info2.getGoogle_latitude().toString());
							if( strip != null)
							tCinemaAddress.setGoogleLat(new BigDecimal(strip));
						} catch (Exception e) {
							// TODO: handle exception
							logger.error("错误：{}",e);
						}
						
						tCinemaAddress.setGoolgeLng(info2.getGoogle_longitude());
						tCinemaAddress.setSource(source);
						tCinemaAddress.setTel(info2.getTel());
	//					tCinemaAddress.setTheatreChain();
						generalDAO.merge(tCinemaAddress);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromUrl(){
		Document doc;
        try {
        	Connection connect = Jsoup.connect("http://www.lefeng.com/zhuanti_yydz.html");
        	connect.timeout(60*1000);
            doc = connect.get();
            Elements select = doc.select(".wraper table tbody tr");
            ListIterator<Element> listIterator = select.listIterator();
            int i =1;
            while(listIterator.hasNext()){
            	Element next = listIterator.next();
            	if(i == 1){
            		i++;
            		continue;
            	}
            	else{
            		parserElement(next);
            		///////
//            		logger.info("{}",tCinemaAddress);
            	}
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		 
	}

	/** 
	* @Title: parserElement 
	* @Description: TODO
	* @param @param next  
	* @return void    
	* @throws 
	*/
	public void parserElement(Element next) {
		TCinemaAddress tCinemaAddress = new TCinemaAddress();
		Elements children = next.children();
		ListIterator<Element> listIterator2 = children.listIterator();
		int j =0;
		while(listIterator2.hasNext()){
			Element next2 = listIterator2.next();
			System.out.println(next2.html());
			try {
				Element child = next2.child(0);
				switch (j) {
				case 0:
					tCinemaAddress.setCity(child.ownText());
					break;
				case 1: 
					tCinemaAddress.setCounty(child.ownText());
					break;
				case 2:
					tCinemaAddress.setCinemaName(child.ownText());
					break;
				case 3:
					tCinemaAddress.setAddress(child.ownText());
					break;
				case 4:
					tCinemaAddress.setTel(child.ownText());
					break;
				case 5:
					tCinemaAddress.setTheatreChain(child.ownText());
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j++;
		}
		logger.info("{}",tCinemaAddress);
		generalDAO.save(tCinemaAddress);
	}
	

}
