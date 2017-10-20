package com.blog.model;

import java.io.Serializable;

public class Setting implements Serializable {
    private Integer settingId;

    private String keyword;

    private String blogName;

    private String description;

    private String title;

    private String logo;

    private String github;

    private String mayun;

    private String csdn;

    private String tuling;

    private String chatroomId;

    private static final long serialVersionUID = 1L;

    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getMayun() {
        return mayun;
    }

    public void setMayun(String mayun) {
        this.mayun = mayun;
    }

    public String getCsdn() {
        return csdn;
    }

    public void setCsdn(String csdn) {
        this.csdn = csdn;
    }

    public String getTuling() {
        return tuling;
    }

    public void setTuling(String tuling) {
        this.tuling = tuling;
    }

    public String getChatroomId() {
        return chatroomId;
    }

    public void setChatroomId(String chatroomId) {
        this.chatroomId = chatroomId;
    }
}