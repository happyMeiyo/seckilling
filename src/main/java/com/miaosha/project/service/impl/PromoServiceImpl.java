package com.miaosha.project.service.impl;

import com.miaosha.project.dao.PromoDOMapper;
import com.miaosha.project.dataobject.PromoDO;
import com.miaosha.project.service.PromoService;
import com.miaosha.project.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/15 15:50
 */
@Service
public class PromoServiceImpl implements PromoService {
    @Resource
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        if(promoDO == null) return null;

        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));

        if(promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }
        else if(promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);
        }
        else
            promoModel.setStatus(2);
        return promoModel;
    }
}
