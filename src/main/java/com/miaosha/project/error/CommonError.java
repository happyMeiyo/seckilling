package com.miaosha.project.error;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 16:23
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public void setErrMsg(String errMsg);
}
