
--ROW_NUMBER：给查询结果集编号；
--OVER：并不是一个函数，而是一个表达式，它的作用是定义一个作用域（或者可以说是结果集），
--OVER前面的函数只对OVER定义的结果集起作用。
SELECT  
    ROW_NUMBER() OVER(ORDER BY SALARY) AS 序号,  
    NAME AS 姓名,  
    DEPT AS 部门,  
    SALARY AS 工资  
FROM  
(   
    VALUES  
    ('张三','市场部',4000),  
    ('赵红','技术部',2000),  
    ('李四','市场部',5000),  
    ('李白','技术部',5000),  
    ('王五','市场部',NULL),  
    ('王蓝','技术部',4000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
序号       姓名       部门       工资  
1           赵红       技术部    2000  
2           张三       市场部    4000  
3           王蓝       技术部    4000  
4           李四       市场部    5000  
5           李白       技术部    5000  
6           王五       市场部    (null)  

SELECT  
    ROW_NUMBER() OVER(PARTITION BY DEPT ORDER BY SALARY) AS 序号,  
......

 查询结果如下：  
序号       姓名       部门       工资  
1           赵红      技术部     2000
2           王蓝      技术部     4000
3           李白      技术部     5000
1           张三      市场部     4000
2           李四      市场部     5000
3           王五      市场部 


--DB2 在线分析处理的格式包括两部分：函数部分和OVER表达式部分。
--函数部分如下：
ROW_NUMBER  
RANK  
DENSE_RANK  
FIRST_VALUE  
LAST_VALUE  
LAG  
LEAD  
COUNT  
MIN  
MAX  
AVG  
SUM  
 

--假设在不改变上面语句的查询结果的情况下，
--追加对部门员工的平均工资和全体员工的平均工资的查询。
--用OLAP函数则非常简单，如下SQL所示：
--ORDER BY:用来对结果集排序。
--PARTITION BY:用来对结果集分组。
SELECT  
    ROW_NUMBER() OVER() AS 序号,  
    ROW_NUMBER() OVER(PARTITION BY DEPT ORDER BY SALARY) AS 部门序号,  
    NAME AS 姓名,  
    DEPT AS 部门,  
    SALARY AS 工资,  
    AVG(SALARY) OVER(PARTITION BY DEPT) AS 部门平均工资,  
    AVG(SALARY) OVER() AS 全员平均工资  
FROM  
(  
    --姓名    部门  工资  
    VALUES  
    ('张三','市场部',4000),  
    ('赵红','技术部',2000),  
    ('李四','市场部',5000),  
    ('李白','技术部',5000),  
    ('王五','市场部',NULL),  
    ('王蓝','技术部',4000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
序号       部门序号       姓名       部门       工资       部门平均工资       全员平均工资  
1            1                  张三       市场部    4000       4500                     4000  
2            2                  李四       市场部    5000       4500                     4000  
3            3                  王五       市场部    (null)      4500                     4000  
4            1                  赵红       技术部    2000       3666                     4000  
5            2                  王蓝       技术部    4000       3666                     4000  
6            3                  李白       技术部    5000       3666                     4000  

 
--把null放在前边?使用NULLS FIRST关键字即可，默认是NULLS LAST：
SELECT  
    ROW_NUMBER() OVER(ORDER BY SALARY desc NULLS FIRST) AS RN,  
    RANK() OVER(ORDER BY SALARY desc NULLS FIRST) AS RK,  
    DENSE_RANK() OVER(ORDER BY SALARY desc NULLS FIRST) AS D_RK,  
    NAME AS 姓名,  
    DEPT AS 部门,  
    SALARY AS 工资  
FROM  
(  
    --姓名    部门  工资  
    VALUES  
    ('张三','市场部',4000),  
    ('赵红','技术部',2000),  
    ('李四','市场部',5000),  
    ('李白','技术部',5000),  
    ('王五','市场部',NULL),  
    ('王蓝','技术部',4000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
RN  RK   D_RK     姓名       部门       工资  
1     1     1     王五       市场部    (null)  
2     2     2     李四       市场部    5000  
3     2     2     李白       技术部    5000  
4     4     3     张三       市场部    4000  
5     4     3     王蓝       技术部    4000  
6     6     4     赵红       技术部    2000  

--RANK：排名；当有并列情况，会跳过重复排名
--DENSE_RANK：排名；当有并列情况，不跳过重复排名


 

  现在又有个新问题，假设让你查询一下每个员工的工资以及工资小于他的所有员工的平均工资，该怎么办呢？
  怎么？没听明白问题？不要紧，请看下面的SQL：

SELECT  
    NAME AS 姓名,  
    SALARY AS 工资,  
    SUM(SALARY) OVER(ORDER BY SALARY NULLS FIRST ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS 小于本人工资的总额,  
    SUM(SALARY) OVER(ORDER BY SALARY NULLS FIRST ROWS BETWEEN  CURRENT ROW AND UNBOUNDED FOLLOWING) AS 大于本人工资的总额,  
    SUM(SALARY) OVER(ORDER BY SALARY NULLS FIRST ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS 工资总额1,  
    SUM(SALARY) OVER() AS 工资总额2  
FROM  
(  
    --姓名    部门  工资  
    VALUES  
    ('张三','市场部',4000),  
    ('赵红','技术部',2000),  
    ('李四','市场部',5000),  
    ('李白','技术部',5000),  
    ('王五','市场部',NULL),  
    ('王蓝','技术部',4000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
   
姓名       工资       小于本人工资的总额    大于本人工资的总额    工资总额1     工资总额2  
王五       (null)     (null)             20000              20000            20000  
赵红       2000       2000               20000              20000            20000  
张三       4000       6000               18000              20000            20000  
王蓝       4000       10000              14000              20000            20000  
李四       5000       15000              10000              20000            20000  
李白       5000       20000              5000               20000            20000  
 
 

上面SQL 中的OVER部分出现了一个ROWS子句，我们先来看一下ROWS子句的结构：

ROWS BETWEEN <上限条件> AND <下限条件>  
   
其中“上限条件”可以是如下关键字：  
UNBOUNDED PRECEDING  
<number>  PRECEDING  
CURRENT ROW  
   
“下线条件”可以是如下关键字：  
CURRENT ROW  
<number> FOLLOWING  
UNBOUNDED FOLLOWING  
 
 

注意，以上关键字都是相对当前行的，UNBOUNDED PRECEDING表示当前行前面的所有行，
也就是说没有上限；<number>  PRECEDING表示从当前行开始到它前面的<number>行为止，
例如，number=2，表示的是当前行前面的2行；CURRENT ROW表示当前行。
至于其它两个关键字，我想，不用我说，你也应该知道了吧。
如果你还不明白，请仔细分析上面SQL的查询结果。

  OVER表达式还可以有个子句，那就是RANGE，
  它的使用方式和ROWS 十分相似，或者说一模一样，作用也差多不，不过有点区别，如下所示：

RANGE BETWEEN <上限条件> AND <下限条件>

其中的<上限条件> 、<下限条件>和ROWS一模一样，如下的SQL演示它们之间的区别：

SELECT  
    NAME AS 姓名,  
    DEPT AS 部门,  
    SALARY AS 工资,  
    FIRST_VALUE(SALARY, 'IGNORE NULLS') OVER(PARTITION BY DEPT) AS 部门最低工资,  
    LAST_VALUE(SALARY, 'RESPECT NULLS') OVER(PARTITION BY DEPT) AS 部门最高工资,  
    SUM(SALARY) OVER(ORDER BY SALARY ROWS BETWEEN 1 PRECEDING  AND 1 FOLLOWING) AS ROWS,  
    SUM(SALARY) OVER(ORDER BY SALARY RANGE BETWEEN 500 PRECEDING AND 500 FOLLOWING) AS RANGE  
FROM  
(  
    --姓名    部门  工资  
    VALUES  
    ('张三','市场部',2000),  
    ('赵红','技术部',2400),  
    ('李四','市场部',3000),  
    ('李白','技术部',3200),  
    ('王五','市场部',4000),  
    ('王蓝','技术部',5000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
   
姓名       部门       工资       部门最低工资       部门最高工资       ROWS    RANGE  
张三       市场部    2000       2000              4000             4400       4400  
赵红       技术部    2400       2400              5000             7400       4400  
李四       市场部    3000       2000              4000             8600       6200  
李白       技术部    3200       2400              5000             10200     6200  
王五       市场部    4000       2000              4000             12200     4000  
王蓝       技术部    5000       2400              5000             9000       5000  
 
 

上面SQL的RANGE 子句的作用是定义一个工资范围，
这个范围的上限是当前行的工资-500，下限是当前行工资+500。
例如：李四的工资是3000，所以上限是3000-500=2500，下限是3000+500=3500，
那么有谁的工资在2500-3500这个范围呢？只有李四和李白，
所以RANGE列的值就是3000(李四)+3200(李白)=6200。以上就是ROWS和RANGE得区别。


  上面的SQL 还用到了FIRST_VALUE和LAST_VALUE两个函数，
  它们的作用也非常简单，用来求OVER 定义集合的最小值和最大值。
  值得注意的是这两个函数有个参数，'IGNORE NULLS' 或 'RESPECT NULLS'，
  它们的作用正如它们的名字一样，用来忽略NULL值和考虑NULL值。



  还有两个函数我们没有介绍，LAG和LEAD，这两个函数的功能非常强大，请看下面SQL：

SELECT  
    NAME AS 姓名,  
    SALARY AS 工资,  
    LAG(SALARY,0) OVER(ORDER BY SALARY) AS LAG0,  
    LAG(SALARY) OVER(ORDER BY SALARY) AS LAG1,  
    LAG(SALARY,2) OVER(ORDER BY SALARY) AS LAG2,  
    LAG(SALARY,3,0,'IGNORE NULLS') OVER(ORDER BY SALARY) AS LAG3,  
    LAG(SALARY,4,-1,'RESPECT NULLS') OVER(ORDER BY SALARY) AS LAG4,  
    LEAD(SALARY) OVER(ORDER BY SALARY) AS LEAD  
FROM  
(  
    --姓名    部门  工资  
    VALUES  
    ('张三','市场部',2000),  
    ('赵红','技术部',2400),  
    ('李四','市场部',3000),  
    ('李白','技术部',3200),  
    ('王五','市场部',4000),  
    ('王蓝','技术部',5000)  
) AS EMPLOY(NAME,DEPT,SALARY);  
   
查询结果如下：  
   
姓名       工资       LAG0      LAG1      LAG2      LAG3      LAG4      LEAD  
张三       2000       2000      (null)   (null)       0       -1        2400  
赵红       2400       2400       2000    (null)       0       -1        3000  
李四       3000       3000       2400     2000       0        -1        3200  
李白       3200       3200       3000     2400       2000     -1        4000  
王五       4000       4000       3200     3000       2400     2000      5000  
王蓝       5000       5000       4000     3200       3000     2400      (null)  
 
 

  我们先来看一下LAG 和 LEAD 函数的声明，如下

LAG(表达式或字段, 偏移量, 默认值, IGNORE NULLS或RESPECT NULLS)
LAG是向下偏移，LEAD是想上偏移，大家看一下上面SQL的查询结果就一目了然了。


  到此为止，有关DB2 OLAP 函数的所有知识都介绍给大家了，下面我们再次回顾一下 DB2 在线分析处理 的组成部分，如下：

函数 OVER(PARTITION BY 子句 ORDER BY 子句 ROWS或RANGE子句)

 