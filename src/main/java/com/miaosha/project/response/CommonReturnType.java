package com.miaosha.project.response;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectJoin;
import lombok.Data;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/5 16:12
 */
@Data
public class CommonReturnType {
    private String status;
    private Object data;

    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, "success");
    }

    public static CommonReturnType create(Object result, String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);

        return type;
    }


}
