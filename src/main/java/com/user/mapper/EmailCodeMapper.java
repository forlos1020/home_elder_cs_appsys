package com.user.mapper;

import com.user.pojo.EmailCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【email_code(邮箱验证码)】的数据库操作Mapper
* @createDate 2024-04-19 10:57:52
* @Entity com.user.pojo.EmailCode
*/
@Mapper
public interface EmailCodeMapper extends BaseMapper<EmailCode> {

}




