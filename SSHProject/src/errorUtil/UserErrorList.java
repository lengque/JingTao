package errorUtil;

public enum UserErrorList implements ErrorList {
	USER_NOT_LOGIN("USER001"),//用户未登录
	USERNAME_IS_BLANK("USER002"),//用户名为空
	USERNAME_TOO_LONG("USER003"),//用户名过长
	USERNAME_EXIST("USER004"),//用户名已存在
	USERNAME_NOT_EXIST("USER005"),//用户名不存在
	//password error
	USER_PASSWORD_IS_BLANK("USER006"),//不设密码真的好吗
	USER_PASSWORD_TOO_SHORT("USER007"),//密码太短
	USER_PASSWORD_TOO_LONG("USER008"),//密码这么长，伦家记不住了
	USER_PASSWORD_DEFFIRENT_FORMAT_CONFIRMPSW("USER009"),//两次密码要一样哦
	USER_PASSWORD_NOT_CORRECT("USER010"),//密码好像不正确呢
	USER_ORIGINAL_PASSWORD_NOT_CORRECT("USER011"),//原始密码不正确
	//telphone
	USER_TEL_IS_BLANK("USER012"),//电话是空号
	USER_Tel_FORM_ERROR("USER013"),//电话格式错误
	//ID card
	USER_ID_NUMBER_IS_BLANK("USER014"),//
	USER_ID_FORMAT_ERROR("USER015"),//
	//realName
	USER_REALNAME_IS_TOO_LONG("USER016"),//
	//Email
	USER_EMAIL_IS_BLANK("USER017"),//
	USER_EMAIL_FORMAT_ERROR("USER018"),//
	//address
	USER_ADDRESS_TOO_LONG("USER019");//
	
	private String value;
	
	UserErrorList(String value){
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}
}