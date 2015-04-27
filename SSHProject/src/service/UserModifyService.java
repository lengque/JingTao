package service;

import model.User;

public interface UserModifyService {
	/**
	 * <p>修改信息</p>
	 * 
	 */
	public User modifyInfo(User u);
	
	/**
	 * <p>修改密码</p>
	 * 
	 */
	public User modifyPsw(User u,String newPsw);
}
