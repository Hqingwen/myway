key-value存储系统
通常被称为数据结构服务器，因为值（value）可以是 字符串(String), 哈希(Map), 列表(list), 集合(sets) 和 有序集合(sorted sets)等类型


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