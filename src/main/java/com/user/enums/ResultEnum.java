package com.user.enums;

/**
 * @author： lsh
 * @create： 2023-12-06 11:33
 */
public enum ResultEnum {
    //这里是可以自己定义的，方便与前端交互即可
    ERROR(500,"请求失败"),
    SUCCESS(200,"请求成功"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
