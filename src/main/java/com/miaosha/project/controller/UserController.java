package com.miaosha.project.controller;

import com.miaosha.project.controller.viewObject.UserVO;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 14:58
 */

@Controller("user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public UserVO getUser(@RequestParam(name="id") Integer id){
        UserModel userModel = userService.getUserById(id);

        return convertFromModel(userModel);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null) return null;

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
