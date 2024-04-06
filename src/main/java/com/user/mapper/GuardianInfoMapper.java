package com.user.mapper;

import com.user.pojo.GuardianInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【guardian_info(监护人数据)】的数据库操作Mapper
* @createDate 2024-04-06 13:10:02
* @Entity com.user.pojo.GuardianInfo
*/
@Mapper
public interface GuardianInfoMapper extends BaseMapper<GuardianInfo> {

}




