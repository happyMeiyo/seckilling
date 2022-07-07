package com.miaosha.project.service;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.UserModel;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 15:02
 */
public interface UserService {
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException;
}
