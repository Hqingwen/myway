/**************************************************************************************
技术名称：SQL 结构化查询语言 Structured Query Language		  			
创建时间：2016/3/
使用说明：适用于DB2语法结构
**************************************************************************************/
/**************************************************************************************
替代词解释：

表中 		tablename
新表名		new_tablename

字段名		columnname
新字段名	new_columnname

字段类型	datatype

用户名		username

**************************************************************************************/


--创建表
CREATE TABLE tablename
(
	columnname		datatype	 NOT NULL PRIMARY KEY, 
	columnname		datatype
);    

--表中文名及字段中文名设置
COMMENT ON TABLE tablename IS  'XXXXX';
COMMENT ON COLUMN tablename.columnname IS 'XXXXX';

--授权
GRANT SELECT, INSERT, UPDATE, DELETE ON tablename TO username;
COMMIT;

--修改表名
ALTER TABLE tablename RENAME TO new_tablename;

RENAME TABLE "M1_WE"."SU_WE_SY000" TO SU_WE_SY00;

--修改字段名
ALTER TABLE tablename RENAME  COLUMN columnname TO new_columnname ;

--增加列
ALTER TABLE tablename ADD COLUMN columnname datatype;

--修改列属性
ALTER TABLE tablename ALTER COLUMN columnname SET DATA TYPE datatype;  

--删除表字段 
ALTER TABLE tablename DROP COLUMN columnname;

--修改完后需要命令进行表重组，否则select的时候会报错：
REORG TABLE tablename;

--SQL连接类型
INNER JOIN			--如果表中有至少一个匹配，则返回行
LEFT JOIN				--即使右表中没有匹配，也从左表返回所有的行
RIGHT JOIN			--即使左表中没有匹配，也从右表返回所有的行
FULL JOIN				--只要其中一个表中存在匹配，则返回行


--UNION：对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序； 
--UNION ALL：对两个结果集进行并集操作，包括重复行，不进行排序； 
--INTERSECT：对两个结果集进行交集操作，不包括重复行，同时进行默认规则的排序； 
--EXCEPT：对两个结果集进行差操作，不包括重复行，同时进行默认规则的排序。
--例：
SELECT columnname(s) FROM tablename1
UNION
SELECT columnname(s) FROM tablename2

--UNIQUE 约束唯一标识数据库表中的每条记录。
--UNIQUE 和 PRIMARY KEY 约束均为列或列集合提供了唯一性的保证。
--PRIMARY KEY 拥有自动定义的 UNIQUE 约束。
--请注意，每个表可以有多个 UNIQUE 约束，但是每个表只能有一个 PRIMARY KEY 约束。
--如果需要命名 UNIQUE 约束，以及为多个列定义 UNIQUE 约束，请使用下面的 SQL 语法：constraint
--例：
CREATE TABLE Persons
(
	Id_P int NOT NULL,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255),
	Address varchar(255),
	City varchar(255),
	CONSTRAINT uc_PersonID UNIQUE (Id_P,LastName)
);

--添加主键
ALTER TABLE tablename ADD PRIMARY KEY(columnname(,columnname2));
ALTER TABLE tablename DROP PRIMARY KEY
--主键删除

--增加 HAVING 子句原因是，WHERE 关键字无法与合计函数一起使用
--例：
SELECT 
	Customer
	,SUM(OrderPrice) 
FROM Orders
WHERE Customer='Bush' OR Customer='Adams'
GROUP BY Customer
HAVING SUM(OrderPrice)>1500




/*******************************************************************************
存储过程部分

*******************************************************************************/
--三种方式定义临时表： 
--方法1：  
DECLARE GLOBAL TEMPORARY TABLE SESSION.EMP  
(  
 	 NAME VARCHAR(10),---姓名  
 	 DEPT SMALLINT,---部门  
 	 SALARY DEC(7,2)---工资  
)  
ON COMMIT DELETE ROWS;  
 
--方法2：  
DECLARE GLOBAL TEMPORARY TABLE session.emp  
LIKE staff INCLUDING COLUMN DEFAULTS  
WITH REPLACE  
ON COMMIT PRESERVE ROWS;  
 
--方法3：  
DECLARE GLOBAL TEMPORARY TABLE session.emp AS  
(  
	SELECT * FROM staff   
)  
DEFINITION ONLY  
WITH REPLACE;  


--全局临时表
DECLARE GLOBAL TEMPORARY TABLE SESSION.TBRPT_EMP
(
	id  integer
)
ON COMMIT DELETE ROWS
NOT LOGGED ON ROLLBACK DELETE ROWS            
WITH REPLACE; 

-- 解析并马上执行动态的SQL语句或非运行时创建的PL/SQL块
EXECUTE IMMEDIATE  (SELECT * FROM tablename);

--赋权??
GRANT EXECUTE ON PROCEDURE HR.pro_demo_fan( INTEGER, INTEGER, VARCHAR(500) ) TO  username;
GRANT EXECUTE ON PROCEDURE HR.pro_demo_fan( INTEGER, INTEGER, VARCHAR(500) ) TO username;