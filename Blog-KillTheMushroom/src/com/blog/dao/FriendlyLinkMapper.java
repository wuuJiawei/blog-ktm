package com.blog.dao;

import com.blog.model.FriendlyLink;

public interface FriendlyLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(FriendlyLink record);

    int insertSelective(FriendlyLink record);

    FriendlyLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(FriendlyLink record);

    int updateByPrimaryKey(FriendlyLink record);
}