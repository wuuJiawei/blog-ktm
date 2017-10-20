package com.blog.dao;

import java.util.List;
import java.util.Map;

import com.blog.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * ����������ѯ
     * @param map
     * @return
     */
    List<User> selectAll(Map<String, Object> map);
}