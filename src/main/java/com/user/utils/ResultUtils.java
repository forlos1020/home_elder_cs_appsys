package com.user.utils;


import com.user.enums.ResultEnum;
import com.user.pojo.Result;

/**
 * @author： lsh
 * @create： 2023-12-06 11:34
 */
public class ResultUtils {
    /**
     * 成功，且返回体有数据
     * @param object
     * @return
     */
    public static Result<Object> success(Object object) {
        Result<Object> r = new Result<Object>();
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMsg(ResultEnum.SUCCESS.getMsg());
        r.setData(object);
        return r;
    }

    /**
     * 成功，返回体无数据
     * @return
     */
    public static Result<Object> success() {
        return success(null);
    }

    /**
     * 成功，自定义返回消息
     * @return
     */
    public static Result<Object> success(Integer code, String message,Object object) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMsg(message);
        r.setData(object);
        return r;
    }

    /**
     * 失败，返回默认消息
     * @return
     */
    public static Result<Object> error() {
        Result<Object> r = new Result<Object>();
        r.setCode(ResultEnum.ERROR.getCode());
        r.setMsg(ResultEnum.ERROR.getMsg());
        return r;
    }

    /**
     * 失败, 自定义返回消息
     * @param code
     * @param message
     * @return
     */
    public static Result<Object> error(Integer code, String message) {
        Result<Object> r = new Result<Object>();
        r.setCode(code);
        r.setMsg(message);
        return r;
    }

}
