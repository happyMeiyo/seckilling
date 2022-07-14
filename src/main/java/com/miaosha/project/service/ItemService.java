package com.miaosha.project.service;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.ItemModel;

import java.util.List;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 13:43
 */
public interface ItemService {
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    List<ItemModel> listItem();

    ItemModel getItemById(Integer id);

    //扣减库存
    boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException;
}
