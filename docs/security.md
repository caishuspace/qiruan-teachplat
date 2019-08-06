# 用户相关模块及系统安全

[[TOC]]

## 用户密码
用户密码使用了spring security默认的`PasswordEncoder`加密，如果你需要手动修改数据库中某个用户的密码，可以用Test类中的`encodePassword`方法帮你计算。

## csrf攻击防护
因为开启了csrf攻击防护，除GET以外的所有http请求受到保护。

请求需要带上一个`token`才会被接受。

### 表单提交
对于表单提交，只要使用`thymeleaf`自带的`th:action`写提交路径，就可以自动帮你插入这个字段。

```html
<form id="form-logout" method="POST" th:action="@{/auth/logout}">
    <input type="submit" style="display:none;">
</form>
```

上述表单解析后变为

```html
<form id="form-logout" method="POST" action="/auth/logout">
    <input type="hidden" name="_csrf" value="10a18ce2-eeb0-4524-befb-e86868a9150b"/>
    <input type="submit" style="display:none;">
</form>
```

### 脚本提交
脚本提交仍然需要带上`csrf`的`token`，或者加上一个header。如果你当前的网页有form，直接从里面取出来就好，或者放到`<meta>`里面。

比如可以使用`thymeleaf`的内联语法：

```html
<script th:inline="javascript">
    $.post("/adm/code/handle", {
        headercode: data,
        /*somedata,*/
        /*csrf攻击防御*/
        /*[(${_csrf.parameterName})]*/a
        :/*[[${_csrf.token}]]*/20
    }, function (data, status) {
        //do something
    });
</script>
```

解析结果

```html
<script>
    $.post("/adm/code/handle", {
        headercode: data,
        /*somedata,*/
        /*csrf攻击防御*/
        _csrf
        :10a18ce2-eeb0-4524-befb-e86868a9150b
    }, function (data, status) {
        //do something
    });
</script>
```

*参考*

- [使用SpringSecurity处理CSRF攻击](https://segmentfault.com/a/1190000018402597)

## 退出登录
为了安全，退出登录只能用POST方法请求，并且要带csrf的token。

## 待完成
- 界面
- 文件上传及管理
- 编辑个人信息时直接写html可能带来的xss攻击问题
