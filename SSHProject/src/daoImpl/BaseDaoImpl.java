package daoImpl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

import model.PageBean;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dao.BaseDao;

/**
 * 基础数据库接触类 负责所有与数据库的直接接触
 */

public class BaseDaoImpl<T> implements BaseDao<T> {
	private SessionFactory sessionFactory;
	private Session session;
	public int totalRecord = -1;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * save object
	 */
	@Override
	public T saveObject(T object) {
		session = getSession();

		session.save(object);

		return object;
	}

	/**
	 * save or update object
	 */
	@Override
	public T saveOrUpdate(T object) {
		session = this.getSession();
		session.saveOrUpdate(object);

		return object;
	}

	/**
	 * update object
	 */
	@Override
	public T updateObject(T object) {
		session = getSession();

		session.update(object);

		return object;
	}

	/**
	 * delete object
	 */
	@Override
	public T deleteObject(T object) {
		session = getSession();

		return object;
	}

	/**
	 * check all the objects order by the property name
	 */
	@Override
	public void deleteAll(Collection<?> entities) {
		if (entities == null) {
			return;
		}
		for (Object object : entities) {
			this.getSession().delete(object);
		}
	}

	/**
	 * check exact unique object 
	 */
	@Override
	public T checkObject(T object) {
		session = getSession();

		try {
			// 首先我们要得到这个对象的类 类型
			Class cls = (Class) object.getClass();

			// 创建一个标准
			Criteria daoCriteria = session.createCriteria(cls);

			// 获得该类所有声明的字段
			Field[] fields = cls.getDeclaredFields();

			// 遍历字段并设置其值
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				// 得到fieldName
				String fieldName = fields[i].getName();
				// 得到属性的类型
				Class<?> type = field.getType();
				// 设置属性可以访问
				field.setAccessible(true);

				Object val = field.get(object);

				if (null != val) {
					if(!(val instanceof String)){
						daoCriteria.add(Restrictions.eq(fieldName, type.cast(val)));
					}else{
						if(StringUtils.isNotBlank(val.toString())){
							daoCriteria.add(Restrictions.eq(fieldName, type.cast(val)));
						}
					}
				}
			}

			object = (T) daoCriteria.uniqueResult();
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return object;
	}

	/**
	 * check all the object with isFuzzy true(fuzzy check)
	 */
	@Override
	public List<T> checkObjects(T object, Criteria criteria, Boolean isFuzzy) {
		try {
			// 1.get class type
			Class cls = (Class) object.getClass();

			// 2.get all the fileds
			Field[] fields = cls.getDeclaredFields();

			// 3.iterator this object to get all the value
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				// 3.1 get field name
				String fieldName = fields[i].getName();

				// 3.2 get filed type
				Class<?> type = field.getType();

				// 3.3 set all the fileds could be access
				field.setAccessible(true);

				// 3.4 get the filed value
				Object val = field.get(object);

				// 4. add the check condition
				if (null != val) {
					if (isFuzzy && val instanceof String) {
						criteria.add(Restrictions.like(fieldName, "%" + val
								+ "%"));
					} else {
						if(!(val instanceof String)){
							criteria.add(Restrictions.eq(fieldName, type.cast(val)));
						}else{
							if(StringUtils.isNotBlank(val.toString())){
								criteria.add(Restrictions.eq(fieldName, type.cast(val)));
							}
						}
					}
				}
			}

			List<T> list = criteria.list();

			return list;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * check object base classtype and serializable id
	 */
	@Override
	public T get(Class clazz, Serializable id) {

		return (T) this.getSession().get(clazz, id);
	}

	/**
	 * check objects with an example
	 */
	@Override
	public List<T> findByExample(T object) {
		Example example = Example.create(object);
		Criteria criteria = this.createCriteria().add(example);

		return criteria.list();
	}

	/**
	 * check object with criterions
	 */
	@Override
	public T findUniqueObjectByProperty(Criterion... criterions) {
		Criteria criteria = this.createCriteria();
		if (null != criterions) {
			for (int i = 0; i < criterions.length; i++) {
				criteria.add(criterions[i]);
			}
		}

		return this.findUniqueObjectByProperty(criteria);
	}

	/**
	 * check object with criteria
	 */
	@Override
	public T findUniqueObjectByProperty(Criteria criteria) {

		return (T) criteria.uniqueResult();
	}

	/**
	 * check all the objects with object
	 */
	@Override
	public List<T> list(Object object) {
		Criteria criteria = this.createCriteria(object);
		List<T> list = criteria.list();

		return list;
	}

	/**
	 * check all the objects with criteria
	 */
	@Override
	public List<T> list(Criteria criteria) {
		return criteria.list();
	}

	/**
	 * check all the objects with DetachedCriteria
	 */
	@Override
	public List<T> list(DetachedCriteria criteria) {
		return (List<T>) criteria.getExecutableCriteria(getSession());
	}

	/**
	 * check all the objects with criterion
	 */
	@Override
	public List<T> list(Criterion criterion) {
		Criteria criteria = this.createCriteria();
		criteria.add(criterion);

		return criteria.list();
	}

	/**
	 * check all the objects with criterions
	 */
	@Override
	public List<T> list(Criterion... criterions) {
		Criteria criteria = this.createCriteria();
		if (null != criterions) {
			for (int i = 0; i < criterions.length; i++) {
				criteria.add(criterions[i]);
			}
		}
		List<T> list = criteria.list();

		return list;
	}

	/**
	 * check all the objects order by the property name
	 */
	@Override
	public List<T> list(String orderBy, boolean isAsc) {
		Criteria criteria = this.createCriteria();

		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}

		return criteria.list();
	}

