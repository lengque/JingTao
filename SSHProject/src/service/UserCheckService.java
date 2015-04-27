package service;

import model.User;

public interface UserCheckService {

	/**
	 * <p>根据用户名 查找用户</p>
	 * 
	 */
	public User checkUser(User u);
}
