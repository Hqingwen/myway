spring原理，spring优势
控制反转模式（也称作依赖性介入）：
不创建对象，但是描述创建它们的方式。在代码中不直接与对象和服务连接，但在配置文件中描述哪一个组件需要哪一项服务。
容器（在 Spring 框架中是 IOC 容器） 负责将这些联系在一起。

面向方面的编程，即AOP：
是一种编程技术，它允许程序员对横切关注点或横切典型的职责分界线的行为（例如日志和事务管理）进行模块化。
AOP 的核心构造是方面，它将那些影响多个类的行为封装到可重用的模块中。

spring优势
a.使用Spring的IOC容器，将对象之间的依赖关系交给Spring，降低组件之间的耦合性，让我们更专注于应用逻辑
b.可以提供众多服务，事务管理，WebService等。
c.AOP的很好支持，方便面向切面编程。
d.对主流的框架提供了很好的集成支持，如Hibernate,Struts2,JPA等
e.Spring DI机制降低了业务对象替换的复杂性。
f.Spring属于低侵入，代码污染极低。
g.Spring的高度可开放性，并不强制依赖于Spring，开发者可以自由选择Spring部分或全部

springMVC的工作原理：
1、客户端发出一个http请求给web服务器，web服务器对http请求进行解析，
如果匹配DispatcherServlet的请求映射路径（在web.xml中指定），web容器将请求转交给DispatcherServlet.
2、DipatcherServlet接收到这个请求之后将根据请求的信息（包括URL、Http方法、请求报文头和请求参数Cookie等）
以及HandlerMapping的配置找到处理请求的处理器（Handler）。
3-4、DispatcherServlet根据HandlerMapping找到对应的Handler,将处理权交给Handler（Handler将具体的处理进行封装），
再由具体的HandlerAdapter对Handler进行具体的调用。
5、Handler对数据处理完成以后将返回一个ModelAndView()对象给DispatcherServlet。
6、Handler返回的ModelAndView()只是一个逻辑视图并不是一个正式的视图，
DispatcherSevlet通过ViewResolver将逻辑视图转化为真正的视图View。
7、Dispatcher通过model解析出ModelAndView()中的参数进行解析最终展现出完整的view并返回给客户端。

重点：从一个url请求，怎么去找到最后执行的controller，描述这个技术实现过程或者实现的机制。


有2台服务的时候，syschronize能不能保证同步？
不能，因为是不同的JVM，如果需要同步，则需要分布式锁。
分布式锁可以看看，zookeeper,redis；
多台服务器需要引入缓存机制，比如redis,memcache的同步锁cas。
数据库锁：悲观锁或者乐观锁






CGLIB原理
JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，如何实现动态代理呢，这就需要CGLib了。
CGLib创建的动态代理对象性能比JDK创建的动态代理对象的性能高不少，
但是CGLib在创建代理对象时所花费的时间却比JDK多得多，
所以对于单例的对象，因为无需频繁创建对象，用CGLib合适，反之，使用JDK方式要更为合适一些。
同时，由于CGLib由于是采用动态创建子类的方法，对于final方法，无法进行代理。

二十一、写SQL：1、有一个表Employee，查一个公司里所有超过平均工资的员工。2、女性员工数大于五个人的部门。3、分页语句。
1、select m.name, m.salary
from EMPLOYEE m, (select avg(t.salary) salavg from EMPLOYEE t) n
where m.salary > n.salavg;

select n.dep from (select count(*)  numb,t.dep  from EMPLOYEE t where t.sex = 'f'  group by t.dep) n where n.numb>5;

SELECT *
FROM (SELECT ROWNUM AS rowno, t.*
FROM Employee t
WHERE 1 = 1
AND ROWNUM <= 10) table_alias
WHERE table_alias.rowno >= 5;

SELECT *
FROM (SELECT a.*, ROWNUM rn
FROM (SELECT *
FROM Employee) a
WHERE ROWNUM <= 10)
WHERE rn >= 5


二十二、
A.悲观锁：指在应用程序中显示的为数据资源加锁。尽管能防止丢失更新和不可重复读这类并发问题，但是它会影响并发性能，因此应该谨慎地使用。
B.乐观锁：乐观锁假定当前事务操作数据资源时，不回有其他事务同时访问该数据资源，因此完全依靠数据库的隔离级别来自动管理锁的工作。应用程序采用版本控制手段来避免可能出现的并发问题。


二十四、spring注入方式
接口注入（interface injection）
Set注入（setter injection）
构造注入（constructor injection）


二十六、动态代理实现都有哪些方式。

