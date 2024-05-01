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

    public String phone;
    public String password;
    public String email;
    public String emailCode;
    public Integer loginType;
    public Integer registerType;

    public String code;
}
