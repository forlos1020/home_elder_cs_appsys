package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
