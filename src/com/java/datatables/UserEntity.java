package com.java.datatables;

public class UserEntity {
	  private String name;
	  private  String age;
	  private  String  sxe;
		public UserEntity() {
		}
	public UserEntity(String name, String age, String sxe) {
		super();
		this.name = name;
		this.age = age;
		this.sxe = sxe;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSxe() {
		return sxe;
	}

	public void setSxe(String sxe) {
		this.sxe = sxe;
	}

	@Override
	public String toString() {
		return "UserEntity [name=" + name + ", age=" + age + ", sxe=" + sxe + "]";
	}
	  
	  
	  
	  
	
}
