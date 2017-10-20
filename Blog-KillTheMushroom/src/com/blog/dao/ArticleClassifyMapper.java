package com.blog.dao;

import com.blog.model.ArticleClassify;

public interface ArticleClassifyMapper {
    int deleteByPrimaryKey(Integer classifyId);

    int insert(ArticleClassify record);

    int insertSelective(ArticleClassify record);

    ArticleClassify selectByPrimaryKey(Integer classifyId);

    int updateByPrimaryKeySelective(ArticleClassify record);

    int updateByPrimaryKey(ArticleClassify record);
}