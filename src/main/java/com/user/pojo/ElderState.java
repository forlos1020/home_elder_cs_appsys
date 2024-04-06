package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 老年人身体数据
 * @TableName elder_state
 */
@TableName(value ="elder_state")
@Data
public class ElderState implements Serializable {
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
     * 用户身高
     */
    private String userHeight;

    /**
     * 用户体重
     */
    private String userWeight;

    /**
     * 用户过敏信息
     */
    private String userAllergyInfo;

    /**
     * 用户近五次血压测量状况
     */
    private Object lastFiveBloodPressure;

    /**
     * 用户近五次血脂测量状况
     */
    private Object lastFiveBloodLipids;

    /**
     * 用户近五次血糖测量状况
     */
    private Object lastFiveBloodSugar;

    /**
     * 用户BMI指数
     */
    private String userBmi;

    /**
     * 用户关联监护人id
     */
    private String associatedGuardiansId;

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
        ElderState other = (ElderState) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserRealname() == null ? other.getUserRealname() == null : this.getUserRealname().equals(other.getUserRealname()))
            && (this.getUserHeight() == null ? other.getUserHeight() == null : this.getUserHeight().equals(other.getUserHeight()))
            && (this.getUserWeight() == null ? other.getUserWeight() == null : this.getUserWeight().equals(other.getUserWeight()))
            && (this.getUserAllergyInfo() == null ? other.getUserAllergyInfo() == null : this.getUserAllergyInfo().equals(other.getUserAllergyInfo()))
            && (this.getLastFiveBloodPressure() == null ? other.getLastFiveBloodPressure() == null : this.getLastFiveBloodPressure().equals(other.getLastFiveBloodPressure()))
            && (this.getLastFiveBloodLipids() == null ? other.getLastFiveBloodLipids() == null : this.getLastFiveBloodLipids().equals(other.getLastFiveBloodLipids()))
            && (this.getLastFiveBloodSugar() == null ? other.getLastFiveBloodSugar() == null : this.getLastFiveBloodSugar().equals(other.getLastFiveBloodSugar()))
            && (this.getUserBmi() == null ? other.getUserBmi() == null : this.getUserBmi().equals(other.getUserBmi()))
            && (this.getAssociatedGuardiansId() == null ? other.getAssociatedGuardiansId() == null : this.getAssociatedGuardiansId().equals(other.getAssociatedGuardiansId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserRealname() == null) ? 0 : getUserRealname().hashCode());
        result = prime * result + ((getUserHeight() == null) ? 0 : getUserHeight().hashCode());
        result = prime * result + ((getUserWeight() == null) ? 0 : getUserWeight().hashCode());
        result = prime * result + ((getUserAllergyInfo() == null) ? 0 : getUserAllergyInfo().hashCode());
        result = prime * result + ((getLastFiveBloodPressure() == null) ? 0 : getLastFiveBloodPressure().hashCode());
        result = prime * result + ((getLastFiveBloodLipids() == null) ? 0 : getLastFiveBloodLipids().hashCode());
        result = prime * result + ((getLastFiveBloodSugar() == null) ? 0 : getLastFiveBloodSugar().hashCode());
        result = prime * result + ((getUserBmi() == null) ? 0 : getUserBmi().hashCode());
        result = prime * result + ((getAssociatedGuardiansId() == null) ? 0 : getAssociatedGuardiansId().hashCode());
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
        sb.append(", userHeight=").append(userHeight);
        sb.append(", userWeight=").append(userWeight);
        sb.append(", userAllergyInfo=").append(userAllergyInfo);
        sb.append(", last5BloodPressure=").append(lastFiveBloodPressure);
        sb.append(", last5BloodLipids=").append(lastFiveBloodLipids);
        sb.append(", last5BloodSugar=").append(lastFiveBloodSugar);
        sb.append(", userBmi=").append(userBmi);
        sb.append(", associatedGuardiansId=").append(associatedGuardiansId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}