动态代理工具比较成熟的产品有：
JDK自带的，ASM，CGLIB(基于ASM包装)，JAVAASSIST
1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。
2. CGLIB次之，是JDK自带的两倍。
3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。
4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式)
(三) 差异原因：
各方案生成的字节码不一样，
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类，
所以生成的字节码非常大，而我们很多时候用不上这些，
而手工生成的字节码非常小，所以速度快，
具体的字节码对比，后面有贴出，可自行分析。

二十八、设计数据库表的三大范式
第一范式(确保每列保持原子性)
第二范式(确保表中的每列都和主键相关)
第三范式(确保每列都和主键列直接相关,而不是间接相关)

三十一、spring事务传播机制，里面有哪几个关键字。
REQUIRED:业务方法需要在一个容器里运行。如果方法运行时，已经处在一个事务中，那么加入到这个事务，否则自己新建一个新的事务。
NOT_SUPPORTED:声明方法不需要事务。如果方法没有关联到一个事务，容器不会为他开启事务，如果方法在一个事务中被调用，该事务会被挂起，调用结束后，原先的事务会恢复执行。
REQUIRESNEW:不管是否存在事务，该方法总汇为自己发起一个新的事务。如果方法已经运行在一个事务中，则原有事务挂起，新的事务被创建。
MANDATORY：该方法只能在一个已经存在的事务中执行，业务方法不能发起自己的事务。如果在没有事务的环境下被调用，容器抛出例外。
SUPPORTS:该方法在某个事务范围内被调用，则方法成为该事务的一部分。如果方法在该事务范围外被调用，该方法就在没有事务的环境下执行。
NEVER：该方法绝对不能在事务范围内执行。如果在就抛例外。只有该方法没有关联到任何事务，才正常执行。
NESTED:如果一个活动的事务存在，则运行在一个嵌套的事务中。如果没有活动事务，则按REQUIRED属性执行。它使用了一个单独的事务，这个事务拥有多个可以回滚的保存点。内部事务的回滚不会对外部事务造成影响。它只对DataSourceTransactionManager事务管理器起效。

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

三十二、事务的ACID属性&5种状态
事务的ACID属性：
1. 原子性（Atomicity）
原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。
2. 一致性（Consistency）
事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
3. 隔离性（Isolation）
事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
4. 持久性（Durability）
持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响。
5种状态：
1. 活动状态
事务在执行时的状态叫活动状态。
2. 部分提交状态
事务中最后一条语句被执行后的状态叫部分提交状态。
3. 失败状态
事务不能正常执行的状态叫失败状态。
4. 提交状态
事务在部分提交后，将往硬盘上写入数据，当最后一条信息写入后的状态叫提交状态。进入提交状态的事务就成功完成了。
5. 中止状态
事务回滚并且数据库已经恢复到事务开始执行前的状态叫中止状态。

三十九、 jsp的内置对象都有哪些
JSP中九大内置对象为: request、response、pageContext、session 、application、out 、config、page、exception


四十一、缓存的优势是什么，一般用在哪些方面，真能提高系统性能么
OSCache或者EHCache这种是JVM缓存，它不能在多个应用或者说不能被你的集群共享，这样就会造成内存的浪费，适用于不需要集群的小型应用。memcache 独立的缓存，可以被集群或者多个应用共享，适用于比较大一些的应用吧。


四十二、存储过程和函数的不同
本质上没区别。只是函数有如：只能返回一个变量的限制。而存储过程可以返回多个。而函数是可以嵌入在sql中使用的,可以在select中调用，而存储过程不行。执行的本质都一样。
函数限制比较多，比如不能用临时表，只能用表变量．还有一些函数都不可用等等．而存储过程的限制相对就比较少
1. 一般来说，存储过程实现的功能要复杂一点，而函数的实现的功能针对性比较强。
2. 对于存储过程来说可以返回参数，而函数只能返回值或者表对象。
3. 存储过程一般是作为一个独立的部分来执行，而函数可以作为查询语句的一个部分来调用，由于函数可以返回一个表对象，因此它可以在查询语句中位于FROM关键字的后面。
4. 当存储过程和函数被执行的时候，SQL Manager会到procedure cache中去取相应的查询语句，如果在



四十六、SpringMVC和Struts2比较
spring mvc的入口是servlet，而struts2是filter（这里要指出，filter和servlet是不同的。以前认为filter是servlet的一种特殊），这样就导致了二者的机制不同，这里就牵涉到servlet和filter的区别了。
springmvc是方法级别的拦截，一个方法对应一个request上下文，而方法同时又跟一个url对应,参数的传递是直接注入到方法中的，是该方法独有的。
struts2是类级别的拦截， 一个类对应一个request上下文， struts是在接受参数的时候，可以用属性来接受参数， 这就说明参数是让多个方法共享的,这也就无法用注解或其他方式标识其所属方法了

