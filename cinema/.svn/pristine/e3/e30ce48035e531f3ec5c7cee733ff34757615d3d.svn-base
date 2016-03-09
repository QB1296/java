/**   
* @Title: CinemaNameService.java 
* @Package com.ganjx.cinema.service 
* @Description: TODO
* @author ganjianxin   
* @date 2015年11月4日 下午8:45:19 
* @version V1.0   
*/
package com.ganjx.cinema.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ganjx.cinema.entity.Cinema;
import com.ganjx.cinema.entity.CityCode;
import com.google.common.collect.Lists;
import com.vion.core.dao.IGeneralDAO;

/** 
 * @ClassName: CinemaNameService 
 * @Description: TODO
 * @author ganjx 
 * @date 2015年11月4日 下午8:45:19 
 *  
 */
@Service
@Transactional
public class CinemaNameService {

	Logger logger = LoggerFactory.getLogger(CinemaNameService.class);
	
	@Autowired
	IGeneralDAO generalDAO;
	
	/** 
	* @Title: readCinema 
	* @Description: TODO
	* @param   
	* @return void    
	* @throws 
	*/
	public void readCinema() {
		String hql = "from Cinema t where t.cinemaCode like ?";
		List<Cinema> findAll = generalDAO.findByJPQL(hql,new Object[]{"65%"}).result(Cinema.class);
		for (Cinema cinema : findAll) {
			resolveCinema(cinema);
		}
	}
	
	
	/** 
	* @Title: resolveCinema 
	* @Description: TODO
	* @param @param cinema  
	* @return void    
	* @throws 
	*/
	public void resolveCinema(Cinema cinema) {
		String cityCode = StringUtils.left(cinema.getCinemaCode().toString(), 4);
		String provinceCode = StringUtils.left(cityCode, 2);
		String cityString = StringUtils.rightPad(cityCode, cityCode.length()+2,"0");
		CityCode province = generalDAO.findById(CityCode.class, StringUtils.rightPad(provinceCode, provinceCode.length()+4,"0"));
		CityCode city = generalDAO.findById(CityCode.class, cityString);
		if(province !=null && city != null){
			String proName = province.getName()+province.getSuffix();
			String cityName = city.getName()+city.getSuffix();
			
			String newName = removeSymbolList(cinema.getCinemaName());
			if(proName.equals(cityName)){
				one(province,cinema,newName);
			}
			else{
				two(province,city,cinema,newName);
			}
		}
	}
	
	public String removeSymbolList(String cinemaName){
		cinemaName = StringUtils.trimToEmpty(cinemaName);
		cinemaName = StringUtils.remove(cinemaName, " ");
		cinemaName = StringUtils.remove(cinemaName, "--");
		cinemaName = StringUtils.remove(cinemaName, "-");
		cinemaName =replaceSymbol(cinemaName,"\\(.*\\)",Lists.newArrayList("(",")"));
		cinemaName =replaceSymbol(cinemaName,"（.*）",Lists.newArrayList("（","）"));
		cinemaName =replaceSymbol(cinemaName,"\\(.*）",Lists.newArrayList("(","）"));
		cinemaName =replaceSymbol(cinemaName,"（.*\\)",Lists.newArrayList("（",")"));
		return cinemaName;
	}
	
	/**
	 * 直辖市
	 * @param code
	 */
	public void one(CityCode code,Cinema cinema,String nCinemaName){
    	List<String> ignoreStrings = Lists.newArrayList(code.getName(),code.getSuffix());
    	nCinemaName = removeStringList(ignoreStrings,nCinemaName);
    	cinema.setAliasName(code.getName()+code.getSuffix()+nCinemaName);
		logger.info("{}",cinema.getAliasName());
	}
	
	/**
	 * 替换字符
	 * @param cinemaName
	 * @param patternString
	 * @param removeList
	 * @return
	 */
	public String replaceSymbol(String cinemaName,String patternString,List<String> removeList){
		Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(cinemaName);
        if(matcher.find()){
        	String group = matcher.group(0);
        	cinemaName = StringUtils.remove(cinemaName, group);
        	String removeStringList = removeStringList(removeList, group);
        	if(removeStringList.lastIndexOf("店") == -1){
        		removeStringList += "店";
        	}
        	cinemaName += removeStringList;
        }
        return cinemaName;
	}
	
	public String removeStringList(List<String> ignoreStrings,String orginal){
		String temp = orginal;
		for (String string : ignoreStrings) {
			temp = StringUtils.remove(temp, string);
		}
		return temp;
	}
	
	public void two(CityCode pro,CityCode city,Cinema cinema,String nCinemaName){
		List<String> ignoreStrings = Lists.newArrayList(pro.getName()+pro.getSuffix(),pro.getName(),city.getName()+city.getSuffix(),city.getName());
    	nCinemaName = removeStringList(ignoreStrings,nCinemaName);
    	nCinemaName = pro.getName()+pro.getSuffix()+city.getName()+city.getSuffix()+nCinemaName;
    	cinema.setAliasName(nCinemaName);
		logger.info("{},{}",cinema.getCinemaName(),nCinemaName);
	}
}
