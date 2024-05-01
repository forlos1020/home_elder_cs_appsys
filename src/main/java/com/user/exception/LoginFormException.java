package com.user.exception;

/**
 * @Author: LSH
 * @Date: 2023/6/25-06-25-12:37
 * @Description: com.SwordBBS.constants.Exception
 * @Version: 1.0
 */
public class LoginFormException extends Exception{
    public LoginFormException(String msg){
        //super中的字符串代表抛出异常时打印的信息
        super(msg);
    }
}
