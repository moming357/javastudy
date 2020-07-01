#### Spring能帮我们解决什么问题？
* 一种胶水，Java开发需要很多很多框架，如果每一个框架的引入都让我们去学习，想想都会令人崩溃
* 最重要的是，这种每个框架的引入学习本身是没啥太大意义的。真正有意义的是框架的使用与其实现
* 而Java作为一门免费的语言，其本身并没有去做过多的引入机制和约定。所以这也导致了，各种框架的引入真是千奇百怪
* 所以Spring用了一种极其优雅的方式帮我们做了这样的事情，慢慢有种海纳百川之势。
* 各大框架争相恐后与Spring看齐，形成了一种没有约定的事实上的标准

#### Spring环境搭建
##### XML配置版(iocx)
* 导入依赖
```xml
 <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-beans -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.2.7.RELEASE</version>
        </dependency>

    </dependencies>
```
* 编写接口与实现类

 * controller 
 ```java
public class IocxController {

    private IocxService iocxService;

    public String iocxFoo(String msg){
        System.out.println("controller start");
        return iocxService.iocxFoo(msg);
    }
    public void setIocxService(IocxService iocxService) {
        this.iocxService = iocxService;
    }
}
```
 * service
```java
public interface IocxService {

    String iocxFoo(String msg);
}

public class IocxServiceImpl implements IocxService {

    private IocxDao iocxDao;

    public void setIocxDao(IocxDao iocxDao) {
        this.iocxDao = iocxDao;
    }

    public String iocxFoo(String msg) {
        System.out.println("IocxServiceImpl init -----处理业务逻辑");
        return iocxDao.iocxFoo("iocxServiceImpl response");
    }
}
```
* dao
```java
public interface IocxDao {
    String iocxFoo(String msg);
}

public class IocxDaoImpl implements IocxDao {
    public String iocxFoo(String msg) {
        return "IocxDaoImpl response";
    }
}
```
* 编写beans.xml配置文件
```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="controller" class="com.zhenL.iocx.controller.IocxController">
        <property name="iocxService" ref="service"></property>
    </bean>
    <bean id="service" class="com.zhenL.iocx.service.impl.IocxServiceImpl">
        <property name="iocxDao" ref="dao"></property>
    </bean>
    <bean id="dao" class="com.zhenL.iocx.dao.impl.IocxDaoImpl">

    </bean>

</beans>
```
* 使用 scope配置实现多例和单例
  * beans.xml文件配置
```xml
    <bean id="controller" class="com.zhenL.iocx.controller.IocxController" scope="prototype">
        <property name="iocxService" ref="service"></property>
    </bean>
```
*  
  * 测试类
```java
 /**
     * 默认: scope="singleton"
     * 要实现多例: scope="prototype"
     */
    @Test
    public void testScope() {
        ApplicationContext context = new ClassPathXmlApplicationContext("iocx/beans.xml");

        for (int i = 0; i < 5; i++) {
            IocxController controller = context.getBean("controller", IocxController.class);
            System.out.println("controller = " + controller);
        }
    }
```
* 使用lazy-init配置懒加载
  * 什么时候使用什么时候加载，不使用时不加载
  * 默认非懒加载
  * 需要配置懒加载时,设置属性 lazy-init="true"
  
