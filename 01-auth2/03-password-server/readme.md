基于密码模式+Spring Security OAuth2的最简授权服务器
======

# 操作方式

### 1. 获取访问令牌

```shell script
curl -X POST --user clientapp:112233 http://localhost:8080/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=bobo&password=xyz&scope=read_userinfo"
```

响应案例：
```json 
{
    "access_token": "58a02fd5-87f5-44ff-bbdd-d429cf6a2f60",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read_userinfo"
}
```

### 2. 调用API

```shell script
curl -X GET http://localhost:8080/api/userinfo -H "authorization: Bearer 58a02fd5-87f5-44ff-bbdd-d429cf6a2f60"
```

案例响应：

```json
{
    "name": "bobo",
    "email": "bobo@spring2go.com"
}
```

详见[03-password-server.postman_collection.json](src/main/resources/03-password-server.postman_collection.json)