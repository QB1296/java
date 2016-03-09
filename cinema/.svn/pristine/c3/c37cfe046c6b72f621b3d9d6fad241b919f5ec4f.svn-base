package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 系统表
 * @author YUJB
 * @date 2014年10月27日 上午10:42:56
 */
@Entity
@Table(name="VTP_SYSTEM")
public class VtpSystem  implements IEntity {


    /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
    private String code;
    
    private String name;
    
    private String url;

    @Id 
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="SID", unique=true, nullable=false, length=50)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="SYS_CODE", nullable=false, length=50)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="SYS_NAME", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="URL")
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }




}


