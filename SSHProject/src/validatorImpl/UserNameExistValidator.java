package validatorImpl;

import Exception.BaseException;
import model.User;
import util.ErrorList;
import validator.BaseValidator;

public class UserNameExistValidator implements BaseValidator{
	
	/**
	 * check the user name
	 * 
	 */
	@Override
	public void validate(User user) {
		// if not found the u will be null
		if(null != user){
			//throw new BaseException(ErrorList.UserName_Is_Exist);
		}
	}
}
