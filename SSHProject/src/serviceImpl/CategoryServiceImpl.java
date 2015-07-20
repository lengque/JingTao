package serviceImpl;

import java.util.*;

import model.Category;
import service.CategoryService;
import util.CategoryUtil;
import dao.BaseDao;
import daoImpl.CategoryDao;

public class CategoryServiceImpl implements CategoryService{
	
	private CategoryDao categoryDao;
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	/**
	 *得到所有的根类别
	 */
	@Override
	public List<Category> getTopCategories() {
		Category category = new Category();
		category.setGrade(1);
		
		List<Category> categoryList = categoryDao.getCategories(category);
		
		return categoryList;
	}
	
	/**
	 *得到某个类别的子类别
	 */
	@Override
	public List<Category> getChildren(Category category) {
		List<Category> categoryList = null;
		
		if(null != category) {
			Category childCategory = new Category();
			
			childCategory.setItemPid(category.getItemId());
			
			categoryList = categoryDao.getCategories(childCategory);
		}
		
		return categoryList;
	}
	
	/**
	 *得到某个类别的所有子类别(包括一级子 二级子 三级子等等)
	 */
	@Override
	public List<Category> getAllChildren(Category category) {
		List<Category> categoryList = new ArrayList<Category>();
		
		if(null != category) {
			int flag = 0;
			
			List<Category> resultList = this.getChildren(category);
			if(null != resultList){
				categoryList.addAll(categoryList);
				//得到列表中的第一个元素
				category = categoryList.get(flag);
				
				while(null != category && flag++ < categoryList.size()){
					resultList = this.getChildren(category);
					if(null != resultList){
						categoryList.addAll(categoryList);
					}
					category = categoryList.get(flag);
				}
			}
		}
		
		return categoryList;
	}
	
	/**
	 *增加一个新的目录
	 */
	@Override
	public Category addCategory(Category category) {
		category = categoryDao.addNewCategory(category);
		
		return category;
	}
	
	/**
	 *删除某个类别 删除并删除其子节点
	 */
	@Override
	public void deleteCategory(Category pcategory) {
		
		//首先检查是不是叶子节点
		if(CategoryUtil.Leaf_Point.equals(pcategory.getIsLeaf())){
			//删除一个节点
			categoryDao.deleteCategory(pcategory);
		}else {
		//删除多个节点	
			//首先得到本节点下的所有节点
			List<Category> deleteCatList = this.getAllChildren(pcategory);
			//把本节点一并加入到删除队列中
			deleteCatList.add(pcategory);
			
			//批量删除
			categoryDao.batchDeleteCategory(deleteCatList);
		}
		
	}
	
	/**
	 *查找某个类别
	 */
	@Override
	public Category checkById(Category category) {
		category = categoryDao.findCategory(category);
		
		return category;
	}
	
	/**
	 *更新一个节点的信息
	 */
	@Override
	public Category updateCategory(Category category) {
		category = categoryDao.updateCategory(category);
		
		return category;
	}
}
