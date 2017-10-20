package com.blog.dao;

import com.blog.model.Setting;

public interface SettingMapper {
    int deleteByPrimaryKey(Integer settingId);

    int insert(Setting record);

    int insertSelective(Setting record);

    Setting selectByPrimaryKey(Integer settingId);

    int updateByPrimaryKeySelective(Setting record);

    int updateByPrimaryKey(Setting record);
}