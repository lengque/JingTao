package controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import service.OrderService;

import com.opensymphony.xwork2.ActionSupport;

import converter.OrderConvert;
import model.Order;
import model.OrderDTO;

public class OrderController extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private OrderConvert orderConvert;
	private OrderService orderService;

	/**
	 * 新增一个order
	 */
	public String addNewOrder(OrderDTO orderDto) {
		// adaptor OrderDTO to order
		Order order = orderConvert.addOrderConverter(orderDto);

		// 对order进行存储
		orderService.addOrder(order);

		return SUCCESS;
	}

	/**
	 * 检索所有的order
	 */
	public String checkOrders() {

		// 检索所有的order
		List<Order> orders = orderService.getOrders();
		
		// 对orders进行反转换并存储到session中
		
		return SUCCESS;
	}

	/**
	 * 检索某一类order
	 */
	public String checkOrders(OrderDTO orderDto) {
		// adaptor OrderDTO to order
		Order order = new Order();
		// = orderConvert.addOrderConverter(orderDto);

		// 对此类orderorder进行检索
		List<Order> orders = orderService.getOrders(order);

		// 对orders进行反转换并存储到session中

		return SUCCESS;
	}

	/**
	 * 检索某个具体的order
	 */
	public String checkOrder(OrderDTO orderDto) {
		// adaptor OrderDTO to order
		Order order = new Order();// = orderConvert.addOrderConverter(orderDto);

		// 对order进行存储
		order = orderService.checkOrder(order);

		// 对order进行反转换并存储到session中

		return SUCCESS;
	}

	/**
	 *更新某个Order
	 */
	public String updateOrder(OrderDTO orderDto) {
		// adaptor OrderDTO to order
		Order order = new Order();// = orderConvert.addOrderConverter(orderDto);

		// 对order进行更新
		order = orderService.updateOrder(order);

		// 对order进行反转换并存储到session中

		return SUCCESS;
	}
	/**
	 * 删除某个order
	 */
	/**
	 *更新某个Order
	 */
	public String deleteOrder(OrderDTO orderDto) {
		// adaptor OrderDTO to order
		Order order = new Order();// = orderConvert.addOrderConverter(orderDto);

		// 删除 order
		orderService.deleteOrder(order);

		return SUCCESS;
	}
	
	/**
	 * 注入session
	 */
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * 注入OrderConvert
	 */
	public void setOrderConvert(OrderConvert orderConvert) {
		this.orderConvert = orderConvert;
	}

	/**
	 * 注入OrderService
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
}
