/******************************************
* 环境：Ubuntu
******************************************/
--安装
sudo apt-get install mysql-server
sudo apt-get install mysql-client
sudo apt-get install libmysqlclient-dev
--验证
sudo netstat -tap | grep mysql
--检查
ps -ef | grep mysqld
--启动
sudo service mysql start
--停止
sudo service mysql stop
--重启
sudo service mysql restart
--登录
mysql -u root -p


/******************************************
* 基本语法
******************************************/
--查询数据库
SHOW DATABASES;

--删除数据库
DROP DATABSES [ IF EXISTS dbName ];

--创建数据库
CREATE DATABASE dbName;

--选择数据库
USE dbName;

--查询所有表
SHOW TABLES;

--删除表
DROP TABLE [ IF EXISTS tableName ];

--创建表
--AUTO_INCREMENT 自动增长
--ENGINE	存储引擎类型
--DEFAULT CHARSET	字符集类型
--INDEX 索引
CREATE TABLE USER(
    ID INT NOT NULL AUTO_INCREMENT,
    USERNAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) NOT NULL,
    CREDITS INT DEFAULT 0，
    PRIMARY KEY(ID),
    [ UNIQUE | FULLTEXT ] IDNEX [ index_name ]( column_name[(length)] [ asc | desc ] );
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--复合类型的使用（enum枚举类型，set集合类型）
--插入时只能插入已定义的内容
CREATE TABLE table_name(
	sex ENUM('男','女'),
	interset set('听音乐','看电影','打游戏')
)


--修改表名
ALTER TABLE old_table_name RENAME new_table_name;
RENAME TABLE old_table_name TO new_table_name;

--修改表字段名及数据类型
ALTER TABLE  table_name CHANGE old_column_name new_column_name date_type;
--仅修改数据类型
ALTER TABLE table_name MODIFY column_name  date_type;
--添加约束条件
ALTER TABLE table_name ADD CONSTRAINT constraint_name constraint_type (column_name);

--删除约束条件
--约束主要用于保证业务逻辑操作数据库时数据的完整性。
ALTER TABLE table_name DROP PRIMARY KEY;
ALTER TABLE table_name DROP FOREIGN KEY constraint_name;
ALTER TABLE table_name DROP INDDEX index_name;

--修改其他选项
ALTER TABLE table_name ENGINE=engin_name;
ALTER TABLE table_name DEFAULT CHARSET=charset_name;
ALTER TABLE table_name AUTO_INCREMENT=num;

--数据库 数据表字典 SQL查询语句：  
SELECT 
	COLUMN_NAME '字段名称',
	LEFT(COLUMN_COMMENT,LOCATE('】',COLUMN_COMMENT)) '业务分类',
	COLUMN_TYPE '数据类型',
	IS_NULLABLE '允许为空',
	COLUMN_DEFAULT '缺省值',  
	SUBSTRING(COLUMN_COMMENT FROM LOCATE('】',COLUMN_COMMENT)+1) '字段说明' 
FROM information_schema.columns 
WHERE table_name='order_info' and  table_schema = 'mengdian_order_0' 
ORDER BY LEFT(COLUMN_COMMENT,LOCATE('】',COLUMN_COMMENT));  


--插入语句（多条）
INSERT INTO USER(USERNAME,PASSWORD,CREDITS)
VALUES
('张三','111111',100),
('李四','222222',100),
('王五','333333',100),
('赵六','444444',100);


--索引的理解
索引其实是数据库中字段值的复制，该段称为索引的关键字。需要另外的存储空间。
索引是将关键字数据以某种数据结构的方式存储到外村，用于提升数据的检索性能。
例如：实际可以是更复杂结构（双向链表、B+树，Hash等）
结构：		关键字		表记录指针
内容：		主键值		物理地址

InnoDB表的“主索引”关键字的顺序必须有InnoDB表记录主键值的顺序一直，
严格地说，这种“主索引”称为“聚簇索引”，并且每一张表必须与且只能拥有一个聚簇索引。
即使一个没有主键的MySQL表，MySQL也会为其自动创建一个“隐式”的主键。

对于InnoDB表而言，MySQL的费聚簇索引统称为“辅助索引”，辅助索引的“表记录指针”称为“书签”，实际为主键值。
例如：
结构：		关键字		书签
内容：		某个字段	主键值


--索引关键字的选取原则
1、 表的某个字段值得离散度越高，该字段越适合选作索引的关键字。
	主键字段以及唯一性约束字段适合选作索引的关键字，原因就是这些字段的值非常离散。
	尤其是在主键字段创建索引时，cardinality（基数，集的势）的值就等于该表的行数。
	MySQL在处理主键约束以及唯一性约束时，考虑周全。数据库用户创建主键约束的同时，
	MySQL自动创建主索引（primary index），且索引名称为Primary；
	数据库用户创建唯一性索引时，MySQL自动创建唯一性索引（unique index），默认情况下，索引名为唯一性索引的字段名。 
2、 占用存储空间少的字段更适合选作索引的关键字。
	例如，与字符串相比，整数字段占用的存储空间较少，因此，较为适合选作索引关键字。 
3、 存储空间固定的字段更适合选作索引的关键字。
	                                                                                                                                          与text类型的字段相比，char类型的字段较为适合选作索引关键字。 
4、 Where子句中经常使用的字段应该创建索引，分组字段或者排序字段应该创建索引，两个表的连接字段应该创建索引。 
5、 更新频繁的字段不适合创建索引，不会出现在where子句中的字段不应该创建索引。 
6、 最左前缀原则。 
7、 尽量使用前缀索引。


--索引的创建
CREATE  [ UNIQUE | FULLTEXT ] IDNEX  index_name ON table_name ( column_name[(length)] [ asc | desc ] );
ALTER TABLE  table_name ADD [ UNIQUE | FULLTEXT ] IDNEX  index_name  ( column_name[(length)] [ asc | desc ] );
--删除索引
DROP INDEX index_name ON table_name;

--连接集合中的字符串
--按照分组字段（group by），将另一个字段的值（NULL值除外）使用逗号连接起来
group_concat()
--连接集合中的字符串
concat()































