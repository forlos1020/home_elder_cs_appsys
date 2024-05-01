package com.user.annotations;


import com.user.enums.VerifyRegexEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: LSH
 * @Date: 2023/6/20-06-20-17:29
 * @Description: com.SwordBBS.annotation
 * @Version: 1.0
 */

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyParam {
    boolean required() default false;

    int max() default -1;

    int min() default -1;

    /**
     * 正则
     */
    VerifyRegexEnum regex() default VerifyRegexEnum.NO;
}
