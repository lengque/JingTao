package validatorImpl;

import daoImpl.UserDao;
import Exception.BaseException;
import model.User;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;

public class UserNameExistValidator implements BaseValidator{
	UserDao userDao;
	/**
	 * check the user name
	 * 
	 */
	@Override
	public void validate(User user) {
		// if not found the u will be null
		user = userDao.checkUserByName(user);
		//如果用户找到了 并且 用户状态不是停用状态 那么说明这个用户存在
		if(null != user && user.getState() != UserUtil.disable){
			throw new BaseException(ErrorList.UserName_Is_Exist);
		}
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
