package controller;


import java.util.Map;

import javax.annotation.Resources;

import service.UserService;
import util.UserUtil;
import model.User;
import model.UserDTO;
import Exception.BaseException;

import com.opensymphony.xwork2.ActionSupport;

import converter.UserConverter;

public class UserController extends ActionSupport {
	    
		private UserService userService;
		private UserConverter userConverter;
		private UserDTO userDTO;
	    
	    /**
		 * user register
		 */
	    public String register() {
	        try {
	        	//1.convert the userDTO to user
        		User user = userConverter.registerConverter(userDTO);
        		
        		//2.involk userService to save the user
        		userService.saveUser(user);
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
	        	//1.primary check and convert
		    	User user = userConverter.loginConverter(userDTO);
		    	
		    	//2.get the user info from database
		    	User u = userService.login(user);
		    	
		    	//3.convert the user to userDTO
		    	userDTO = userConverter.reverseConverter(u);
		    	//4.set the log in state to session and return the userDTO
		    	
		    	//sessionContext.put("userId",u.getUserId());
		    	//sessionContext.put("userName", u.getUsername());
		    	
	        } catch (BaseException e) {
	        	String errorMessage = e.getMessage();
	        	System.out.println(errorMessage);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("登陆成功");
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
		    	//1.convert the userDTO to user
		    	User user = userConverter.modifyConverter(userDTO);
		    	
		    	//2.save the new info to db
		    	user = this.userService.updateInfo(user);
		    	
		    	//3.convert user to UserDto
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
	    	String flag = "error";
	    	try {
        		//convert userDTO to user for password
        		User user = userConverter.modifyPswConverter(userDTO);
        		//2.save the new password to db
        		this.userService.updateInfo(user);
        		
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "error";
	        }
	        
	        return flag;
	    }
	    
	    /**
		 * delete user
		 */
	    public String deleteUser() {
	        try {
        		//if user is login convert the userDTO to user
	        	User user = userConverter.deleteUserConverter(userDTO);
        		//delete the user from DB
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
		 * <p>userRegisterService</p>
		 * 
		 */
	    public void setUserService(UserService userService) {  
	        this.userService = userService;  
	    }
	    
	    /**
		 * <p>UserConverter</p>
		 * 
		 */
		public void setUserConverter(UserConverter userConverter) {
			this.userConverter = userConverter;
		}
}