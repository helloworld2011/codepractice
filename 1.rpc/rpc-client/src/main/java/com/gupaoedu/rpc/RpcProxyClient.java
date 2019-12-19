package com.gupaoedu.rpc;

import java.lang.reflect.Proxy;

/**
 * @Author : xy
 * @Description :动态代理类
 * @Result:
 * @Date: Created in 2019/12/18 下午 6:14
 **/
public class RpcProxyClient {

    public <T> T clientProxy(final Class<T> interfaceCls,final String host,final int port){
       return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class<?>[]{interfaceCls},
                new RemoteInvocationHandler(host,port));
    }
}
