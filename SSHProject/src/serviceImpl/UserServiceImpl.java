package serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import model.PageBean;
import model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import Exception.BaseException;
import service.UserService;
import util.ErrorList;
import util.UserUtil;
import validator.BaseValidator;
import daoImpl.UserDao;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	
	private List<BaseValidator<User>> saveValidators;
	private List<BaseValidator<User>> modifyUserInfoValidators;
	private List<BaseValidator<User>> modifyUserPswValidators;
	private List<BaseValidator<User>> deleteUserValidators;
	
	/**
	 * init the userDao
	 */
	public void setUserDao(UserDao userdao) {  
	    this.userDao = userdao;
	}
	
	/**
	 * init validators for save method
	 */
	public void setSaveValidators(List<BaseValidator<User>> saveValidators) {
		this.saveValidators = saveValidators;
	}
	
	/**
	 * init validators for save method
	 */
	public void setModifyUserInfoValidators(List<BaseValidator<User>> modifyUserInfoValidators) {
		this.modifyUserInfoValidators = modifyUserInfoValidators;
	}
	
	/**
	 * init validators for save method
	 */
	public void setDeleteUserValidators(List<BaseValidator<User>> modifyUserPswValidators) {
		this.modifyUserPswValidators = modifyUserPswValidators;
	}
	
	/**
	 * init validators for delete method
	 */
	public void setModifyUserPswValidators(List<BaseValidator<User>> deleteUserValidators) {
		this.deleteUserValidators = deleteUserValidators;
	}
	
	/**
	 * 获取 用户列表
	 */
	public PageBean<User> userList(User user,PageBean<User> page){
		
		if(null != page){
			int curPageNum = page.getCurrentPage();
			int pageSize = page.getPageSize();
			
			page = userDao.findPage(user, curPageNum, pageSize);
		}
		
		
		return page;
	}
	
	/**
	 * 查找一个特定的用户
	 */
	@Override
	public User checkUser(User u) {
		User user = null;
		
		return user;
	}

	/**
	 * 保存一个用户
	 */
    @Override  
    public User saveUser(User user){
    	//1.首先从数据库中查询这个用户
    	User dbUser = userDao.checkUserByName(user);
    	
    	//2.validate the user data
		for(BaseValidator<User> validator : saveValidators ){
			validator.validate(user,dbUser);
		}
		//2.save object
    	user = userDao.saveUser(user);
    	
    	return user;
    }
    
    /**
	 * 删除一个用户
	 */
    @Override
	public void deleteUser(User user) {
    	//1.首先从数据库中查询这个用户
    	User dbUser = userDao.checkUserByName(user);
    	
    	//2.validate the user data
		for(BaseValidator<User> validator : deleteUserValidators){
			validator.validate(user,dbUser);
		}
		
		//change the user state
		user.setStatus(UserUtil.disable);
		
		//save the state
		userDao.updateUser(user);
	}

    /**
	 * 用户登陆
	 */
	@Override
	public User login(User u) {
		//check the user from DB
		User user = userDao.checkUser(u);
		
		if(null == user || user.getStatus()==UserUtil.disable){
			//user not exist
			throw new BaseException(ErrorList.User_Not_Exist);
		}
		if(user.getStatus()==UserUtil.pause){
			//user temporaily close
			throw new BaseException(ErrorList.Temporarily_Closed);
		}
		if(!StringUtils.equals(u.getPassword(), user.getPassword())){
			//password not equal 
			throw new BaseException(ErrorList.Password_not_Correct);
		}
		
		return user;
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public User updateUserInfo(User user) {
		//1.首先从数据库中查询这个用户
    	User dbUser = userDao.checkUserByName(user);
    	
		//validate the userInfo
		for(BaseValidator<User> validator : modifyUserInfoValidators ){
			validator.validate(user,dbUser);
		}
		
		//update the user to db
		user = (User) userDao.updateUser(user);
		
		return user;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public User updateUserPsw(User user) {
		//1.首先从数据库中查询这个用户
    	User dbUser = userDao.checkUserByName(user);
    	
		//validate the user
		for(BaseValidator<User> validator : modifyUserPswValidators ){
			validator.validate(user,dbUser);
		}
		
		//save the new password
		user = (User) userDao.updateUser(user);
		
		return user;
	}
}
