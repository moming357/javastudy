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
public class Response {
	
	private String errorCode;
	
	private String errorMsg;
}
