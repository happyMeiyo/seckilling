package com.miaosha.project.service;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.OrderModel;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/14 17:10
 */
public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException;
}
