package xml;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class CategoryInfo {

	private ArrayList<Category> category;
	
	@XmlElement
	public ArrayList<Category> getCategory() {
		return category;
	}

	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}
	@Override  
	public String toString(){
		String rtn ="";
		rtn="CategoryInfo "+"\n";
		   
		for(Category cate:category){
			rtn+=cate.toString()+"\n";
		}
		rtn+="========="+"\n";
		return rtn;
	}
	
}
