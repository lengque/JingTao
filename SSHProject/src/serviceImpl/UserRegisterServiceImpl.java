package serviceImpl;

import java.util.List;

import model.User;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;

import service.UserRegisterService;
import util.UserUtil;
import validator.BaseValidator;
import dao.BaseDao;
import daoImpl.UserDao;

public class UserRegisterServiceImpl implements UserRegisterService{
	
	private UserDao userDao;
	private List<BaseValidator> validators;
	/**
	 * <p>注入userDao</p>
	 * 
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;  
	}
	
	/**
	 * <p>注入validators</p>
	 * 
	 */
	public void setValidators(List<BaseValidator> validators) {
		this.validators = validators;
	}
	
	/**
	 * <p>新增用户</p>
	 * 
	 */
    @Override  
    public User saveUser(User u){
    	
    	//1.validate the user data
		for(BaseValidator validator : validators ){
			validator.validate(u);
		}
		//2.save object
    	userDao.saveObject(u);
    	
    	return u;
    }
}
