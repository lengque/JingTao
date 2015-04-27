package service;

import model.User;

  
public interface UserRegisterService {  
	/**
	 * <p>新增一个用户</p>
	 * 
	 */
    public User saveUser(User u);
}  