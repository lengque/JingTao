package errorUtil;

public enum OrderErrorList implements ErrorList{
	/**
	 * ORDER error list
	 */
	//order title
	ORDER_TITLE_IS_BLANK("ORD001"),//标题不能为空
	ORDER_TITLE_TOO_LONG("ORD002"),
	//order degree
	ORDER_DEGREE_IS_BLANK("ORD003"),//
	ORDER_DEGREE_IS_NOT_CORRECT("ORD004"),//
	//order price
	ORDER_PRICE_IS_BLANK("ORD005"),
	//detail
	ORDER_DETAIL_IS_BLANK("ORD006"),
	ORDER_DETAIL_TOO_LONG("ORD007"),
	//item
	ORDER_ITEM_IS_BlANK("ORD008"),// 
	//order userId
	ORDER_NO_RIGHT_HANDEL("ORD009"),// = "当前登录人与订单创建人不一致";
	//order not exist
	ORDER_NOT_EXIST("ORD010")//
	;
	
	private String value;
	
	OrderErrorList(String value){
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}
}
