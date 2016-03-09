package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 角色资源表
 * @author YUJB
 * @date 2014年10月27日 上午10:40:02
 */
@Entity
@Table(name="VTP_ROLE_RESOURCE")
public class VtpRoleResource  implements IEntity {


    /**   */
	private static final long serialVersionUID = 1L;

	private String id;
     
    private String roleId;
     
    private String resourceId;
     
    private String resourceType;
    
    private VtpDataResource data;
    
    private VtpFuncSource func;

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

    
    @Column(name="ROLE_ID", nullable=false, length=50)
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    @Column(name="RESOURCE_ID", nullable=false, length=50)
    public String getResourceId() {
        return this.resourceId;
    }
    
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    
    @Column(name="RESOURCE_TYPE", nullable=false, length=10)
    public String getResourceType() {
        return this.resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name="RESOURCE_ID",insertable=false,updatable=false)
	public VtpDataResource getData() {
		return data;
	}

	public void setData(VtpDataResource data) {
		this.data = data;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
    @JoinColumn(name="RESOURCE_ID",insertable=false,updatable=false)
	public VtpFuncSource getFunc() {
		return func;
	}

	public void setFunc(VtpFuncSource func) {
		this.func = func;
	}

    


}


