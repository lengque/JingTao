package service;

import java.util.List;

import model.PageBean;
import model.User;

public interface UserService {
	/**
	 * check out one user from database
	 */
	public PageBean<User> userList(User user,PageBean<User> page);
	/**
	 * check out one user from database
	 */
	public User checkUser(User u);
	
	/**
	 *add a  new user
	 */
	 public User saveUser(User u);
	
	/**
	 * delete user from database
	 */
	public void deleteUser(User user);
	
	/**
	 * user login
	 */
	public User login(User user);
	
	/**
	 * modify user info
	 */
	public User updateUserInfo(User u);
	
	/**
	 *modify user password
	 */
	public User updateUserPsw(User u);
}
