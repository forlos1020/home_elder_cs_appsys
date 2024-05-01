package com.user.controller;

import com.user.pojo.Result;
import com.user.service.ForumArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： lsh
 * @create： 2024-05-01 16:02
 */
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {


    private final ForumArticleService articleService;


    @PostMapping("/getArticleList")
    public Result getArticleList(){
        return articleService.getArticleList();
    }
}
