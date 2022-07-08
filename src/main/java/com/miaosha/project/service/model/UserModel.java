package com.miaosha.project.service.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 15:06
 */

@Data
public class UserModel {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String name;

    @NotNull(message = "性别必须填写")
    private Byte gender;

    @NotNull(message = "年龄必须填写")
    @Min(value = 0, message = "年龄必须大于0")
    @Max(value = 150, message = "年龄必须小于150")
    private Integer age;

    @NotBlank(message = "手机号必须填写")
    private String telephone;
    private String registerMode;
    private String thirdPartyId;

    @NotBlank(message = "密码必须填写")
    private String encrptPassword;

}
