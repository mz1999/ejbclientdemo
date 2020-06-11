# EJB多语言客户端演示

## 将`EJB`发布为`Restful Web Service`

`EJB3`的`Stateless Session Bean`可以同时支持`RMI-IIOP`和`Web Service`等多种远程调用方式，只要Bean同时实现多种接口：

* 业务接口

```
public interface BusinessService {

    public Response createOrder(String userId, String commodityCode, int count);

    public List<String> getUserList(String start, String end);
}

``` 

* `Remote`接口

```
@Remote
public interface BusinessServiceRemote extends BusinessService {
}
```

* `Local`和`Restful Web Service`接口

```
@Local
@Path("business")
public interface BusinessServiceLocal extends BusinessService {

    @POST
    @Path("/createOrder")
    @Produces("application/json")
    @Override
    public Response createOrder(@FormParam("userId") String userId,
                                @FormParam("commodityCode") String commodityCode,
                                @FormParam("count") int count);

    @GET
    @Path("/getUserList")
    @Produces("application/json")
    @Override
    public List<String> getUserList(@QueryParam("start") String start,
                                    @QueryParam("end") String end);
}
```

* Bean同时实现多种接口

```
@Stateless
public class BusinessServiceBean implements BusinessService,
        BusinessServiceLocal, BusinessServiceRemote, Serializable {
...
```

## C++ REST客户端

[cpprestsdk](https://github.com/microsoft/cpprestsdk) 是微软开源的 C++ REST SDK，使用了现代异步C ++ API设计，帮助C ++的开发人员与`Web Services`进行连接和交互。

本示例使用了`cpprestsdk`，演示了`POST`和`GET`两种`HTTP method`访问`EJB`暴露的`Web Services`：

* [ejbcppclientcreateorder.cpp](./cpprestclient/ejbcppclientcreateorder.cpp) 访问`BusinessService.createOrder`

* [ejbcppclientgetuserlist.cpp](./cpprestclient/ejbcppclientgetuserlist.cpp)  访问`BusinessService.getUserList`

## Python REST客户端

[Requests](https://github.com/psf/requests) 是Python的第三方库，让处理HTTP请求变得非常简单。

本示例使用了`Requests`访问`EJB`暴露的`Web Services`：

* [ejbpythonclient.py](./pythonrestclient/ejbpythonclient.py) 访问`BusinessService.createOrder`

## Java客户端

Java客户端运行在`Application Client Container (ACC)`中。`ACC`使用`RMI-IIOP`协议与应用服务器通信。 

Java客户端[business-client](./business-client)打包在 [business-ear](./business-ear) 中部署到应用服务器，使用`asadmin get-client-stubs`命令获得`Client Stubs`：

```
./asadmin get-client-stubs --appname business-ear .
```

运行Java客户端：

```
./appclient -client business-earClient.jar -targetserver localhost:3700  [userId] [commodityCode] [count]
```
