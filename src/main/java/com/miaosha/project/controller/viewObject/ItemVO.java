package com.miaosha.project.controller.viewObject;

import lombok.Data;
import org.joda.time.DateTime;

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

    //秒杀活动状态 1：未开始；2：进行中；0：无秒杀活动
    private Integer promoStatus;

    private BigDecimal promoPrice;

    private Integer promoId;

    private String startDate;
}
