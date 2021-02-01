package com.respeso.data;

import java.util.Arrays;
import java.util.List;

public class Holder {
	
	private Integer id;
	private String name;
	private String lastName;
	private String gender;
	private Integer age;
	private List<String> phoneNumbers;
	
	public Holder(Integer id, String name, String lastName, String gender, Integer age, String... phoneNumbers) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.age = age;
		this.phoneNumbers = Arrays.asList(phoneNumbers);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers){
		this.phoneNumbers = phoneNumbers;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Holder [id=" + id + ", name=" + name + ", lastName=" + lastName + ", gender=" + gender + ", age=" + age
				+ ", phoneNumbers=" + phoneNumbers + "]";
	}

	
//
//	@Override
//	public int compareTo(Holder o) {
//		return this.id.compareTo(o.getId());
//	}
	
}
