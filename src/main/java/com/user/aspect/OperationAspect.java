package com.user.aspect;


import com.user.annotations.GlobalInterceptor;
import com.user.annotations.VerifyParam;
import com.user.exception.NotLoginException;
import com.user.exception.RegexErrorException;
import com.user.enums.VerifyRegexEnum;
import com.user.pojo.Result;
import com.user.utils.VerifyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: LSH
 * @Date: 2023/6/20-06-20-16:48
 * @Description: com.SwordBBS.aspect
 * @Version: 1.0
 */
@Component
@Aspect
public class OperationAspect {

    private Logger logger = LoggerFactory.getLogger(OperationAspect.class);

    private static final String[] TYPE_BASE = {"java.lang.String", "java.lang.Integer", "java.lang.Long"};

    @Pointcut("@annotation(com.user.annotations.GlobalInterceptor)")
    private void requestInterceptor() {

    }

    @Around("requestInterceptor()")
    public Object interceptorDo(ProceedingJoinPoint point) throws RegexErrorException {
        try {
            Object target = point.getTarget();
            Object[] args = point.getArgs();
            String methodName = point.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (interceptor == null) {
                return null;
            }

            //校验登录
            if (interceptor.checkLogin()) {
                checkLogin();
            }

            //校验参数
            if (interceptor.checkParams()) {
                validateParams(method, args);
            }
            Object pointResult = point.proceed();
            if (pointResult instanceof Result) {
                Result result = (Result) pointResult;
                if ("200".equals(result.getCode())) {

                }
            }
            return pointResult;
        } catch (RegexErrorException e) {
            throw new RegexErrorException(e.getMessage());
        } catch (NotLoginException e) {
            throw new RegexErrorException(e.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RegexErrorException("服务器错误");
        }
    }




    private void checkLogin() throws NotLoginException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("Login");
        if (user == null) {
            throw new NotLoginException("用户未登录");
        }
    }

    /**
     * 校验参数
     *
     * @param method 方法名
     * @param args   参数
     */
    private void validateParams(Method method, Object[] args) throws RegexErrorException {
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = args[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            if (ArrayUtils.contains(TYPE_BASE, parameter.getParameterizedType().getTypeName())) {
                checkValue(value, verifyParam);
            }
        }
    }

    private void checkObjectValue(Parameter parameter, Object value) throws RegexErrorException {
        try {
            String typeName = parameter.getParameterizedType().getTypeName();
            Class clazz = Class.forName(typeName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                VerifyParam fieldVerifyParam = field.getAnnotation(VerifyParam.class);
                if(fieldVerifyParam == null){
                    continue;
                }
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, fieldVerifyParam);
            }
        } catch (Exception e){
            logger.error("参数校验失败",e);
            throw new RegexErrorException("参数校验失败");
        }
    }
    private void checkValue(Object value, VerifyParam verifyParam) throws RegexErrorException {
        boolean isEmpty = value == null || StringUtils.isEmpty(value.toString());
        int length = value == null ? 0 : value.toString().length();
        if (isEmpty || length == 0) {
            throw new RegexErrorException("参数为空");
        }
        /**
         * 校验参数
         */
        if (verifyParam.regex() == VerifyRegexEnum.NO) {
            return;
        } else if (!VerifyUtils.verify(verifyParam.regex(), String.valueOf(value))) {
            throw new RegexErrorException("参数不正确");
        }
    }
}
