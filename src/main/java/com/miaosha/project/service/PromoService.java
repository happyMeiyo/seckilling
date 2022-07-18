package com.miaosha.project.service;

import com.miaosha.project.service.model.PromoModel;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/15 15:49
 */
public interface PromoService {

    PromoModel getPromoByItemId(Integer itemId);
}
