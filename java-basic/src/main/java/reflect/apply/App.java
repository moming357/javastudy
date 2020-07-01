package reflect.apply;

/**
 * @Author: zhenL
 * @Description:
 */
public class App {
	public static void main(String[] args) throws Exception {
		
		CaseOneArgHandler.callCase("create_order", "case1.properties");
		CaseOneArgHandler.callCase("create_order", "case2.properties");
		CaseOneArgHandler.callCase("create_order", "case3.properties");
		CaseOneArgHandler.callCase("create_order", "case4.properties");
	}
}
