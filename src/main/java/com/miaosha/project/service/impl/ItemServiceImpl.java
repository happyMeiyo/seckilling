package com.miaosha.project.service.impl;

import com.miaosha.project.dao.ItemDOMapper;
import com.miaosha.project.dao.ItemStockDOMapper;
import com.miaosha.project.dataobject.ItemDO;
import com.miaosha.project.dataobject.ItemStockDO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinessError;
import com.miaosha.project.service.ItemService;
import com.miaosha.project.service.PromoService;
import com.miaosha.project.service.model.ItemModel;
import com.miaosha.project.service.model.PromoModel;
import com.miaosha.project.validator.ValidationResult;
import com.miaosha.project.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 13:44
 */

@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ValidatorImpl validator;

    @Resource
    ItemDOMapper itemDOMapper;

    @Resource
    ItemStockDOMapper itemStockDOMapper;

    @Resource
    private PromoService promoService;

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        ValidationResult validationResult = validator.validate(itemModel);
        if (validationResult.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrorMsg());
        }

        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel, itemDO);
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        itemStockDOMapper.insertSelective(itemStockDO);


        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        return itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());
            ItemModel itemModel = new ItemModel();
            BeanUtils.copyProperties(itemDO, itemModel);
            itemModel.setStock(itemStockDO.getStock());
            return itemModel;
        }).collect(Collectors.toList());
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null) {
            return null;
        }

        ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(itemDO.getId());

        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO, itemModel);
        itemModel.setStock(itemStockDO.getStock());

        PromoModel promoModel = promoService.getPromoByItemId(id);
        if(promoModel != null && promoModel.getStatus() !=3){
            itemModel.setPromoModel(promoModel);
        }

        return itemModel;
    }

    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {
        int affectedRow = itemStockDOMapper.decreaseStock(itemId, amount);
        return affectedRow > 0;
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) {
        itemDOMapper.increaseSales(itemId, amount);
    }
}
