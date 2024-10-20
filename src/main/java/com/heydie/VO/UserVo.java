package com.heydie.VO;

import java.io.Serializable;
import java.util.List;

public class UserVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String name;
	private String address;
	private Integer age;
	
	private List<String> city;
	
	
	
	public List<String> getCity() {
		return city;
	}
	public void setCity(List<String> city) {
		this.city = city;
	}
	public String getUsername() {
		System.out.println("USER 1 => "+username);
		return username;
	}
	public void setUsername(String username) {
		System.out.println("USER 2 => "+username);
		this.username = username;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserVo [username=" + username + ", name=" + name + ", address=" + address + ", age=" + age + "]";
	}
	
	

}
