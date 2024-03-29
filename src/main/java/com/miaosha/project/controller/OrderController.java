package com.miaosha.project.controller;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinessError;
import com.miaosha.project.response.CommonReturnType;
import com.miaosha.project.service.OrderService;
import com.miaosha.project.service.model.OrderModel;
import com.miaosha.project.service.model.UserModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/15 09:56
 */
@RestController("order")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/order")
public class OrderController extends BaseController  {
    @Resource
    private OrderService orderService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @PostMapping("/create")
    @Transactional
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name="promoId", required = false) Integer promoId) throws BusinessException {
        Boolean isLogin  = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin)
        {
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }

        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel =  orderService.createOrder(userModel.getId(), itemId, amount, promoId);

        return CommonReturnType.create(orderModel);
    }
}
