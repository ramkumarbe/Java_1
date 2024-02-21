package com.ramkumarbe.problem.dailyproblems.day7.flightticketbooking.dto;

public class Passenger {
	
	private int passengerId;
	private String name;
	private int age;
	private String gender;
	private String status;
	
	public Passenger(int passengerId, String name, int age, String gender, String status) {
		this.passengerId = passengerId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.status = status;
	}
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
