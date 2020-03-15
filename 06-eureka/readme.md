### 单机Eureka部署

#### 运行[Eureka服务器](./01-discovery-server)(standalone模式)

校验: http://localhost:8761/

#### 运行[Time微服务服务端](./02-time-service)
在Eclipse IDE中，以定制配置方式运行time-service微服务，
Edit Configurations中配置服务启动端口`Override Properties`

```
server.port=4444
server.port=5555
```

UI校验：
http://localhost:8761/

API校验：
http://localhost:8761/eureka/apps

#### 运行[Time微服务客户端](./03-time-client)(Ribbon负载均衡模式)

校验Ribbon负载均衡生效，浏览器访问：
http://localhost:8080


**[集群模式部署](https://github.com/spring2go/eureka_lab/tree/master/lab02)**