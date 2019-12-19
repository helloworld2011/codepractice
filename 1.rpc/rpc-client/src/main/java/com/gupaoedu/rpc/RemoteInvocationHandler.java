package com.gupaoedu.rpc;

import com.gupaoedu.vip.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 6:21
 **/
public class RemoteInvocationHandler  implements InvocationHandler {

    private String host;
    private int prot;

    public RemoteInvocationHandler(String host, int prot) {
        this.host = host;
        this.prot = prot;
    }

    //传输本地请求方法到 server
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in");
        RpcRequest rpcRequest=new RpcRequest();
        //请求数据包装成 RpcRequest对象
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, prot);
        Object result = rpcNetTransport.send(rpcRequest);
        return  result;
    }
}
