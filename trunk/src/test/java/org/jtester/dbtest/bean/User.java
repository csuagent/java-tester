package org.jtester.dbtest.bean;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "table_user")
public class User extends BaseBean {
	private String name;

	private String email;

	private Collection<Address> addresses;

	@Column(name = "name", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Where(clause = "is_deleted = 0")
	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}
}
