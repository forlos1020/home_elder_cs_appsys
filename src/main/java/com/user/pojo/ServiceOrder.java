package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    private Integer stars;

}
