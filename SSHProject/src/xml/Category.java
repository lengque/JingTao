package xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Category {
	
	private int  categoryId;
	private String categoryName;  
	private int categoryParent;
	
	@XmlAttribute
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	@XmlAttribute
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@XmlAttribute
	public int getCategoryParent() {
		return categoryParent;
	}
	public void setCategoryParent(int categoryParent) {
		this.categoryParent = categoryParent;
	}
	
	@Override  
    public String toString() {  
        return "Category [categoryId=" + categoryId + ",categoryParent=" + categoryParent + ",categoryName=" + categoryName  + "]";  
    }
	
	
}
