package service;

import java.util.List;

import model.Order;

public interface OrderService {
	/**
	 * 增加一个order
	 */
	public Order addOrder(Order o);
	
	/**
	 * 查找具体的order
	 */
	public Order checkOrder(Order o);
	
	/**
	 * 得到所有的order 按照一定顺序进行排序
	 */
	public List<Order> getOrders();
	
	/**
	 * 得到某一类的order
	 */
	public List<Order> getOrders(Order o);
	
	/**
	 * 更新某个order
	 */
	public Order updateOrder(Order o);
	
	/**
	 * 删除某个order
	 */
	public void deleteOrder(Order o);

	
	
}
