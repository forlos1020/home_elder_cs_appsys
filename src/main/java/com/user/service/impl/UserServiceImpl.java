package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.UserMapper;
import com.user.pojo.Result;
import com.user.pojo.User;
import com.user.service.UserService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2024-01-12 15:52:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Override
    public Result certification(String realName, String idNumber, String userId) throws IOException {
        String url = "https://eid.shumaidata.com/eid/check";
        String appCode = "e2863f1ebf1a4857838cba5775d14d70";
        Map<String, String> params = new HashMap<>();
        params.put("idcard", idNumber);
        params.put("name", realName);
        Boolean result = postForm(appCode, url, params);
        if(!result){
            return Result.error("未知错误");
        }
        User one = this.lambdaQuery().eq(User::getIdNum, idNumber).one();
        if(one != null){
            return Result.error("该用户已有账号");
        }
        else{
            this.lambdaUpdate().eq(User::getUserId,userId).set(User::getUserRealname,realName).set(User::getIdNum,idNumber)
                    .update();
        }
        return Result.success("请求成功");
    }
    public static Boolean postForm(String appCode, String url, Map<String, String> params) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder formbuilder = new FormBody.Builder();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            formbuilder.add(key, params.get(key));
        }
        FormBody body = formbuilder.build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        if(response.code()!= 200){
            return false;
        }
        return true;
    }
}