# 文件上传
## 配置

```
qiruan.uppath=E:\\test\\
```

即文件保存的目录。注意Windows下\需要转义。

## demo

[/file/up](/file/up) 是一个文件上传的demo页，你可以在这里查看用表单/jquery上传文件的方法。

## api
### 上传
**路径**：`/file/up`
**方法**：POST
**参数**：

- file 上传的文件内容
- mode 1 图片压缩，保存比例 2 图片压缩，强制 其他 不处理
- x,y 图片压缩后的尺寸

### 下载
**路径**：`/down/{name}`
**方法**：GET
**参数**：

- name 文件名

### 更新文件依赖
文件与页面可能是多对多的关系，当页面中图片发生改变时，更新这个依赖。

**没有被任何页面使用的文件会被定期删除。**

```
    /**
     * 更新文件与页面的依赖关系
     * @param itemName 页面
     * @param filenames 文件列表
     */
    void updateFileRelationShip(String itemName,List<String> filenames);
```

如，用户admin更换了头像，就需要`updateFileRelationShip("avatar_admin",Arrays.asList("newavatar.png"))`。如果页面中的图片有多张，一定要传递所有的filenames。