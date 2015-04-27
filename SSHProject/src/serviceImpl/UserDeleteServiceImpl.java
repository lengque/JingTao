package serviceImpl;

import model.User;
import service.UserDeleteService;
import util.UserUtil;
import daoImpl.UserDao;

public class UserDeleteServiceImpl implements UserDeleteService {
	private UserDao userDao;
	
	/**
	 * <p>×¢ÈëuserDao</p>
	 * 
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;  
	}

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
	
	
}
