package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.model.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
    
    /**
     * 根据条件查询，
     * 建议拆分为多个service方法
     * @author 武佳伟丶 
     * @version 2017/10/8 11:12
     * @param map
     * @return
     */
    List<Article> selectAll(Map<String, Object> map);
}