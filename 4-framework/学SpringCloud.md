[TOC]

##一、微服务构建：Sping Boot

####  多环境配置
*application.properties*
>```
><!--当前环境-->
>spring-profiles-active=dev
>```

*application-{profile}.properties*
> application-dev.properties
> application-test.properties
> application-online.properties

#### 属性加载顺序
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

#### 服务注册
*以下是基于IEDA的示例*
>1. Create New Project -> Spring Initializr
>2. 填写Project Metadata
>3. Dependencies -> Cloud Discovery -> Eureka Server
>4. 填写 Project name
>5. Finish
>6. 在springboot工程的启动application类上添加@EnableEurekaServer
>7. 修改appication.yml，详见`代码1`
>8.
```
#代码1
server:
	port: 8080
eureka:
	instance:
		hostname: localhost
client:
	registerWithEureka: false
	fetchRegistry: false
	serviceUrl:
	defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
#### 服务发现






