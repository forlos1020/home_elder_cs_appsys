package com.user.annotations;

/**
 * @Author: LSH
 * @Date: 2023/6/20-06-20-16:41
 * @Description: com.SwordBBS.annotation
 * @Version: 1.0
 */


import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GlobalInterceptor {
    /**
     * 是否需要登录
     * @return
     */
    boolean checkLogin() default false;

    /**
     * 是否需要校验参数
     * @return
     */
    boolean checkParams() default false;

}
