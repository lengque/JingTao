package serviceImpl;

import java.util.List;

import daoImpl.OrderDao;
import model.Order;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
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
		orderDao.deleteOrder(order);
	}

	@Override
	public Order updateOrder(Order order) {
		order = orderDao.updateOrder(order);
		
		return order;
	}

}
