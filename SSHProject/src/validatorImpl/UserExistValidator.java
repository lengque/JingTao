package validatorImpl;

import model.User;
import service.UserCheckService;
import util.ErrorList;
import validator.BaseValidator;
import Exception.BaseException;

public class UserExistValidator implements BaseValidator{
	private UserCheckService userCheckService;
	
	/**
	 * <p>注入uerService</p>
	 * 
	 */
    public void setUserCheckService(UserCheckService userCheckService) {  
        this.userCheckService = userCheckService;  
    }
    
	/**
	 * <p>检验用户名是不是 已经存在</p>
	 * 
	 */
	@Override
	public void validate(User user) {
		User u = userCheckService.checkUser(user);
		
		// if not found the u will be null
		if(null == u){
			throw new BaseException(ErrorList.UserName_Not_Exist);
		}
	}
}
