package com.stickify.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Modified on: 4 Jun 2012
 * 
 * An entity class which contains the information of user role.
 *
 * @version: 1.0 4 Jun 2012
 * @author (c): Michael Le
 * 
 */
@Entity
@Table(name="role")
public class Role implements Serializable {
		
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", nullable = false)
	@NotEmpty
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		// object must be Role at this point
		Role role = (Role) obj;
		return this.name.equals(role.getName());
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(5, 7).
	            	append(this.name).
	            	toHashCode();
	}
}
