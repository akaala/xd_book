## 部署

URL: http://10.2.6.29:8080/book/view/login.html

deploy path: 10.2.6.29  /opt/ctrip/web/book, tomcat在/opt/app/tomcat/。
注意该机器一并部署了Clogging FAT Data-Rest服务，勿随意重启tomcat!

mysql: 安装在这台本机上。
mysql password: 不是默认myBatis.xml中的"123456"！发布后需手动修改，密码来自：sudo cat ~/.mysql_secret

#### 所需软件

1.git: 

2.Mysql:    

2.1 Mysql Client:

3.Tomcat:

4.Maven

