package com.user.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author： lsh
 * @create： 2023-12-06 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  LoginParam {

    @NonNull
    public String phone;
    @NonNull
    public String password;
    @NonNull
    public String code;
}
