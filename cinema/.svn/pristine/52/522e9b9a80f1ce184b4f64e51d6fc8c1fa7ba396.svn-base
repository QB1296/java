package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.WhereJoinTable;

import com.vion.core.annotation.ExtPOloader;
import com.vion.core.annotation.Loader;
import com.vion.core.annotation.Loaders;
import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 权限角色表
 * @author YUJB
 * @date 2014年10月27日 上午10:42:08
 */
@Entity
@Table(name="VTP_ROLES")
@Loaders({ @Loader(name = "datasLoader", value = "BasicSecureDao!getDatasByRoleId", params = { "id" })})
public class VtpRoles  implements IEntity {

     /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
    private String name;
    
    private String system;
    
    private String isDefault;
    
    private String description;
    
    private List<VtpFuncSource> funcs;
    
    private List<VtpDataResource> datas;
    
    private List<VtpAccounts> accounts;
    
    
    /**
	 * 获得accounts 
	 * @return  accounts accounts
	 */
    @ManyToMany(mappedBy="roles")
	public List<VtpAccounts> getAccounts() {
		return accounts;
	}
	
	/** 
	 * 设置accounts 
	 * @param accounts accounts 
	 */
	public void setAccounts(List<VtpAccounts> accounts) {
		this.accounts = accounts;
	}
    
    /**
	 * 获得datas 
	 * @return  datas datas
	 */
	@Transient
	@ExtPOloader(loaderName = "datasLoader")
	public List<VtpDataResource> getDatas() {
		return datas;
	}
	
	
	/** 
	 * 设置datas 
	 * @param datas datas 
	 */
	public void setDatas(List<VtpDataResource> datas) {
		this.datas = datas;
	}
    
    /**
	 * 获得roles 
	 * @return  roles roles
	 */
    @OneToMany
    @JoinTable(name = "VTP_ROLE_RESOURCE", 
    	joinColumns = { @JoinColumn(name = "ROLE_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") }
    )
    @WhereJoinTable(clause="RESOURCE_TYPE = 'FUNC' ")
	public List<VtpFuncSource> getFuncs() {
		return funcs;
	}
	
	/** 
	 * 设置funcs 
	 * @param funcs funcs 
	 */
	public void setFuncs(List<VtpFuncSource> funcs) {
		this.funcs = funcs;
	}
	

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

    
    @Column(name="ROLE_NAME", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="ROLE_SYSTEM", nullable=false, length=50)
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }

    
    @Column(name="IS_DEFAULT", nullable=false, length=3)
    public String getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    
    @Column(name="DESCRIPTION", length=510)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }




}


