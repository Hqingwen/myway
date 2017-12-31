[TOC]

##一、微服务构建：Sping Boot

####1 多环境配置
*application.properties*
```xml
<!--当前环境-->
spring-profiles-active=dev
```

*application-{profile}.properties*
> application-dev.properties
> application-test.properties
> application-online.properties

####2 属性加载顺序
*优先级由高到低*
>1. 在命令行中传入的参数。
>2. SPRING_APPLICATION_JSON中的属性。
>3. java:comp/env中的JNDI属性。
>4. Java的系统属性，可以通过System.getProperties()获得的内容。
>5. 操作系统的环境变量。
>6. 通过random.*配置的随机属性。
>7. 位于当前应用jar包之外，针对不用{profile}环境的配置文件内容，例如application-{profile}.properties或是YAML定义的配置文件。
>8. 位于当前引用jar包之内，针对不用{profile}环境的配置文件内容，例如application-{profile}.properties或是YAML定义的配置文件。
>9. 位于当前应用jar包之外的application-{profile}.properties或是YAML定义的配置文件。
>10. 位于当前应用jar包之内的application-{profile}.properties或是YAML定义的配置文件。
>11. 在@Configuration注解修改的类中，通过@PropertySource注解定义的属性。
>12. 应用默认属性，使用SpringApplication.setDefaultProperties定义的内容。

## 二、服务治理：Spring Cloud Eureka

#### 1 服务注册中心
*以下是基于IEDA的示例*
>1. Create New Project -> Spring Initializr
>2. 填写Project Metadata
>3. Dependencies -> Cloud Discovery -> Eureka Server
>4. 填写 Project name
>5. Finish
>6. 在springboot工程的启动application类上添加@EnableEurekaServer，详见`代码1.1`
>7. 修改appication.yml，详见`代码1.2`
>8. 运行，查看 http://localhost:8080

```java
//代码1.1
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaApplication.class, args);
	}
}
```
```yaml
#代码1.2
server:
	port: 8080

eureka:
	instance:
		hostname: localhost
		#当hostnam为IP地址时
		#prefer-ip-address: true
	client:
		#以下两句设置该server不注册
		registerWithEureka: false
		fetchRegistry: false
		serviceUrl:
			defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

####2  注册服务提供者
*以下是基于IEDA的示例*
>1. Create New Project -> Spring Initializr
>2. 填写Project Metadata
>3. Dependencies -> Cloud Discovery -> Eureka Discovery
>4. 填写 Project name
>5. Finish
>6. 在springboot工程的启动application类上添加@EnableDiscoveryClient，详见`代码2.1`
>7. 修改appication.yml，详见`代码2.2`
>8. 运行，查看 http://localhost:8180

```java
//代码2.1
@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringCloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaClientApplication.class, args);
	}

	@Value("${server.port}")
	String port;
	@RequestMapping("/hello")
	public String home(@RequestParam String name){
		return "hi "+name+",i am from port:" +port;
	}
}
```
```yaml
#代码2.2
server:
  port: 8180

spring:
  application:
    name: service-hi

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8080/eureka/
```

####3 高可用注册中心
> Eureka server的高可用其实就是将自己作为服务向其他服务注册中心注册自己，即形成一组相互注册的服务注册中心，以实现服务清单的相互同步，达到高可用效果。

*以`服务注册`章节为例，创建两个Eureka Server，并修改`代码1.2`，构建一个双节点的服务注册中心集群*
```yaml
#代码3.1
server:
	port: 8081

eureka:
	instance:
		hostname: localhost1
	client:
		serviceUrl:
			defaultZone: http://localhost2:8082/eureka/
```

```yaml
#代码3.2
server:
	port: 8082

eureka:
	instance:
		hostname: localhost2
	client:
		serviceUrl:
			defaultZone: http://localhost1:8081/eureka/
```

*服务提供方需修改`代码2.2`如下*
```yaml
#代码2.2
server:
  port: 8180

spring:
  application:
    name: service-hi

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost1:8180/eureka/,http://localhost2:8280/eureka/
```

####4 服务发现与消费
*Ribbon是一个基于Http和TCP的客户端负载均衡器，它可以通过在客户端中配置的ribbonServerList服务端列表取轮询访问以达到均衡负载的作用Ribbon在Eureka服务发现的基础上，实现了一套对服务实例的选择策略，从而实现对服务的消费。*

*以下是基于IEDA的示例*
>1. Create New Project -> Spring Initializr
>2. 填写Project Metadata
>3. Dependencies -> Cloud Discovery -> Eureka Discovery
	>Dependencies -> Cloud Routing -> Ribbon
>4. 填写 Project name
>5. Finish
>6. 在springboot工程的启动application类上添加@EnableDiscoveryClient，详见`代码4.1`
>7. 添加一个Controller类，详见代码`代码4.2`
>8. 修改appication.yml，详见`代码4.3`
>9. 运行：
>运行一个服务注册中心或集群
>运行多个服务提供者
>运行此实例作为消费者
>查看 http://localhost:8480

```java
//代码4.1
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudRibbonServiceApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibbonServiceApplication.class, args);
	}
}
```

```java
//代码4.2
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://service-hi/hello?name=aa",String.class).getBody();
    }
}
```

```yaml
#代码4.3
server:
    port: 8280

spring:
    application:
        name: ribbon-consumer

eureka:
    client:
        serviceUrl:
            defaultZone: http://localhost:8080/eureka/
```

####5 服务治理体制
1. 服务提供者
* 服务注册
* 服务同步
* 服务续约
2. 服务消费者
* 获取服务
* 服务调用
* 服务下线
3. 服务注册中心
* 失效剔除
* 自我保护
