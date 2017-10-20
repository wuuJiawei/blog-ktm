package com.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.ArticleMapper;
import com.blog.model.Article;
import com.blog.service.IArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer articleId) {
		return articleMapper.deleteByPrimaryKey(articleId);
	}

	@Override
	public int insertSelective(Article record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public Article selectByPrimaryKey(Integer articleId) {
		return articleMapper.selectByPrimaryKey(articleId);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Article get4Index() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleFreeze", 0);//文章是否被冻结：0-未冻结
		map.put("classifyFreeze", 0);//分类是否被冻结：0-未冻结
		map.put("privacy", "1");//0-私有，1-公开
		String sort = " a.sort desc,articleId desc limit 0,1";
		map.put("sort", sort);
		List<Article> articlesList = articleMapper.selectAll(map);
		if (articlesList.size()>0) {
			return articlesList.get(0);
		}
		return null;
	}

}
