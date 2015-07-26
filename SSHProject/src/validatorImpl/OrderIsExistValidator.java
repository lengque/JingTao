package validatorImpl;

import model.Order;
import util.ErrorList;
import validator.BaseValidator;
import Exception.BaseException;

public class OrderIsExistValidator implements BaseValidator<Order>{
	/**
	 * 检查order的创建人与当前登录人是不是一致
	 */
	@Override
	public void validate(Order order,Order dbOrder) {
		if(null == dbOrder){
			throw new BaseException(ErrorList.Order_Not_Exist);
		}
	}
}
