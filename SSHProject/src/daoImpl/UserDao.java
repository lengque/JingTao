package daoImpl;

import java.util.List;

import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.commons.lang.StringUtils;

import dao.BaseDao;

/**
*用户与数据库的接触
*/

public class UserDao{
	BaseDao<User> baseDao;
	
	public BaseDao<User> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<User> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 *新增一个用户
	 */
    public void saveUser(User user){
    	
    	baseDao.saveObject(user);
    }
	
	/**
	 * 检索一个用户
	 */
    public User checkUser(User user){
		
      /*StringBuffer checkSql = new StringBuffer("select userid,username,password,telphone from user where 1=1");
    	String checkSql = "from User";
    	
    	
	    //按照用户名来查找用户
	    if(StringUtils.isNotBlank(user.getUsername())){
	    	checkSql.append(" AND username = '"+user.getUsername() +"'");
	    	
	    	if(StringUtils.isNotBlank(user.getPassword())){
	    		checkSql.append(" AND password = '"+user.getPassword() +"'");
	    	}
	    }
	     if(StringUtils.isNotBlank(user.getUserId())){
	    	checkSql.append(" AND userId = '"+user.getUserId() +"'");
	    }
		
	    user = (User)baseDao.checkObject(checkSql.toString());
	    */
	    user = baseDao.checkObject(user);
	    
	    
	    
		return user;
    }
    
    public User checkUserByID(User user){
    	User u = new User();
    	u.setUsername(user.getUserId());
    	
    	u = baseDao.checkObject(u);
    	
    	return u;
    }
    
    //根据用户名来检索用户
    public User checkUserByName(User user){
    	User u = new User();
    	u.setUsername(user.getUsername());
    	
    	u = baseDao.checkObject(u);
    	
    	return u;
    }
	
	/**
	 *删除用户
	 */
	public void deleteUser(User user) {
	    baseDao.deleteObject(user);
	}
	
	/**
	 *更新用户信息
	 */
	public Object updateUser(User user) {
		
		user = baseDao.updateObject(user);
		
		return user;
	}
}  