package com.miaosha.project.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Author: my.deng@tuya.com
 * Date: 2022/7/8 10:02
 */

@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    //实现检验方法并返回检验结果
    public ValidationResult validate(Object bean){
        final ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> ConstraintValidatorSet = validator.validate(bean);
        if(ConstraintValidatorSet.size()>0){
            result.setHasErrors(true);
            ConstraintValidatorSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();

                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }

        return result;
    }


    @Override
    public void afterPropertiesSet(){
        //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
