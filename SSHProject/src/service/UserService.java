package service;

import model.User;

public interface UserService {
	/**
	 * check out one user from database
	 * 
	 */
	public User checkUser(User u);
	
	/**
	 *add a  new user
	 * 
	 */
	 public User saveUser(User u);
	
	/**
	 * delete user from database
	 * 
	 */
	public void deleteUser(User user);
	
	/**
	 * user login
	 * 
	 */
	public User login(User user);
	
	/**
	 * modify user info
	 * 
	 */
	public User updateInfo(User u);
	
	/**
	 *modify user password
	 * 
	 */
	public User updatePsw(User u,String newPsw);
	
	
	
}
