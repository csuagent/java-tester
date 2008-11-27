package org.jtester.dbtest.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseBean implements Serializable {
	private int id;

	/**
	 * 记录创建时间
	 */
	private Date createDate;

	/**
	 * 操作员的名称
	 */
	private String creator;

	/**
	 * 记录修改时间
	 */
	private Date modifiedDate;

	/**
	 * 操作员的名称
	 */
	private String modifior;

	/**
	 * 是否已经逻辑删除
	 */
	private Boolean deleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name = "creator")
	public String getCreator() {
		return creator;
	}

	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Column(name = "modifior")
	public String getModifior() {
		return modifior;
	}

	@Column(name = "is_deleted", nullable = false)
	public boolean isDeleted() {
		return deleted == null ? false : deleted;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setModifior(String modifior) {
		this.modifior = modifior;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}
