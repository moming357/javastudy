package reflect.apply.pojo;

import lombok.*;

/**
 * @Author: zhenL
 * @Description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
	
	private String orderId;
	private String userId;
	private String amount;
}
