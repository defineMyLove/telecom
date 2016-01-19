下载地址：http://code.jellycan.com/memcached/

安装Memcache Server(也可以不安装直接启动)

1. 下载memcached的windows稳定版，解压放某个盘下面，比如在c:\memcached
2. 在CMD下输入 "c:\memcached\memcached.exe -d install" 安装.
3. 再输入："c:\memcached\memcached.exe -d start" 启动。NOTE: 以后memcached将作为windows的一个服务每次开机时自动启动。这样服务器端已经安装完毕了。

如果下载的是二进制的版本，直接运行就可以了，可以加上参数来加以设置。


常用设置：
-p <num>          监听的端口
-l <ip_addr>      连接的IP地址, 默认是本机
-d start          启动memcached服务
-d restart        重起memcached服务
-d stop|shutdown  关闭正在运行的memcached服务
-d install        安装memcached服务
-d uninstall      卸载memcached服务
-u <username>     以<username>的身份运行 (仅在以root运行的时候有效)
-m <num>          最大内存使用，单位MB。默认64MB
-M                内存耗尽时返回错误，而不是删除项
-c <num>          最大同时连接数，默认是1024
-f <factor>       块大小增长因子，默认是1.25
-n <bytes>        最小分配空间，key+value+flags默认是48
-h                显示帮助