package com.vion.core.security.entity;
// Generated 2014-10-27 10:28:06 by Hibernate Tools 4.3.1


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.WhereJoinTable;

import com.vion.core.domain.entity.SpiderEntity;


/**
 * <b>功能描述</b> <br>
 * 账户表
 * @author YUJB
 * @date 2014年10月27日 上午10:35:32
 */
@Entity
@Table(name="VTP_ACCOUNTS")
public class VtpAccounts  extends SpiderEntity {


     /**   */
	private static final long serialVersionUID = 1L;
	
	private String accountsIdentify;
	
	private String loginName;
	
	private String password;
	
    private String name;
    
    private String system;
    
    private long loginCount;
    
    private String isValid;
    
    private List<VtpRoles> roles;
    
    private List<VtpGroups> groups;
    
    
    @Transient
    public List<VtpRoles> getAllRoles(){
    	List<VtpRoles> retRoles = new ArrayList<VtpRoles>();
    	List<VtpRoles> roles = getRoles();
    	if (roles != null) {
			retRoles.addAll(roles);
		}
    	retRoles.addAll(getGroupRoles());
    	return retRoles;
    }
    
    
    @Transient
    public List<VtpRoles> getGroupRoles(){
    	List<VtpRoles> retRoles = new ArrayList<VtpRoles>();
    	List<VtpGroups> groups = getGroups();
    	if(groups != null){
    		for (VtpGroups vtpGroups : groups) {
    			List<VtpRoles> groupRoles = vtpGroups.getRoles();
    			if(groupRoles != null){
    				retRoles.addAll(groupRoles);
    			}
			}
    	}
    	return retRoles;
    }
    
    /**
	 * 获得groups 
	 * @return  groups groups
	 */
    @ManyToMany
    @JoinTable(name = "VTP_ACCOUNT_GROUP", 
    	joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") }
    )
	public List<VtpGroups> getGroups() {
		return groups;
	}
	
	/** 
	 * 设置groups 
	 * @param groups groups 
	 */
	public void setGroups(List<VtpGroups> groups) {
		this.groups = groups;
	}
    
    /**
	 * 获得roles 
	 * @return  roles roles
	 */
	@ManyToMany
    @JoinTable(name = "VTP_ACCOUNT_ROLE", 
    	joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") }
    )
    @WhereJoinTable(clause="ACCOUNT_TYPE = 'SIGNLE'")
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
    
    /** 
	 * 获取accountsIdentify 
	 * @return accountsIdentify accountsIdentify 
	 */
	@Id
    @Column(name="ACCOUNTS_IDENTIFIER", unique=true, nullable=false, length=128)
	public String getAccountsIdentify() {
		return accountsIdentify;
	}


	/**  
	 * 设置accountsIdentify  
	 * @param accountsIdentify accountsIdentify  
	 */
	public void setAccountsIdentify(String accountsIdentify) {
		this.accountsIdentify = accountsIdentify;
	}
    
    @Column(name="USER_NAME", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="SYSTEM", nullable=false, length=50)
    public String getSystem() {
        return this.system;
    }
    
    public void setSystem(String system) {
        this.system = system;
    }

    
    @Column(name="LOGIN_COUNT", nullable=false, precision=10, scale=0)
    public long getLoginCount() {
        return this.loginCount;
    }
    
    public void setLoginCount(long loginCount) {
        this.loginCount = loginCount;
    }

    
    @Column(name="IS_VALID", length=10)
    public String getIsValid() {
        return this.isValid;
    }
    
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Column(name="LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


    

}


