
Q：jsp的内置对象都有哪些？
request
response
pageContext
session 
application
out 
config
page
exception


Q：Get 和 Post 区别？
这是http协议方法：get、post、head、 delete等
get只有一个流，参数附加在url后，大小个数有严格限制且只能是字符串。
post的参数是通过另外的流传递的，不通过url，所以可以很大，也可以传递二进制数据，如文件的上传。

在servlet开发中，以doGet()和doPost()分别处理get和post方法。
另外还有一个doService(), 它是一个调度方法，当一个请求发生时，首先执行doService(),不管是get还是post。
在HttpServlet这个基类中实现了一个角度， 首先判断是请求时get还是post,
如果是get就调用doGet(), 如果是post就调用doPost()。
你也可以直接过载doService()方法，这样你可以不管是get还是post。都会执行这个方法。

service()是在javax.servlet.Servlet接口中定义的, 在 javax.servlet.GenericServlet 中实现了这个接口, 
而 doGet/doPost 则是在 javax.servlet.http.HttpServlet 中实现的, 
javax.servlet.http.HttpServlet 是 javax.servlet.GenericServlet 的子类. 
所有可以这样理解, 其实所有的请求均首先由 service() 进行处理, 
而在 javax.servlet.http.HttpServlet 的 service() 方法中, 主要做的事情就是判断请求类型是 Get 还是 Post, 
然后调用对应的 doGet/doPost 执行.

重复访问使用GET方法请求的页面，浏览器会使用缓存处理后续请求。
使用POST方法的form提交时，浏览器基于POST将产生永久改变的假设，将让用户进行提交确认。
当编成人员正确的使用 GET，POST 后，浏览器会给出很好的缓存配合，时响应速度更快。

从使用经验，我们有如下总结：
1、get是把参数数据队列加到提交表单的Action属性所指的url中，值和表单内各个字段一一对应，在url中可以看到。
	格式为“？字段1=输入数据1&字段2=输入素具2&。。”,再将其送到服务器。
	Post 是通过HTTP post机制，将表单内各个字段与其内容放置在 HTML HEADER 内一起传送到Action属性所指的URL地址，用户看不到这个过程。
2、Get 是不安全的，因为在传输过程，数据被放在请求的URL上，
	而如今现有的很多服务器，代理服务器或者用户代理都会将请求URL记录到日志文件中，
	然后放在某个地方，这样就可能会有一些隐私的信息被第三方看到，
	另外，用户也可以再浏览器上直接看到提交的数据，一些系统内部消息将会显示在用户面前。
	Post 的所有操作对用户来说都是不可见的。
3、Get 传输的数据量小，这主要是因为受URL长度限制；而Post方式提交，所以在上传文件只能使用Post
4、Get 方式的提交需要用 Resquest.QueryString 来取得变量的值，
	而Post方式提交时，必须通过 Request.Form 来访问提交的内容。
5、<form method="get" action="a.asp?b=b">跟<form method="get" action="a.asp">是一样的，
	也就是说，action页面后边带的参数列表会被忽视；
	而<form method="post" action="a.asp?b=b">跟<form method="post" action="a.asp">是不一样的。

另外
Get请求有如下特性：
它会将数据添加到URL中，通过这种方式传递到服务器，通常利用一个问号？代表URL地址的结尾与数据参数的开端，
后面的参数每一个数据参数以“名称=值”的形式出现，参数与参数之间利用一个连接符&来区分。
Post请求有如下特性：
数据是放在HTTP主体中的，其组织方式不只一种，有&连接方式，也有分割符方式，可隐藏参数，传递大批数据，比较方便。
