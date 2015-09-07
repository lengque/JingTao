package xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

//Unmarshaller
public class XmlCategoryDemo {

	public static void main(String[] args) throws JAXBException {
		new XmlCategoryDemo().getCateList();
	}

	public CategoryInfo getCateList() {
		File file = new File(new File("").getAbsolutePath() + "\\src\\conf\\category.xml");
		JAXBContext jaxbContext;
		CategoryInfo categoryinfo = new CategoryInfo() ;
		try {
			jaxbContext = JAXBContext.newInstance(CategoryInfo.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			categoryinfo = (CategoryInfo) jaxbUnmarshaller.unmarshal(file);
			System.out.println(categoryinfo);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return categoryinfo;
	}
}
