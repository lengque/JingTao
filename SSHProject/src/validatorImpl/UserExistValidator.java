package validatorImpl;

import daoImpl.UserDao;
import model.User;
import service.UserService;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;
import Exception.BaseException;

public class UserExistValidator implements BaseValidator<User>{
	/**
	 * validotor is exist in DB
	 */
	@Override
	public void validate(User user,User dbUser) {
		// if not found the u will be null
		if(null == dbUser || dbUser.getState()== UserUtil.disable){
			throw new BaseException(ErrorList.User_Not_Exist);
		}
	}
}
