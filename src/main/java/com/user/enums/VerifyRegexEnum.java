package com.user.enums;

/**
 * @Author: LSH
 * @Date: 2023/6/20-06-20-17:32
 * @Description: com.SwordBBS.constants.Enum
 * @Version: 1.0
 */
public enum VerifyRegexEnum {
    /**
     * 校验正则的枚举
     */
    NO("","不校验"),
    IP("^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$","IP地址"),
    POSITIVE_INTEGER("^[1-9]\\d*|0$","正整数"),
    NUMBER_LETTER_UNDERLINE("^\\w+$","数字、字母和下划线组成"),
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$","邮箱"),
    PHONE("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$","手机号码"),
    COMMON("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$","数字、字母、中文、下划线"),
    PASSWORD("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$","密码"),
    ACCOUNT("^[0-9a-zA-Z_]{1,)$","字母开头，由数字字母下划线组成"),
    MONEY("^[0-9]+(.[0-9]{1,2)?$","金额");


    private String regex;
    private String desc;

    public String getRegex() {
        return regex;
    }

    public String getDesc() {
        return desc;
    }

    VerifyRegexEnum(String regex, String desc) {
        this.regex = regex;
        this.desc = desc;
    }
}
