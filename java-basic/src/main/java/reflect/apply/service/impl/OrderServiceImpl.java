package reflect.apply.service.impl;

import collection.User;
import reflect.apply.pojo.Order;
import reflect.apply.pojo.Response;
import reflect.apply.service.OrderService;

/**
 * @Author: zhenL
 * @Description:
 */
public class OrderServiceImpl implements OrderService {
	
	@Override
	public Response createOrder(Order order) {
		System.out.println("createOrder execute..." + order);
		return new Response("200", "success");
	}
	
}
