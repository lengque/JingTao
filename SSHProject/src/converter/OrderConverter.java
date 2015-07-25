package converter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import util.ErrorList;
import Exception.BaseException;
import model.Order;
import model.OrderDTO;
import model.User;

public class OrderConverter {
	/**
	 * 将前台OrderDto对象数据放到User中
	 * 1.对orderDto中的信息进行检查
	 * 2.生成一个order
	 */
	public Order addOrderConverter(OrderDTO orderDto,Map<String, Object> session) {
		
		User user = (User) session.get("user");
		Order order = new Order();
		
		if(null != user){
			String userId = user.getUserId();
			//userId
			order.setUserId(userId);
			
			//ItemId
			String itemId = orderDto.getItemId();
			if(StringUtils.isBlank(itemId)){
				throw new BaseException(ErrorList.Item_Is_Blank);
			}
			order.setItemId(itemId);
			
			//title  
			String title = orderDto.getTitle().trim();
			if(StringUtils.isBlank(title)){
				throw new BaseException(ErrorList.Title_Is_Blank);
			}
			if(title.length()>15){
				throw new BaseException(ErrorList.Title_Is_Too_long);
			}
			order.setTitle(title);
			
			//degree
			Integer degree = orderDto.getDegree();
			if(null == degree){
				throw new BaseException(ErrorList.Degree_Is_Blank);
			}
			if(degree.intValue()>10 || degree.intValue()<1){
				throw new BaseException(ErrorList.Degree_Is_Not_Correct);
			}
			order.setDegree(degree);
			
			//price
			Double price = orderDto.getPrice();
			if(null == price){
				throw new BaseException(ErrorList.Price_Is_Blank);
			}
			order.setPrice(price);
			
			String detail = orderDto.getDetail();
			if(StringUtils.isBlank(detail)){
				throw new BaseException(ErrorList.Detail_Is_Blank);
			}
			if(detail.length()>200){
				throw new BaseException(ErrorList.Detail_Is_Too_Long);
			}
			order.setDetail(detail);
			
			//image
			String image = orderDto.getImage();
			order.setImage(image);
			//create time
			order.setCreateTime(new Timestamp(System.currentTimeMillis()));
			//update time
			order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		}
		
		return order;
	}
	
	//将批量的order转换为orderDto
	public List<OrderDTO> OrderConverterOrderDto(List<Order> orders) {
		List<OrderDTO> orderDtos = new ArrayList<OrderDTO>();
		if(null != orders && orders.size()>0){
			for(Order o : orders){
				OrderDTO orderDto = adaptor(o);
				orderDtos.add(orderDto);
			}
		}
		
		return orderDtos;
	}
	
	//这里是不加验证的将Order 转换为OrderDTO
	public OrderDTO adaptor(Order order){
		OrderDTO orderDto = new OrderDTO();
		
		orderDto.setDegree(order.getDegree());
		orderDto.setDetail(order.getDetail());
		orderDto.setImage(order.getImage());
		orderDto.setItemId(order.getItemId());
		orderDto.setPrice(order.getPrice());
		orderDto.setTitle(order.getTitle());
		orderDto.setCreateTime(order.getCreateTime());
		orderDto.setUpdateTime(order.getUpdateTime());
		
		return orderDto;
	}
	
	/**
	 * 将前台OrderDto对象数据放到User中
	 * 1.对orderDto中的信息进行检查
	 * 2.生成一个order
	 */
	public Order updateOrderConverter(OrderDTO orderDto,Map<String, Object> session) {
		Order order = new Order();
		String orderId = orderDto.getOrderId();
		
		if(StringUtils.isNotBlank(orderId)){
			order.setOrderId(orderId);
			
			User user = (User) session.get("user");
			if(null != user){
				String userId = user.getUserId();
				//userId
				order.setUserId(userId);
				
				//ItemId
				String itemId = orderDto.getItemId();
				if(StringUtils.isNotBlank(itemId)){
					order.setItemId(itemId);
				}
				
				//title  
				String title = orderDto.getTitle().trim();
				if(StringUtils.isBlank(title)){
					throw new BaseException(ErrorList.Title_Is_Blank);
				}
				if(title.length()>15){
					throw new BaseException(ErrorList.Title_Is_Too_long);
				}
				order.setTitle(title);
				
				//degree
				Integer degree = orderDto.getDegree();
				if(null == degree){
					throw new BaseException(ErrorList.Degree_Is_Blank);
				}
				if(degree.intValue()>10 || degree.intValue()<1){
					throw new BaseException(ErrorList.Degree_Is_Not_Correct);
				}
				order.setDegree(degree);
				
				//price
				Double price = orderDto.getPrice();
				if(null == price){
					throw new BaseException(ErrorList.Price_Is_Blank);
				}
				order.setPrice(price);
				
				String detail = orderDto.getDetail();
				if(StringUtils.isBlank(detail)){
					throw new BaseException(ErrorList.Detail_Is_Blank);
				}
				if(detail.length()>200){
					throw new BaseException(ErrorList.Detail_Is_Too_Long);
				}
				order.setDetail(detail);
				
				//image
				String image = orderDto.getImage();
				order.setImage(image);

				//update time
				order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			}
		}
		
		return order;
	}
	
	public Order deleteOrderConverter(OrderDTO orderDto,Map<String, Object> session) {
		Order order = new Order();
		String orderId = orderDto.getOrderId();
		
		if(StringUtils.isNotBlank(orderId)){
			order.setOrderId(orderId);
			
			User user = (User) session.get("user");
			if(null != user){
				String userId = user.getUserId();
				//userId
				order.setUserId(userId);
			}
		}
		
		return order;
	}
}
