package com.miaosha.project.service.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 11:47
 */

@Data
public class ItemModel {
    private Integer id;
    private String title;
    private BigDecimal price;
    private String description;
    //商品图的url
    private String imgUrl;

    private Integer stock;

    //商品的销量
    private Integer sales;


}
