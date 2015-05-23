package validatorImpl;

import model.User;
import service.UserService;
import util.ErrorList;
import validator.BaseValidator;
import Exception.BaseException;

public class UserExistValidator implements BaseValidator{
	private UserService userService;
	
	/**
	 * <p>ע��uerService</p>
	 * 
	 */
    public void setUserCheckService(UserService userService) {  
        this.userService = userService;  
    }
    
	/**
	 * validotor is exist in DB
	 */
	@Override
	public void validate(User user) {
		User u = userService.checkUser(user);
		
		// if not found the u will be null
		if(null == u){
			throw new BaseException(ErrorList.UserName_Not_Exist);
		}
	}
}
