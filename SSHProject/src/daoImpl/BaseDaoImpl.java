package daoImpl;

import java.io.Serializable;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import model.User;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import dao.BaseDao;

/**
 * 基础数据库接触类 负责所有与数据库的直接接触
 */

public class BaseDaoImpl<T> implements BaseDao<T> {
	 private SessionFactory  sessionFactory;
	 private Session session;
	 
	 private Session getSession(){
		 return this.sessionFactory.getCurrentSession();
	 }
	 
	 public void setSessionFactory(SessionFactory  sessionFactory){
		 this.sessionFactory = sessionFactory;
	 }
	 
	@Override
	public T saveObject(T object){
		session = getSession();
		
		session.save(object);
		
		return object;
	}

	/**
	 * 用于返回某个特定的对象
	 */
	public T checkObject(T object) {
		session = getSession();
		
		try {
			//首先我们要得到这个对象的类 类型
			Class cls = (Class)object.getClass();
			
			//创建一个标准
			Criteria daoCriteria = session.createCriteria(cls);
			
			//获得该类所有声明的字段
			Field[] fields = cls.getDeclaredFields();
			
			//遍历字段并设置其值
			for(int i = 0; i<fields.length; i++){
				Field field = fields[i];
				//得到fieldName
				String fieldName = fields[i].getName();
				//得到属性的类型
				Class<?> type = field.getType();
				//设置属性可以访问
				field.setAccessible(true);
				
				Object val = field.get(object);
				  
				if(null != val){
					daoCriteria.add(Restrictions.eq(fieldName,type.cast(val)));
				}
			}
			List<T> checkResult = (List<T>)daoCriteria.list();
			
			//如果result不是空就返回第一个元素 否则返回空
			if(null != checkResult && checkResult.size()>0){
				object = checkResult.get(0);
			}else{
				object = null;
			}
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return object;
	}
	

	@Override
	public T deleteObject(T object) {
		session = getSession();
		
		return object;
	}

	@Override
	public T updateObject(T object){
		session = getSession();
		
		session.update(object);
		
		return object;
	}

	@Override
	public T saveOrUpdate(T object) {
		session = this.getSession();
		session.saveOrUpdate(object);
		
		return object;
	}

	@Override
	public T get(Class clazz,Serializable id) {
		
		return (T) this.getSession().get(clazz, id);
	}

	@Override
	public List<T> findByExample(T object) {
		Example example = Example.create(object);
		Criteria criteria = this.createCriteria().add(example);
		
		return criteria.list();
	}

	@Override
	public T findUniqueObjectByProperty(String propertyName, Object value) {
		Criterion criterion = Restrictions.eq(propertyName, value);
		Criteria criteria = this.createCriteria(criterion);
		return  (T) criteria.uniqueResult();
	}

	@Override
	public T findUniqueObjectByProperty(Criterion... criterions) {
		Criteria criteria = this.createCriteria();
		return this.findUniqueObjectByProperty(criteria);
	}

	@Override
	public T findUniqueObjectByProperty(Criteria criteria) {
		
		return (T) criteria.uniqueResult();
	}

	@Override
	public List<T> list(Object object) {
		
		return this.createCriteria(object).list();
	}

	@Override
	public List<T> list(Criteria criteria) {
		return criteria.list();
	}

	@Override
	public List<T> list(DetachedCriteria criteria) {
		return (List<T>) criteria.getExecutableCriteria(getSession());
	}

	@Override
	public List<T> list(String orderBy, boolean isAsc) {
		Criteria criteria = this.createCriteria();
		if (isAsc){
			criteria.addOrder(Order.asc(orderBy));
		}else{
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria.list();
	}

	@Override
	public List<T> list(String propertyName, Object value) {
		Criterion criterion = Restrictions.like(propertyName, "%" + value + "%");
		return this.list(criterion);
	}

	@Override
	public List<T> list(Criterion criterion) {
		Criteria criteria = this.createCriteria();
		criteria.add(criterion);
		return criteria.list();
	}

	@Override
	public List<T> list(Criterion... criterions) {
		return this.createCriteria(criterions).list();
	}

	@Override
	public void delelteById(Class clazz,Serializable id) {
		this.getSession().delete(this.get(clazz,id));
		
	}

	@Override
	public void deleteAll(Collection<?> entities) {
		if(entities == null){
			return;
		}
		for (Object object : entities) {
			this.getSession().delete(object);
		}
	}

	@Override
	public T merge(T object) {
		this.getSession().merge(object);
		return object;
	}

	@Override
	public boolean exists(Class clazz,Serializable id) {
		return this.get(clazz,id) != null;
	}

	@Override
	public T load(Class clazz,Serializable id) {
		return (T) this.getSession().load(clazz, id);
	}

	@Override
	public void flush() {
		this.getSession().flush();
	}

	@Override
	public void clear() {
		this.getSession().clear();
	}

	@Override
	public int countAll(Object object) {
		Criteria criteria = this.createCriteria(object);
		if(criteria != null){
			criteria = criteria.setProjection(Projections.rowCount());
			Object rowNo = criteria.uniqueResult();
			return (rowNo != null) ? Integer.parseInt(rowNo.toString()) : -1;
		}
		return -1;
	}
	
	@Override
	public int countAll(Criteria criteria) {
		String rowNo = criteria.setProjection(Projections.rowCount())
				.uniqueResult()
				.toString();
		
		return Integer.valueOf(rowNo);
	}

	@Override
	public Criteria createCriteria(Object object) {
		return this.getSession().createCriteria(object.getClass());
	}

	@Override
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = this.createCriteria();
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	@Override
	public List<T> findPage(Criteria criteria, int pageNo, int pageSize) {
		
		return null;
	}
}
