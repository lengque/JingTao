package controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import Exception.BaseException;

import com.opensymphony.xwork2.ActionSupport;

import model.Category;
import service.CategoryService;
import service.UserService;
import util.CategoryUtil;

public class CategoryController extends ActionSupport implements SessionAware {

	private CategoryService categoryService;
	protected Map<String, Object> session;

	/**
	 * 注入categoryService
	 */
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 注入session
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * 显示目前所有的根节点
	 */
	public String CheckRootCategories() {

		try {
			List<Category> rootCategory = categoryService.getTopCategories();

			session.put("rootCategories", rootCategory);

		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 查找某个根节点的所有子节点
	 */
	public String CheckChildCategories(Category category) {
		try {
			List<Category> childCategories = categoryService
					.getChildren(category);

			session.put("rootCategories", childCategories);

		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 新增一个子类别
	 */
	public String addNewCategory(Category category) {
		try {
			// 首先把Category转换成为一个适合存储的类型 adaptor

			// 存储到数据库中，并得到存储后的对象
			category = categoryService.addCategory(category);

			// 发送到前台之前进行检查并转换

			// 存入到session中用来显示
			session.put("newCategory", category);
		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 删除一个类目
	 */
	public String deleteCategory(Category category) {
		try {
			// 首先对category进行检查是不是适合删除

			// 进行删除
			categoryService.deleteCategory(category);

			// 发送到前台之前进行检查并转换

			// 存入到session中用来显示
			session.put("newCategory", category);
		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 查看一个类别的详细信息
	 */
	public String checkCategory(Category category) {
		// 首先对category 进行检查并转换 adaptor

		// 查找category
		category = categoryService.checkById(category);
		session.put("category", category);

		return SUCCESS;
	}

	/**
	 * 查看一个类别的详细信息
	 */
	public String updateCategory(Category category) {
		// 首先对category 进行检查并转换(adaptor)

		// 更新category
		category = categoryService.updateCategory(category);
		session.put("category", category);
		
		return SUCCESS;
	}
}
