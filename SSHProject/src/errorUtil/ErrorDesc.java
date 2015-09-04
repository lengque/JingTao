package errorUtil;

public enum ErrorDesc {
	/**
	 * user error list
	 */
	USER_NOT_LOGIN("用户未登录"),//用户未登录
	USERNAME_IS_BLANK("用户名不能唯恐"),//用户名为空
	USERNAME_TOO_LONG("用户名不要超过15位"),//用户名过长
	USERNAME_EXIST("用户名已存在"),//用户名已存在
	USERNAME_NOT_EXIST("用户不存在"),//用户名不存在
	USER_PASSWORD_IS_BLANK("密码为空"),//不设密码真的好吗
	USER_PASSWORD_TOO_SHORT("密码不能少于6位"),//密码太短
	USER_PASSWORD_TOO_LONG("密码不能超过20位"),//密码这么长，伦家记不住了
	USER_PASSWORD_DEFFIRENT_FORMAT_CONFIRMPSW("两次输入密码不一致"),//两次密码要一样哦
	USER_PASSWORD_NOT_CORRECT("密码不正确"),//密码好像不正确呢
	USER_ORIGINAL_PASSWORD_NOT_CORRECT("原始密码错误"),//原始密码不正确
	//telphone
	USER_TEL_IS_BLANK("电话号码为空"),//电话是空号
	USER_Tel_FORM_ERROR("电话格式错误"),//电话格式错误
	//ID card
	USER_ID_NUMBER_IS_BLANK("身份证号码不能为空"),//
	USER_ID_FORMAT_ERROR("身份证号格式错误"),//
	//realName
	USER_REALNAME_IS_TOO_LONG("真实姓名过长"),//
	//Email
	USER_EMAIL_IS_BLANK("邮箱为空"),//
	USER_EMAIL_FORMAT_ERROR("邮箱格式错误"),//
	//address
	USER_ADDRESS_TOO_LONG("地址不能超过36位"),//
	
	/**
	 * ORDER error list
	 */
	//order title
	ORDER_TITLE_IS_BLANK("标题不能为空"),//标题不能为空
	ORDER_TITLE_TOO_LONG("标题过长"),
	//order degree
	ORDER_DEGREE_IS_BLANK("新旧程度为空"),//
	//order price
	ORDER_PRICE_IS_BLANK("价格为空"),
	//detail
	ORDER_DETAIL_IS_BLANK("详细描述为空"),
	ORDER_DETAIL_TOO_LONG("详细描述不应超过150字"),
	//item
	ORDER_ITEM_IS_BlANK("类目为空"),// 
	//order userId
	ORDER_NO_RIGHT_HANDEL("无权操作该条信息"),// = "当前登录人与订单创建人不一致";
	//order not exist
	ORDER_NOT_EXIST("查找的信息不存在")//
	;
	
	private String value;
	
	ErrorDesc(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}
