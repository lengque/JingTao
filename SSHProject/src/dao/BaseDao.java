package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {  
	/**
	 * <p>保存</p>
	 * 
	 */
	public T saveObject(T object);
	
	public T saveOrUpdate(T object);
	
	/**
	 * <p>检索</p>
	 * 
	 */
	public T checkObject(T object);
	
	public T get(Class clazz,Serializable id);
	
	public List<T> findByExample(T object);
	
	public T findUniqueObjectByProperty(String propertyName, Object value);
	
	public T findUniqueObjectByProperty(Criterion... criterions);
	
	public T findUniqueObjectByProperty(Criteria criteria);
	
	/**
	 *分页 
	 */
	public List<T> findPage(Criteria criteria, int pageNo, int pageSize);
	
	/**
	 *查找多数据 
	 */
	public List<T> list(Object object);
	
	public List<T> list(Criteria criteria);
	
	public List<T> list(DetachedCriteria criteria);
	
	public List<T> list(String orderBy, boolean isAsc);
	
	public List<T> list(String propertyName, Object value);
	
	public List<T> list(Criterion criterion);
	
	public List<T> list(Criterion... criterions);
	
	/**
	 * <p>删除</p>
	 * 
	 */
	public T deleteObject(T object);
	
	public void delelteById(Class clazz,Serializable id);
	
	public void deleteAll(Collection<?> entities);
	
	/**
	 * <p>更新</p>
	 * 
	 */
	public T updateObject(T object);
	
	/**
	 * <p>合并</p>
	 * 
	 */
	public T merge(T object);
	
	/**
	 * 判断是否存在
	 */
	public boolean exists(Class clazz,Serializable id);
	
	/**
	 * 载入
	 */
	public T load(Class clazz,Serializable id);
	
	public void flush();
	
	public void clear();
	
	public int countAll(Object object);
	
	public int countAll(Criteria criteria);
	
	public Criteria createCriteria(Object object);
	
	public Criteria createCriteria(Criterion... criterions);
	
	
}  