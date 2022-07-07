package com.miaosha.project.service.impl;

import com.miaosha.project.dao.UserDOMapper;
import com.miaosha.project.dao.UserPasswordDOMapper;
import com.miaosha.project.dataobject.UserDO;
import com.miaosha.project.dataobject.UserPasswordDO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinessError;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if(StringUtils.isEmpty(userModel.getName())
        || StringUtils.isEmpty(userModel.getTelephone())
        || userModel.getGender() == null
        || userModel.getAge() == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        try {
            userDOMapper.insertSelective(userDO);
        } catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已重复注册");
        }
        userModel.setId(userDO.getId());

        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setUserId(userModel.getId());
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());

        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel validateLogin(String telephone, String encrptPassword) throws BusinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if (userDO == null){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL, "用户名或密码不正确！");
        }

        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel = convertFromDataObject(userDO, userPasswordDO);

        if(!StringUtils.equals(encrptPassword, userModel.getEncrptPassword())){
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL, "用户名或密码不正确！");
        }

        return userModel;
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
