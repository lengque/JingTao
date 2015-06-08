package daoImpl;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.List;

import model.User;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import dao.BaseDao;

/**
 * 基础数据库接触类 负责所有与数据库的直接接触
 */

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	 private SessionFactory  sessionFactory;
	 private Session session;
	 
	@Override
	public T saveObject(T object){
		sessionFactory = getSessionFactory();
		session = sessionFactory.getCurrentSession();
		
		//session.save(saveSql);
		
		return object;
	}

	/**
	 * 用于返回某个特定的对象
	 */
	public T checkObject(T object) {
		sessionFactory = getSessionFactory();
		session = sessionFactory.openSession();
		
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
		session = sessionFactory.getCurrentSession();
		
		return object;
	}

	@Override
	public T updateObject(T object){
		session = sessionFactory.getCurrentSession();
		
		//session.update(updateSql);
		
		return null;
	}

}
