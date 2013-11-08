package com.mycompany.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 */
@MappedSuperclass
public abstract class BaseObject implements Serializable {    

	private static final long serialVersionUID = 1L;
	
	private Date createDate = new Date();
	private Date lastUpdateDate;
	
	@Column(name = "create_date")	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "last_update_date")	
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}	
}
