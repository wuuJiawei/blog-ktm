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
     * ����������ѯ��
     * ������Ϊ���service����
     * @author ���ΰؼ 
     * @version 2017/10/8 11:12
     * @param map
     * @return
     */
    List<Article> selectAll(Map<String, Object> map);
}