* xml文件中的set、list、map的注入
```xml
 <bean id="service" class="com.zhenL.iocx.service.impl.IocxServiceImpl">
        <property name="iocxDao" ref="dao"/>
        <!-- Set注入 -->
        <property name="infos">
            <set>
                <value>aaa</value>
                <value>bbb</value>
                <value>ccc</value>
            </set>
        </property>

        <!--  List注入 -->
        <property name="accounts">
            <list>
                <value>list1</value>
                <value>list2</value>
                <value>list3</value>
            </list>
        </property>

        <!-- Map注入 -->
        <property name="mapData">
            <map>
                <entry key="k1" value="v1"/>
                <entry key="k2" value="v2"/>
            </map>
        </property>
    </bean>
```
* 对应iocxServiceImpl修改
```java
public class IocxServiceImpl implements IocxService {

    private Set<String> infos;

    private List<String> accounts;

    private Map<String, String> mapData;

    private IocxDao iocxDao;


    public String iocxFoo(String msg) {
        System.out.println("IocxServiceImpl init -----处理业务逻辑");
        System.out.println("infos = " + infos);
        System.out.println("accounts = " + accounts);
        System.out.println("mapData = " + mapData);
        return iocxDao.iocxFoo("iocxServiceImpl response");
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    public void setInfos(Set<String> infos) {
        this.infos = infos;
    }

    public void setIocxDao(IocxDao iocxDao) {
        this.iocxDao = iocxDao;
    }

}
```
##### 使用xml形式配置aop
* 切面类编写
```java
public class PrintLogHandler {

  /**
   * 入口log,通知: 前置通知
   */
  public void preLog() {
    System.out.println("before log start");
  }

  /**
   * 出口log
   */
  public void postLog() {
    System.out.println("after log start");
  }

  /**
   * 异常通知
   */
  public void errLog() {
    System.out.println("哈哈 出异常啦");
  }

  /**
   * 最终通知
   */
  public void finalLog() {
    System.out.println("最终通知");
  }

  /**
   * 环绕通知
   */
  public Object aroundTest(ProceedingJoinPoint joinPoint) {
    try {
      System.out.println("around before log");

      Object response = joinPoint.proceed();

      System.out.println("around after log");

      return response;
    } catch (Throwable t) {
      System.out.println("around exception log");
    } finally {
      System.out.println("around finally log");
    }

    return null;
  }

}
```
* 配置类编写
```xml
 <!-- 开始aop配置 -->
    <aop:config>

        <!-- 切面 -->
        <aop:aspect ref="logHandler">

            <!-- 切入点 -->
            <aop:pointcut id="pp"
                          expression="execution(* com.zhenL.aopx.service.impl.*.*(..))"/>

            <!-- 前置通知 -->
            <aop:before method="preLog" pointcut-ref="pp"/>

            <!-- 后置通知-->
            <aop:after-returning method="postLog" pointcut-ref="pp"/>

            <!-- 异常通知 -->
            <aop:after-throwing method="errLog" pointcut-ref="pp"/>

            <!-- 最终通知-->
            <aop:after method="finalLog" pointcut-ref="pp"/>

            <!-- 环绕通知,相当于上面几种通知的结合体-->
            <!-- <aop:around method="aroundTest" pointcut-ref="pp"/>-->
        </aop:aspect>
    </aop:config>
```
##### 注解版spring环境搭建
* 配置类
```java
// 相当于beans.xml
@Configuration
// <context:component-scan base-package="com.zhenL.ioca"/>
@ComponentScan(basePackages = "com.zhenL.ioca")
public class IocaConfiguration {
}
```
* controller层
```java
@Controller
public class IocaController {

    @Autowired
    private IocaService iocxService;

    public String iocxFoo(String msg){
        System.out.println("controller start");
        return iocxService.iocaFoo(msg);
    }
}
```
* service层
```java
public interface IocaService {

    String iocaFoo(String msg);
}

@Service
public class IocaServiceImpl implements IocaService {

    @Autowired
    private IocaDao iocaDao;

    @Override
    public String iocaFoo(String msg) {
        System.out.println("iocxServiceImpl start");
        return iocaDao.iocaFoo("iocxServiceImpl response");
    }
}
```
* 持久层
```java
public interface IocaDao {
    String iocaFoo(String msg);
}

@Repository
public class IocaDaoImpl implements IocaDao {
    public String iocaFoo(String msg) {
        System.out.println("IocaDaoImpl start");
        return "IocxDaoImpl response";
    }
}
```
* 测试类
```java
@Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(IocaConfiguration.class);
        IocaController controller = context.getBean(IocaController.class);
        controller.iocxFoo("hello");
    }
```