四十七、集群是怎么实现同步和负载均衡的？
负载均衡分硬件层和软件层两种。
可以了解下LVS，这是现在很流行的开源负载均衡软件。
LVS是Linux Virtual Server的简写，意即Linux虚拟服务器，是一个虚拟的服务器集群系统。
LVS属于四层负载均衡,工作在tcp/ip协议栈上,通过修改网络包的ip地址和端口来转发, 由于效率比七层高,一般放在架构的前端.
七层的负载均衡有nginx, haproxy, apache等, 工作在应用层,因此可以将HTTP请求等应用数据发送到具体的应用服务器,如将图片请求转发到特定的服务器上,总之可以做到更智能的负载均衡，这些功能在四层负载均衡上不好实现,一般放在架构的后面位置,布置在应用服务器前面.

synchronized 在集群环境不能起到同步控制的作用
如果需要解决并发问题
1.分布式锁
2.数据库锁：悲观锁或者乐观锁

并发控制，主要是控制共享资源
数据库锁是数据库自带的，比如悲观锁for update
分布式锁可以看看, zookeeper, redis，都能实现
既然你是集群，程序里的数据同步也只是在单台服务器，多台服务器需要引入缓存机制，比如redis,memcache的同步锁cas ，计数器。


五十四、jsp servlet的区别和联系
JSP在本质上就是SERVLET,但是两者的创建方式不一样.
Servlet完全是JAVA程序代码构成，擅长于流程控制和事务处理，通过Servlet来生成动态网页很不直观.
JSP由HTML代码和JSP标签构成，可以方便地编写动态网页.
因此在实际应用中采用Servlet来控制业务流程，而采用JSP来生成动态网页.
在struts框架中，JSP位于MVC设计模式的视图层，而Servlet位于控制层.

五十五、Get和Post区别
get和post这是http协议的两种方法，另外还有head, delete等
这两种方法有本质的区别，get只有一个流，参数附加在url后，大小个数有严格限制且只能是字符串。post的参数是通过另外的流传递的，不通过url，所以可以很大，也可以传递二进制数据，如文件的上传。
在servlet开发中，以doGet()和doPost()分别处理get和post方法。
另外还有一个doService(), 它是一个调度方法，当一个请求发生时，首先执行doService(),不管是get还是post。在HttpServlet这个基类中实现了一个角度， 首先判断是请求时get还是post,如果是get就调用doGet(), 如果是post就调用doPost()。你也可以直接过载doService()方法，这样你可以不管是get还是post。都会执行这个方法。

service()是在javax.servlet.Servlet接口中定义的, 在 javax.servlet.GenericServlet 中实现了这个接口, 而 doGet/doPost 则是在 javax.servlet.http.HttpServlet 中实现的, javax.servlet.http.HttpServlet 是 javax.servlet.GenericServlet 的子类. 所有可以这样理解, 其实所有的请求均首先由 service() 进行处理, 而在 javax.servlet.http.HttpServlet 的 service() 方法中, 主要做的事情就是判断请求类型是 Get 还是 Post, 然后调用对应的 doGet/doPost 执行.

重复访问使用GET方法请求的页面，浏览器会使用缓存处理后续请求。使用POST方法的form提交时，浏览器基于POST将产生永久改变的假设，将让用户进行提交确认。当编成人员正确的使用GET，POST后，浏览器会给出很好的缓存配合，时响应速度更快。

从使用经验，我们有如下总结：
1、get是吧参数数据队列加到提交表单的Action属性所指的url中，值和表单内各个字段--对应，在url中可以看到。
格式为“？字段1=输入数据1&字段2=输入素具2&。。”,再将其送到服务器。
如action为字段Name输入数据为jack,字段age的数据为15，则用get方法为“http://www.abc.com?Name=jack&Age=15”;Post是通过HTTP post机制，将表单内各个字段与其内容放置在HTML HEADER内一起传送到Action属性所指的URL地址，用户看不到这个过程。
2、Get是不安全的，因为在传输过程，数据被放在请求的URL上，而如今现有的很多服务器，代理服务器或者用户代理都会将请求URL记录到日志文件中，然后放在某个地方，这样就可能会有一些隐私的信息被第三方看到，另外，用户也可以再浏览器上直接看到提交的数据，一些系统内部消息将会显示在用户面前。Post的所有操作对用户来说都是不可见的。
3、Get传输的数据量小，这主要是因为受URL长度限制；而Post方式提交，所以在上传文件只能使用Post
4、Get方式的提交需要用Resquest.QueryString来取得变量的值，而Post方式提交时，必须通过Request.Form来访问提交的内容。
5、<form method="get" action="a.asp?b=b">跟<form method="get" action="a.asp">是一样的，也就是说，action页面后边带的参数列表会被忽视；而<form method="post" action="a.asp?b=b">跟<form method="post" action="a.asp">是不一样的。
另外
Get请求有如下特性：它会将数据添加到URL中，通过这种方式传递到服务器，通常利用一个问号？代表URL地址的结尾与数据参数的开端，后面的参数每一个数据参数以“名称=值”的形式出现，参数与参数之间利用一个连接符&来区分。
Post请求有如下特性：数据是放在HTTP主体中的，其组织方式不只一种，有&连接方式，也有分割符方式，可隐藏参数，传递大批数据，比较方便。



