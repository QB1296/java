package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 功能资源表
 * @author YUJB
 * @date 2014年10月27日 上午10:38:26
 */
@Entity
@Table(name="VTP_FUNC_SOURCE")
public class VtpFuncSource  implements IEntity {


     /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String code;
	
	private String name;
	
	private String pid;
	
	private String type;
	
	private String url;
	
	private String system;
	
	/**   */
	@Transient
	public static String TYPE_MENU = "MENU";
	
	@Transient
	public static String TYPE_FUNC = "FUNC";
	
	@Transient
	public static String ROOT_PID = "0";
	

    @Id 
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="FUNC_ID", unique=true, nullable=false, length=50)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="FUNC_CODE", nullable=false, length=50)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="FUNC_NAME", length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="PID", nullable=false, length=50)
    public String getPid() {
        return this.pid;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }

    
    @Column(name="FUNC_TYPE", length=10)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    
    @Column(name="URL", length=510)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    
    @Column(name="SYSTEM", length=50)
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }




}


