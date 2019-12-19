package com.gupao.vip;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 3:19
 **/
public class App {

    public static void main(String[] args) throws Exception{
        //1.提供服务
        HelloServiceImpl helloService = new HelloServiceImpl();
        //2.构造一个RpcProxyServer,远程代理
        RpcProxyServer proxyServer = new RpcProxyServer();
        //3.
        proxyServer.publish(helloService,8080);
    }
}
