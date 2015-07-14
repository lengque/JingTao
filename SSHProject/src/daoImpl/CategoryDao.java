package daoImpl;

import java.util.*;

import model.Category;
import dao.BaseDao;

public class CategoryDao {
	BaseDao<Category> baseDao;

	public BaseDao<Category> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<Category> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 获取 category list
	 */
	public List<Category> getCategories(Category category) {
		
		List<Category> categories = baseDao.list(category);
		
		return categories;
	}
	
	/**
	 * 新增一个category
	 */
	public Category addNewCategory(Category category){
		category = baseDao.saveObject(category);
		
		return category;
	}
	
	/**
	 * 删除一个category
	 */
	public void deleteCategory(Category category){
		baseDao.deleteObject(category);
	}
	/**
	 * 批量删除category
	 */
	public void batchDeleteCategory(List<Category> categoryList){
		baseDao.deleteAll(categoryList);
	}
	
	
	/**
	 * 查找category
	 */
	public Category findCategory(Category category){
		return baseDao.checkObject(category);
	}
	
	/**
	 * 更新category
	 */
	public Category updateCategory(Category category){
		category = baseDao.updateObject(category);
		
		return category;
	}
}
