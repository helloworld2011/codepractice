package com.gupaoedu.rpc;

import com.gupaoedu.vip.RpcRequest;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 6:35
 **/
public class RpcNetTransport {

    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object send(RpcRequest request){
        Socket socket = null;
        Object result = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        //客户端 建立连接
        try {
            socket = new Socket(host, port);
            //构建输出流
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //向输出流中写入 request 对象
            objectOutputStream.writeObject(request);  //对象序列化
            objectOutputStream.flush();

            //读服务端返回的数据
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
