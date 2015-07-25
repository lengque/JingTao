package validator;

import model.Order;
import model.User;

public interface BaseValidator<T> {
   /**
	 * <p>validate</p>
	*/
   public void validate(T t,T dbT);
}
