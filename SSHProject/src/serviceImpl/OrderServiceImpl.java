package serviceImpl;

import java.util.List;

import daoImpl.OrderDao;
import model.Order;
import service.OrderService;
import validator.BaseValidator;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;
	
	private List<BaseValidator<Order>>	updateOrderValidators;
	private List<BaseValidator<Order>>	deleteOrderValidators;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	/**
	 * init validators for update method
	 */
	public void setUpdateOrderValidators(List<BaseValidator<Order>> updateOrderValidators) {
		this.updateOrderValidators = updateOrderValidators;
	}
	
	/**
	 * init validators for delete method
	 */
	public void setDeleteOrderValidators(List<BaseValidator<Order>> deleteOrderValidators) {
		this.deleteOrderValidators = deleteOrderValidators;
	}

	@Override
	public Order addOrder(Order order) {
		order = orderDao.addNewOrder(order);

		return order;
	}

	@Override
	public Order checkOrder(Order order) {

		order = orderDao.findOrder(order);

		return order;
	}

	@Override
	public List<Order> getOrders() {
		List<Order> orders = orderDao.getOrders(new Order());

		return orders;
	}

	@Override
	public List<Order> getOrders(Order order) {
		List<Order> orders = orderDao.getOrders(order);

		return orders;
	}

	@Override
	public void deleteOrder(Order order) {
		//首先从数据库中查出
		Order dbOrder = orderDao.findOrderById(order.getOrderId());
		//validate the user
		for(BaseValidator<Order> validator : deleteOrderValidators ){
			validator.validate(order,dbOrder);
		}
		
		orderDao.deleteOrder(dbOrder);
	}

	@Override
	public Order updateOrder(Order order) {
		//首先从数据库中查出
		Order dbOrder = orderDao.findOrderById(order.getOrderId());
		
		//validate the order
		for(BaseValidator<Order> validator : updateOrderValidators ){
			validator.validate(order,dbOrder);
		}
		
		order = orderDao.updateOrder(order);
		
		return order;
	}
}
