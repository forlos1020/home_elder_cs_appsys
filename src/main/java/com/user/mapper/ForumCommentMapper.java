package com.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.ForumComment;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【forum_comment(评论)】的数据库操作Mapper
* @createDate 2024-05-01 16:01:07
* @Entity generator.pojo.ForumComment
*/
@Mapper

public interface ForumCommentMapper extends BaseMapper<ForumComment> {

}




