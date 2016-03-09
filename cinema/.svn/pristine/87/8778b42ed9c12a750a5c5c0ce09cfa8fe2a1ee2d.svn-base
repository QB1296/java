/**   
* @Title: TCity.java 
* @Package com.ganjx.cinema.entity 
* @Description: TODO
* @author ganjianxin   
* @date 2015年10月17日 下午1:45:24 
* @version V1.0   
*/
package com.ganjx.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vion.core.domain.entity.IEntity;

/** 
 * @ClassName: TCity 
 * @Description: TODO
 * @author ganjx 
 * @date 2015年10月17日 下午1:45:24 
 *  
 */
@Table(name="t_city")
@Entity
public class TCity implements IEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer cityid;
	
	private String name;
	
	private Integer pid;

	@Column(name = "name", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pid")
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return "TCity [cityid=" + cityid + ", name=" + name + ", pid=" + pid
				+ "]";
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	
	
	
}
