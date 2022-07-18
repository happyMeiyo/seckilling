package com.miaosha.project.service.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 11:47
 */

@Data
public class ItemModel {
    private Integer id;

    @NotBlank(message = "商品名称必填")
    private String title;

    @NotNull(message = "商品价格必填")
    @Min(value = 0, message = "商品价格必须大于0")
    private BigDecimal price;

    @NotBlank(message = "商品描述必填")
    private String description;

    //商品图的url
    @NotBlank(message = "商品图片必填")
    private String imgUrl;

    @NotNull(message = "商品库存必填")
    private Integer stock;

    //商品的销量
    private Integer sales;

    //使用聚合模型
    private PromoModel promoModel;

}
