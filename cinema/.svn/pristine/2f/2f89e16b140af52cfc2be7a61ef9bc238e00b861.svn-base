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
 * 角色辅助资源表
 * @author YUJB
 * @date 2014年10月27日 上午10:40:02
 */
@Entity
@Table(name="VTP_ROLE_ASSIST")
public class VtpRoleAssist  implements IEntity {


    /**   */
	private static final long serialVersionUID = 1L;

	private String id;
     
    private String roleId;
     
    private String dataResourceId;
    
    private String resourceCode;

	/**
	 * {@link #id}
	 * @return the id
	 */
    @Id
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="SID")
	public String getId() {
		return id;
	}

	/**
	 * {@link #id}	
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@link #roleId}
	 * @return the roleId
	 */
	@Column(name="ROLE_ID")
	public String getRoleId() {
		return roleId;
	}

	/**
	 * {@link #roleId}	
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * {@link #dataResourceId}
	 * @return the dataResourceId
	 */
	@Column(name="DATA_RESOURCE_ID")
	public String getDataResourceId() {
		return dataResourceId;
	}

	/**
	 * {@link #dataResourceId}	
	 * @param dataResourceId the dataResourceId to set
	 */
	public void setDataResourceId(String dataResourceId) {
		this.dataResourceId = dataResourceId;
	}

	/**
	 * {@link #resourceCode}
	 * @return the resourceCode
	 */
	@Column(name="RESOURCE_CODE")
	public String getResourceCode() {
		return resourceCode;
	}

	/**
	 * {@link #resourceCode}	
	 * @param resourceCode the resourceCode to set
	 */
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
    
    
    
    
    

    


}


