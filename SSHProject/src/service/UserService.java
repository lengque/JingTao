package service;

import java.util.List;

import model.User;

public interface UserService {
	/**
	 * check out one user from database
	 */
	public List<User> userList();
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
