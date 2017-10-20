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
     * 根据条件查询
     * @param map
     * @return
     */
    List<User> selectAll(Map<String, Object> map);
}