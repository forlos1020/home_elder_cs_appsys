package com.user.exception;

/**
 * @Author: LSH
 * @Date: 2023/6/20-06-20-18:15
 * @Description: com.SwordBBS.constants
 * @Version: 1.0
 */
public class RegexErrorException extends Exception{
    public RegexErrorException(String msg){
        //super中的字符串代表抛出异常时打印的信息
       super(msg);
    }
}