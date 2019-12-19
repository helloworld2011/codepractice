package com.gupao.vip;

/**
 * @Author : xy
 * @Description :
 * @Result:
 * @Date: Created in 2019/12/18 下午 3:17
 **/
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String content) {
        System.out.println("request in sayHello"+content);
        return "say hello"+content;
    }
}
