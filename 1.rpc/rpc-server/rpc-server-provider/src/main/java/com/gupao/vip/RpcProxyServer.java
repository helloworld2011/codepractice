package com.gupao.vip;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 3:20
 **/
public class RpcProxyServer {

    ExecutorService executorService= Executors.newCachedThreadPool();

    public void publish(Object service,int port) throws Exception{
        ServerSocket serverSocket = null;
        //构建服务端客户端
        serverSocket = new ServerSocket();
        while (true){
            Socket socket = serverSocket.accept();
            executorService.execute(new ProcessorHandler(socket,service));
        }
    }
}
