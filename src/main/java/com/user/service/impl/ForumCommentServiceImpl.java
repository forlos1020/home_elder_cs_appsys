package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.user.mapper.ForumCommentMapper;
import com.user.pojo.ForumComment;
import com.user.service.ForumCommentService;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【forum_comment(评论)】的数据库操作Service实现
* @createDate 2024-05-01 16:01:07
*/
@Service
public class ForumCommentServiceImpl extends ServiceImpl<ForumCommentMapper, ForumComment>
    implements ForumCommentService {

}




