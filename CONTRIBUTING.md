# 开发指导

[[TOC]]

## 文档
- [需求规约](docs/demand.md)

## 需要用到的工具
### git
git是一个版本控制工具，[下载](https://git-scm.com/downloads)安装并配置环境变量。

### maven
[下载](https://maven.apache.org/download.cgi)安装并添加环境变量。

### mysql
MySQL数据库，略。

### IDE
[https://spring.io/tools](https://spring.io/tools)三选一，推荐VSCode。

## 需要学习的技术
> 斜体部分为进阶考虑，不做强制要求。

|技术|说明|
|---|---|
|markdown|文本标记语言，便于书写文档|
|*plantuml*|绘制uml图|
|git|版本控制|
|maven|管理项目依赖|
|SQL语言|数据库，至少能写增删查改|
|html、css、js基本知识|网页设计|
|springboot Controller|控制器|
|thymeleaf|java模板引擎|
|jdbcTemplate|数据持久化|
|jquery|js库，简化js写法，解决兼容性问题|
|*spring boot发送邮件*|联系会员手段|
|*WebSocket*|在线交流功能实现|
|*bootstrap*|css模板|
|*拦截器/过滤器*|权限控制|
|*spring security*|权限控制|
|*shiro*|权限控制|

## 学习参考
- [阿里巴巴Java开发手册](https://yq.aliyun.com/articles/69327)
- [Markdown Guide](https://simplemde.com/markdown-guide)
- [PlantUML](http://plantuml.com/zh/)
- [git教程](https://www.liaoxuefeng.com/wiki/896043488029600)
- [Spring boot 项目目录结构](https://blog.csdn.net/u012675150/article/details/79351990)
- [BootStrap4中文手册](http://code.z01.com/v4/docs/)
- [springboot遇到的坑](https://www.ntutn.top/show/90)

## 项目开发规范
### 包名规范
**所有包名要求小写！**

**代码层的结构**
> 根目录：team.qiruan

|类|包名|
|---|---|
|工程启动类|team.qiruan|
|实体类|team.qiruan.domain|
|数据服务层|team.qiruan.service|
|数据服务的实现接口|team.qiruan.service.impl|
|前端控制器|team.qiruan.controller|
|工具类|team.qiruan.utils|
|常量接口类|team.qiruan.constant|
|配置信息类|team.qiruan.config|

**资源文件的结构**
可以使用[webjars](https://www.jianshu.com/p/66d1b35bcd9d)引入的静态资源使用webjars引入。

|文件类型|位置|
|---|---|
|配置文件|config|
|静态文件|static|
|脚本|config/js|
|图片|config/img|
|样式表|config/css|
|模板文件|templates|
