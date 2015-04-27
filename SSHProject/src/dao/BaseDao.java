package dao;

import org.hibernate.HibernateException;

public interface BaseDao {  
	/**
	 * <p>存储一个对象到数据库中</p>
	 * 
	 */
	public void saveObject(Object obj) throws HibernateException;
	
	/**
	 * <p>查找一个 对象</p>
	 * 
	 */
	public Object checkObjet(String sql);
	
	/**
	 * <p>删除一个 对象</p>
	 * 
	 */
	public void deleteObject(String sql);
	
	/**
	 * <p>更新一个 对象</p>
	 * 
	 */
	public Object updateObject(Object obj);
}  