package daoImpl;

import java.util.List;

import model.PageBean;
import model.User;

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
    public User saveUser(User user){
    	user = baseDao.saveObject(user);
    	
    	return user;
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
	
	public PageBean<User> findPage(User user, int currentPage, int pageSize){
		return baseDao.findPage(user, currentPage, pageSize);
	}
}  