	/**
	 * check all the objects order by the property name
	 */
	@Override
	public void delelteById(Class clazz, Serializable id) {
		this.getSession().delete(this.get(clazz, id));

	}

	/**
	 * check all the class type is exist with class type and id;
	 */
	@Override
	public boolean exists(Class clazz, Serializable id) {
		return this.get(clazz, id) != null;
	}
	
	/**
	 * create criteria with object
	 */
	@Override
	public Criteria createCriteria(Object object) {
		Criteria criteria = this.getSession().createCriteria(object.getClass());
		return criteria;
	}

	/**
	 * create criteria with criterions
	 */
	@Override
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = this.createCriteria();
		for (Criterion criterion : criterions) {
			criteria.add(criterion);
		}

		return criteria;
	}


	/**
	 * count the number of objects check by criteria
	 */
	@Override
	public int countAll(Criteria criteria) {
		criteria = criteria.setProjection(Projections.rowCount());
		Object rowNo = criteria.uniqueResult();
		
		return (rowNo != null) ? Integer.parseInt(rowNo.toString()) : -1;
	}

	/**
	 * create the page bean
	 */
	@Override
	public PageBean<T> findPage(T object, int currentPage, int pageSize) {
		// 1.create Criteria
		Criteria criteria = this.createCriteria(object);

		// 2.set first result and max results
		criteria = criteria.setFirstResult((currentPage - 1) * pageSize);
		criteria = criteria.setMaxResults(pageSize);

		// 3.get the list of object
		List<T> list = checkObjects(object, criteria, true);
		
		if(currentPage == 1){
			totalRecord = this.countAll(criteria);
		}

		PageBean<T> pageBean = new PageBean<T>();

		pageBean.setCurrentPage(currentPage);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalRecord(totalRecord);
		pageBean.setTotalPage(PageBean.countTotalPage(pageSize, totalRecord));
		pageBean.setList(list);
		pageBean.init();

		return pageBean;
	}
	

	/**
	 * load class into session
	 */
	@Override
	public T load(Class clazz, Serializable id) {
		return (T) this.getSession().load(clazz, id);
	}

	/**
	 * merge object into session
	 */
	@Override
	public T merge(T object) {
		this.getSession().merge(object);

		return object;
	}
	
	/**
	 * flush the session
	 */
	@Override
	public void flush() {
		this.getSession().flush();
	}

	/**
	 * clear the session
	 */
	@Override
	public void clear() {
		this.getSession().clear();
	}
}
