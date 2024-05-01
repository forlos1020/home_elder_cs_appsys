package com.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.ForumArticleMapper;
import com.user.pojo.ForumArticle;
import com.user.pojo.Result;
import com.user.service.ForumArticleService;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【forum_article(文章信息)】的数据库操作Service实现
* @createDate 2024-05-01 16:00:59
*/
@Service
public class ForumArticleServiceImpl extends ServiceImpl<ForumArticleMapper, ForumArticle>
    implements ForumArticleService {

    @Override
    public Result getArticleList() {
        return Result.success("获取成功",this.lambdaQuery().list());
    }
}




