package com.miaosha.project.error;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 16:23
 */
public interface CommonError {
    int getErrCode();

    String getErrMsg();

    void setErrMsg(String errMsg);
}
