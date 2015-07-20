package converter;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.catalina.Session;
import org.apache.commons.lang.StringUtils;

import Exception.BaseException;
import util.CommonUtil;
import util.ErrorList;
import util.UserUtil;
import model.User;
import model.UserDTO;

public class UserConverter {

	/**
	 * 将前台DTO对象数据放到User中
	 * 
	 */
	public User registerConverter(UserDTO userDTO) {
		User user = new User();

		if (null != userDTO) {
			String userName = userDTO.getUserName();
			String password = userDTO.getPassword();
			String conPsw = userDTO.getConfirmPsw();
			Integer gender = userDTO.getGender();

			if (StringUtils.isBlank(userName)) {
				// throw exception
				throw new BaseException(ErrorList.UserName_Is_Blank);
			}
			if (userName.length() > 12) {
				// throw exception
				throw new BaseException(ErrorList.UserName_Too_Long);
			}
			user.setUserName(userName);

			if (StringUtils.isBlank(password)) {
				// throw exception
				throw new BaseException(ErrorList.Password_Is_Blank);
			}
			if (password.length() > 30) {
				// throw exception
				throw new BaseException(ErrorList.Password_Too_Long);
			}
			if (!StringUtils.equals(password, conPsw)) {
				// throw exception
				throw new BaseException(
						ErrorList.Password_Diffirent_From_ConfirmPsw);
			}
			user.setPassword(password);

			// 设置创建时间和信息更新时间
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			// 设置用户状态为活跃状态
			user.setState(UserUtil.effective);
		} else {
			// throw exception
			throw new BaseException(ErrorList.UserName_Is_Blank);
		}

		return user;
	}

	/**
	 * 
	 * 
	 */
	public User loginConverter(UserDTO userDTO) {
		User user = new User();

		if (null != userDTO) {
			String userName = userDTO.getUserName();
			String password = userDTO.getPassword();

			if (StringUtils.isBlank(userName)) {
				// throw exception
				throw new BaseException(ErrorList.UserName_Is_Blank);
			}
			user.setUserName(userName);

			if (StringUtils.isBlank(password)) {
				// throw exception
				throw new BaseException(ErrorList.Password_Is_Blank);
			}
			user.setPassword(password);

		} else {
			// throw exception
			throw new BaseException(ErrorList.UserName_Is_Blank);
		}

		return user;
	}

	/**
	 * 
	 * 
	 */
	public UserDTO reverseConverter(User user) {
		UserDTO userDto = new UserDTO();
		if (user != null) {
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
			userDto.setUserName(user.getUserName());
		}

		return userDto;
	}

	/**
	 * 修改用户的密码
	 */
	public User modifyPswConverter(UserDTO userDTO, Map<String, Object> session) {
		User user = new User();

		if (null != userDTO) {
			String userId = session.get("userId").toString();
			String userName = session.get("userName").toString();
			String password = session.get("password").toString();

			if (!StringUtils.equals(userId, userDTO.getUserId())
					|| !StringUtils.equals(userName, userDTO.getUserName())
					|| !StringUtils.equals(password, userDTO.getPassword())) {
				throw new BaseException(ErrorList.User_Not_LogIn);
			}
			user.setUserId(userId);
			user.setUserName(userName);
			
			// get the original password
			String originalPsw = userDTO.getPrimitivePsw();

			// check the original password
			if (StringUtils.isBlank(originalPsw)
					|| !StringUtils.equals(originalPsw, password)) {
				// throw exception
				throw new BaseException(ErrorList.Original_Password_Not_Correct);
			}

			// get the new password
			String newPsw = userDTO.getPassword();
			String confPsw = userDTO.getConfirmPsw();
			// compare the new password with confirm password
			if (StringUtils.isBlank(newPsw) || StringUtils.isBlank(confPsw)
					|| !StringUtils.equals(newPsw, confPsw)) {
				// throw exception
				throw new BaseException(
						ErrorList.Password_Diffirent_From_ConfirmPsw);
			}

			user.setPassword(newPsw);
		}

		return user;
	}

	/**
	 * 更改用户信息 对于用户信息的更改以UUID为准
	 */
	public User modifyConverter(UserDTO userDTO) {
		User user = new User();

		if (null != userDTO) {
			// Id 和 username不允许更改
			user.setUserId(userDTO.getUserId());
			user.setUserName(userDTO.getUserName());

			// set IdCardNo
			String IdCardNo = userDTO.getIdCardNo();
			if (StringUtils.isNotBlank(IdCardNo)) {
				// check the format of ID CARD
				if (!IdCardNo.matches(CommonUtil.Id_Card_Format)) {
					throw new BaseException(ErrorList.Id_Card_No_Fromat_Error);
				}
				user.setIdCardNo(IdCardNo);
			}
			// set real name
			String realName = userDTO.getRealName();
			if (StringUtils.isNotBlank(realName)) {
				if (realName.length() > 32) {
					throw new BaseException(ErrorList.Real_Name_Is_Too_Long);
				}
				user.setRealName(realName);
			}

			// set gender
			Integer gender = userDTO.getGender();
			if (!gender.equals(UserUtil.male) && gender.equals(UserUtil.female)) {
				throw new BaseException(ErrorList.Gender_Error);
			}
			user.setGender(gender);

			// set telphone
			String telphone = userDTO.getTelphone();
			if (StringUtils.isNotBlank(telphone)) {
				if (!telphone.matches(CommonUtil.Telphone_Format)) {
					throw new BaseException(ErrorList.Telphone_Format_Error);
				}
				user.setTelphone(telphone);
			}

			// set email
			String email = userDTO.getEmail();
			if (StringUtils.isNotBlank(email)) {
				if (email.matches(CommonUtil.Email_Format)) {
					throw new BaseException(ErrorList.Email_Format_Error);
				}
				user.setEmail(email);
			}

			// set address
			String address = userDTO.getAddress();
			if (StringUtils.isNotBlank(address)) {
				user.setAddress(address);
			}

			// set update time
			user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		}

		return user;
	}

	public User deleteUserConverter(UserDTO userDTO) {
		User user = new User();

		user.setUserId(userDTO.getUserId());

		return user;
	}
}
