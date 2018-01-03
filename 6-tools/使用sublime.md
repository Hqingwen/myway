
### 使用参考：

>[Ubuntu16.04下使用sublime text3](http://www.bkjia.com/Pythonjc/1144780.html)
```
--Ubuntu
--安装
sudo add-apt-repository ppa:webupd8team/sublime-text-3
sudo apt-get update
sudo apt-get install sublime-text-installer

--卸载
sudo apt-get remove sublime-text-installer
```

> [Sublime Text 全程指引](https://www.cnblogs.com/figure9/p/sublime-text-complete-guide.html)

--安装Package Control
import urllib.request,os,hashlib;
h = 'df21e130d211cfc94d9b0905775a7c0f' + '1e3d39e33b79698005270310898eea76';
pf = 'Package Control.sublime-package';
ipp = sublime.installed_packages_pathourllib.request.install_opener( urllib.request.build_opener( urllib.request.ProxyHandler()) );
by = urllib.request.urlopen( 'http://packagecontrol.io/' + pf.replace(' ', '%20')).read();
dh = hashlib.sha256(by).hexdigest();
print('Error validating download (got %s instead of %s), please try manual install' % (dh, h)) if dh != h else open(os.path.join( ipp, pf), 'wb' ).write(by)

--安装

--快捷键
Alt+.：闭合标签
Alt+数字：切换打开第N个文件
Alt+F3 选中文本按下快捷键，即可一次性选择全部的相同文本进行同时编辑。
Alt+Shift+1 窗口分屏，恢复默认1屏（非小键盘的数字）
Alt+Shift+2 左右分屏-2列
Alt+Shift+3 左右分屏-3列
Alt+Shift+4 左右分屏-4列
Alt+Shift+5 等分4屏
Alt+Shift+8 垂直分屏-2屏
Alt+Shift+9 垂直分屏-3屏
Ctrl+/：注释当前行
Ctrl+Alt+/：块注释，并Focus到首行，写注释说明用的
Ctrl+Alt+↑ 向上添加多行光标，可同时编辑多行。
Ctrl+Alt+↓ 向下添加多行光标，可同时编辑多行。
Ctrl+D 选中光标所占的文本，继续操作则会选中下一个相同的文本。
Ctrl+Enter 在下一行插入新行
Ctrl+F 打开底部搜索框，查找关键字。
Ctrl+F2：设置/删除标记
Ctrl+G 打开搜索框，自动带：，输入数字跳转到该行代码。
Ctrl+H：替换
Ctrl+J 合并选中的多行代码为一行
Ctrl+K+0 展开所有折叠代码。
Ctrl+K+B：开关侧栏
Ctrl+K+K 从光标处开始删除代码至行尾。
Ctrl+K+L 转换小写。
Ctrl+K+U 转换大写。
Ctrl+L 选中整行，继续操作则继续选择下一行，效果和 Shift+↓ 效果一样。
Ctrl+M 光标移动至括号内结束或开始的位置。
Ctrl+N：新建窗口
Ctrl+PageDown 向左切换当前窗口的标签页。
Ctrl+PageUp 向右切换当前窗口的标签页。
Ctrl+P：搜索项目中的文件
Ctrl+R 打开搜索框，自动带@，输入关键字，查找文件中的函数名。
Ctrl+Shift+/：当前位置插入注释
Ctrl+Shift+[ 选中代码，按下快捷键，折叠代码。
Ctrl+Shift+] 选中代码，按下快捷键，展开代码。
Ctrl+Shift+A：选择当前标签前后，修改标签用的
Ctrl+Shift+D 复制光标所在整行，插入到下一行。
Ctrl+Shift+Enter 在上一行插入新行。举个栗子：即使光标不在行首，也能快速向上插入一行。
Ctrl+Shift+Enter：在当前行前插入新行
Ctrl+shift+F 在文件夹内查找
Ctrl+Shift+K 删除整行。
Ctrl+Shift+L 先选中多行，再按下快捷键，会在每行行尾插入光标，即可同时编辑这些行。
Ctrl+Shift+P：打开命令面板
Ctrl+Shift+V：粘贴并格式化
Ctrl+Shift+W：关闭所有打开文件
Ctrl+Shift+← 向左单位性地选中文本。
Ctrl+Shift+→ 向右单位性地选中文本。
Ctrl+Shift+↑ 将光标所在行和上一行代码互换（将光标所在行插入到上一行之前）。
Ctrl+Shift+↓ 将光标所在行和下一行代码互换（将光标所在行插入到下一行之后）。
Ctrl+T 左右字母互换。
Ctrl+Tab 按文件浏览过的顺序，切换当前窗口的标签页。
Ctrl+U：软撤销，撤销光标位置
Ctrl+W：关闭当前打开文件
Ctrl+X：删除当前行
Ctrl+Y 恢复撤销。
Ctrl+Z 撤销。
Ctrl+← 向左单位性地移动光标，快速移动光标。
Ctrl+→ 向右单位性地移动光标，快速移动光标。
Ctrl+：打开搜索框，自动带#，输入关键字，查找文件中的变量名、属性名等。
F11 全屏模式
F6 单词检测拼写
Shift+F11 免打扰模式
Shift+Tab 向左缩进。
Shift+← 向左选中文本。
Shift+→ 向右选中文本。
shift+↑ 向上选中多行。
shift+↓ 向下选中多行。
Shift+右键拖动：光标多不，用来更改或插入列内容
