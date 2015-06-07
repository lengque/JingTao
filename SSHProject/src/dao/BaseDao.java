package dao;

public interface BaseDao<T> {  
	/**
	 * <p>保存</p>
	 * 
	 */
	public T saveObject(T object);
	
	/**
	 * <p>检索</p>
	 * 
	 */
	public T checkObject(T object);
	
	/**
	 * <p>删除</p>
	 * 
	 */
	public T deleteObject(T object);
	
	/**
	 * <p>更新</p>
	 * 
	 */
	public T updateObject(T object);
}  