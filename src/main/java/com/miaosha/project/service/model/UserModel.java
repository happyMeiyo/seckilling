package com.miaosha.project.service.model;

import lombok.Data;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 15:06
 */

@Data
public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;
    private String registerMode;
    private String thirdPartyId;

    private String encrptPassword;

}
