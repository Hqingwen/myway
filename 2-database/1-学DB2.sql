/**************************************************************************************
技术名称：SQL 结构化查询语言 Structured Query Language	
基础语境：DB2

替代词解释：
	表中 			tablename
	新表名		new_tablename
	字段名		columnname
	新字段名		new_columnname
	字段类型		datatype
	用户名		username

**************************************************************************************/

--创建表
CREATE TABLE tablename(
	columnname		datatype	 NOT NULL PRIMARY KEY, 
	columnname		datatype
);    

--约束唯一标识数据库表中的每条记录。
UNIQUE 	
--拥有自动定义的 UNIQUE 约束。		
PRIMARY KEY 		
--UNIQUE 和 PRIMARY KEY 约束均为列或列集合提供了唯一性的保证。
--请注意，每个表可以有多个 UNIQUE 约束，但是每个表只能有一个 PRIMARY KEY 约束。
--如果需要命名 UNIQUE 约束，以及为多个列定义 UNIQUE 约束，请使用：constraint
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

--设置表中文名及字段中文名
COMMENT ON TABLE tablename IS  'XXXXX';
COMMENT ON COLUMN tablename.columnname IS 'XXXXX';

--授权
GRANT SELECT, INSERT, UPDATE, DELETE ON tablename TO username;
--取消授权
REVOKE SELECT, INSERT, UPDATE, DELETE ON tablename FROM username;


--修改表名
ALTER TABLE tablename RENAME TO new_tablename;
--DB2
RENAME TABLE "M1_WE"."SU_WE_SY000" TO SU_WE_SY00;

--增加列
ALTER TABLE tablename ADD COLUMN columnname datatype;
--删除列
ALTER TABLE tablename DROP COLUMN columnname;

--修改列名
ALTER TABLE tablename RENAME COLUMN columnname TO new_columnname ;
--修改列属性
ALTER TABLE tablename ALTER COLUMN columnname SET DATA TYPE datatype;  
--例如：
ALTER TABLE W1_QM.WH_QMCB0011 ALTER COLUMN SAMPE_DSCR SET NOT NULL;

--增加主键约束
ALTER TABLE tablename ADD PRIMARY KEY (columnname,columnname2,...);
ALTER TABLE tablename ADD CONSTRAINT constraintname PRIMARY KEY(columnname,columnname2,...);
--删除主键约束
ALTER TABLE tablename DROP PRIMARY KEY;

--修改完后需要命令进行表重组，否则select的时候会报错：
REORG TABLE tablename;
COMMIT;


--插入单行
INSERT INTO tablename (columnname,columnname2,...) VALUES (value,value2,...);
--将现有的表数据添加到一个已有表
INSERT INTO new_tablename (columnname,columnname2,...) SELECT columnname,columnname2,... FROM tablename;
--直接拿现有表数据创建一个新表并填充
SELECT columnname,columnname2,... into new_tablename FROM tablename;


--删除
/*
1.truncate和 delete只删除数据不删除表的结构(定义) ;
	drop语句将删除表的结构，被依赖的约束(constrain)，触发器(trigger)，索引(index)，
	但依赖于该表的存储过程、函数将保留，但是变为invalid状态。

2.delete语句是dml，这个操作会放到rollback segement中，事务提交之后才生效；如果有相应的trigger，执行的时候将被触发。
	truncate,drop是ddl, 操作立即生效,原数据不放到rollback segment中,不能回滚. 操作不触发trigger.

3.delete语句不影响表所占用的extent, 高水线(high watermark)保持原位置不动 显然drop语句将表所占用的空间全部释放 truncate 语句缺省情况下见空间释放到 minextents个 extent,除非使用reuse storage; truncate会将高水线复位(回到最开始). 

4.速度,一般来说: drop>; truncate >; delete 

5.安全性:小心使用drop 和truncate,尤其没有备份的时候.否则哭都来不及。

使用上,想删除部分数据行用delete,注意带上where子句，回滚段要足够大。 
　想删除表,当然用drop 
　想保留表而将所有数据删除. 如果和事务无关,用truncate即可. 如果和事务有关,或者想触发trigger,还是用delete. 
　如果是整理表内部的碎片,可以用truncate跟上reuse stroage,再重新导入/插入数据 
　　
在实际应用中，三者的区别是明确的。 
　当你不再需要该表时， 用 drop; 
　当你仍要保留该表，但要删除所有记录时， 用 truncate; 
　当你要删除部分记录时（always with a WHERE clause), 用 delete.
 */
DELETE FROM tablename (WHERE ....);
TRUNCATE TABLE tablename;
DROP TABLE tablename;


--更新
UPDATE tablename SET columnname=value WHERE ....;


--SQL连接类型
--如果表中有至少一个匹配，则返回行
INNER JOIN			
--即使右表中没有匹配，也从左表返回所有的行
LEFT JOIN			
--即使左表中没有匹配，也从右表返回所有的行
RIGHT JOIN			
--只要其中一个表中存在匹配，则返回行
FULL JOIN			

--对两个结果集进行并集操作，不包括重复行，同时进行默认规则的排序；
UNION				 
--对两个结果集进行并集操作，包括重复行，不进行排序； 
UNION ALL		
--对两个结果集进行交集操作，不包括重复行，同时进行默认规则的排序； 
INTERSECT		
--对两个结果集进行差操作，不包括重复行，同时进行默认规则的排序。
EXCEPT			



--通配符
Id	LastName		FirstName		Address			City
1	Adams			John		Oxford Street		London
2	Bush			George	Fifth Avenue			New York
3	Carter			Thomas	Changan Street		Beijing

--替代一个或多个字符：%
SELECT * FROM Persons WHERE City LIKE 'Ne%'	

--仅替代一个字符：_			
SELECT * FROM Persons WHERE LastName LIKE 'C_r_er'		

--字符列中的任何单一字符：[charlist]	
SELECT * FROM Persons WHERE City LIKE '[ALN]%'
		
--不在字符列中的任何单一字符：[^charlist]  [!charlist]
SELECT * FROM Persons WHERE City LIKE '[!ALN]%'