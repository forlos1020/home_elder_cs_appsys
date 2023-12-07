package com.user.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author： lsh
 * @create： 2023-12-06 15:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderParam {

    public String orderId;
    @NonNull
    public String elderlyId;
    @NonNull
    public String elderlyName;
    @NonNull
    public String guardianId;
    @NonNull
    public String guardianName;
    @NonNull
    public BigDecimal orderAmount;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime startTime;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public LocalDateTime endTime;

}
