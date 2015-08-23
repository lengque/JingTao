package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import model.PageBean;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {  
	/**
	 * save object
	 */
	public T saveObject(T object);

	/**
	 * save or update object
	 */
	public T saveOrUpdate(T object);

	/**
	 * update object
	 */
	public T updateObject(T object);

	/**
	 * delete object
	 */
	public T deleteObject(T object);

	/**
	 * check all the objects order by the property name
	 */
	public void deleteAll(Collection<?> entities);

	/**
	 * check exact unique object 
	 */
	public T checkObject(T object);

	/**
	 * check all the object with isFuzzy true(fuzzy check)
	 */
	public List<T> checkObjects(T object, Criteria criteria, Boolean isFuzzy);

	/**
	 * check object base class type and serializable id
	 */
	public T get(Class clazz, Serializable id);

	/**
	 * check objects with an example
	 */
	public List<T> findByExample(T object);

	/**
	 * check object with criterions
	 */
	public T findUniqueObjectByProperty(Criterion... criterions);

	/**
	 * check object with criteria
	 */
	public T findUniqueObjectByProperty(Criteria criteria);

	/**
	 * check all the objects with object
	 */
	public List<T> list(Object object);

	/**
	 * check all the objects with criteria
	 */
	public List<T> list(Criteria criteria);
	
	/**
	 * check all the objects with DetachedCriteria
	 */
	public List<T> list(DetachedCriteria criteria);

	/**
	 * check all the objects with criterion
	 */
	public List<T> list(Criterion criterion);

	/**
	 * check all the objects with criterions
	 */
	public List<T> list(Criterion... criterions);

	/**
	 * check all the objects order by the property name
	 */
	public List<T> list(String orderBy, boolean isAsc);
	
	/**
	 * check all the objects order by the property name
	 */
	public void delelteById(Class clazz, Serializable id);

	/**
	 * check all the class type is exist with class type and id;
	 */
	public boolean exists(Class clazz, Serializable id);
	
	/**
	 * create criteria with object
	 */
	public Criteria createCriteria(Object object);

	/**
	 * create criteria with criterions
	 */
	public Criteria createCriteria(Criterion... criterions);

	/**
	 * count the number of objects check by criteria
	 */
	public int countAll(Criteria criteria);
	
	/**
	 * create the page bean
	 */
	public PageBean<T> findPage(T object, int currentPage, int pageSize);
	

	/**
	 * load class into session
	 */
	public T load(Class clazz, Serializable id);

	/**
	 * merge object into session
	 */
	public T merge(T object);
	
	/**
	 * flush the session
	 */
	public void flush();

	/**
	 * clear the session
	 */
	public void clear();
	
	
}  