package serviceImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import model.User;
import service.UserModifyService;
import validator.BaseValidator;
import daoImpl.UserDao;

public class UserModifyServiceImpl implements UserModifyService{
	private UserDao userDao;
	private List<BaseValidator> validators;
	
	/**
	 * <p>×¢ÈëuserDao</p>
	 * 
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;  
	}
	/**
	 * <p>×¢Èëvalidators</p>
	 * 
	 */
	public void setValidators(List<BaseValidator> validators) {
		this.validators = validators;
	}

	@Override
	public User modifyInfo(User u) {
		//validate the user
		for(BaseValidator validator : validators ){
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
	
	@Override
	public User modifyPsw(User user, String newPsw) {
		
		//find the user from DB 
		
		//compare the passwords
		
		//set new password
		user.setPassword(newPsw);
		//save the new password
		userDao.updateObject(user);
		return user;
	}
	
}
