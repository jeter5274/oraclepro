package com.javaex.phone2;

public class PhoneVo {

	//필드
	public int personId;
	public String name, hp, company;
	
	//생성자
	public PhoneVo() {}
	
	public PhoneVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	//메소드 getter/setter
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	//메소드 일반
	@Override
	public String toString() {
		return "PhoneVo [personId=" + personId + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}

	
	
}
