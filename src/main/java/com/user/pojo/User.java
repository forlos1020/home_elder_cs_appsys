package com.user.pojo;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="用户")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "用户密码")
    private String userPwd;

    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "用户性别(0-男,1-女)")
    private String userSex;

    @ApiModelProperty(value = "用户类型(1-老年人,2-监护人,3-护理人,4-系统管理员)")
    private String userType;

    @ApiModelProperty(value = "用户状态(1-正常,2-禁用,0-删除)")
    private String userState;

    @ApiModelProperty(value = "用户创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;


}
