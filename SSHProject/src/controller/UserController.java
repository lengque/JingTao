package controller;


import java.util.Map;

import service.UserDeleteService;
import service.UserLoginService;
import service.UserModifyService;
import service.UserRegisterService;
import util.UserUtil;
import model.User;
import model.UserDTO;
import Exception.BaseException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import converter.UserConverter;

public class UserController extends ActionSupport {
		//private Map<String,Object> sessionContext = ActionContext.getContext().getSession();
		//service
	    private UserRegisterService userRegisterService;
	    private UserLoginService userLoginService;
	    private UserModifyService userModifyService;
	    private UserDeleteService userDeleteService;
	    
	    private UserConverter userConverter;
	    private UserDTO userDTO;
	    
	    
	    /**
		 * <p>注入userRegisterService</p>
		 * 
		 */
	    public void setUserRegisterService(UserRegisterService userRegisterService) {  
	        this.userRegisterService = userRegisterService;  
	    }
	    
	    /**
		 * <p>注入userLoginService</p>
		 * 
		 */
	    public void setUserLoginService(UserLoginService userLoginService) {  
	        this.userLoginService = userLoginService;  
	    }
	    
	    /**
		 * <p>注入UserModifyService</p>
		 * 
		 */
		public void setUserModifyService(UserModifyService userModifyService){
			this.userModifyService = userModifyService;
		}
		
		/**
		 * <p>注入UserConverter</p>
		 * 
		 */
		public void setUserDeleteService(UserDeleteService userDeleteService) {
			this.userDeleteService = userDeleteService;
		}
		
	    /**
		 * <p>注入UserConverter</p>
		 * 
		 */
		public void setUserConverter(UserConverter userConverter) {
			this.userConverter = userConverter;
		}
		
	    /**
		 * <p>用户注册</p>
		 */
	    public String register() {
	        try {
	        	//1.convert the userDTO to user
        		User user = userConverter.registerConverter(userDTO);
        		
        		//2.involk userService to save the user
        		userRegisterService.saveUser(user);
	        } catch (BaseException e) {
	        	String errorMessage = e.getMessage();
	        	System.out.println(errorMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return SUCCESS;
	    }
	    
	    /**
		 * <p>用户登陆</p>
		 * 
		 */
	    public String login() {
	    	
	        try {
	        	//1.primary check and convert
		    	User user = userConverter.loginConverter(userDTO);
		    	
		    	//2.get the user info from database
		    	User u = userLoginService.login(user);
		    	
		    	//3.convert the user to userDTO
		    	
		    	//4.set the log in state to session and return the userDTO
		    	//sessionContext.put("userId",u.getUserId());
		    	//sessionContext.put("userName", u.getUsername());
	        } catch (BaseException e) {
	        	String errorMessage = e.getMessage();
	        	System.out.println(errorMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return SUCCESS;
	    }
	    
	    /**
		 * <p>用户资料修改</p>
		 * 
		 */
	    public String modifyInfo() {
	    	this.userDTO.setGender(UserUtil.female);
	    	this.userDTO.setAddress("中国");
	    	this.userDTO.setIdCardNo("123123123123123");
	    	this.userDTO.setUserId("297e5af94b7941b6014b7942b6ca0000");
	    	
	    	try {
		    	//1.convert the userDTO to user
		    	User user = userConverter.modifyConverter(userDTO);
		    	user.setPassword("12322");
		    	//2.save the new info to db
		    	this.userModifyService.modifyInfo(user);
	    	} catch (BaseException e) {
	        	String errorMessage = e.getMessage();
	        	System.out.println(errorMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    	
	    	return SUCCESS;
	    }
	    
	    /**
		 * <p>修改密码</p>
		 * 
		 */
	    public String modifyPassword() {
	    	String flag = "error";
	    	try {
        		//convert userDto to user for password
        		User user = userConverter.modifyPswConverter(userDTO);
        		//2.save the new password to db
        		this.userModifyService.modifyInfo(user);
        		
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "error";
	        }
	        
	        return flag;
	    }
	    
	    /**
		 * <p>删除用户</p>
		 * 
		 */
	    public String deleteUser() {
	        try {
        		//if user is login convert the userDTO to user
	        	User user = userConverter.deleteUserConverter(userDTO);
        		//delete the user from DB
	        	userDeleteService.deleteUser(user);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return SUCCESS;
	    }
	   
	    
	    /**
		 * <p>获取userDTO</p>
		 * 
		 */
		public UserDTO getUserDTO() {
			return userDTO;
		}

		/**
		 * <p>注入userDTO</p>
		 * 
		 */
		public void setUserDTO(UserDTO userDTO) {
			this.userDTO = userDTO;
		}
}