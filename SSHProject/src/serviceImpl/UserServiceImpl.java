package serviceImpl;

import java.util.List;

import model.User;

import org.apache.commons.lang.StringUtils;

import Exception.BaseException;
import service.UserService;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;
import daoImpl.UserDao;

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
    	userDao.saveObject(u);
    	
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
		userDao.saveObject(user);
	}

    /**
	 *use login
	 */
	@Override
	public User login(User u) {
		//check the user from DB
		User user = checkUser(u);
		
		if(null == user || user.getState()==UserUtil.disable){
			//username not exist
			throw new BaseException(ErrorList.UserName_Not_Exist);
		}
		if(user.getState()==UserUtil.pause){
			//user temporaily close
			throw new BaseException(ErrorList.Temporarily_Closed);
		}
		if(StringUtils.equals(u.getPassword(), user.getPassword())){
			//password not equal 
			throw new BaseException(ErrorList.Password_not_Correct);
		}
		
		return user;
	}

	@Override
	public User modifyInfo(User u) {
		//validate the user
		for(BaseValidator validator : modifyUserInfoValidators ){
			validator.validate(u);
		}
		boolean flag = false;
		//update the user to db
		userDao.updateObject(u);
		/*StringBuffer preSql = new StringBuffer("UPDATE User SET ");
		if(StringUtils.isNotBlank(u.getAddress())){
			preSql.append("Address = '"+u.getAddress()+"'");
			flag = true;
		}
		if(flag = true){
			preSql.append(", ");
		}
		
		if(StringUtils.isNotBlank(u.getEmail())){
			preSql.append("Email = '"+u.getEmail()+"'");
			flag = true;
		}
		if(flag = true){
			preSql.append(", ");
		}

		if(flag){
			userDao.updateObject(preSql.toString());
		}*/
		
		
		//return user 
		return u;
	}
	
	 /**
	 * modify user password 
	 */
	@Override
	public User modifyPsw(User user, String newPsw) {
		//find the user from DB
		User userInDB  =  checkUser(user);
		
		//validate the user
		for(BaseValidator validator : modifyUserPswValidators ){
			validator.validate(userInDB);
		}
		
		//save the new password
		userDao.updateObject(user);
		
		return user;
	}
}
