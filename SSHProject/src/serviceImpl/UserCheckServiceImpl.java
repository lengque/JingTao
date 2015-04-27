package serviceImpl;

import model.User;

import org.apache.commons.lang.StringUtils;

import service.UserCheckService;
import daoImpl.UserDao;

public class UserCheckServiceImpl implements UserCheckService {
	
	private UserDao userDao;
	
	/**
	 * <p>注入userDao</p>
	 * 
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;  
	}
	
	/**
	 * <p>根据用户名 查找用户</p>
	 * 
	 */
	@Override
	public User checkUser(User u) {
		User user = null;
		
		String str = "select userid,username,password,telphone from user where 1=1";
	    StringBuffer sql = new StringBuffer(str);
	    
	    if(StringUtils.isNotBlank(u.getUsername())){
	    	sql.append(" AND username = '"+u.getUsername() +"'");
	    	
	    	if(StringUtils.isNotBlank(u.getPassword())){
	    		sql.append(" AND password = '"+u.getPassword() +"'");
	    		
	    		user = (User) userDao.checkObjet(sql.toString());
	    	}
	    }
	    
		return user;
	}
}
