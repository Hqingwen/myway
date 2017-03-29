1、原生JavaScript
//创建XMLHttpRequest对象
var request = new XMLHttpRequest();
//建立对服务器的调用，请求方式、请求路径
request.open("GET",url);
//向服务器发送请求，请求内容
request.send(null);
//由服务器触发的响应函数
request.onreadystatechange = function(){
    //请求状态：0-未初始化、1-正在加载、2-已经加载、3-交互中、4-完成
    if(request.readyState == 4){
        //服务器的HTTP状态码，其中statusText为HTTP状态码的相应文本
        if(request.status == 200 || request.status  == 304){
            //服务器响应，分为responeText、responeXML
            alert(request.responeText);
        }
    }
}

2、服务器响应（responeText/responeXML）格式
    文本
    HTML片段
    （数据首选）XML
    （性能首选）JSON：var jsonvar = eval("("+request.responeText+")")
注：
    eval()：可以打一个字符串转为本地的JS代码来执行，但存在风险

3、jQuery中的Ajax
load([url],[args],[callback])
    args：JSON格式；没有args参数时，默认GET方式，有则为POST
如：
$("#id").load(url,{"tiem":new Date()},function(){
    ...
});

$.get(url[,data][,callback][,type])
    type：服务器返回内容的格式
如：
//data：返回内容，textstatus：请求状态，success，error，notmodify，timeout
$.get(url, args, fucntion(data, textstatus){
    ...
},"JSON");
进一步：$.getJSON()：默认返回格式JSON

$.post(url[,data][,callback][,type])：与$.get()类似

$.ajax()

4、第三方库jackson：可以将JavaBean转换成JSON格式