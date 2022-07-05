package com.miaosha.project.error;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 16:50
 */
public class BusinessException extends Exception implements CommonError{
    private final CommonError commonError;

    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public void setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
    }
}
