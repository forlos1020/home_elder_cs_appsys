package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 订单操作详细
 */
@TableName(value ="order_operation")
@Data
public class OrderOperation implements Serializable {
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 操作人Id
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作
     */
    private Object operationState;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderOperation other = (OrderOperation) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
                && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
                && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
                && (this.getOperationState() == null ? other.getOperationState() == null : this.getOperationState().equals(other.getOperationState()))
                && (this.getOperationTime() == null ? other.getOperationTime() == null : this.getOperationTime().equals(other.getOperationTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getOperationState() == null) ? 0 : getOperationState().hashCode());
        result = prime * result + ((getOperationTime() == null) ? 0 : getOperationTime().hashCode());
        return result;
    }

}