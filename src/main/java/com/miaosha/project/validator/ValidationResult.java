package com.miaosha.project.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 09:57
 */

public class ValidationResult {
    private boolean hasErrors = false;
    private Map<String, String> errorMsgMap = new HashMap<>();

    public String getErrorMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }


    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
