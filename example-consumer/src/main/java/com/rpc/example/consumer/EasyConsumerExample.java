package com.rpc.example.consumer;


import com.rpc.example.common.model.User;
import com.rpc.example.common.service.UserService;
import com.rpc.proxy.ServiceProxyFactory;

public class EasyConsumerExample {
  public static void main(String[] args) {
    UserService userService = ServiceProxyFactory.getProxy(UserService.class);
    User user = new User();
    user.setName("hch");
    User newUser = userService.getUser(user);
    if(newUser != null) {
      System.out.println(newUser.getName());
    }else{
      System.out.println("user == null");
    }
  }
}
