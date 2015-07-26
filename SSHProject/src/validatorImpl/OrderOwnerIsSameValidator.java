package validatorImpl;

import org.apache.commons.lang.StringUtils;

import model.Order;
import util.ErrorList;
import validator.BaseValidator;
import Exception.BaseException;

public class OrderOwnerIsSameValidator implements BaseValidator<Order>{
	/**
	 * 检查order的创建人与当前登录人是不是一致
	 */
	@Override
	public void validate(Order order,Order dbOrder) {
		String currUserId = order.getOrderId();
		String dbUserId = dbOrder.getOrderId();
		
		if(!StringUtils.equals(currUserId, dbUserId)){
			throw new BaseException(ErrorList.UserId_Is_Not_Same);
		}
	}
}
