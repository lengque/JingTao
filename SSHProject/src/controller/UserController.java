package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.*;
import org.apache.struts2.interceptor.SessionAware;

import service.UserService;
import util.UserUtil;
import model.PageBean;
import model.Response;
import model.User;
import model.UserDTO;
import Exception.BaseException;

import com.opensymphony.xwork2.ActionSupport;

import converter.JsonAdaptor;
import converter.UserConverter;

public class UserController extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(this.getClass().getName());
	
	
	//最终留下 start
	private UserService userService;
	private UserConverter userConverter;
	private JsonAdaptor adptor;
	
	private String requestJson;
	private String responseJson;
	private List<Object> dtoList;
	//最终留下 end
	
	private Response result;
	protected Map<String, Object> session;
	
	/**
	 * <p>查询分页数据</p>
	 * @parm pageBean
	 * @parm UserDTO
	 */
	public String userList() {
		try {
			logger.debug("start chec k userList~~~");
			JSONObject  requestObjects = adptor.RequestAdapt(requestJson);
			JSONObject userDTOJson = requestObjects.getJSONObject("userDTO");
			JSONObject pageJson = requestObjects.getJSONObject("page");
			
			UserDTO userDTO = (UserDTO)JSONObject.toBean(userDTOJson, UserDTO.class);
			PageBean pageDTO = (PageBean)pageJson.toBean(pageJson,PageBean.class);
			
			//将userDto转换成为User
			User user = userConverter.converter(userDTO);
			
			PageBean page = userService.userList(user, pageDTO);
			
			//adaptor page to result
			dtoList = new ArrayList<Object>();
			dtoList.add(page);
			
			result = new Response();
			result.setDtoList(dtoList);
			//adapt the result to response json
			responseJson = adptor.ResponseAdapt(result);
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			
			// 1.convert the userDTO to user
			User user = userConverter.registerConverter(userDTO);

			// 2.involk userService to save the user
			User dbUser = userService.saveUser(user);
			
			//3.prepare a list for contain dto list
			dtoList = new ArrayList<Object>();
			// 3.1 convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);
			dtoList.add(userDTO);
			
			//create result 
			result = new Response();
			result.setDtoList(dtoList);
			
			responseJson = adptor.ResponseAdapt(result);
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			
			// 1.convert the userDTO to user
			User user = userConverter.registerConverter(userDTO);

			// 2.involk userService to save the user
			User dbUser = userService.saveUser(user);
			
			//3.prepare a list for contain dto list
			dtoList = new ArrayList<Object>();
			// 3.1 convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);
			dtoList.add(userDTO);
			
			//create result 
			result = new Response();
			result.setDtoList(dtoList);
			
			responseJson = adptor.ResponseAdapt(result);
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			
			// 1.primary check and convert
			User user = userConverter.loginConverter(userDTO);

			// 2.get the user info from database
			User dbUser = userService.login(user);
			
			//3.prepare a list for contain dto list
			dtoList = new ArrayList<Object>();
			// 3.1 convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);
			
			dtoList.add(userDTO);
			
			//create result 
			result = new Response();
			result.setDtoList(dtoList);
			
			responseJson = adptor.ResponseAdapt(result);
			
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			// 1.convert the userDTO to user
			User user = userConverter.modifyConverter(userDTO,session);

			// 2.save the new info to db
			User dbUser = this.userService.updateUserInfo(user);

			//3.prepare a list for contain dto list
			dtoList = new ArrayList<Object>();
			// 3.1 convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);
			dtoList.add(userDTO);
			
			//create result 
			result = new Response();
			result.setDtoList(dtoList);
			
			responseJson = adptor.ResponseAdapt(result);
			logger.debug("register user end");
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			// convert userDTO to user for password
			User user = userConverter.modifyPswConverter(userDTO, session);
			// 2.save the new password to db
			User dbUser = this.userService.updateUserPsw(user);

			//3.prepare a list for contain dto list
			dtoList = new ArrayList<Object>();
			// 3.1 convert the user to userDTO
			userDTO = userConverter.reverseConverter(dbUser);
			dtoList.add(userDTO);
			
			//create result 
			result = new Response();
			result.setDtoList(dtoList);
			
			responseJson = adptor.ResponseAdapt(result);
			logger.debug("register user end");
			
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
			JSONObject  requestObject = adptor.RequestAdapt(requestJson);
			UserDTO userDTO = (UserDTO)JSONObject.toBean(requestObject, UserDTO.class);
			// if user is login convert the userDTO to user
			User user = userConverter.deleteUserConverter(userDTO);
			// delete the user from DB
			userService.deleteUser(user);
			
			//create result 
			result = new Response();
			
			responseJson = adptor.ResponseAdapt(result);
			logger.debug("register user end");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
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

	/**
	 * 注入jsonAdaptor
	 */	
	public void setAdptor(JsonAdaptor adptor) {
		this.adptor = adptor;
	}
	
	/**
	 * set request json
	 */
	public void setRequestJson(String requestJson){
		this.requestJson = requestJson;
	}
	
	/**
	 * get response json
	 */
	public String getResponseJson(){
		return this.responseJson;
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