package serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import Exception.BaseException;
import service.UserService;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;
import daoImpl.UserDao;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	private List<BaseValidator> saveValidators;
	private List<BaseValidator> modifyUserInfoValidators;
	private List<BaseValidator> modifyUserPswValidators;
	
	/**
	 * init the userDao
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;
	}
	
	/**
	 * init validators for save method
	 */
	public void setSaveValidators(List<BaseValidator> saveValidators) {
		this.saveValidators = saveValidators;
	}
	
	/**
	 * init validators for save method
	 */
	public void setModifyUserInfoValidators(List<BaseValidator> modifyUserInfoValidators) {
		this.modifyUserInfoValidators = modifyUserInfoValidators;
	}
	
	/**
	 * init validators for save method
	 */
	public void setModifyUserPswValidators(List<BaseValidator> modifyUserPswValidators) {
		this.modifyUserPswValidators = modifyUserPswValidators;
	}
	
	/**
	 * check out a user from DB
	 */
	@Override
	public User checkUser(User u) {
		User user = null;
		
		
	    
		return user;
	}

	/**
	 * add a new user
	 */
    @Override  
    public User saveUser(User u){
    	//1.validate the user data
		for(BaseValidator validator : saveValidators ){
			validator.validate(u);
		}
		//2.save object
    	userDao.saveUser(u);
    	
    	return u;
    }
    
    /**
	 * delete a user
	 */
    @Override
	public void deleteUser(User user) {
		//check the user state 
		int frontPageState = user.getState();
		
		int DBUserState = user.getState();
		//change the user state 
		user.setState(UserUtil.disable);
		//save the state
		userDao.saveUser(user);
	}

    /**
	 *use login
	 */
	@Override
	public User login(User u) {
		//check the user from DB
		User user = userDao.checkUser(u);
		
		if(null == user || user.getState()==UserUtil.disable){
			//user not exist
			throw new BaseException(ErrorList.User_Not_Exist);
		}
		if(user.getState()==UserUtil.pause){
			//user temporaily close
			throw new BaseException(ErrorList.Temporarily_Closed);
		}
		if(!StringUtils.equals(u.getPassword(), user.getPassword())){
			//password not equal 
			throw new BaseException(ErrorList.Password_not_Correct);
		}
		
		return user;
	}

	@Override
	public User updateUserInfo(User user) {
		//validate the userInfo
		for(BaseValidator validator : modifyUserInfoValidators ){
			validator.validate(user);
		}
		
		//update the user to db
		user = (User) userDao.updateUser(user);
		
		return user;
	}
	
	 /**
	 * modify user password 
	 */
	@Override
	public User updateUserPsw(User user) {
		//validate the user
		for(BaseValidator validator : modifyUserPswValidators ){
			validator.validate(user);
		}
		
		//save the new password
		user = (User) userDao.updateUser(user);
		
		return user;
	}
}
