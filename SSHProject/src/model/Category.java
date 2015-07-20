package model;

public class Category {
	private String itemId;
	private String itemPid;
	private String itemName;
	private Integer grade;
	private Integer isLeaf;
	private String descr;
	
	//ID
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	//PID
	public String getItemPid() {
		return itemPid;
	}
	public void setItemPid(String itemPid) {
		this.itemPid = itemPid;
	}
	
	//NAME
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	//Grade
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
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
	public Integer getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
}
