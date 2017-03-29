/**********************************************************************************
技术名词：Redis（key-value非关系型数据库）
创建日期：2017-03-29
名词解释：
    通常被称为数据结构服务器，因为值（value）可以是
    字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型

**********************************************************************************/

--Ubuntu 安装
    $sudo apt-get update
    $sudo apt-get install redis-server

--启动 Redis
    $ redis-server

--查看 redis 是否启动？
    $ redis-cli
--以上命令将打开以下终端：
    redis 127.0.0.1:6379>
--127.0.0.1 是本机 IP ，6379 是 redis 服务端口。现在我们输入 PING 命令。
    redis 127.0.0.1:6379> ping
    PONG
--以上说明我们已经成功安装了redis。
--也可以指定端口连接
    redis-cli -p 6379

--关闭服务？？？
    redis-cli shutdown
--如果非默认端口，可指定端口:
    redis-cli -p 6380 shutdown

--获得所有配置项
    CONFIG GET *

/*************************************************************************************************/
--该命令用于在 key 存在时删除 key。
DEL key

--序列化给定 key ，并返回被序列化的值。
DUMP key

--检查给定 key 是否存在。
EXISTS key

--为给定 key 设置过期时间。
EXPIRE key seconds

--EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
EXPIREAT key timestamp

--设置 key 的过期时间以毫秒计。
PEXPIRE key milliseconds

--设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
PEXPIREAT key milliseconds-timestamp

--查找所有符合给定模式( pattern)的 key 。
KEYS pattern

--将当前数据库的 key 移动到给定的数据库 db 当中。
MOVE key db

--移除 key 的过期时间，key 将持久保持。
PERSIST key

--以毫秒为单位返回 key 的剩余的过期时间。
PTTL key

--以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
TTL key

--从当前数据库中随机返回一个 key 。
RANDOMKEY

--修改 key 的名称
RENAME key newkey

--仅当 newkey 不存在时，将 key 改名为 newkey 。
RENAMENX key newkey

--返回 key 所储存的值的类型。
TYPE key