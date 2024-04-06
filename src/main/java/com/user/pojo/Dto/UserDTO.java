package com.user.pojo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author： lsh
 * @create： 2023-12-11 16:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO implements Serializable {
    private String userId;
    private String userRealname;
    private String userPhone;
    private Integer userType;
}
