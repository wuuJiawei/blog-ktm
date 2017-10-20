package com.blog.dao;

import com.blog.model.ArticleTag;

public interface ArticleTagMapper {
    int deleteByPrimaryKey(Integer tag);

    int insert(ArticleTag record);

    int insertSelective(ArticleTag record);

    ArticleTag selectByPrimaryKey(Integer tag);

    int updateByPrimaryKeySelective(ArticleTag record);

    int updateByPrimaryKey(ArticleTag record);
}