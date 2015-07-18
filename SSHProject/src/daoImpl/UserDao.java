package daoImpl;

import java.util.List;

import model.User;

import org.hibernate.Criteria;
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
	 *userService
	 */
    public List<User> userList(){
    	
    	List<User> userPage = null;//baseDao.findPage(Criteria criteria, int pageNo, int pageSize);
    	
    	return userPage;
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
	    user = baseDao.checkObject(user);
	    
		return user;
    }
    
    public User checkUserByID(User user){
    	User u = new User();
    	u.setUserName(user.getUserId());
    	
    	u = baseDao.checkObject(u);
    	
    	return u;
    }
    
    //根据用户名来检索用户
    public User checkUserByName(User user){
    	User u = new User();
    	u.setUserName(user.getUserName());
    	
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