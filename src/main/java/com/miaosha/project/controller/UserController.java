package com.miaosha.project.controller;

import cn.hutool.core.codec.Base64Encoder;
import com.miaosha.project.controller.viewObject.UserVO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinessError;
import com.miaosha.project.response.CommonReturnType;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.UserModel;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 14:58
 */

@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController("user")
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("/getotp")
    public CommonReturnType getOtp(@RequestParam(name="telephone") String telephone){
        Random random = new Random();
        int randomInt = random.nextInt(99999) + 10000;
        String optCode = String.format("%06d", randomInt);

        httpServletRequest.getSession().setAttribute(telephone, optCode);

        System.out.println("telephone = " + telephone + ", otpCode = " + optCode);
        return CommonReturnType.create(optCode);
    }

    @PostMapping("/register")
    @Transactional
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Byte gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException {
        String otpCodeInSession = String.valueOf(this.httpServletRequest.getSession().getAttribute(telephone));
        if (!StringUtils.nullSafeEqual(otpCodeInSession, otpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不一致");
        }

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("phone");
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setEncrptPassword(this.EncodeByMd5(password));

        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    @PostMapping("/login")
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException {
        if(StringUtils.isEmptyOrWhitespaceOnly(telephone) || StringUtils.isEmptyOrWhitespaceOnly(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "输入用户名或密码为空");
        }

        UserModel userModel = userService.validateLogin(telephone, this.EncodeByMd5(password));

        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        return CommonReturnType.create(null);
    }

    @GetMapping("/get")
    public CommonReturnType getUser(@RequestParam(name = "id") Integer id) throws BusinessException{
        UserModel userModel = userService.getUserById(id);
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);

    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) return null;

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
}
