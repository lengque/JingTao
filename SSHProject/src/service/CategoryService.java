package service;

import java.util.List;

import model.Category;
import model.User;

public interface CategoryService {
	
	/**
	 * 得到所有的父节点
	 */
	public List<Category> getTopCategories();
	
	/**
	 * 得到下一层子节点
	 */
	public List<Category> getChildren(Category c);
	
	/**
	 * 得到下N层子节点
	 */
	public List<Category> getAllChildren(Category c);
	
	/**
	 * 增加一个类别
	 */
	public Category addCategory(Category c);
	
	/**
	 * 删除某个节点及其子节点
	 */
	public void deleteCategory(Category c);

	/**
	 * 查找某个节点
	 */
	public Category checkById(Category c);
	
	/**
	 * 更新某个节点
	 */
	public Category updateCategory(Category c);
}
