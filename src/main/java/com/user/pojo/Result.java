package com.user.pojo;

public class Result {
    private Integer code;
    private String status;
    private String info;
    private Object data;

    public static Result success(String message){
        return new Result(200,"请求成功",message,null);
    }

    public static Result success(String message, Object data){
        return new Result(200,"请求成功",message,data);
    }

    public static Result error(String message){
        return new Result(500,"服务器错误",message,null);
    }

    public static Result error(String message, Object data){
        return new Result(500,"服务器错误",message,data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(Integer code, String status, String info, Object data) {
        this.code = code;
        this.status = status;
        this.info = info;
        this.data = data;
    }
}