五十七、struts2和spring的结合原理
http://www.cnblogs.com/lraa/p/3544475.html
在Struts2中Action是我们扩展的点，我们使用Action处理用户提交的请求，向用户展现数据。
为了更好的组织代码的结构，我们一般情况下使用三层的构架：
Action → Logic → Dao 这样如果我们手动的管理这些对象之间的关系，以方便非常的繁琐，另外也很不利于管理 变更，所以我们更倾向于使用Spring等类似的IOC框架类管理。
2.如何将Springframework和Struts2结合起来 回想第一章，我们指导在Struts2中每一个对象都是靠ObjectFactory创建的，而Springframework
就是用来管理对象创佳的，我们只需要将ObjectFactory的对象创建方法改为Spring即可。

1）在struts.properties或者struts.xml文件中将objectfactory的属性设置为spring方式：
xml方式：
<constant name="struts.objectFactory" value="org.apache.struts2.spring.StrutsSpringObjectFactory" />
properties方式：
struts.objectFactory=org.apache.struts2.spring.StrutsSpringObjectFactory
2）当然还需要将需要的Jar文件放在WEB-INF/lib目录下
这些文件包括：
struts2-spring-plugin-2.0.9.jar
spring.jar（为了省事，包含了所有的spring模块，实际项目中可以考虑使用具体的模块）
3）剩余的就是Springframework的配置了
第一，告诉Spring，对象间关系的配置在那里存放
需要在web.xml文件中添加如下内容（注意顺序）：
这个Listener在应用程序启动的时候启动，用来读取配置文件。
<listener>
<listener-class>
org.springframework.web.context.ContextLoaderListener
</listener-class>
</listener>

这个参数配置了配置文件的路径，说明我们可以在classpath或者WEB-INF目录下
防止名字满足applicationContext-*.xml格式你的文件。
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>
/WEB-INF/applicationContext-*.xml,classpath*:applicationContext-*.xml
</param-value>
</context-param>

4）如何让Struts2的Action和Springframework的bean管理起来？
Struts2会根据struts.xml中配置的class名字在springframeowrk中找id相同的bean，如果找不到
就按照普通的方式实例化Action类。




六十、超过5千万或1亿的时候，应该怎么处理数据。
http://www.xuebuyuan.com/2190409.html
1、分区分表
2、读写分离

分区的好处
1) 增强可用性：如果表的某个分区出现故障，表在其他分区的数据仍然可用；
2) 维护方便：如果表的某个分区出现故障，需要修复数据，只修复该分区即可；
3) 均衡I/O：可以把不同的分区映射到磁盘以平衡I/O，改善整个系统性能；
4) 改善查询性能：对分区对象的查询可以仅搜索自己关心的分区，提高检索速度。

Oracle数据库提供对表或索引的分区方法有三种：
1) 范围分区
2) Hash分区（散列分区）
3) 复合分区

读写分离，基本的原理是让主数据库处理事务性增、改、删操作（INSERT、UPDATE、DELETE），而从数据库处理SELECT查询操作。数据库复制被用来把事务性操作导致的变更同步到集群中的从数据库。


六十二、spring注解
Spring 2.5 在 @Repository 的基础上增加了功能类似的额外三个注解：@Component、@Service、@Constroller，它们分别用于软件系统的不同层次：
@Component 是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
@Service 通常作用在业务层，但是目前该功能与 @Component 相同。
@Constroller 通常作用在控制层，但是目前该功能与 @Component 相同。

@Autowired 按 byType 自动注入
@Resource 默认按 byName 自动注入
@Resource 的作用相当于 @Autowired

@Repository 只能标注在 DAO 类上呢？这是因为该注解的作用不只是将类识别为 Bean，同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。 Spring 本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。

通过在类上使用 @Repository、@Component、@Service 和 @Constroller 注解，Spring 会自动创建相应的 BeanDefinition 对象，并注册到 ApplicationContext 中。这些类就成了 Spring 受管组件。这三个注解除了作用于不同软件层次的类，其使用方式与 @Repository 是完全相同的。

