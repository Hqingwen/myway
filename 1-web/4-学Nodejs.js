一、安装
    http://www.runoob.com/nodejs/nodejs-install-setup.html
//预安装环境
GCC
    rpm -q gcc
G++
    rpm -q gcc-c++
Python
    python -V
GNUＭake

//CentOS终端安装命令
# cd /usr/src
# wget  https://nodejs.org/dist/v6.10.0/node-v6.10.0.tar.gz
# tar -xf node-v6.10.0.tar.gz
# ls
# cd node-v6.10.0
# ./configure
# make
# sudo make install
# node -v
# npm -v

//Ubuntu终端安装命令
apt install nodejs
apt install npm
//安装管理nodeJS版本的第三方库
npm install -g n
//安装最新版本
n latest
//安装最新稳定版本
n stable


二、示例
var http = require('http');
http.createServer(function (request, response) {
    // 发送 HTTP 头部
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    response.writeHead(200, {'Content-Type': 'text/plain'});
    // 发送响应数据 "Hello World"
    response.end('Hello World\n');
}).listen(8888);
// 终端打印如下信息
console.log('Server running at http://127.0.0.1:8888/');


