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
 * 账户账户组关系表
 * @author YUJB
 * @date 2014年10月27日 上午10:32:04
 */
@Entity
@Table(name="VTP_ACCOUNT_GROUP")
public class VtpAccountGroup  implements IEntity {


     /**   */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
    /** 账户ID  */
    private String accountId;
    
    /** 账户组ID  */
    private String groupId;

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

    
    @Column(name="ACCOUNT_ID", nullable=false, length=50)
    public String getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    
    @Column(name="GROUP_ID", nullable=false, length=50)
    public String getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }




}


