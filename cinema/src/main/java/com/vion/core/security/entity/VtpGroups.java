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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.WhereJoinTable;

import com.vion.core.domain.entity.IEntity;

/**
 * <b>功能描述</b> <br>
 * 账户组表
 * @author YUJB
 * @date 2014年10月27日 上午10:39:06
 */
@Entity
@Table(name="VTP_GROUPS")
public class VtpGroups  implements IEntity {


     /**   */
	private static final long serialVersionUID = 2947770204665989622L;
	
	private String id;
	
    private String name;
    
    private String system;
    
    private List<VtpRoles> roles;
    
    private List<VtpAccounts> accounts;
    
    
    /**
	 * 获得accounts 
	 * @return  accounts accounts
	 */
    @ManyToMany(mappedBy="groups")
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
    
    @OneToMany
    @JoinTable(name = "VTP_ACCOUNT_ROLE", 
    	joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    @WhereJoinTable(clause="ACCOUNT_TYPE = 'GROUP'")
	public List<VtpRoles> getRoles() {
		return roles;
	}
	
	/** 
	 * 设置roles 
	 * @param roles roles 
	 */
	public void setRoles(List<VtpRoles> roles) {
		this.roles = roles;
	}

    @Id 
    @GenericGenerator(name="hibernate-uuid", strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    @Column(name="GROUPS_ID", unique=true, nullable=false, length=50)
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
    @Column(name="GROUPS_NAME", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="GROUPS_SYSTEM", nullable=false, length=50)
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }




}


