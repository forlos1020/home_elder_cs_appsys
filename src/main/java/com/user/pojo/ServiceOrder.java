package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务订单
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("service_order")
@ApiModel(value = "ServiceOrder对象", description = "服务订单")
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单编号")
    private String orderId;

    @ApiModelProperty(value = "老年人编号")
    private String elderlyId;

    @ApiModelProperty(value = "老年人姓名")
    private String elderlyName;

    @ApiModelProperty(value = "监护人编号")
    private String guardianId;

    @ApiModelProperty(value = "监护人姓名")
    private String guardianName;

    @ApiModelProperty(value = "护理服务提供者编号")
    private String servProvId;

    @ApiModelProperty(value = "护理服务提供者姓名")
    private String servProvName;

    @ApiModelProperty(value = "护理状态")
    private String servState;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "护理开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "护理结束时间")
    private LocalDateTime endTime;

}
