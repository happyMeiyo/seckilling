package com.miaosha.project.service.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/15 15:26
 */
@Data
public class PromoModel {
    private Integer id;

    //秒杀活动状态 1：未开始；2：进行中；3：已结束
    private Integer status;

    private String promoName;

    private DateTime startDate;

    private DateTime endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;
}
