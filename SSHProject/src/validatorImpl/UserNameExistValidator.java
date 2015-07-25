package validatorImpl;

import daoImpl.UserDao;
import Exception.BaseException;
import model.User;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;

public class UserNameExistValidator implements BaseValidator<User>{
	/**
	 * check the user name
	 */
	@Override
	public void validate(User user,User dbUser) {
		//如果用户找到了 并且 用户状态不是停用状态 那么说明这个用户存在
		if(null != dbUser && dbUser.getState() != UserUtil.disable){
			throw new BaseException(ErrorList.UserName_Is_Exist);
		}
	}

	
}
