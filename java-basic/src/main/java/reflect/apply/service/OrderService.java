package reflect.apply.service;

import reflect.apply.pojo.Order;
import reflect.apply.pojo.Response;

/**
 * @Author: zhenL
 * @Description:
 */
public interface OrderService {
	Response createOrder(Order order);
}
