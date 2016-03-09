/**   
* @Title: CityCode.java 
* @Package com.ganjx.cinema.entity 
* @Description: TODO
* @author ganjianxin   
* @date 2015年11月4日 下午9:07:55 
* @version V1.0   
*/
package com.ganjx.cinema.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.vion.core.domain.entity.IEntity;

/** 
 * @ClassName: CityCode 
 * @Description: TODO
 * @author ganjx 
 * @date 2015年11月4日 下午9:07:55 
 *  
 */
@Entity
@Table(name = "city_code")
@DynamicUpdate
public class CityCode implements IEntity{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String pid;
	private String detail;
	private String suffix;
	private CityCode parent;
	private String level;
	/** 
	 * <p>Title: </p> 
	 * <p>Description: </p>  
	 */
	public CityCode() {
		// TODO Auto-generated constructor stub
	}
	public CityCode(String id, String name, String pid, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.detail = detail;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "pid")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(name = "detail")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@ManyToOne
	@JoinColumn(name="pid",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	public CityCode getParent() {
		return parent;
	}
	public void setParent(CityCode parent) {
		this.parent = parent;
	}
	
	@Column(name = "suffix")
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	@Column(name = "level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "CityCode [id=" + id + ", name=" + name + ", pid=" + pid
				+ ", detail=" + detail + ", suffix=" + suffix + ", parent="
				+ parent + ", level=" + level + "]";
	}
	
	
}
