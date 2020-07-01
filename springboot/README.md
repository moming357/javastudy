#### SpringBoot环境搭建
* 依赖配置(maven)
```xml
 <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>2.3.1.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <version>2.3.1.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
            <version>2.3.1.RELEASE</version>
        </dependency>

    </dependencies>

```

* 在resources目录下添加配置文件application.yml(非必须,不加用默认的)
```yml
server:
  port: 8081
```

* 填写主启动类
```java
// 关键注解，表示当前类是工程的启动类且会基于此类找到包,作为扫描的根
@SpringBootApplication
public class SpringExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExampleApplication.class,args);
    }
}

```
* 添加测试控制器
```java
@Slf4j
@RestController
public class PingController {

    @RequestMapping("/ping")
    public String ping(){
        log.info("ping start");
        return "pong";
    }
}
```
* 测试: http://127.0.0.1:8081/ping

#### SpringBoot与mybatis整合
* 测试sql
* 依赖添加
```xml
 <!-- https://mvnrepository.com/artifact/com.ruijc/spring-boot-starter-mybatis -->
        <dependency>
            <groupId>com.ruijc</groupId>
            <artifactId>spring-boot-starter-mybatis</artifactId>
            <version>3.2.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.14</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
            <version>2.3.1.RELEASE</version>
        </dependency>
```
* application.yml配置
```yaml
server:
  port: 8081

Spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://49.233.204.164/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456

mybatis:
  configuration:
    map-underscore-to-camel-case: true
```
* 编写主启动类
```java
@SpringBootApplication
// MapperScan配置mapper的扫描路径
@MapperScan("com.zhenL.mapper")
public class SpringExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExampleApplication.class,args);
    }
}
```
* 编写实体bean与mapper
```java
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TbUser {
    private Long id;
    private String userId;
    private String userName;
    public TbUser(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }
}
```

```java
@Mapper
public interface TbUserMapper {
    @Select("select * from `tb_user` where `user_id` = #{userId}")
    List<TbUser> selectByUserId(String userId);
}
```

*测试类
```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserMapper {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void test(){
        List<TbUser> users = tbUserMapper.selectByUserId("1000001");
        for (TbUser user : users) {
            System.out.println("user = " + user);
        }
    }
}
```

#### 老生常谈之MVC
* @Controller
    - 拦截器，过滤器的处理(安全，登陆。。。)
    - 参数校验（通用的参数校验，与业务关联不大）
        + amount（金额） ===》 金额大于0
        + orderId 通用校验，除了非空之外， 那么所有业务都需要长度必须为32位， 可以放在controller层来做处理。
        + 业务层校验，比如支付限制最小支付金额必须大于10块。  --> 放到业务层去处理
    - access log, cost time
    - 处理service代码时，在controller来做统一的异常处理
    - 做统一的流量收集。
    - 做统计，PV，UV
* @Service
    - 真正的处理业务逻辑，
    - 完成我们业务需求的绝大多数的能力都在这里做的。
    - 要做好业务异常的处理（订单状态不对，订单不存在，支付金额不足）
    - 做好业务拆分和设计，将各自的能力界定好边界。
    - 调外部服务。。。=> 支付宝|微信 ，
* @Repository
    - 简单： 数据库的增改查。没有删除

#### SpringBoot参数接受
* @RequestParam
```java
  /**
     * @RequestParam(value = "userId", required = true)
     * 设置参数必填相当于
     * if (Strings.isNullOrEmpty(userId)) {
     *     throw new IllegalArgumentException("userId should not be null");
     *        }
     */
    @RequestMapping("/getUser2")
    public List<TbUser> selectTbUser(@RequestParam(value = "userId", required = true)String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

    /**
     *@RequestParam(value = "userId", required = false,defaultValue = "1000002")
     * 允许为空，若传空，默认值为 "1000002"
     */
    @RequestMapping("/getUser3")
    public List<TbUser> selectUser(@RequestParam(value = "userId", required = false,defaultValue = "1000002")String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }
```
* @PathVariable
```java
 /**
     * 注意： @PathVariable("userId") 要与 @RequestMapping("getUser4/{userId}")的名称一致
     * 相当于在url里拼接参数
     * 访问地址：http://localhost:8081/user/getUser4/1000002
     */
    @RequestMapping("getUser4/{userId}")
    public List<TbUser> selectUser1(@PathVariable("userId")String userId){
        log.info(userId);
        return tbUserService.selectByUserId(userId);
    }

```
* @RequestBody & @ResponseBody 
```java

```

* 配置项获取
    - @Value
    - @ConfigurationProperties // 注: 需在加一个@Component来标识让Spring识别
    - 区别: 
        + 若只是个别的属性导入可以使用@value来指定
        + 若是要导入一个实体配置项则使用@ConfigurationProperties更加简单
    - @PropertySource
        + 用于读取指定的properties配置文件
        + ConfigurationProperties默认从application.yml中直接读取。文件会变得很臃肿
        + 可以配合PropertySource将配置项迁移至单独的配置文件中
        + 例:
            * @PropertySource(value = "classpath:db.properties")
            * @ConfigurationProperties(prefix = "db")
    - @ImportResource
        + 读取外部配置文件，一般用于将配置文件进行拆分后做的统一导入
        + 一般在主启动类或配置类上添加即可
        + 如: @ImportResource(locations = "classpath:xxx.xml")
* 添加依赖
```xml
 <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <version>2.3.1.RELEASE</version>
        </dependency>
```
*读取property文件
```java
@Component
@PropertySource("classpath:dbconfig.properties")
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
@ToString
public class DbConfigInfo {
    private String url;
    private String userName;
    private String passwd;
    private String driverClassName;
}
```
* controller层配置
```java
@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {
    
    @Resource
    private DbConfigInfo dbConfigInfo;

    @RequestMapping("/d1")
    public DbConfigInfo data3() {
        log.info("data 3 start. dbConfigInfo={}", dbConfigInfo);
        return dbConfigInfo;
    }
}
```
* active profile 配置
    - No active profile set, falling back to default profiles: default
    - 一般用于多套环境支持。如: application-dev.yml,application-test.yml
    - 指定profile启动
        + java -jar target/libs/springboot-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
* profile配置示例
```yaml
spring:
  profiles:
    active: test

---

spring:
  profiles: dev
server:
  port: 8081
  servlet:
    context-path: /dev

Spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456

mybatis:
  configuration:
    map-underscore-to-camel-case: true

---

spring:
  profiles: test
server:
  port: 8082
  servlet:
    context-path: /test

Spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1/mybatis?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
    username: root
    password: 123456

mybatis:
  configuration:
    map-underscore-to-camel-case: true

```
