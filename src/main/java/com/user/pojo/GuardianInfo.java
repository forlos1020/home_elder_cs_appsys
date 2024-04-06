package com.user.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 监护人数据
 * @TableName guardian_info
 */
@TableName(value ="guardian_info")
@Data
public class GuardianInfo implements Serializable {
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
     * 与被监护人的关系
     */
    private String relationshipWithWard;

    /**
     * 被监护人id
     */
    private String wardId;

    /**
     * 被监护人姓名
     */
    private String wardName;

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
        GuardianInfo other = (GuardianInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserRealname() == null ? other.getUserRealname() == null : this.getUserRealname().equals(other.getUserRealname()))
            && (this.getRelationshipWithWard() == null ? other.getRelationshipWithWard() == null : this.getRelationshipWithWard().equals(other.getRelationshipWithWard()))
            && (this.getWardId() == null ? other.getWardId() == null : this.getWardId().equals(other.getWardId()))
            && (this.getWardName() == null ? other.getWardName() == null : this.getWardName().equals(other.getWardName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserRealname() == null) ? 0 : getUserRealname().hashCode());
        result = prime * result + ((getRelationshipWithWard() == null) ? 0 : getRelationshipWithWard().hashCode());
        result = prime * result + ((getWardId() == null) ? 0 : getWardId().hashCode());
        result = prime * result + ((getWardName() == null) ? 0 : getWardName().hashCode());
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
        sb.append(", relationshipWithWard=").append(relationshipWithWard);
        sb.append(", wardId=").append(wardId);
        sb.append(", wardName=").append(wardName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}