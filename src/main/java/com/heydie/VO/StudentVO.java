package com.heydie.VO;

import java.io.Serializable;

public class StudentVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String address;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
