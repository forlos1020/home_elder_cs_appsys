package com.user.pojo;

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
public class LoginParam {

    @NonNull
    public String username;
    @NonNull
    public String password;
    @NonNull
    public String code;
}
