package controller;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.*;
import org.apache.struts2.interceptor.SessionAware;

import service.UserService;
import util.UserUtil;
import model.PageBean;
import model.User;
import model.UserDTO;
import Exception.BaseException;

import com.opensymphony.xwork2.ActionSupport;

import converter.UserConverter;

public class UserController extends ActionSupport implements SessionAware {
	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	private UserService userService;
	private UserConverter userConverter;
	
	private UserDTO userDTO;
	private PageBean<User> page;
	private JSONObject requestJson;
	private String responseJson;
	
	protected Map<String, Object> session;
	
	/**
	 * <p>查询分页数据</p>
	 * @parm pageBean
	 * @parm UserDTO
	 */
	public String userList() {
		try {
			logger.debug("start chec k userList~~~");
			//将userDto转换成为User
			User user = userConverter.converter(userDTO);
			
			page = userService.userList(user, page);
			String json1 = JSONObject.fromObject(page).toString();
			String json2 = JSONArray.fromObject(page).toString();
			System.out.println("Json1: "+json1);
			System.out.println("Json2 "+json2);
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
			//0.adptor change the json to object
			//0.1 change the request json to json object
			JSONObject  request= JSONObject.fromObject(requestJson);
			userDTO = (UserDTO)JSONObject.toBean(request, UserDTO.class);
			// 1.primary check and convert
			User user = userConverter.loginConverter(userDTO);

			// 2.get the user info from database
			User dbUser = userService.login(user);
			// 3.convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);

			// 4.change the userDTO to response Json
			JSONObject response = JSONObject.fromObject(userDTO);
			responseJson = response.toString();
			
			// 4.set the logger in state to session and return the userDTO
			session.put(UserUtil.User_Login, dbUser);
			session.put(UserUtil.User_Is_Login,true);
			
			if(logger.isDebugEnabled()){
				logger.info("登录成功");
			}
		} catch (BaseException e) {
			session.put(UserUtil.User_Login, null);
			session.put(UserUtil.User_Is_Login,false);
			if(logger.isErrorEnabled()){
				logger.error("登录失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * login
	 */
	public String logOut() {
		try {
			if(logger.isDebugEnabled()){
				logger.debug("开始登出~~~");
			}
			
			session.put(UserUtil.User_Login, null);
			session.put(UserUtil.User_Is_Login,false);
			
			session.clear();
			
			if(logger.isDebugEnabled()){
				logger.info("登出成功");
			}
		} catch (BaseException e) {
			if(logger.isErrorEnabled()){
				logger.error("登出失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return LOGIN;
	}
	
	/**
	 * modify info
	 * 
	 */
	public String modifyInfo() {
		try {
			// 1.convert the userDTO to user
			User user = userConverter.modifyConverter(userDTO,session);

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
	/*public UserDTO getUserDTO() {
		return userDTO;
	}*/

	/**
	 * get userDTO
	 * 
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * userRegisterService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * UserConverter
	 */
	public void setUserConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	/**
	 * 注入session
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	/*public PageBean<User> getPage() {
		return page;
	}

	public void setPage(PageBean<User> page) {
		this.page = page;
	}*/
	
	/**
	 * set request json
	 */
	public void setRequestJson(JSONObject requestJson){
		this.requestJson = requestJson;
	}
	
	/**
	 * get response json
	 */
	public void getResponseJson(String responseJson){
		this.responseJson = responseJson;
	}
	
/*	//test
	public static void main(String[] args){
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("asdf");
		userDTO.setConfirmPsw("sa");
		User user = new User();
		user.setAddress("asdf");
		user.setEmail("asd");
		Object[] objects = {userDTO,user};
		JSONArray  json= JSONArray.fromObject(objects);
		
		System.out.println(json.toString());
	}*/
}