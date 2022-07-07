package com.miaosha.project.error;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 16:25
 */
public enum EmBusinessError implements CommonError {
    PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
    UNKNOWN_ERROR(10002, "未知错误"),
    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "用户登录失败");

    private final int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }
    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
