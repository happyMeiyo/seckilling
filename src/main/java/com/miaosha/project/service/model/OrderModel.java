package com.miaosha.project.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/14 16:49
 */

@Data
public class OrderModel {
    private String id;
    private Integer userId;
    private Integer itemId;

    //购买商品的单价
    private BigDecimal itemPrice;
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;
}
