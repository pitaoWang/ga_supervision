# 空管企业管理
==================

## 文档约定

* 在引用的代码中，如果出现符号`//`，是用于注释说明。符号后面文字内容，是对符号前面代码的注释说明。
* 在引用的代码中，如果出现符号`[]`，是用于规定替换关键词，符号中间的关键词，是需要替换成按协议或注释规定内容。
* 在文档内引用的源代码中，相同的关键词，代表相同的含义，不重复注释说明。

## 预先准备

开发之前，请准备：

- mysql，数据库
- lombok，工具在tools/lombok.jar，双击运行选择IDE工具添加

## 服务器配置文件说明

配置文件profile选择：

```
ga-company-booter/src/main/resources/config/application.properties
```

具体配置信息（如profile=dev）：

```
ga-company-booter/src/main/resources/config/application-dev.properties

1）数据源
2）应用启动信息
3）日志配置
```

## 启动

```
启动器：Spring Boot: com.atosenet.ga.company.booter.Application
```

## 项目结构说明

### 1. 数据库相关

数据库DAO操作在 ga-company-database 项目下

```
JPA：
配置： com.atosenet.ga.company.db.config.JpaConfig

MyBatis ORM：
配置： 在com.atosenet.ga.company.db.config下面放入配置或使用

    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.1</version>
    </dependency>
    
进行配置（pom.xml中已添加）
```

#### 2.业务相关
业务相关的操作在ga-company-mgt 项目下，与UI的交互大部分体现在这里

```
MVC配置：com.atosenet.ga.company.mgt.config
控制器：com.atosenet.ga.company.mgt.controller
拦截器：com.atosenet.ga.company.mgt.interceptor
事务包装：com.atosenet.ga.company.mgt.service
```

#### 3.认证授权相关
认证授权相关的操作在ga-company-core 项目下，基于Shiro的无状态管理

```
登录

* 路径：/login
* 提交方式：ajax提交
* 参数：username,password
* 返回：
{
  "tk": "w/5kCrG1m/807ghSXuWe/+nUGd5gN8KqpB4HbbGU6G45ehzunXG3qN22R/yCVHZSjHmcg3qXhREmzq9AwjvZBw==",
  "un": "admin",
  "ts": 1504858224889,
  "resultCode": 200,
  "message": null
}
```

```
认证

* 路径： 被拦截的路径
* 提交方式：Ajax提交（只适配Ajax请求）
* Header：
    Content-Type:application/json
    X-Requested-With:XMLHttpRequest
    Authority:un=admin;ts=1504857750877;tk=amLjRdZt4nzdJDZAc28Cc4B+y2so8gNs1/159GPcl9fP6YAgvfFqlM8z2/PsX5yEriUpJtHlX3zGJTitZDz2hw==
    
注： 
    1）X-Requested-With作为后台判断是否为Ajax请求的标志
    2）Authority为登录后产生的数据所拼接的字符串
      后台校验优先从Header取Authority，其次从Cookie中取，Cookies的格式相当于Authority拆分的key-value形式
```

```
授权

* 访问带有Permission和Role限制的资源时，会进行授权
  授权根据用户名进行，从数据库中加载Role和Resource（Resource中写定了Permission）
```

```
Shiro Filter配置

* 可配置filter列表：com.atosenet.ga.company.mgt.config.SecurityConfig
    filterChainDefinitionMap.put("/favicon.ico", "anon");
    filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
    filterChainDefinitionMap.put("/login", "anon");
    filterChainDefinitionMap.put("/api/**", "anon");
    filterChainDefinitionMap.put("/data/api/**", "anon");
    filterChainDefinitionMap.put("/app/api/**", "anon");
```

```
获取用户信息
* 路径： /profile
* 提交方式：Ajax提交（POST）
* 参数：无
* 返回：
    {
        "user": {
            "id": 1,
            "account": "admin",
            "email": null,
            "mobilephone": null,
            "valid": true,
            "ipaddress": null,
            "createTime": 1505389406000,
            "updateTime": 1505389419000,
            "delFlag": 0
        },
        "roles": [
            "role_company_admin"
        ],
        "resources": [
            {
                "id": 1,
                "parentId": null,
                "parentIds": null,
                "name": "test_list_view",
                "sort": null,
                "href": null,
                "target": null,
                "icon": null,
                "show": null,
                "permission": "test:test:view",
                "createTime": null,
                "updateTime": null,
                "delFlag": null
            }
        ],
        "org": {
            "id": 1,
            "parentId": null,
            "depth": 0,
            "name": "中航通飞",
            "orgtype": 0,
            "jobtitle": null,
            "telephone": null,
            "sort": null,
            "createTime": 1504852290000,
            "updateTime": null,
            "delFlag": 0
        },
        "buz": {
            "companyId": 1
        },
        "resultCode": 200,
        "message": null
    }
```
特别说明：返回的对象存储在 `this.$profile`中，在.vue文件中使用`this.$profile.getProfile().then((profile)=>{}, (error)=>{})`可以获得 `profile`，其中`error`为出错的信息
* 1）返回信息中，buz对象作为扩展字段，以后需要的额外的信息，都在profile的这个buz对象中
* 2）返回的resources为此用户所持有的功能，用于构建菜单，permission字段对应着资源权限的名称，在授权的时候会加入权限缓存中
    若访问了自己不持有的授权资源，则会得到403响应
* 3）返回的user为此用户的基本信息，用于界面展示


#### 4.Swagger API相关
API文档在ga-company-doc 项目下，基于Swager

#### 5.应用监控相关
基于spring-boot-starter-actuator提供以下功能：

```
1）应用计数器（如URL访问计数）
2）组件（例如连接池）监控
```

#### 6.文件服务器相关
```
上传
* 服务器：fileserver，例如http://fs.glinkus.com:18081
* 路径： http://fs.glinkus.com:18081/upload
* 提交方式：POST
* 参数：file
* 返回
    {
        "resultCode": 200,
        "message": null,
        "url": "http://fs.glinkus.com:18081/file/sjlajxaskxk.jpg",
        "name": "MyPic.jpg"
    }

```

```
下载
* 服务器：fileserver，例如http://fs.glinkus.com:18081
* 路径： http://fs.glinkus.com:18081/file/[fileid]
* 提交方式：GET
* 参数：fileid
* 返回：文件数据

``` 