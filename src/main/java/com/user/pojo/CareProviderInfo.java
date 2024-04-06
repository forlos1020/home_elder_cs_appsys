package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 护理服务提供者数据
 * @TableName care_provider_info
 */
@TableName(value ="care_provider_info")
@Data
public class CareProviderInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId
    private String userId;

    /**
     * 用户姓名
     */
    private String userRealname;

    /**
     * 用户评分
     */
    private BigDecimal userScore;

    /**
     * 用户评分人次
     */
    private Integer reviewsNum;

    /**
     * 用户差评数
     */
    private Integer badReviewsGes;

    /**
     * 用户接单数
     */
    private Integer orderNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        CareProviderInfo other = (CareProviderInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserRealname() == null ? other.getUserRealname() == null : this.getUserRealname().equals(other.getUserRealname()))
            && (this.getUserScore() == null ? other.getUserScore() == null : this.getUserScore().equals(other.getUserScore()))
            && (this.getReviewsNum() == null ? other.getReviewsNum() == null : this.getReviewsNum().equals(other.getReviewsNum()))
            && (this.getBadReviewsGes() == null ? other.getBadReviewsGes() == null : this.getBadReviewsGes().equals(other.getBadReviewsGes()))
            && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserRealname() == null) ? 0 : getUserRealname().hashCode());
        result = prime * result + ((getUserScore() == null) ? 0 : getUserScore().hashCode());
        result = prime * result + ((getReviewsNum() == null) ? 0 : getReviewsNum().hashCode());
        result = prime * result + ((getBadReviewsGes() == null) ? 0 : getBadReviewsGes().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userRealname=").append(userRealname);
        sb.append(", userScore=").append(userScore);
        sb.append(", reviewsNum=").append(reviewsNum);
        sb.append(", badReviewsGes=").append(badReviewsGes);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}