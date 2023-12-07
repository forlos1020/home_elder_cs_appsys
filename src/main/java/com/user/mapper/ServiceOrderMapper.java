package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.ServiceOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 服务订单 Mapper 接口
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Mapper
public interface ServiceOrderMapper extends BaseMapper<ServiceOrder> {

}
