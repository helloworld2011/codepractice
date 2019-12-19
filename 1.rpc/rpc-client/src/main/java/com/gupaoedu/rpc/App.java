package com.gupaoedu.rpc;

import com.gupaoedu.vip.IHelloService;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 6:12
 **/
public class App {
    public static void main(String[] args) {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService iHelloService = rpcProxyClient.clientProxy(IHelloService.class, "localhost", 8080);
        String result = iHelloService.sayHello("mic");
        System.out.println(result);

    }
}
