package com.user.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author： lsh
 * @create： 2024-04-22 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListParams {
    public LocalDateTime[] orderTime;
    public String orderStatus;
    public Integer page;
    public Integer size;
}
