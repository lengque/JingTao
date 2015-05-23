package daoImpl;

import java.util.List;

import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.lang.StringUtils;

import dao.BaseDao;

  

public class UserDao extends HibernateDaoSupport implements BaseDao {
	
	/**
	 *
	 * 
	 */
	@Override  
    public void saveObject(Object obj){
		//getSession().save(obj);
		//this.saveObject(obj);
	    //SessionFactory s = getSessionFactory();
		Session se =this.getSession();
	    this.getHibernateTemplate().saveOrUpdate(obj);
	    //session.beginTransaction();
		//session.save(obj);
		//session.getTransaction().commit();
		//session.close();
		
		//this.getHibernateTemplate().save(obj);
    }
	
	/**
	 *
	 * 
	 */
	@Override  
    public Object checkObjet(String sql) throws HibernateException {
		
	    SessionFactory s = getSessionFactory();
	    Session session = s.openSession();
	    session.beginTransaction();
	    
		List list = session.createSQLQuery(sql).addEntity("ss",User.class).list();
		
		Object obj = null;
		if(list.size()>0){
			obj = list.get(0);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return obj;
    }
	
	/**
	 * 
	 */
	@Override
	public void deleteObject(String sql) {
		SessionFactory s = getSessionFactory();
	    Session session = s.openSession();
	    session.beginTransaction();
	    
		session.update(sql, User.class);
		
		session.getTransaction().commit();
		session.close();
	}
	
	/**
	 * 
	 * 
	 */
	@Override
	public Object updateObject(Object obj) {
		
		SessionFactory s = getSessionFactory();
	    Session session = s.openSession();
	    session.beginTransaction();
	    
	    session.update(obj);
		//session.update(sql, User.class);
		
		session.getTransaction().commit();
		session.close();
		
		return null;
	}
}  