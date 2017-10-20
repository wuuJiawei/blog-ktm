package com.blog.service;

import com.blog.model.User;

public interface IUserService {

	int deleteByPrimaryKey(Integer userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);
    
    /**
     * µÇÂ¼
     * @param username
     * @param password
     * @return
     */
    User login(String username,String password);

}
