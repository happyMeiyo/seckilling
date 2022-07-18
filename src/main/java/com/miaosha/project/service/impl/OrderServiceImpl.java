package com.miaosha.project.service.impl;

import com.miaosha.project.dao.OrderDOMapper;
import com.miaosha.project.dao.SequenceDOMapper;
import com.miaosha.project.dataobject.OrderDO;
import com.miaosha.project.dataobject.SequenceDO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinessError;
import com.miaosha.project.service.ItemService;
import com.miaosha.project.service.OrderService;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.ItemModel;
import com.miaosha.project.service.model.OrderModel;
import com.miaosha.project.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/14 17:12
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private ItemService itemService;

    @Resource
    private UserService userService;

    @Resource
    private OrderDOMapper orderDOMapper;

    @Resource
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount, Integer promoId) throws BusinessException {
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买商品不存在");
        }

        UserModel userModel = userService.getUserById(userId);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户信息不存在");
        }

        if(amount <= 0 || amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "购买商品的数量不正确");
        }

        if(promoId !=null){
            if(promoId.intValue() != itemModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "秒杀信息不正确");
            }
            else if(itemModel.getPromoModel().getStatus() !=2){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "秒杀活动未开始");
            }
        }

        boolean result = itemService.decreaseStock(itemId, amount);
        if(!result)
        {
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH, "库存不足");
        }

        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if(promoId !=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(BigDecimal.valueOf(amount)));
        orderModel.setId(generatorOrderNo());

        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel, orderDO);
        orderDOMapper.insertSelective(orderDO);

        itemService.increaseSales(itemId, amount);

        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generatorOrderNo() {
        StringBuilder stringBuilder = new StringBuilder();

        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);

        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        Integer current = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(current + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        String sequenceStr = String.format("%06d", current);
//        stringBuilder.append("0".repeat(Math.max(0, 6 - sequenceStr.length())));
        stringBuilder.append(sequenceStr);

        stringBuilder.append("00");

        return String.valueOf(stringBuilder);
    }
}
