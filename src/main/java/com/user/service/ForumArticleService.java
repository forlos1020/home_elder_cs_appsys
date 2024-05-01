package com.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.ForumArticle;
import com.user.pojo.Result;

/**
* @author ASUS
* @description 针对表【forum_article(文章信息)】的数据库操作Service
* @createDate 2024-05-01 16:00:59
*/
public interface ForumArticleService extends IService<ForumArticle> {

    Result getArticleList();

}
