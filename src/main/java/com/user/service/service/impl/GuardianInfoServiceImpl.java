package com.user.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.pojo.GuardianInfo;
import com.user.service.service.GuardianInfoService;
import com.user.mapper.GuardianInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【guardian_info(监护人数据)】的数据库操作Service实现
* @createDate 2024-04-06 13:10:02
*/
@Service
public class GuardianInfoServiceImpl extends ServiceImpl<GuardianInfoMapper, GuardianInfo>
    implements GuardianInfoService{

}




