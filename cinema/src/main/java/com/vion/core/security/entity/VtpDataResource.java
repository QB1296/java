package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.vion.core.domain.entity.IEntity;


/**
 * <b>功能描述</b> <br>
 * 数据资源表
 * @author YUJB
 * @date 2014年10月27日 上午10:37:02
 */
@Entity
@Table(name="VTP_DATA_RESOURCE")
public class VtpDataResource  implements IEntity {


     /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
    private String name;
    
    private String content;
    
    private String moudleCode;
    
    private String moudleName;
    
    private String system;

    @Id 
    @GenericGenerator(name="hibernate-uuid", strategy="uuid.hex")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="RESOURCE_ID", unique=true,length=50)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="RESOURCE_NAME", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="CONTENT", nullable=false)
    @Lob
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    
    @Column(name="MOUDLE_CODE", nullable=false, length=3)
    public String getMoudleCode() {
        return this.moudleCode;
    }
    
    public void setMoudleCode(String moudleCode) {
        this.moudleCode = moudleCode;
    }

    
    @Column(name="MOUDLE_NAME", nullable=false, length=510)
    public String getMoudleName() {
        return this.moudleName;
    }
    
    public void setMoudleName(String moudleName) {
        this.moudleName = moudleName;
    }

    
    @Column(name="SYSTEM", nullable=false, length=50)
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }




}


