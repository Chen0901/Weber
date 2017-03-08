package org.lanqiao.dao;

import org.lanqiao.bean.GoodAndBad;

public interface GoodAndBadDao {
	// 查询是否点过赞或踩
	public GoodAndBad selectGBInfoById(GoodAndBad goodAndBad);

	// 添加用户踩或赞的信息
	public int addGBInfo(GoodAndBad goodAndBad);
}
