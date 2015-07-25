package daoImpl;

import java.util.List;

import model.Order;
import dao.BaseDao;

public class OrderDao {
	BaseDao<Order> baseDao;

	public BaseDao<Order> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<Order> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 获取 order list
	 */
	public List<Order> getOrders(Order order) {

		List<Order> orders = baseDao.list(order);

		return orders;
	}

	/**
	 * 新增一个order
	 */
	public Order addNewOrder(Order order) {
		order = baseDao.saveObject(order);

		return order;
	}

	/**
	 * 删除一个order
	 */
	public void deleteOrder(Order order) {
		
		baseDao.deleteObject(order);
	}

	/**
	 * 批量删除order
	 */
	public void batchDeleteOrder(List<Order> orderList) {
		baseDao.deleteAll(orderList);
	}

	/**
	 * 查找order
	 */
	public Order findOrder(Order order) {
		return baseDao.checkObject(order);
	}
	
	/**
	 * 查找order
	 */
	public Order findOrderById(String orderId) {
		Order order = new Order();
		order.setOrderId(orderId);
		
		return baseDao.checkObject(order);
	}

	/**
	 * 更新order
	 */
	public Order updateOrder(Order order) {
		order = baseDao.updateObject(order);

		return order;
	}
}
