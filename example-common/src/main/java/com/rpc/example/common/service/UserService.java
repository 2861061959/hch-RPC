package com.rpc.example.common.service;

import com.rpc.example.common.model.User;

/**
 * 获取用户
 */
public interface UserService {

  /**
   * 获取用户
   * @param user 用户
   * @return
   */
  User getUser(User user);
}
