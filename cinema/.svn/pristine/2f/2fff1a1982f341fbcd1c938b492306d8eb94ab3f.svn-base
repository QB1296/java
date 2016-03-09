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
 * 账户账户组角色表
 * @author YUJB
 * @date 2014年10月27日 上午10:33:47
 */
@Entity
@Table(name="VTP_ACCOUNT_ROLE")
public class VtpAccountRole  implements IEntity{


     /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
    private String roleId;
    
    private String accountId;
    
    private String accountType;

   
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

    
    @Column(name="ACCOUNT_ID", nullable=false, length=50)
    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    
    @Column(name="ACCOUNT_TYPE", nullable=false, length=10)
    public String getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }




}


