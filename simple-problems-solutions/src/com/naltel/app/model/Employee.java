package com.naltel.app.model;

public class Employee {
	private Long id;
	private String name;
	private Integer rank;
	private Double salary;
	private boolean isManager;
	private Long managerId;
	
	public Employee(Long id, String name, Integer rank,
			Double salary, boolean isManager,Long managerId){
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.salary = salary;
		this.isManager = isManager;
		this.managerId = managerId;
	}
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
}
