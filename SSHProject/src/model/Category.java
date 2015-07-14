package model;

public class Category {
	private String id;
	private String pid;
	private String name;
	private int grade;
	private int isLeaf;
	private String descr;
	
	//ID
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	//PID
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	//NAME
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Grade
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	//Describe
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	//isLeaf
	public int getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
}
