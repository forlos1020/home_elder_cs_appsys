package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "service_order")
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private String elderlyId;

    private String elderlyName;

    private String guardianId;

    private String guardianName;

    private String servProvId;

    private String servProvName;

    private String servState;

    private BigDecimal orderAmount;

    private LocalDateTime createTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer stars;

}
