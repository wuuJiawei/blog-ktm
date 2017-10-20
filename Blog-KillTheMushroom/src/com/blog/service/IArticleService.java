package com.blog.service;

import java.util.List;
import java.util.Map;

import com.blog.model.Article;

public interface IArticleService {

	int deleteByPrimaryKey(Integer articleId);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    /**
     * 首页的推荐文章
     * @author 武佳伟丶
     * @version 2017/10/8 11:14
     * @return
     */
    Article get4Index();
    
}
