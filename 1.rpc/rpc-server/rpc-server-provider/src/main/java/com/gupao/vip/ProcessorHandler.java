package com.gupao.vip;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author : xy
 * @Description :业务处理类
 * @Result:
 * @Date: Created in 2019/12/18 下午 3:35
 **/
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object object) {
        this.socket = socket;
        this.service = object;
    }

    /**
     * 处理输入流
     */
    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            //输入流
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            //输入流中应该有什么东西 读客户端请求的数据
            //请求那个类 方法名称 参数
            //反序列化成对象
            RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
            //调用本地方法
            Object result = invoke(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //把结果写回客户端
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
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


    }


    //调用本地服务
    public Object invoke(RpcRequest request) throws Exception{
        Object[] args = request.getParameters();
        Class[] types = new Class[args.length];
        for(int i=0;i<args.length;i++){
            types[i] = args[i].getClass();
        }
        Class clazz = Class.forName(request.getClassName());
        Method method = clazz.getMethod(request.getMethodName(), types);
        //利用反射执行方法
        Object result = method.invoke(service, args);
        return result;
    }
}
