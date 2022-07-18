package com.miaosha.project.controller;

import com.miaosha.project.controller.viewObject.ItemVO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.response.CommonReturnType;
import com.miaosha.project.service.ItemService;
import com.miaosha.project.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.joda.time.format.DateTimeFormat.*;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 14:13
 */

@Controller("item")
@CrossOrigin(originPatterns = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping(value = "/item")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    @ResponseBody
    @Transactional
    public CommonReturnType createItem(@RequestParam(name = "title")String title,
                                       @RequestParam(name = "description")String description,
                                       @RequestParam(name = "imgUrl")String imgUrl,
                                       @RequestParam(name = "price") BigDecimal price,
                                       @RequestParam(name = "stock") Integer stock) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setTitle(title);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModel1 = itemService.createItem(itemModel);

        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel1, itemVO);
        return CommonReturnType.create(itemVO);
    }

    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
        ItemModel itemModel = itemService.getItemById(id);

        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);

        if(itemModel.getPromoModel() != null){
            itemVO.setPromoId(itemModel.getPromoModel().getId());
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(forPattern("yyyy-MM-dd HH:mm:ss")));
        }else
            itemVO.setPromoStatus(0);
        return CommonReturnType.create(itemVO);
    }

    @GetMapping("/list")
    @ResponseBody
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList = itemService.listItem();

        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = new ItemVO();
            BeanUtils.copyProperties(itemModel, itemVO);
            return itemVO;
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }
}
