package com.miaosha.project.service.impl;

import com.miaosha.project.dao.UserDOMapper;
import com.miaosha.project.dao.UserPasswordDOMapper;
import com.miaosha.project.dataobject.UserDO;
import com.miaosha.project.dataobject.UserPasswordDO;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 15:02
 */

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDOMapper userDOMapper;

    @Resource
    private UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDO == null) return null;

        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());

        return convertFromDataObject(userDO, userPasswordDO);
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO){
        if (userDO == null) return null;
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if(userPasswordDO != null)
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());

        return userModel;
    }
}
