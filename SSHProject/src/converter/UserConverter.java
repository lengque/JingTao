package converter;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import Exception.BaseException;
import util.CommonUtil;
import util.ErrorList;
import util.UserUtil;
import model.User;
import model.UserDTO;

public class UserConverter {
	
	/**
	 * 
	 * 
	 */
	public User registerConverter(UserDTO userDTO){
		User user = new User();
		
		if(null != userDTO){
			String userName = userDTO.getUserName();
			String password = userDTO.getPassword();
			String conPsw = userDTO.getConfirmPsw();
			String telphone = userDTO.getTelphone();
			
			if(StringUtils.isBlank(userName)){
				//throw exception
				throw new BaseException(ErrorList.UserName_Is_Blank);
			}
			if(userName.length()>12){
				//throw exception
				throw new BaseException(ErrorList.UserName_Too_Long);
			}
			user.setUsername(userName);
			
			if(StringUtils.isBlank(password)){
				//throw exception
				throw new BaseException(ErrorList.Password_Is_Blank);
			}
			if(password.length()>30){
				//throw exception
				throw new BaseException(ErrorList.Password_Too_Long);
			}
			if(!StringUtils.equals(password, conPsw)){
				//throw exception
				throw new BaseException(ErrorList.Password_Diffirent_From_ConfirmPsw);
			}
			
			if(!StringUtils.equals(password, conPsw)){
				//throw exception
				throw new BaseException(ErrorList.Password_Diffirent_From_ConfirmPsw);
			}
			user.setPassword(password);
			
			if(StringUtils.isBlank(telphone)){
				//throw exception
				throw new BaseException(ErrorList.Tel_Is_Blank);
			}
			//if(telphone.length()!=11){
				//throw exception
				//throw new BaseException(ErrorList.Tel_Length_Error);
			//}
			user.setTelphone(telphone);
			
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			user.setState(UserUtil.effective);
		}else {
			//throw exception
			throw new BaseException(ErrorList.UserName_Is_Blank);
		}
		
		return user;
	}
	
	/**
	 * 
	 * 
	 */
	public User loginConverter(UserDTO userDTO){
		User user = new User();
		
		if(null != userDTO){
			String userName = userDTO.getUserName();
			String password = userDTO.getPassword();
			
			if(StringUtils.isBlank(userName)){
				//throw exception
				throw new BaseException(ErrorList.UserName_Is_Blank);
			}
			user.setUsername(userName);
			
			if(StringUtils.isBlank(password)){
				//throw exception
				throw new BaseException(ErrorList.Password_Is_Blank);
			}
			user.setPassword(password);
			
		}else {
			//throw exception
			throw new BaseException(ErrorList.UserName_Is_Blank);
		}
		
		return user;
	}
	
	/**
	 * 
	 * 
	 */
	public UserDTO loginReverseConverter(User user){
		UserDTO userDto = new UserDTO();
		if(user !=null ){
			userDto.setAddress(user.getAddress());
			userDto.setCreateTime(user.getCreateTime());
			userDto.setEmail(user.getEmail());
			userDto.setGender(user.getGender());
			userDto.setGrade(user.getGrade());
			userDto.setIdCardNo(user.getIdCardNo());
			userDto.setRealName(user.getRealName());
			userDto.setState(user.getState());
			userDto.setTelphone(user.getTelphone());
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUsername());
		}
		
		return userDto;
	}
	
	/**
	 * 
	 * 
	 */
	public User modifyPswConverter(UserDTO userDTO){
		User user = new User();
		
		if(null != userDTO){
			user.setUserId(userDTO.getUserId());
			user.setUsername(userDTO.getUserName());
			
			String originalPsw = userDTO.getPrimitivePsw();
			
			if(StringUtils.isNotBlank(originalPsw)){
				user.setPassword(originalPsw);
			}else {
				//throw exception
				throw new BaseException(ErrorList.Original_Password_Is_Blank);
			}
			
			String newPsw = userDTO.getPassword();
			if(StringUtils.isBlank(newPsw)){
				//throw exception
				throw new BaseException(ErrorList.New_Password_Is_Blank);
			}
			
			String confPsw = userDTO.getConfirmPsw();
			if(StringUtils.isBlank(newPsw) || !StringUtils.equals(newPsw, confPsw)){
				//throw exception
				throw new BaseException(ErrorList.Password_Diffirent_From_ConfirmPsw);
			}
		}
		
		return user;
	}
	
	/**
	 * 
	 */
	public User modifyConverter(UserDTO userDTO){
		User user = new User();
		
		if(null != userDTO){
			user.setUserId(userDTO.getUserId());
			user.setUsername(userDTO.getUserName());
			//set IdCardNo
			String IdCardNo = userDTO.getIdCardNo();
			if(StringUtils.isNotBlank(IdCardNo)){
				//check the format of ID CARD
				if(!IdCardNo.matches(CommonUtil.Id_Card_Format)){
					throw new BaseException(ErrorList.Id_Card_No_Fromat_Error);
				}
				user.setIdCardNo(IdCardNo);
			}
			//set real name
			String realName = userDTO.getRealName();
			if(StringUtils.isNotBlank(realName)){
				if(realName.length()>32){
					throw new BaseException(ErrorList.Real_Name_Is_Too_Long);
				}
				user.setRealName(realName);
			}
			//set gender
			int gender = userDTO.getGender();
			if(gender != UserUtil.male && gender != UserUtil.female ){
				throw new BaseException(ErrorList.Gender_Error);
			}
			user.setGender(gender);
			
			//set telphone
			String telphone = userDTO.getTelphone();
			if(StringUtils.isNotBlank(telphone)){
				if(!telphone.matches(CommonUtil.Telphone_Format)){
					throw new BaseException(ErrorList.Telphone_Format_Error);
				}
				user.setTelphone(telphone);
			}
			
			//set email
			String email = userDTO.getEmail();
			if(StringUtils.isNotBlank(email)){
				if(email.matches(CommonUtil.Email_Format)){
					throw new BaseException(ErrorList.Email_Format_Error);
				}
				user.setEmail(email);
			}
			
			//set address
			String address = userDTO.getAddress();
			if(StringUtils.isNotBlank(address)){
				user.setAddress(address);
			}
			
			//set update time
			user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		}
		
		return user;
	}
	
	public User deleteUserConverter(UserDTO userDTO){
		User user = new User();
		
		user.setUserId(userDTO.getUserId());
		user.setPassword(userDTO.getPassword());
		
		return user;
	}
}
