package com.practice.eon.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Relative {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String firstName;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String lastName;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String mothersName;
	
	@Column(nullable = false)
	@NotNull
	@NotBlank
	private String phone;
	
	@Column(length=1000)
	private String address;
	
	@Column(nullable = false)
	@NotNull
	private String type;
	
	
	public Relative() {
	}
	
	public Relative(Long id, String firstName, String lastName, String mothersName, String phone, String address,
			String type) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mothersName = mothersName;
		this.phone = phone;
		this.address = address;
		this.type = type;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param name the name to set
	 */
	public void setFirstName(String name) {
		this.firstName = name;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the mothersName
	 */
	public String getMothersName() {
		return mothersName;
	}
	/**
	 * @param mothersName the mothersName to set
	 */
	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
