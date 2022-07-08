package com.miaosha.project.controller.viewObject;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 14:13
 */
@Data
public class ItemVO {
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
