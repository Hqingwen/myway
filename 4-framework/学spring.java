Q：spring原理，spring优势？
控制反转模式（也称作依赖性介入）：
	不创建对象，但是描述创建它们的方式。
	在代码中不直接与对象和服务连接，但在配置文件中描述哪一个组件需要哪一项服务。
	容器（在 Spring 框架中是 IOC 容器） 负责将这些联系在一起。

面向方面的编程，即AOP：
	是一种编程技术，它允许程序员对横切关注点或横切典型的职责分界线的行为（例如日志和事务管理）进行模块化。
	AOP 的核心构造是方面，它将那些影响多个类的行为封装到可重用的模块中。

优势：
1、使用Spring的IOC容器，将对象之间的依赖关系交给Spring，降低组件之间的耦合性，让我们更专注于应用逻辑
2、可以提供众多服务，事务管理，WebService 等。
3、AOP 的很好支持，方便面向切面编程。
4、对主流的框架提供了很好的集成支持，如 Hibernate, Struts2 , JPA 等
5、Spring DI 机制降低了业务对象替换的复杂性。
6、Spring 属于低侵入，代码污染极低。
7、Spring 的高度可开放性，并不强制依赖于Spring，开发者可以自由选择Spring部分或全部


Q：springMVC的工作原理：
1、客户端发出一个http请求给web服务器，web服务器对http请求进行解析，
	如果匹配DispatcherServlet的请求映射路径（在web.xml中指定），web容器将请求转交给DispatcherServlet.
2、DipatcherServlet 接收到这个请求之后将根据请求的信息（包括URL、Http方法、请求报文头和请求参数Cookie等）
	以及HandlerMapping的配置找到处理请求的处理器（Handler）。
3-4、DispatcherServlet 根据 HandlerMapping 找到对应的 Handler,将处理权交给Handler（Handler将具体的处理进行封装），
	再由具体的 HandlerAdapter 对 Handler 进行具体的调用。
5、Handler 对数据处理完成以后将返回一个 ModelAndView() 对象给DispatcherServlet。
6、Handler 返回的 ModelAndView() 只是一个逻辑视图并不是一个正式的视图，
	DispatcherSevlet 通过 ViewResolver 将逻辑视图转化为真正的视图 View。
7、Dispatcher 通过model解析出 ModelAndView() 中的参数进行解析最终展现出完整的view并返回给客户端。

重点：从一个url请求，怎么去找到最后执行的controller，描述这个技术实现过程或者实现的机制。


Q：spring注入方式
1、接口注入
2、Set注入
3、构造注入


Q：事务的ACID属性和5种状态？
事务的ACID属性：
	1. 原子性（Atomicity）：指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
	2. 一致性（Consistency）：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
	3. 隔离性（Isolation）：指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
	4. 持久性（Durability）：指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响。
5种状态：
	1. 活动状态：事务在执行时的状态叫活动状态。
	2. 部分提交状态：事务中最后一条语句被执行后的状态叫部分提交状态。
	3. 失败状态：事务不能正常执行的状态叫失败状态。
	4. 提交状态：事务在部分提交后，将往硬盘上写入数据，当最后一条信息写入后的状态叫提交状态。进入提交状态的事务就成功完成了。
	5. 中止状态：事务回滚并且数据库已经恢复到事务开始执行前的状态叫中止状态。


Q：spring事务传播机制，里面有哪几个关键字。
REQUIRED：业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
NOT_SUPPORTED：声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行。
REQUIRESNEW：不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外。
SUPPORTS：该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行。
NESTED：如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行。它使用了一个单独的事务，这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。它只对DataSourceTransactionManager事务管理器起效。

ISOLATION_DEFAULT：使用数据库默认的隔离级别。
ISOLATION_READ_UNCOMMITTED：允许读取改变了的还未提交的数据，可能导致脏读、不可重复读和幻读。
ISOLATION_READ COMMITTED：允许并发事务提交之后读取，可以避免脏读，可能导致重复读和幻读。
ISOLATION_REPEATABLE_READ：对相同字段的多次读取结果一致，可导致幻读。
ISOLATION_SERIALIZABLE：完全服从ACID的原则，确保不发生脏读、不可重复读和幻读。

Dirty reads                  non-repeatable         reads                phantom reads
Serializable                         不会                   不会                         不会
REPEATABLE READ             不会                   不会                         会
READ COMMITTED             不会                    会                           会
Read Uncommitted            会                        会                           会


Q：spring注解
Spring 2.5 在 @Repository 的基础上增加了功能类似的额外三个注解，它们分别用于软件系统的不同层次：
@Component 是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Constroller 通常作用在控制层，但是目前该功能与 @Component 相同。

@Autowired 按 byType 自动注入
@Resource 默认按 byName 自动注入
@Resource 的作用相当于 @Autowired

@Repository 只能标注在 DAO 类上呢？
这是因为该注解的作用不只是将类识别为 Bean，同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。 
Spring 本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，
用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。

通过在类上使用 @Repository、@Component、@Service 和 @Constroller 注解，
Spring 会自动创建相应的 BeanDefinition 对象，并注册到 ApplicationContext 中。这些类就成了 Spring 受管组件。
这三个注解除了作用于不同软件层次的类，其使用方式与 @Repository 是完全相同的。














































































