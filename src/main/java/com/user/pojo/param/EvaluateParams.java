package com.user.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * @author： lsh
 * @create： 2024-04-06 13:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluateParams {
    //当期用户手机号
    @NonNull
    public String userPhone;
    //订单Id
    @NonNull
    public String orderId;
    //订单评分
    @NonNull
    public Integer stars;


}
