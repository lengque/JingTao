package controller;

import java.util.Map;

import org.apache.commons.logging.*;
import org.apache.struts2.interceptor.SessionAware;

import service.UserService;
import util.UserUtil;
import model.User;
import model.UserDTO;
import Exception.BaseException;

import com.opensymphony.xwork2.ActionSupport;

import converter.UserConverter;

public class UserController extends ActionSupport implements SessionAware {

	private UserService userService;
	private UserConverter userConverter;
	private UserDTO userDTO;
	protected Map<String, Object> session;
	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	/**
	 * user list
	 */
	public String userList() {
		try {
			logger.debug("start check userList");
			userService.userList();

		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * user register
	 */
	public String register() {
		try {
			logger.debug("start register user");
			
			// 1.convert the userDTO to user
			User user = userConverter.registerConverter(userDTO);

			// 2.involk userService to save the user
			userService.saveUser(user);
			
			logger.debug("register user end");
		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			logger.error("", e);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String addNewUser() {
		try {
			logger.debug("start add new user");
			// 1.convert the userDTO to user
			User user = userConverter.registerConverter(userDTO);

			// 2.involk userService to save the user
			userService.saveUser(user);
			logger.debug("add new user success");
		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * login
	 */
	public String login() {

		try {
			if(logger.isDebugEnabled()){
				logger.debug("开始登录~~~");
			}
			// 1.primary check and convert
			User user = userConverter.loginConverter(userDTO);

			// 2.get the user info from database
			User dbUser = userService.login(user);

			// 3.convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);

			// 4.set the logger in state to session and return the userDTO
			session.put("userLogin", user);
			session.put("userDTO", userDTO);

			if(logger.isDebugEnabled()){
				logger.info("登录成功");
			}
			
		} catch (BaseException e) {
			if(logger.isErrorEnabled()){
				logger.error("登录失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * modify info
	 * 
	 */
	public String modifyInfo() {
		this.userDTO.setGender(UserUtil.female);
		this.userDTO.setAddress("new york");
		this.userDTO.setIdCardNo("123123123123123");
		this.userDTO.setUserId("297e5af94b7941b6014b7942b6ca0000");

		try {
			// 1.convert the userDTO to user
			User user = userConverter.modifyConverter(userDTO);

			// 2.save the new info to db
			user = this.userService.updateUserInfo(user);

			// 3.convert user to UserDto
			userDTO = userConverter.reverseConverter(user);

		} catch (BaseException e) {
			String errorMessage = e.getMessage();
			System.out.println(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * modify password
	 */
	public String modifyPassword() {
		try {
			// convert userDTO to user for password
			User user = userConverter.modifyPswConverter(userDTO, session);
			// 2.save the new password to db
			user = this.userService.updateUserPsw(user);

			// 3.logger out and go to logger on page
			session.clear();

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "login";
	}

	/**
	 * delete user
	 */
	public String deleteUser() {
		try {
			// if user is login convert the userDTO to user
			User user = userConverter.deleteUserConverter(userDTO);
			// delete the user from DB
			userService.deleteUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * set userDTO
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * get userDTO
	 * 
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * <p>
	 * userRegisterService
	 * </p>
	 * 
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * <p>
	 * UserConverter
	 * </p>
	 * 
	 */
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	/**
	 * <p>
	 * 注入session
	 * </p>
	 * 
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}